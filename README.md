# System Design 系统设计

## High Level Introduction

### Evaluation Critiera 评分系统

* Work solution. (25%) 可行解
* Special case. (20%) 特定问题
* Analysis ability. (25%)分析能力
* Tradeoff. (15%) 权衡
* Knowledge base. (15%) 知识储备

### Philosophy

**Ask Before Design**
**No More No Less**
**Work Solution First**
**Analysis is Important than Solution**

### 4S Methodology 4S分析法

To have a work solution, you need to go through following:

#### I Scenario (Requirements)

* Functional requirements - What are the features to support?
  * First enumerate the features
  * Sort and find the most important features.
* Non-functional requirements - What is the DAU and MAU. (Usually MAU = 2 * DAW)
  * What would be the average TPS? - Requests per person daily (like 50? 100?) * DAU / 86400 (seconds of a day)
    * TPS 100 - you can use your laptop
    * TPS 1000 - Use a good web server. Need to consider single point failure.
    * TPS 1000,000 - Use 1000 web servers. Need to consider maintainance.
    * Usually a web service can handle 1k TPS, a SQL DB can handle 1k TPS (may reduce due to joins and index), a NoSQL DB can handle 10k (Cassandra) to 1M (Memcached) TPS.
    * What would be the peak TPS? 3 * Avg TPS
    * For fast growing product - MAX peak users in 3 months = Peak users * 2. 
    * Read TPS >> Write TPS usually.

**The calculated numbers don't really matter, what matters is the calculation process**.

#### II Service
Divide the system into micro services. Split/Applicaiton/Module
* Replay the featyures and add a service for each of them.
* Merge shared service.

#### III Storage
1. For each of the services, you may need a different storage. Find it out.
2. Design the schema for the table.

#### IV Scale
Fix drawbacks, deal with possible problems. Sharding/Optimize/Special Case

## Design Examples

### I Twitter

### II SecKill System 秒杀系统
#### Scenario (Requirements)
We can break it down to two parts:
* Business side
* Customer side
#### Service
* Monolithic
* Microservices
#### Storage
#### Scale
* Database Crash
  * Solution - Database cache, e.g, Redis. The query goes to cache. 

## Knowledge Collection
### Data Storage
* Database - Database is just a wrapper of file system, to provide an interface of various query operations. 
  * SQL Database
  * NoSQL Database
* File System - it only provides very simple operations to access files.
* Cache - in memory storage, can be very fast, but it is expensive. You can think of cache as hashtable. Redis (support more data types) and Memocached (support string only).
* Database Index
* Transaction
* Redis 
Redis is an open source (BSD), in-memory key-value data structure store (kind of no sql), which can be used as a database, cache or message broker. It’s a NoSQL database used in GitHub, Pinterest and Snapchat. Redis performance and atomic manipulation of data structures solves problems which can often be found with relational databases.
* Producer/Consumer Model
* Content Delivery Network
* Avalanche (Fan-out)
* Thundering Herd 
When a very popular record get removed from cache, it will lead to huge amount of query traffic go into DB (cache miss) and overwhelm the database server.

## Practice
* [Design a twitter](https://github.com/dengkliu/system-design/blob/main/DesignTwitter.java)

