package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class BigBangWordRequestBodyBean implements Serializable{

    private BigBangWordBean bigBangWordBean;
    private BigBangWordAskBean bigBangWordAskBean;

    public BigBangWordBean getBigBangWordBean(){
        return this.bigBangWordBean;
    }

    public void setBigBangWordBean(BigBangWordBean bigBangWordBean){
        this.bigBangWordBean = bigBangWordBean;
    }
    public BigBangWordAskBean getBigBangWordAskBean(){
        return this.bigBangWordAskBean;
    }

    public void setBigBangWordAskBean(BigBangWordAskBean bigBangWordAskBean){
        this.bigBangWordAskBean = bigBangWordAskBean;
    }

}