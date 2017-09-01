package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class LibEntryJoinMatchBean implements Serializable{

    private String unitCode;
    private String teacherId;
    private String matchId;
    private String entryId;
    private String title;

    public String getUnitCode(){
        return this.unitCode;
    }

    public void setUnitCode(String unitCode){
        this.unitCode = unitCode;
    }
    public String getTeacherId(){
        return this.teacherId;
    }

    public void setTeacherId(String teacherId){
        this.teacherId = teacherId;
    }
    public String getMatchId(){
        return this.matchId;
    }

    public void setMatchId(String matchId){
        this.matchId = matchId;
    }
    public String getEntryId(){
        return this.entryId;
    }

    public void setEntryId(String entryId){
        this.entryId = entryId;
    }
    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

}