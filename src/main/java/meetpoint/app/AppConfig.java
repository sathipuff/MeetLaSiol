package meetpoint.app;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class AppConfig {

    /*
     * Use the standard Mongo driver API to create a com.mongodb.MongoClient instance.
     */
    public @Bean
    MongoClient mongoClient() {
        return new MongoClient("localhost");
        /*return new MongoClient(singletonList(new ServerAddress("127.0.0.1", 27017)),
      singletonList(MongoCredential.createCredential("name", "db", "pwd".toCharArray())));*/
    }

    public @Bean MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "mydatabase");
    }
}