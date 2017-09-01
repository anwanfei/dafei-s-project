package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class SimpleEntryCommentResultBean implements Serializable{

    /**
    *用户信息
    */
    private SimpleUserBean simpleUserBean;
    /**
    *作品评论的对象
    */
    private EntryCommentBean entryCommentBean;

    public SimpleUserBean getSimpleUserBean(){
        return this.simpleUserBean;
    }

    public void setSimpleUserBean(SimpleUserBean simpleUserBean){
        this.simpleUserBean = simpleUserBean;
    }
    public EntryCommentBean getEntryCommentBean(){
        return this.entryCommentBean;
    }

    public void setEntryCommentBean(EntryCommentBean entryCommentBean){
        this.entryCommentBean = entryCommentBean;
    }

}