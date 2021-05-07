# RocketMQ的安装
下载rocketmq的压缩包:

[rocketmq安装包](./files/rocketmq-all-4.6.0-bin-release.zip)

![rocketmq的存放地址](./files/rocketmq_location.png)


## 启动NameServ
![启动NameServ](./files/start_nameserv.png)

## 启动broker
![启动broker](./files/start_broker.png)

## 测试生产者
![测试生产者](./files/test_producer.png)

## 测试消费者
![测试消费者](./files/test_consumer.png)

![测试消费者](./files/test_consumer2.png)



## 使用web-console
![启动web-console](files/start-webconsole.png)


![web-console](./files/rocket_consolepng.png)


------------------------------------------------------------------------------------------------------------------

# RocketMQ的原理

RocketMQ的三大组件：NameServer,Broker,FilterServer

## Name Server:
```text
1. Name Server是RokcetMQ的寻址服务。用于把Broker的路由信息做聚合。客户端依靠NameServer决定去获取对应topic的路由消息，
从而决定对哪些Broker做连接.

2. Name Server是无状态的节点，节点之间是采用share-nothing的设计方案，互相之间是不通信的。

3. 对于一个Name Server集群列表，客户端连接NameServer的时候，只会连接一个节点，做到负载均衡。

4. Name Server 所有状态都从Broker上报而来，本身不存储任何状态，所有的数据均在内存。

5. 所有Name Server节点全部挂了，影响到路由信息的更新，但是不会影响到Broker的通信。
```

## Broker
```text
Broker是存储消息和处理消息和转发消息的服务器。
1.Broker是以Group分开的，每个Group只允许一个master，若干个slave.

2. master才能进行数据写入，slave不允许 

3. 客户端可以从master和Slave消费，默认情况下，消费者都从master进行消费，如果master挂了，客户端会从slave消费。

4. Broker向所有的NameServer节点建立长连接，注册topic信息.
```


## Filter Server

```text
RocketMQ可以允许消费者上传一个Java类给Filter Server进行过滤。

1. Filter Server只能其在Broker所在的机器。

2. Filter Server可以有若干个。

3. 拉取消息的时候，消息先经过Filter Server ,Filter Server靠上传的Java类过滤消息后才推送给Consumer消费。

4. 客户端可以选择在消费的时候进行过滤，这样做可以更加充分的使用网卡的资源，

5. 避免使用分配过多的资源而导致服务器资源泄漏。

```

# 角色
* Producer
```text
生产者。发送消息的客户端角色。发送消息的时候需要指定Topic
```
* Consumer
```text
消费者。消费者的客户端角色。通常是后台的处理异步消费的系统。RocketMQ中Consumer有两种实现：PushConsumer和PullConsumer
```

* PushConsumer
```text
推送模式(虽然RocketMQ使用的是长轮询)的消费者。消息能及时的被消费。使用非常简单，内部已经处理如线程池，流控，负载均衡，
异常处理等等的各种场景。
```

* PullConsumer
```text
拉取模式的消费者。应用注意控制拉取的时机，怎么拉取，怎么消费等。主动权更加高。但是要自己处理各种场景。
```























# RocketMQ的使用