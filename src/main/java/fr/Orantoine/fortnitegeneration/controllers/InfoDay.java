package fr.Orantoine.fortnitegeneration.controllers;


import fr.Orantoine.fortnitegeneration.models.Match;
import fr.Orantoine.fortnitegeneration.models.Player;
import fr.Orantoine.fortnitegeneration.models.Resume;
import fr.Orantoine.fortnitegeneration.repositories.MatchsRepository;
import fr.Orantoine.fortnitegeneration.repositories.PlayerRepository;
import fr.Orantoine.fortnitegeneration.services.GetInfo;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@CrossOrigin(origins = "*")
@RestController
public class InfoDay {

    private static final Logger log = LoggerFactory.getLogger(InfoDay.class);

    @Autowired
    private MatchsRepository matchsRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping(value = "/health")
    public String healthCheck(){
        return "OK";
    }

    @GetMapping(value = "/infoplayer/{pseudo}")
    public Resume getDay(@PathVariable String pseudo){
        log.info("Request type get : /infoplayer/{pseudo}");
        Resume resume = new Resume();
        Player player = playerRepository.findByPseudo(pseudo);
        if (player != null) {
            resume.setPlayerId(player.getAccountid());
            resume.setPlayerMatchs(matchsRepository.findAllByAccountIdOrderByDateCollectedDesc(player.getAccountid()));
            resume.setPlayerName(player.getPseudo());
            resume.setUpdateDate(new Date());
            GetInfo getInfo = new GetInfo(resume.getPlayerMatchs());
            resume.setMatchs(getInfo.findNbMatch());
            resume.setWins(getInfo.findWins());
            resume.setRatio(getInfo.findRatio());
            resume.setUpdateDate(new Date());
            log.info("Envoie des informations");
        }
        else{
            log.error("Pseudo du joueur inconnu : " +pseudo);
            resume.setPlayerName("Le pseudo : " + pseudo + " est inconnu. Vérifier son existance dans la bdd ou contacter notre administrateur");
        }
        return resume;
    }

    @GetMapping(value = "/infoplayer/{pseudo}/{mode}")
    public Resume getDayForMode(@PathVariable String pseudo, @PathVariable String mode){
        log.info("Request type get : /infoplayer/{pseudo}/{mode}");
        Resume resume = new Resume();
        Player player = playerRepository.findByPseudo(pseudo);
        if (player != null) {
            resume.setPlayerId(player.getAccountid());
            resume.setPlayerMatchs(matchsRepository.findAllByAccountIdAndPlaylistOrderByDateCollectedDesc(player.getAccountid(), mode));
            GetInfo getInfo = new GetInfo(resume.getPlayerMatchs());
            resume.setMatchs(getInfo.findNbMatch());
            resume.setWins(getInfo.findWins());
            resume.setRatio(getInfo.findRatio());
            resume.setUpdateDate(new Date());
            log.info("Envoie des informations");
        }else{
            log.error("Pseudo du joueur inconnu : " +pseudo);
            resume.setPlayerName("Le pseudo : " + pseudo + " est inconnu. Vérifier son existance dans la bdd ou contacter notre administrateur");
        }
        return resume;
    }
}
