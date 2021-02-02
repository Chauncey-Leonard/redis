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
# 
```
