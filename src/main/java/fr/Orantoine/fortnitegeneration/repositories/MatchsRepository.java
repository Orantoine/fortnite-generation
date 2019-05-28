package fr.Orantoine.fortnitegeneration.repositories;

import fr.Orantoine.fortnitegeneration.models.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface MatchsRepository extends MongoRepository<Match,String> {
}
