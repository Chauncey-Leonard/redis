## Redis

### 概述

> Redis是什么?

Redis（Remote Dictionary Server），即远程字典服务。 是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、key-value数据库，并提供多种语言的API，免费开源，是当下最热门的NoSQL技术之一，也被称之为结构化数据库，支持多种数据类型，如String、list、set、zset、hash

Redis可以周期性的把更新的数据写入磁盘或者把修改操作写入追加的记录文件，并且在此基础上实现了**master-slave**（主从）同步。



**支持的语言**

| ActionScript | Common Lisp |  Haxe   | Object-C  |     R     |
| :----------: | :---------: | :-----: | :-------: | :-------: |
|      C       |    Dart     |   Io    |   Perl    |   Ruby    |
|     C++      |   Erlang    |  Java   |    PHP    |   Scala   |
|      C#      |     Go      | Node.js | Pure Data | Smalltalk |
|   Clojure    |   Haskell   |   Lua   |  Python   |    Tcl    |


  
> Redis特点

1.以内存作为数据存储的介质，读写数据的效率极高。

2.与memcache不同的是，存储在Redis中的数据是持久化的，断电或重启，数据也不会丢失。

3.存储方式分为内存存储、磁盘存储和log文件。

4.可以从磁盘中重新将数据加载到内存中，也可以通过配置文件对其进行配置，因此，redis才能实现持久化。

5.支持主从模式，可以配置集群，更利于支撑大型项目。

