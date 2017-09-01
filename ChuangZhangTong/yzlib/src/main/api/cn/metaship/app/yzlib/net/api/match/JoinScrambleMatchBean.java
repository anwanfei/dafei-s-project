package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class JoinScrambleMatchBean implements Serializable{

    private String userId;
    private MatchJoinPropositionModel model;

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public MatchJoinPropositionModel getModel(){
        return this.model;
    }

    public void setModel(MatchJoinPropositionModel model){
        this.model = model;
    }

}