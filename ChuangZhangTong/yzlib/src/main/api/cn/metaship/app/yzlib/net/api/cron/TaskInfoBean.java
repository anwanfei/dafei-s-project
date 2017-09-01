package cn.metaship.app.yzlib.net.api.cron;

import java.io.Serializable;

public final class TaskInfoBean implements Serializable{

    private String method;
    private String createTime;
    private String args;
    private String cronExpression;
    private String jobStatus;
    private String serviceName;
    private Integer jobType;
    private String version;
    private Integer timeout;
    private String jobDescription;
    private String jobGroup;

    public String getMethod(){
        return this.method;
    }

    public void setMethod(String method){
        this.method = method;
    }
    public String getCreateTime(){
        return this.createTime;
    }

    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }
    public String getArgs(){
        return this.args;
    }

    public void setArgs(String args){
        this.args = args;
    }
    public String getCronExpression(){
        return this.cronExpression;
    }

    public void setCronExpression(String cronExpression){
        this.cronExpression = cronExpression;
    }
    public String getJobStatus(){
        return this.jobStatus;
    }

    public void setJobStatus(String jobStatus){
        this.jobStatus = jobStatus;
    }
    public String getServiceName(){
        return this.serviceName;
    }

    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }
    public Integer getJobType(){
        return this.jobType;
    }

    public void setJobType(Integer jobType){
        this.jobType = jobType;
    }
    public String getVersion(){
        return this.version;
    }

    public void setVersion(String version){
        this.version = version;
    }
    public Integer getTimeout(){
        return this.timeout;
    }

    public void setTimeout(Integer timeout){
        this.timeout = timeout;
    }
    public String getJobDescription(){
        return this.jobDescription;
    }

    public void setJobDescription(String jobDescription){
        this.jobDescription = jobDescription;
    }
    public String getJobGroup(){
        return this.jobGroup;
    }

    public void setJobGroup(String jobGroup){
        this.jobGroup = jobGroup;
    }

}