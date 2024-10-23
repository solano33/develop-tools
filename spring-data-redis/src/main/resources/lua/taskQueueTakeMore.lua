local zset_key = KEYS[1]
local n = tonumber(ARGV[1])

-- 获取分数最高的 n 个元素
local top_n = redis.call('ZRANGE', zset_key, -n, -1)

-- 移除这些元素
for i = 1, #top_n, 2 do
    redis.call('ZREM', zset_key, top_n[i])
end

return top_n