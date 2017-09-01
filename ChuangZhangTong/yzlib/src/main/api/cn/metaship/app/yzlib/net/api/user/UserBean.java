package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;
import java.util.Map;

public final class UserBean implements Serializable{

    private byte activeState;
    private Integer gender;
    private String mail;
    private BindSinaMicroBlogBean bindSinaMicroBlogBean;
    private String signature;
    private String birthday;
    private BindQQBean bindQQBean;
    private SystemTeacherDetailsModel systemTeacherDetailsModel;
    private String roleType;
    private String password;
    private String id;
    private Map<String,String> details;
    private String nickname;
    private PlatformTeacherDetailsModel platformTeacherDetailsModel;
    private String avatarUrl;
    private StudentDetailsModel studentDetailsModel;
    private byte platformState;
    private String QRCode;
    private String phoneNumber;
    private BindWeiXinBean bindWeiXinBean;
    private String username;
    private byte reviewState;
    private byte registerStep;
    private String InvitationCode;
    private String weChat;

    public byte getActiveState(){
        return this.activeState;
    }

    public void setActiveState(byte activeState){
        this.activeState = activeState;
    }
    public Integer getGender(){
        return this.gender;
    }

    public void setGender(Integer gender){
        this.gender = gender;
    }
    public String getMail(){
        return this.mail;
    }

    public void setMail(String mail){
        this.mail = mail;
    }
    public BindSinaMicroBlogBean getBindSinaMicroBlogBean(){
        return this.bindSinaMicroBlogBean;
    }

    public void setBindSinaMicroBlogBean(BindSinaMicroBlogBean bindSinaMicroBlogBean){
        this.bindSinaMicroBlogBean = bindSinaMicroBlogBean;
    }
    public String getSignature(){
        return this.signature;
    }

    public void setSignature(String signature){
        this.signature = signature;
    }
    public String getBirthday(){
        return this.birthday;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }
    public BindQQBean getBindQQBean(){
        return this.bindQQBean;
    }

    public void setBindQQBean(BindQQBean bindQQBean){
        this.bindQQBean = bindQQBean;
    }
    public SystemTeacherDetailsModel getSystemTeacherDetailsModel(){
        return this.systemTeacherDetailsModel;
    }

    public void setSystemTeacherDetailsModel(SystemTeacherDetailsModel systemTeacherDetailsModel){
        this.systemTeacherDetailsModel = systemTeacherDetailsModel;
    }
    public String getRoleType(){
        return this.roleType;
    }

    public void setRoleType(String roleType){
        this.roleType = roleType;
    }
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public Map<String,String> getDetails(){
        return this.details;
    }

    public void setDetails(Map<String,String> details){
        this.details = details;
    }
    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public PlatformTeacherDetailsModel getPlatformTeacherDetailsModel(){
        return this.platformTeacherDetailsModel;
    }

    public void setPlatformTeacherDetailsModel(PlatformTeacherDetailsModel platformTeacherDetailsModel){
        this.platformTeacherDetailsModel = platformTeacherDetailsModel;
    }
    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }
    public StudentDetailsModel getStudentDetailsModel(){
        return this.studentDetailsModel;
    }

    public void setStudentDetailsModel(StudentDetailsModel studentDetailsModel){
        this.studentDetailsModel = studentDetailsModel;
    }
    public byte getPlatformState(){
        return this.platformState;
    }

    public void setPlatformState(byte platformState){
        this.platformState = platformState;
    }
    public String getQRCode(){
        return this.QRCode;
    }

    public void setQRCode(String QRCode){
        this.QRCode = QRCode;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public BindWeiXinBean getBindWeiXinBean(){
        return this.bindWeiXinBean;
    }

    public void setBindWeiXinBean(BindWeiXinBean bindWeiXinBean){
        this.bindWeiXinBean = bindWeiXinBean;
    }
    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public byte getReviewState(){
        return this.reviewState;
    }

    public void setReviewState(byte reviewState){
        this.reviewState = reviewState;
    }
    public byte getRegisterStep(){
        return this.registerStep;
    }

    public void setRegisterStep(byte registerStep){
        this.registerStep = registerStep;
    }
    public String getInvitationCode(){
        return this.InvitationCode;
    }

    public void setInvitationCode(String InvitationCode){
        this.InvitationCode = InvitationCode;
    }
    public String getWeChat(){
        return this.weChat;
    }

    public void setWeChat(String weChat){
        this.weChat = weChat;
    }

}