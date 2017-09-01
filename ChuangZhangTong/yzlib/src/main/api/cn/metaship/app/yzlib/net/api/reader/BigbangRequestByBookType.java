package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.List;

public final class BigbangRequestByBookType implements Serializable{

    private List<EpubWordPositionBean> epubWordPositionBeans;
    private List<TxtWordPositionBean> txtWordPositionBeans;

    public List<EpubWordPositionBean> getEpubWordPositionBeans(){
        return this.epubWordPositionBeans;
    }

    public void setEpubWordPositionBeans(List<EpubWordPositionBean> epubWordPositionBeans){
        this.epubWordPositionBeans = epubWordPositionBeans;
    }
    public List<TxtWordPositionBean> getTxtWordPositionBeans(){
        return this.txtWordPositionBeans;
    }

    public void setTxtWordPositionBeans(List<TxtWordPositionBean> txtWordPositionBeans){
        this.txtWordPositionBeans = txtWordPositionBeans;
    }

}