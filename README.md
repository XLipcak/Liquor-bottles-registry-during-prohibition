Liquor-bottles-registry-during-prohibition
==========================================

Everybody knows why prohibition went into effect in Czech Republic some time ago. In order to prevent this in the future there is a need for liquor bottles registry. Such a registry would keep track of all the liquor bottles that are, or will be, on the market. For each bottle there is a need to keep record of its producer. All the bottles currently on the market must be gradually analyzed and for each bottle it must be determined if the bottle is toxic or not. In order to return the bottle to the market, it cannot be toxic and it must re-stamp with new kind of tax stamp. Newly-manufactured bottles carry the new stamps automatically and thus can be introduced to the market. The customers are interested in how many bottles of which liquor type are currently in particular stores. Police, on the other hand, is interested in the statistics of the toxic bottles for each store and producer.


Technology requirements
==========================================
Server - Apache Tomcat

Running database server - JavaDB

Port - 1527

Database name - PA165

Database user - pa165

Database user password - pa165


Maven using JDK 1.7.



Running web server
==========================================
Change directory to: \Liquor-bottles-registry-during-prohibition\LiquorBottles-presentation

Run CMD command: mvn tomcat7:run


Running swing client
==========================================
Change directory to: \Liquor-bottles-registry-during-prohibition\LiquorBottles-client

Run CMD command: mvn exec:java
