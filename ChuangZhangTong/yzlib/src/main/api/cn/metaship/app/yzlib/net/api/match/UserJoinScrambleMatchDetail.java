package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class UserJoinScrambleMatchDetail implements Serializable{

    private UserJoinScrambleMatchLogBean userJoinScrambleMatchLogBean;
    private MatchVoteTodayPropositionBean matchVoteTodayPropositionBean;

    public UserJoinScrambleMatchLogBean getUserJoinScrambleMatchLogBean(){
        return this.userJoinScrambleMatchLogBean;
    }

    public void setUserJoinScrambleMatchLogBean(UserJoinScrambleMatchLogBean userJoinScrambleMatchLogBean){
        this.userJoinScrambleMatchLogBean = userJoinScrambleMatchLogBean;
    }
    public MatchVoteTodayPropositionBean getMatchVoteTodayPropositionBean(){
        return this.matchVoteTodayPropositionBean;
    }

    public void setMatchVoteTodayPropositionBean(MatchVoteTodayPropositionBean matchVoteTodayPropositionBean){
        this.matchVoteTodayPropositionBean = matchVoteTodayPropositionBean;
    }

}