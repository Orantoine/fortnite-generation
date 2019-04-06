package fr.Orantoine.fortnitegeneration.repositories;

import fr.Orantoine.fortnitegeneration.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player,String> {
    Player findByPseudo(String pseudo);
}
