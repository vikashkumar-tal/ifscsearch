package com.ifscsearch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

@Configuration
@EnableMongoRepositories
public class MongoConfig {

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

	/*@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		String host = "127.12.214.130";
		int port = 27017;
		String username = "admin";
		String password = "zHpIuJ6wwpT7";
		Mongo mongo = new Mongo(host, port);
		UserCredentials userCredentials = new UserCredentials(username,
				password);
		String databaseName = "ifscsearch";
		return new SimpleMongoDbFactory(mongo, databaseName, userCredentials);
	}*/

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		String host = "127.0.0.1";
		int port = 27017;
		Mongo mongo = new Mongo(host, port);
		String databaseName = "local";
		return new SimpleMongoDbFactory(mongo, databaseName);
	}

}