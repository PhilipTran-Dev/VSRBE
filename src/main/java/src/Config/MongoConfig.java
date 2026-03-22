package src.Config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {
    private static final String MONGO_URI="mongodb+srv://tnvphuccntt_db_user:k9vkpAY8VV9pNXzF@vsr.hzmhooa.mongodb.net/?appName=VSR";
    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create(MONGO_URI);
    }
    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoClient mongoClient){
        return new SimpleMongoClientDatabaseFactory(mongoClient, "VSR");
    }
    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }
}
