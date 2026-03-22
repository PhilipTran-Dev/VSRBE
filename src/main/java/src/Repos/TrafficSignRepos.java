package src.Repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import src.Entity.TrafficSign;
@Repository
public interface TrafficSignRepos extends MongoRepository<TrafficSign, String> {
    TrafficSign findBySignCode(String signCode);
}
