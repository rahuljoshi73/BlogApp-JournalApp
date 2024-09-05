package com.MakeAPI.jounralAPP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}
	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
		return  new MongoTransactionManager(dbFactory);
	}

}


//Rest Api :- representational state transfer application programming interface
//communicate btw client and server transfer state mean information

// rest api = url+HTTP verb

/* HTTP verb
* get:- only can see
* put:- modify
* post:- create
* delete:- delete
* */


/*
****ORM****
*Object-Relational Mapping
*ORM is a technique used to map java objects to database table
*It allows developers to work with databases using object-oriented programming
* making it easier to interact with relational database
*/


/*
* *** JPA ***
* java persistence api
* jpa is a way to achieve ORM
* include interface and annotations that you use in your java classes
* requires a persistence provider (orm tool) for implementation*/
/*A persistence provider is a specific implementation of the jpa specification
* Example "Hibernate" ,"Eclipse Link" And OpenJpa
* these provide implement the JPA interface and provide the underlying functionality to
* interact with DB
* */

/*
***Spring Data JPA***
* it is built on the top of JPA specification
* but is not a JPA implementation itself
* it simplifies working with Jpa
* provide higher level abstraction and utilities
* to use spring data jpa effectively you still need a jpa implementation
*
 */