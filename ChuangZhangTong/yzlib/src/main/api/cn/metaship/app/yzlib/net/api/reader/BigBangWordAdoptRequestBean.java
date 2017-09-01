package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class BigBangWordAdoptRequestBean implements Serializable{

    private BigBangWordAdoptBean bigBangWordAdoptBean;
    private BigBangWordBean bigBangWordBean;

    public BigBangWordAdoptBean getBigBangWordAdoptBean(){
        return this.bigBangWordAdoptBean;
    }

    public void setBigBangWordAdoptBean(BigBangWordAdoptBean bigBangWordAdoptBean){
        this.bigBangWordAdoptBean = bigBangWordAdoptBean;
    }
    public BigBangWordBean getBigBangWordBean(){
        return this.bigBangWordBean;
    }

    public void setBigBangWordBean(BigBangWordBean bigBangWordBean){
        this.bigBangWordBean = bigBangWordBean;
    }

}