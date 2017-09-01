package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;
import java.util.List;

public final class SimpleOtherUserInfoBean implements Serializable{

    private Integer isVip;
    private String userName;
    private Integer exp;
    private List<Integer> roleType;
    private String levelName;
    private String nickname;
    private String avatarUrl;
    private String signature;
    private Integer level;
    private Integer gender;
    private String birthday;
    private List<String> medalList;

    public Integer getIsVip(){
        return this.isVip;
    }

    public void setIsVip(Integer isVip){
        this.isVip = isVip;
    }
    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
    public Integer getExp(){
        return this.exp;
    }

    public void setExp(Integer exp){
        this.exp = exp;
    }
    public List<Integer> getRoleType(){
        return this.roleType;
    }

    public void setRoleType(List<Integer> roleType){
        this.roleType = roleType;
    }
    public String getLevelName(){
        return this.levelName;
    }

    public void setLevelName(String levelName){
        this.levelName = levelName;
    }
    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
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
    public String getBirthday(){
        return this.birthday;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }
    public List<String> getMedalList(){
        return this.medalList;
    }

    public void setMedalList(List<String> medalList){
        this.medalList = medalList;
    }

}