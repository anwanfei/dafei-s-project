package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class LotteryScrambleBean implements Serializable{

    private String matchId;
    private String userId;
    private String proposition;

    public String getMatchId(){
        return this.matchId;
    }

    public void setMatchId(String matchId){
        this.matchId = matchId;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getProposition(){
        return this.proposition;
    }

    public void setProposition(String proposition){
        this.proposition = proposition;
    }

}