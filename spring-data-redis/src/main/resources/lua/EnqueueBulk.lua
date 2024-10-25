local redis = require 'redis'
-- 创建一个Redis连接对象
local client = redis.connect('127.0.0.1', 6379)
 
-- 认证（如果需要）
client:auth('yourpassword')
 
-- 使用数据库（如果需要）
client:select(0)

KEYS = 'test:100'
ARGV = {1, 11, 2, 22, 3, 33}

local queueName = KEYS
local maxCapacity = 20

-- 检查队列当前长度
local currentSize = redis.call('ZCARD', queueName)

if currentSize >= maxCapacity then
    return ARGV
end

-- 获取传入的元素数量：value和score成对出现
local elementsToAdd = #ARGV / 2

-- 如果添加新元素后超出最大容量，则需要移除的元素数量
local elementsToRemove = 0
if currentSize + elementsToAdd > maxCapacity then
    elementsToRemove = (currentSize + elementsToAdd) - maxCapacity
end

-- 如果需要移除元素，取出优先级最低的元素的ID和分数
local removedElements = {}
if elementsToRemove > 0 then
    -- 取出优先级最低的 elementsToRemove 个元素的ID和分数
    local lowestPriorityElements = redis.call('ZRANGE', queueName, 0, elementsToRemove - 1, 'WITHSCORES')
    for i = 1, #lowestPriorityElements, 2 do
        table.insert(removedElements, {msgId = lowestPriorityElements[i], priority = lowestPriorityElements[i + 1]})
    end
    ---- 从队列中移除这些元素
    --for i = 1, #lowestPriorityElements, 2 do
    --    redis.call('ZREM', queueName, lowestPriorityElements[i])
    --end
end

-- 将新元素和取出的元素一起加入队列
local allElements = {}
for i = 1, elementsToRemove * 2, 2 do
    table.insert(allElements, removedElements[i/2])
end
for i = 1, #ARGV, 2 do
    local msgId = ARGV[i]
    local priority = ARGV[i + 1]
    table.insert(allElements, {msgId = msgId, priority = priority})
end

-- 对所有元素按照优先级进行排序
table.sort(allElements, function(a, b) return a.priority > b.priority end)

-- 将排序后的元素前n个重新加入队列
local successfullyAdded = {}
for i = 1, #allElements, elementsToAdd do
    redis.call('ZADD', queueName, allElements[i].priority, allElements[i].msgId)
end

-- 返回没有成功插入的新元素和被移除的老元素
redis.call('set', 'result', 'success')
local outOfQueue = ''
for i = elementsToAdd, #allElements do
    outOfQueue = outOfQueue .. allElements[i].msgId
    if i < #allElements then
        outOfQueue = outOfQueue .. ", "
    end
end
outOfQueue = '[' .. outOfQueue .. ']'
redis.call('set', 'outOfQueueStr', outOfQueue)
return outOfQueue