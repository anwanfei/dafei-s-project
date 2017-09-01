package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;
import java.util.List;

public final class SimpleUserInfoBean implements Serializable{

    private Integer score;
    private List<String> medalList;
    private String nickname;
    private Byte reviewStatus;
    private Integer exp;
    private String username;
    private Integer coin;
    private String invitationCode;
    private String birthday;
    private String avatarUrl;
    private String signature;
    private Integer level;
    private Integer gender;
    private String levelName;
    private String userId;
    private List<Integer> roleType;
    private Integer isVip;

    public Integer getScore(){
        return this.score;
    }

    public void setScore(Integer score){
        this.score = score;
    }
    public List<String> getMedalList(){
        return this.medalList;
    }

    public void setMedalList(List<String> medalList){
        this.medalList = medalList;
    }
    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public Byte getReviewStatus(){
        return this.reviewStatus;
    }

    public void setReviewStatus(Byte reviewStatus){
        this.reviewStatus = reviewStatus;
    }
    public Integer getExp(){
        return this.exp;
    }

    public void setExp(Integer exp){
        this.exp = exp;
    }
    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public Integer getCoin(){
        return this.coin;
    }

    public void setCoin(Integer coin){
        this.coin = coin;
    }
    public String getInvitationCode(){
        return this.invitationCode;
    }

    public void setInvitationCode(String invitationCode){
        this.invitationCode = invitationCode;
    }
    public String getBirthday(){
        return this.birthday;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }
    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }
    public String getSignature(){
        return this.signature;
    }

    public void setSignature(String signature){
        this.signature = signature;
    }
    public Integer getLevel(){
        return this.level;
    }

    public void setLevel(Integer level){
        this.level = level;
    }
    public Integer getGender(){
        return this.gender;
    }

    public void setGender(Integer gender){
        this.gender = gender;
    }
    public String getLevelName(){
        return this.levelName;
    }

    public void setLevelName(String levelName){
        this.levelName = levelName;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public List<Integer> getRoleType(){
        return this.roleType;
    }

    public void setRoleType(List<Integer> roleType){
        this.roleType = roleType;
    }
    public Integer getIsVip(){
        return this.isVip;
    }

    public void setIsVip(Integer isVip){
        this.isVip = isVip;
    }

}