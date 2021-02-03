**启动命令**

```bash
./redis-server /path/to/redis.conf
```

#### 1、单位(UNIT)

- 配置文件 `unit`单位对于大小写不敏感

#### 2、包含(INCLUDE)

可以将其他的配置文件包含进来

```bash
include /path/to/local.conf
include /path/to/other.conf
```

#### 3.网络(NETWORK)

```bash
bind 127.0.0.1     # 绑定的ip地址

protected-mode yes # 保护模式

port 6379          # 端口
```

#### 4、通用(GENERAL)

```bash
# 是否开启守护模式, 默认为no
daemonize yes
# 如果以后台的方式运行,就需要指定一个 pid 文件
pidfile /var/run/redis_6379.pid
# 日志等级设置, 生产环境默认使用 notice
loglevel notice(debug | verbose | notice | warning)
# 生成的日志文件名
logfile ""
# 数据库数量,默认16个
databases 16
# 是否显示logo,默认显示
always-show-logo yes
```

#### 5、快照(SNAPSHOTTING)

持久化,在规定的时间内，执行了多少次操作,则会进行持久化到文件(.aof,.rdb)

`Redis`是内存数据库,如果不进行持久化,数据在断电时就会丢失

```bash
# 如果在900秒内,至少有一个 key 进行了修改,我们就进行持久化操作
save 900 1
# 如果在300秒内,至少有十个 key 进行了修改,我们就进行持久化操作
save 300 10
# 如果在60秒内,至少有一万个 key 进行了修改,我们就进行持久化操作
save 60 10000

# 持久化如果出错了,是否还需要继续工作
stop-writes-on-bgsave-error yes

# 是否压缩 rdb 文件,默认开启
# 需要消耗一些cpu资源
rdbcompression yes

# 是否校验 rdb 文件
# 保存 rdb 文件的时候,进行错误的检查校验
rdbchecksum yes

# rdb 文件的保存目录
# 默认为当前目录
dir ./
```

#### 6、安全(SECURITY)

`Redis`默认是没有密码的,我们可以在这里设置密码

```bash
# 配置文件中的设置
requirepass 123456

# 通过命令行设置
# 获取 redis 的密码
config get requirepass

# 设置密码
config set requirepass "123456"
# 使用密码进行登录
auth 123456
```

#### 7、客户端(CLIENTS)

```bash
maxclients 10000 # 设置能连接上redis的最大客户端
```

#### 8、内存管理(MEMORY MANAGEMENT)

```bash
maxmemory <bytes> # redis 配置最大的内存容量
# 内存达到上限后的处理策略
maxmemory-policy noeviction

# 1、volatile-lru：只对设置了过期时间的key进行LRU（默认值）
# 2、allkeys-lru：删除lru算法的key
# 3、volatile-random：随机删除即将过期的key
# 4、allkeys-random：随机删除
# 5、volatitle-ttl：删除即将过期的
# 6、noeviction：永不过期，返回错误
```

