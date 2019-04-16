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

import javax.annotation.security.PermitAll;
import java.util.*;

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
    public Resume getDataForMode(@PathVariable String pseudo, @PathVariable String mode){
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

    @GetMapping(value = "/resultats/{pseudo}/day/{mode}")
    public Resume getDayOfPlayer(@PathVariable String pseudo,
                                 @PathVariable(required = false) String mode ){
        log.info("Request type get : /infoplayer/{pseudo}/day/{mode}");
        Resume resume = new Resume();
        Player player = playerRepository.findByPseudo(pseudo);
        if(player != null){
            resume.setPlayerId(player.getAccountid());
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
            calendar1.set(Calendar.MONTH,calendar.get(Calendar.MONTH));
            calendar1.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH));
            calendar1.set(Calendar.HOUR_OF_DAY,00);
            calendar1.set(Calendar.MINUTE,00);
            calendar1.set(Calendar.SECOND,00);
            Date start = calendar1.getTime();
            calendar1.set(Calendar.HOUR_OF_DAY,23);
            calendar1.set(Calendar.MINUTE,59);
            calendar1.set(Calendar.SECOND,59);
            Date end = calendar1.getTime();
            log.info("Tout fonctionne");
            resume.setPlayerMatchs(matchsRepository.findAllByAccountIdAndDateCollectedBetweenOrderByDateCollectedDesc(player.getAccountid(),start,end));
            GetInfo getInfo = new GetInfo(resume.getPlayerMatchs());
            resume.setMatchs(getInfo.findNbMatch());
            resume.setWins(getInfo.findWins());
            resume.setRatio(getInfo.findRatio());
            resume.setUpdateDate(new Date());
        }else{
            log.error("Pseudo du joueur inconnu : " +pseudo);
            resume.setPlayerName("Le pseudo : " + pseudo + " est inconnu. Vérifier son existance dans la bdd ou contacter notre administrateur");
        }
        return resume;
    }
}
