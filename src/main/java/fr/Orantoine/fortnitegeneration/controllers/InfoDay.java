package fr.Orantoine.fortnitegeneration.controllers;


import fr.Orantoine.fortnitegeneration.models.Player;
import fr.Orantoine.fortnitegeneration.models.Resume;
import fr.Orantoine.fortnitegeneration.repositories.MatchsRepository;
import fr.Orantoine.fortnitegeneration.repositories.PlayerRepository;
import fr.Orantoine.fortnitegeneration.repositories.resumeRepository;
import fr.Orantoine.fortnitegeneration.services.GetInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class InfoDay {

    private static final Logger log = LoggerFactory.getLogger(InfoDay.class);

    @Autowired
    private MatchsRepository matchsRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private resumeRepository resumeRepository;

    @GetMapping(value = "/health")
    public String healthCheck(){
        return "OK";
    }

    @GetMapping(value = "/infoplayer/{pseudo}")
    public List<Resume> getDay(@PathVariable String pseudo){
        log.info("Request type get : /infoplayer/{pseudo}");
        if (pseudo != null) {
            List<Resume> resumes = resumeRepository.findAllByAccountNameEquals(pseudo);
            log.info("Envoie des informations");
            return resumes;
        }
        else{
            log.error("Pseudo du joueur inconnu : " +pseudo);
        }
       return null;
    }
}
