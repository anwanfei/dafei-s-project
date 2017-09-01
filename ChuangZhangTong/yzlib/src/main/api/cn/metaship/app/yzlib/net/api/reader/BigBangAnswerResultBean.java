package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class BigBangAnswerResultBean implements Serializable{

    private String nickname;
    /**
    *回答对象
    */
    private BigBangWordAnswerBean bigBangWordAnswerBean;
    private String avatarUrl;

    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public BigBangWordAnswerBean getBigBangWordAnswerBean(){
        return this.bigBangWordAnswerBean;
    }

    public void setBigBangWordAnswerBean(BigBangWordAnswerBean bigBangWordAnswerBean){
        this.bigBangWordAnswerBean = bigBangWordAnswerBean;
    }
    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }

}