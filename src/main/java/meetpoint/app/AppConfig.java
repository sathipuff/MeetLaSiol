package meetpoint.app;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Configuration
public class AppConfig {

    /*
     * Use the standard Mongo driver API to create a com.mongodb.MongoClient instance.
     */
    public @Bean
    MongoClient mongoClient()  {
        //return new MongoClient("localhost");

        MongoClientURI uri = new MongoClientURI("mongodb://localhost");

        try {
            String password = URLEncoder.encode("MagicalMe@1318", "UTF-8");


            uri = new MongoClientURI(
                "mongodb://admin:"+password+"@cluster0-shard-00-00-ooctf.mongodb.net:27017," +
                        "cluster0-shard-00-01-ooctf.mongodb.net:27017" +
                        ",cluster0-shard-00-02-ooctf.mongodb.net:27017" +
                        "/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } finally
        {
            return new MongoClient(uri);
        }
    }

    public @Bean MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "mpDatabase");
    }
}