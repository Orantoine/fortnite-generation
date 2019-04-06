package fr.Orantoine.fortnitegeneration.services;

import fr.Orantoine.fortnitegeneration.models.Match;

import java.util.List;

public class GetInfo {

    private String nbMatchs;
    private String nbKills;

    private List<Match> matchs;


    public GetInfo(List<Match> matchs){
        this.matchs = matchs;
    }

    public int findNbMatch(){
        int nbMatch = 0;
        int nbKills = 0;
        for (Match match:matchs) {
           nbMatch+=Integer.parseInt(match.getMatches());
           nbKills+=Integer.parseInt(match.getKills());
        }
        this.nbKills = String.valueOf(nbKills);
        this.nbMatchs = String.valueOf(nbMatch);
        return nbMatch;
    }

    public int findWins(){
        int nbWins = 0;
        for (Match match:matchs) {
            nbWins+=Integer.parseInt(match.getTop1());
        }
        return nbWins;
    }

    public float findRatio(){
        float ratio = 0;
        if(Integer.parseInt(nbMatchs)>0){
            ratio = Float.parseFloat(nbKills)/Float.parseFloat(nbMatchs);
        }
        return ratio;
    }
}
