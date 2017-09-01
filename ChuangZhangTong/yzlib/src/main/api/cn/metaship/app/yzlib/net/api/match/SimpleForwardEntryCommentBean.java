package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class SimpleForwardEntryCommentBean implements Serializable{

    /**
    *评论内容
    */
    private String commentContent;
    /**
    *被评论的ID
    */
    private String beCommentId;
    /**
    *评论人ID
    */
    private String commentUserId;
    /**
    *转发记录ID
    */
    private String forwardEntryId;

    public String getCommentContent(){
        return this.commentContent;
    }

    public void setCommentContent(String commentContent){
        this.commentContent = commentContent;
    }
    public String getBeCommentId(){
        return this.beCommentId;
    }

    public void setBeCommentId(String beCommentId){
        this.beCommentId = beCommentId;
    }
    public String getCommentUserId(){
        return this.commentUserId;
    }

    public void setCommentUserId(String commentUserId){
        this.commentUserId = commentUserId;
    }
    public String getForwardEntryId(){
        return this.forwardEntryId;
    }

    public void setForwardEntryId(String forwardEntryId){
        this.forwardEntryId = forwardEntryId;
    }

}