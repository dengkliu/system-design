# System Design 系统设计

## High Level Introduction

### Evaluation Critiera 评分系统

* Work solution. (25%) 
* Special case. (20%)
* Analysis ability. (25%)
* Tradeoff. (15%) 
* Knowledge base. (15%)

### Philosophy

**Ask Before Design**
**No More No Less**
**Work Solution First**
**Analysis is Important than Solution**

### 4S Methodology 4S分析法

To have a work solution, you need to go through following:

#### 1） Scenario (Requirements)

* Functional requirements - What are the features to support? First enumerate the features，then sort and find the most important features.
* Non-functional requirements - What is the DAU and MAU. (Usually MAU = 2 * DAW). The calculated numbers don't really matter, what matters is the calculation process. What would be the average TPS? - Requests per person daily (like 50? 100?) * DAU / 86400 (seconds of a day)
  * TPS 100 - you can use your laptop
  * TPS 1000 - Use a good web server. Need to consider single point failure.
  * TPS 1000,000 - Use 1000 web servers. Need to consider maintainance.
  * Usually a web service can handle 1k TPS, a SQL DB can handle 1k TPS (may reduce due to joins and index), a NoSQL DB can handle 10k (Cassandra) to 1M (Memcached) TPS.
  * What would be the peak TPS? 3 * Avg TPS
  * For fast growing product - MAX peak users in 3 months = Peak users * 2. 
  * Read TPS >> Write TPS usually.
   
#### 2） Service - Divide the system into micro services. Replay the featyures and add a service for each of them. Merge shared service.

#### 3） Storage - For each of the services, you may need a different storage. Find it out. Design the schema for the table.

#### 4） Scale - Fix drawbacks, deal with possible problems. Sharding/Optimize/Special Case

## Design Examples
### Twitter
#### 1） Scenario (Requirements)
First enumerate the features, then sort and find out the important ones!
* **User register and signin**.
* User profile display edit.
* **User can follow other users**.
* **User can post a tweet**.
* **User can delete/update a tweet**
* User can search a tweet
* User can upload image and video when posting a tweet.
* **User can get newsfeed, consists of his/her own tweets and the thweets from the people he/she followers**.
* **User can read his own timeline**.
* User can like a tweet.

Then ask about non-functional requirements. 
* Daily active users, month active users? Usually DAU = 1/2 * MAU. 
  * Let's say MAU is 300 million and DAU is 150 million.
  * Then we assume every people daily makes 60 requests, then avg TPS is 150M * 60 / 86400 = 10k.
* Let's say Timeline read TPS
* Newsfeed read TPS
#### 2） Service 
* User signin/register service.
* Friends service.
* Tweets Service.
* Media Service
#### 3） Storage
* Users table (SQL)
* Tweets table (NoSQL)
* Friends table (SQL)
* Media Storage (S3)
#### 4） Scale
* Fan-out (push model)
* Pull model
* hybrid 
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
* Database Index - an index is an additional structure that is derived from the primary data of the DB. Any kind of index usually slows down writes, because the index also needs to be updated every time data is writtern. Well-chosen indexes speed up read queries. 
* Transaction
* Redis 
Redis is an open source (BSD), in-memory key-value data structure store (kind of no sql), which can be used as a database, cache or message broker. It’s a NoSQL database used in GitHub, Pinterest and Snapchat. Redis performance and atomic manipulation of data structures solves problems which can often be found with relational databases.
* Thundering Herd 
When a very popular record get removed from cache, it will lead to huge amount of query traffic go into DB (cache miss) and overwhelm the database server.

### Web server

![Screen Shot 2021-08-20 at 7 12 55 PM](https://user-images.githubusercontent.com/12690456/130301718-63960e34-3250-4a18-b7ae-92a7727771ba.png)


* Producer/Consumer Model
* Content Delivery Network
* Avalanche (Fan-out)

## Practice
* [Design a twitter](https://github.com/dengkliu/system-design/blob/main/DesignTwitter.java)

