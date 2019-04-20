package fr.Orantoine.fortnitegeneration.controllers;


import fr.Orantoine.fortnitegeneration.models.Player;
import fr.Orantoine.fortnitegeneration.models.Resume;
import fr.Orantoine.fortnitegeneration.repositories.MatchsRepository;
import fr.Orantoine.fortnitegeneration.repositories.PlayerRepository;
import fr.Orantoine.fortnitegeneration.services.GetInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
public class ResultatDay {

    private static final Logger log = LoggerFactory.getLogger(ResultatDay.class);

    @Autowired
    private MatchsRepository matchsRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping(value = "/resultat/{pseudo}/{mode}")
    public Resume getDayForMode(@PathVariable String pseudo, @PathVariable(required = false) String mode){
        Resume resume = new Resume();
        Player player = playerRepository.findByPseudo(pseudo);
        if (player != null) {
            resume.setPlayerId(player.getAccountid());
            if(mode!= null) {
                resume.setPlayerMatchs(matchsRepository.findAllByAccountIdAndPlaylistOrderByDateCollectedDesc(player.getAccountid(), mode));
                log.info("Le mode a été choisi est se trouve être : " + mode);
            }
            else{
                resume.setPlayerMatchs(matchsRepository.findAllByAccountIdOrderByDateCollectedDesc(player.getAccountid()));
                log.info("Aucun mode n'a été choisi");
            }
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
