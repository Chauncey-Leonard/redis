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

