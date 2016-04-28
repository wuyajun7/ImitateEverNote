package com.vpdemo2;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuyajun on 16/4/28.
 */
public class NNVipBean implements Serializable {

    private String title;
    private int sortDetailColor;
    private List<NNVipDetail> vipBeans;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSortDetailColor() {
        return sortDetailColor;
    }

    public void setSortDetailColor(int sortDetailColor) {
        this.sortDetailColor = sortDetailColor;
    }

    public List<NNVipDetail> getVipBeans() {
        return vipBeans;
    }

    public void setVipBeans(List<NNVipDetail> vipBeans) {
        this.vipBeans = vipBeans;
    }
}
