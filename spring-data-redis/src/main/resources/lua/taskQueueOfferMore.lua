local function addElementsToZset(key, elements)
    -- 尝试添加元素到zset
    local added = redis.call('ZADD', key, unpack(elements))
    
    -- 获取zset的元素数量
    local length = redis.call('ZCARD', key)
    
    -- 如果元素数量超过100，移除分数最低的元素
    if length > 100 then
        -- 移除分数最低的元素，直到zset的大小为100
        redis.call('ZREMRANGEBYRANK', key, 0, -101)
    end
    
    return added
end

-- 调用Lua脚本的参数
-- 第一个参数是zset的key
-- 后续参数是成对的score和member
local key = KEYS[1]

-- 调用函数并返回结果
addElementsToZset(key, ARGV)