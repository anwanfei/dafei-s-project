package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;
import java.util.List;

public final class BaseUserBean implements Serializable{

    private Integer exp;
    private String nickname;
    private Integer isVip;
    private List<String> medalIdList;
    private String userName;
    private List<Integer> roleType;
    private String userId;
    private String levelName;
    private Integer gender;
    private String avatarUrl;
    private String signature;
    private Integer level;
    private String birthday;

    public Integer getExp(){
        return this.exp;
    }

    public void setExp(Integer exp){
        this.exp = exp;
    }
    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public Integer getIsVip(){
        return this.isVip;
    }

    public void setIsVip(Integer isVip){
        this.isVip = isVip;
    }
    public List<String> getMedalIdList(){
        return this.medalIdList;
    }

    public void setMedalIdList(List<String> medalIdList){
        this.medalIdList = medalIdList;
    }
    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
    public List<Integer> getRoleType(){
        return this.roleType;
    }

    public void setRoleType(List<Integer> roleType){
        this.roleType = roleType;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getLevelName(){
        return this.levelName;
    }

    public void setLevelName(String levelName){
        this.levelName = levelName;
    }
    public Integer getGender(){
        return this.gender;
    }

    public void setGender(Integer gender){
        this.gender = gender;
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
    public String getBirthday(){
        return this.birthday;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

}