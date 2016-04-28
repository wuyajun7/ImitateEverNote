package com.vpdemo2;

import java.io.Serializable;

/**
 * Created by wuyajun on 16/4/28.
 */
public class NNVipDetail implements Serializable {
    private int imgId;
    private String describe;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
