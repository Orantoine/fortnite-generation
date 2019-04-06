package fr.Orantoine.fortnitegeneration.repositories;

import fr.Orantoine.fortnitegeneration.models.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface resumeRepository extends MongoRepository<Resume,String> {
}
