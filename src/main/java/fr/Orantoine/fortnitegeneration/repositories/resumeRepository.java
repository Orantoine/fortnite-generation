package fr.Orantoine.fortnitegeneration.repositories;

import fr.Orantoine.fortnitegeneration.models.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface resumeRepository extends MongoRepository<Resume,String> {

    List<Resume> findAllByAccountNameEquals(String playername);
}
