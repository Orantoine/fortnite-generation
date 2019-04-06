package fr.Orantoine.fortnitegeneration.repositories;

import fr.Orantoine.fortnitegeneration.models.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MatchsRepository extends MongoRepository<Match,String> {

    List<Match> findAllByAccountId(String id);
    List<Match> findAllByAccountIdAndPlaylist(String id, String mode);
    List<Match> findAllByAccountIdOrderByDateCollectedDesc(String id);
    List<Match> findAllByAccountIdAndPlaylistOrderByDateCollectedDesc(String id, String mode);
}
