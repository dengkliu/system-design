# System Design

## 4S Methodology
### Scenario (Requirements)
What are the requirements? Functional requirements and non functional requirements.
### Service
### Storage
### Scale

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
