--local queueName = KEYS[1]
--local maxCapacity = 10
--
---- 检查队列当前长度
--local currentSize = redis.call('ZCARD', queueName)
--
---- 计算需要移除的元素数量
--local elementsToAdd = #ARGV / 2
--local elementsToRemove = math.max(0, currentSize + elementsToAdd - maxCapacity)
--
---- 如果需要移除元素，移除最低优先级（即最旧的）元素
--if elementsToRemove > 0 then
--    redis.call('ZREMRANGEBYRANK', queueName, 0, elementsToRemove - 1)
--end
--
---- 循环遍历所有传入的消息和优先级，将它们加入队列
--for i = 1, #ARGV, 2 do
--    local msgId = ARGV[i]
--    local priority = ARGV[i + 1]
--    redis.call('ZADD', queueName, priority, msgId)
--end

local queueName = KEYS[1]
-- 创建一个新的空表来存储ARGV的副本
local argList = {}

-- 遍历ARGV中的所有元素
-- 遍历ARGV中的所有元素
for i = 1, #ARGV do
    -- 使用LPUSH命令将每个元素添加到Redis列表的头部
    -- 这里假设ARGV中的元素都是字符串类型
    redis.call('LPUSH', 'list', ARGV[i])
    table.insert(argList, ARGV[i])
end
local result = {111, 222}
return cjson.encode(result)
--return cjson.encode({'111', '222'})
--return "{\¬"test\": \"555\"}"