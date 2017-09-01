package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class MatchExpertBean implements Serializable{

    /**
    *头像地址
    */
    private String avatarUrl;
    /**
    *专家简历
    */
    private String profile;
    /**
    *真实姓名
    */
    private String realName;
    /**
    *用户id
    */
    private String userId;
    /**
    *大赛id
    */
    private String matchId;
    /**
    *组别编码
    */
    private String groupCode;
    private String id;
    /**
    *职级。教授等
    */
    private String rank;

    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }
    public String getProfile(){
        return this.profile;
    }

    public void setProfile(String profile){
        this.profile = profile;
    }
    public String getRealName(){
        return this.realName;
    }

    public void setRealName(String realName){
        this.realName = realName;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
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
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getRank(){
        return this.rank;
    }

    public void setRank(String rank){
        this.rank = rank;
    }

}