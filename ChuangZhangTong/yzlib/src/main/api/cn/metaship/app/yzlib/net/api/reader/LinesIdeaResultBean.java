package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class LinesIdeaResultBean implements Serializable{

    /**
    *用户昵称
    */
    private String nickname;
    private LinesContentBean linesContentBean;
    /**
    *用户头像
    */
    private String avatarUrl;
    private LinesIdeaBean linesIdeaBean;

    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public LinesContentBean getLinesContentBean(){
        return this.linesContentBean;
    }

    public void setLinesContentBean(LinesContentBean linesContentBean){
        this.linesContentBean = linesContentBean;
    }
    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }
    public LinesIdeaBean getLinesIdeaBean(){
        return this.linesIdeaBean;
    }

    public void setLinesIdeaBean(LinesIdeaBean linesIdeaBean){
        this.linesIdeaBean = linesIdeaBean;
    }

}