# System Design 系统设计

## System Design vs OOD 系统设计 vs 面向对象设计

### Form
OOD requires coding, microscope design. System design requires hight level abstraction, more backend.
### Knowledge
OOD - class, object, method, inheritance, interface
System design - database, schema, SQL, NoSQL, Memcached, File System, Distributed System, latency, scalability, master slave, load balancer, web server, message queue, sharding, consistent hashing and so on.
### Typical problem
OOD - Design an elevator, a game.
System design - Design tiny url website, new feeds system

## Evaluation System 评分系统
* Work solution. (25%) 可行解
* Special case. (20%) 特定问题
* Analysis ability. (25%)分析能力
* Tradeoff. (15%) 权衡
* Knowledge base. (15%) 知识储备

## 4S Methodology 4S分析法

To have a work solution, you need to go through following:

### Scenario (Requirements)
What are the requirements? Functional requirements and non functional requirements (TPS). Ask/Features/TPS/DAU/Interfaces
### Service
Divide the system into micro services. Split/Applicaiton/Module
### Storage
How to store and query the data? Schema/Data/SQL/NoSQL/File System

To improve the system, need to do:

### Scale
Fix drawbacks, deal with possible problems. Sharding/Optimize/Special Case

## I. SecKill System 秒杀系统
### Scenario (Requirements)
We can break it down to two parts:
* Business side
* Customer side
### Service
* Monolithic
* Microservices
### Storage
### Scale
* Database Crash
  * Solution - Database cache, e.g, Redis. The query goes to cache. 
* 

## Basic Knowledge
### Database Index
### Transaction
### Redis 
Redis is an open source (BSD), in-memory key-value data structure store (kind of no sql), which can be used as a database, cache or message broker. It’s a NoSQL database used in GitHub, Pinterest and Snapchat. Redis performance and atomic manipulation of data structures solves problems which can often be found with relational databases.
### Producer/Consumer Model
### Content Delivery Network
### Avalanche (Fan-out)
