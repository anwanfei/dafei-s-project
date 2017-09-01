package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.List;

public final class SlideShowUpdateBean implements Serializable{

    private SlideShowBean bean;
    private List<String> properties;

    public SlideShowBean getBean(){
        return this.bean;
    }

    public void setBean(SlideShowBean bean){
        this.bean = bean;
    }
    public List<String> getProperties(){
        return this.properties;
    }

    public void setProperties(List<String> properties){
        this.properties = properties;
    }

}