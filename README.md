# Used-Tech Stack

1.Java 8
2.Spring Boot 1.5.8
3.Spring Data JPA
4.MongoDB -NOSql
5.build Tool -Maven
6.DockerFile

# Project structure :

1.Created as Maven Based project structure.
2.RestApi (Controllers,Interfaces,Services).

# How scalable:

1.Mongo db is a horizontal scaling, the database system can be scaled through different servers instead of only one server, that is the database is scaled horizontally rather than vertically. This approach is safer, easier, and less expensive than vertical scaling.
2.Created as a statless Application.
3.Application is comprised of self-contained but interacting logical blocks, the more you’ll be able to scale each of those blocks independently as your use load demands.
4.Built-in redundancy
5.Easy to size and resize properly to your needs

# Need Knowledges :

1.I dont know how to do equal @requestmapping and same @RequestParam along with different values.

Example:

Given:
/ads/statistics?start={start}&end={end}&group_by=browser&group_by=os

above mapping i have done with below Requestmapping:

/ads/statisticss?start={start}&end={end}&group_by=browser&group_by=os





