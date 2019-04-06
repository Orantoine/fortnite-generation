package fr.Orantoine.fortnitegeneration.models;

import org.springframework.data.annotation.Id;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.List;

public class Resume {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String id;

    public String playerId;

    public String playerName;

    public Date updateDate;

    public int matchs;

    public float ratio;

    public int wins;

    public List<Match> playerMatchs;


    public Resume(String id, String playerId, String playerName, List<Match> playerMatchs, Date updateDate, int matchs, float ratio, int wins) {
        this.id = id;
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerMatchs = playerMatchs;
        this.updateDate = updateDate;
        this.matchs = matchs;
        this.ratio = ratio;
        this.wins = wins;
    }

    public Resume() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<Match> getPlayerMatchs() {
        return playerMatchs;
    }

    public void setPlayerMatchs(List<Match> playerMatchs) {
        this.playerMatchs = playerMatchs;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getMatchs() {
        return matchs;
    }

    public void setMatchs(int matchs) {
        this.matchs = matchs;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id='" + id + '\'' +
                ", playerId='" + playerId + '\'' +
                ", playerName='" + playerName + '\'' +
                ", playerMatchs=" + playerMatchs +
                ", updateDate=" + updateDate +
                ", matchs=" + matchs +
                ", ratio=" + ratio +
                ", wins=" + wins +
                '}';
    }
}
