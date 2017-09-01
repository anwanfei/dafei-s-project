package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class SimpleJoinMatchBean implements Serializable{

    /**
    *大赛id
    */
    private String matchId;
    /**
    *参赛组别编码
    */
    private String groupCode;
    /**
    *用户id
    */
    private String userId;

    public String getMatchId(){
        return this.matchId;
    }

    public void setMatchId(String matchId){
        this.matchId = matchId;
    }
    public String getGroupCode(){
        return this.groupCode;
    }

    public void setGroupCode(String groupCode){
        this.groupCode = groupCode;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

}