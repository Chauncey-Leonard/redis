package com.example.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private RedisUtil() {
    }

    // ================================= common ================================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return 指定失效时间是否成功
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取指定键的失效时间
     *
     * @param key 键
     * @return 失效时间(秒), 返回值为0则表示永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断键是否存在
     *
     * @param key 键
     * @return true表示存在，false表示不存在
     */
    public Boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 键，可以传入一个或多个
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(
                        String.valueOf(CollectionUtils.arrayToList(key))
                );
            }
        }
    }

    // ================================= String ================================

    /**
     * 获取字符串类型的缓存
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 字符串存储缓存
     *
     * @param key   键
     * @param value 值
     * @return 存储是否成功，true成功，false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 字符串类型缓存存储并设置过期时间
     * 如果设置的过期时间小于等于0，则表示永不过期
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间，单位：秒
     * @return true成功，false失败
     */
    public boolean setEx(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key 键
     * @return 修改后的值
     */
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 增加的值
     * @return 修改后的值
     */
    public Long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }

        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 增加的值
     * @return 修改后的值
     */
    public Double incr(String key, double delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }

        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key 键
     * @return 修改后的值
     */
    public Long decr(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 递减因子
     * @return 修改后的值
     */
    public Long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }

        return redisTemplate.opsForValue().decrement(key, delta);
    }

    // ================================= Hash ==================================

    /**
     * Hash获取普通键值
     *
     * @param key   键
     * @param field 项
     * @return 值
     */
    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取指定键的所有键值对
     *
     * @param key 键
     * @return 多个键值对
     */
    public Map<Object, Object> hMGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 保存多个键值对
     *
     * @param key 键
     * @param map 键值对集合
     * @return 存储是否成功
     */
    public boolean hMSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存多个键值对并设置过期时间
     *
     * @param key  键
     * @param map  键值对
     * @param time 过期时间，单位：秒
     * @return 保存是否成功
     */
    public boolean hMSet(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * hash普通保存数据，如果不存在就创建
     *
     * @param key   键
     * @param field 项
     * @param value 值
     * @return true 成功，false 失败
     */
    public boolean hSet(String key, String field, Object value) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存键值对信息并设置过期时间
     *
     * @param key   键
     * @param field 项
     * @param value 值
     * @param time  过期时间
     * @return true 成功，false 失败
     */
    public boolean hSet(String key, String field, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
            if (time > 0) {
                expire(key, time);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * hash删除数据
     *
     * @param key   键
     * @param field 项
     */
    public void hDel(String key, Object... field) {
        redisTemplate.opsForHash().delete(key, field);
    }

    /**
     * 判断hash表中是否存在该项值
     *
     * @param key   键
     * @param field 项
     * @return true 存在，false 不存在
     */
    public boolean hHasKey(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * hash递增，如果不存在，则会新建一个，并将新增的值返回
     *
     * @param key   键
     * @param field 项
     * @param value 增加的数值
     * @return 修改后的值
     */
    public Double hIncr(String key, String field, double value) {
        return redisTemplate.opsForHash().increment(key, field, value);
    }

    /**
     * hash递增，如果不存在，则会新建一个，并将新增的值返回
     *
     * @param key   键
     * @param field 项
     * @param value 增加的数值
     * @return 修改后的值
     */
    public Long hIncr(String key, String field, long value) {
        return redisTemplate.opsForHash().increment(key, field, value);
    }

    /**
     * hash递减
     *
     * @param key   键
     * @param field 项
     * @param value 减少的数值
     * @return 修改后的值
     */
    public Double hDecr(String key, String field, double value) {
        return redisTemplate.opsForHash().increment(key, field, -value);
    }

    /**
     * hash递减
     *
     * @param key   键
     * @param field 项
     * @param value 减少的数值
     * @return 修改后的值
     */
    public Long hDecr(String key, String field, long value) {
        return redisTemplate.opsForHash().increment(key, field, -value);
    }

    // ================================= Set ===================================

    /**
     * 获取指定键中的所有值
     *
     * @param key 键
     * @return 值集合
     */
    public Set<Object> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 判断指定键中是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在，false 不存在
     */
    public Boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存一个或多个缓存
     *
     * @param key    键
     * @param values 值
     * @return 保存成功的个数
     */
    public Long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 保存一个或多个缓存并设置过期时间
     *
     * @param key    键
     * @param time   过期时间
     * @param values 值
     * @return 成功的个数
     */
    public Long sSet(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }

            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 获取指定集合中的元素个数
     *
     * @param key 键
     * @return 集合的长度
     */
    public Long sGetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 移除指定集合中的元素
     *
     * @param key    键
     * @param values 移除的元素
     * @return 移除的个数
     */
    public Long sRemove(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    // ================================= List ==================================

    /**
     * 获取指定区间的缓存
     *
     * @param key   键
     * @param start 区间开始
     * @param end   区间结束 0 到 -1 表示所有的值
     * @return 指定区间缓存集合
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取集合的元素个数
     *
     * @param key 键
     * @return 元素个数
     */
    public Long lGetSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 通过下标获取对象
     *
     * @param key   键
     * @param index 下标
     * @return 指定下标的对象
     */
    public Object lGetByIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return 保存是否成功
     */
    public boolean lPush(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  过期时间
     * @return 保存是否成功
     */
    public boolean lPush(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
