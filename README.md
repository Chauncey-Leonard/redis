## Redis

### 一、概述

Redis（Remote Dictionary Server），即远程字典服务。 是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、key-value数据库，并提供多种语言的API，免费开源，是当下最热门的NoSQL技术之一，也被称之为结构化数据库，支持多种数据类型，如String、list、set、zset、hash

Redis可以周期性的把更新的数据写入磁盘或者把修改操作写入追加的记录文件，并且在此基础上实现了**master-slave**（主从）同步。



**支持的语言**

| ActionScript | Common Lisp |  Haxe   | Object-C  |     R     |
| :----------: | :---------: | :-----: | :-------: | :-------: |
|      C       |    Dart     |   Io    |   Perl    |   Ruby    |
|     C++      |   Erlang    |  Java   |    PHP    |   Scala   |
|      C#      |     Go      | Node.js | Pure Data | Smalltalk |
|   Clojure    |   Haskell   |   Lua   |  Python   |    Tcl    |



### 二、特点

1.以内存作为数据存储的介质，读写数据的效率极高。

2.与memcache不同的是，存储在Redis中的数据是持久化的，断电或重启，数据也不会丢失。

3.存储方式分为内存存储、磁盘存储和log文件。

4.可以从磁盘中重新将数据加载到内存中，也可以通过配置文件对其进行配置，因此，redis才能实现持久化。

5.支持主从模式，可以配置集群，更利于支撑大型项目。



### 三、应用场景

1、会话缓存

2、消息队列

3、活动排行榜或计数

4、订阅发布消息

5、商品列表、评论列表

...



### 四、数据类型

一共支持**五种**数据类型：String(字符串)、hash(哈希)、list(列表)、set(集合)、zset(有序集合)

**String**：Redis**最基本**的数据类型，一个键对应一个值，一个键值最大存储512MB

**Hash**：是一个键值对的集合，是一个String类型的field和value的映射表，适合用于**存储对象**

**List**：简单的字符串列表，按插入顺序排序

**Set**：字符串类型的无序集合，也不可重复

**ZSet**：字符串类型的有序集合，也不可重复，有序集合中的每个元素都需要指定一个分数，根据分数对元素进行升序排序



### 五、下载地址

[Redis官网](https://redis.io)

[Redis中文网](http://www.redis.cn)

注意：Windows版本的在[GitHub](https://github.com/redis/redis)上下载，推荐Redis都在Linux服务器上进行搭建。



### 六、Windows

1、下载安装包，地址：https://github.com/microsoftarchive/redis/releases

2、下载得到压缩包

3、解压缩到磁盘，Redis很小，只有5M

4、双击`redis-server.exe`文件启动服务

5、使用`redis-cli.exe`客户端连接redis

注意：Windows下使用简单，但是Redis推荐我们使用**Linux**部署



### 七、Linux

#### 1、下载压缩包

从[redis官网](https://redis.io/download)去下载压缩包，推荐下载6.0以下的版本，下载完成后上传至云服务器，本案例的版本为`redis-5.0.10.tar.gz`

#### 2、解压缩

程序一般安装在`/opt/`目录下

```bash
mv redis-5.0.10.tar.gz /opt
tar -zxvf redis-5.0.10.tar.gz
```

#### 3、解压完成

解压完成后，进入redis目录可以看到redis的配置文件`redis-config`

```bash
cd redis-5.0.10
```

#### 4、基本的环境安装

```bash
yum install gcc-c++
gcc -v # 查看gcc-c++安装是否成功

make
make install
```

#### 5、默认安装路径

redis的安装路径为`/usr/local/bin`

#### 6、拷贝配置文件

创建用于存放配置文件的文件夹

```bash
mkdir config
```

将redis的配置文件拷贝至新创建的文件夹中，之后就使用这个配置文件进行启动

```bash
cp /opt/redis-5.0.10/redis-config config
```

#### 7、修改配置文件

redis默认不是后台启动的，我们需要手动配置

```bash
# 进入配置文件夹
cd config
# 编辑配置文件
vim redis-config
# 按insert或i键进入编辑模式，找到daemonize，并修改其值为yes
daemonize yes
# 保存退出，点击esc，并输入
:wq
```

#### 8、启动服务

```bash
# 进入/usr/local/bin目录，启动redis-server，同时指定启动的配置文件
cd /usr/local/bin
redis-server config/redis-config
```

#### 9、连接服务

```bash
redis-cli -p 6379
```

输入ping，回车显示PONG，则表示连接成功

#### 10、查看redis的进程信息

```bash
ps -ef|grep redis
```

#### 11、关闭服务

```bash
# 在redis-cli中输入
shutdown
exit

# 再次查看redis进程信息
ps -ef|grep redis
```



### 八、性能测试

`redis-benchmark`是一个官方自带的性能测试工具

**语法**

```bash
redis-benchmark [option] [option value]
```



|   选项    |                    描述                    |  默认值   |
| :-------: | :----------------------------------------: | :-------: |
|  -**h**   |              指定服务器主机名              | 127.0.0.1 |
|  -**p**   |               指定服务器端口               |   6379    |
|  -**s**   |              指定服务器socket              |           |
|  -**c**   |               指定并发连接数               |    50     |
|  -**n**   |                 指定请求数                 |   10000   |
|  -**d**   |   以字节的形式指定 SET/GET 值的数据大小    |     2     |
|  -**k**   |          1=keep alive 0=reconnect          |     1     |
|  -**r**   | SET/GET/INCR 使用随机 key, SADD 使用随机值 |           |
|  -**P**   |         通过管道传输 <numreq> 请求         |     1     |
|  -**q**   |    强制退出 redis。仅显示 query/sec 值     |           |
| --**csv** |              以 CSV 格式输出               |           |
|  -**l**   |           生成循环，永久执行测试           |           |
|  -**t**   |       仅运行以逗号分隔的测试命令列表       |           |
|  **-I**   |   Idle 模式，仅打开 N 个 idle 连接并等待   |           |

**案例**

```bash
# 测试100个并发连接，每个连接100000个请求
redis-benchmark -h localhost -p 6379 -c 100 -n 100000

# 结果
====== SET ======
  100000 request completed in 1.69 seconds # 10万个请求完成的总时间
  100 parallel clients					   # 100个客户端
  3 bytes payload						   # 每次写入3个字节
  keep alive: 1							   # 只有一台服务器来处理这些请求，单机性能

28.93% <= 1 milliseconds
99.85% <= 2 milliseconds
100.00% <= 3 milliseconds				   # 所有请求在3秒内完成
59276.82 requests per second			   # 每秒处理的请求数
```



### 九、基础知识

#### 1、默认有16个数据库

Redis 有16个数据库，并且默认使用第0个数据库，可以使用`select`切换数据库

```bash
# 切换数据库
select 3 
# 查看数据库大小
DBSIZE
# 查看数据库中的所有key
keys *
# 清空当前数据库
flushdb
# 清空全部数据库
```

#### 2、Redis是单线程的

Redis是基于内存操作的，CPU不是Redis的瓶颈，而是根据服务器的内存和网络的带宽决定的，既然可以使用单线程来实现，就直接使用单线程了 

Redis 是由C语言编写的，官方提供的数据是100000+的QPS，完全不比同样使用key-value的Memcache差

**问：Redis为什么这么快？**

误区1：高性能的服务器一定是多线程的

误区2：多线程一定比单线程效率高

Redis是将所有的数据全部放在内存中的，所以使用单线程去操作效率最高，如果是多线程的话，CPU在进行上下文切换的时候会增加耗时，对于内存系统来说，没有上下文的切换，效率就是最高的