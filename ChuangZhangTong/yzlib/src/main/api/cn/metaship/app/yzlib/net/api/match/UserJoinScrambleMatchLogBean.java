package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class UserJoinScrambleMatchLogBean implements Serializable{

    /**
    *用户ID
    */
    private String userId;
    /**
    *参与命题
    */
    private MatchJoinPropositionModel matchJoinPropositionModel;
    /**
    *抽奖总次数
    */
    private Integer lotteryCount;
    /**
    *未抽奖次数
    */
    private Integer notLotteryCount;
    private String id;

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public MatchJoinPropositionModel getMatchJoinPropositionModel(){
        return this.matchJoinPropositionModel;
    }

    public void setMatchJoinPropositionModel(MatchJoinPropositionModel matchJoinPropositionModel){
        this.matchJoinPropositionModel = matchJoinPropositionModel;
    }
    public Integer getLotteryCount(){
        return this.lotteryCount;
    }

    public void setLotteryCount(Integer lotteryCount){
        this.lotteryCount = lotteryCount;
    }
    public Integer getNotLotteryCount(){
        return this.notLotteryCount;
    }

    public void setNotLotteryCount(Integer notLotteryCount){
        this.notLotteryCount = notLotteryCount;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}