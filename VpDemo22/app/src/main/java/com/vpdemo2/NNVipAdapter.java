package com.vpdemo2;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.vpdemo2.wnadapter.WNBaseAdapter;
import com.vpdemo2.wnadapter.WNViewHolder;

import java.util.List;

/**
 * Created by wuyajun on 15/6/19.
 * <p>
 * VIP界面宫格 adapter
 */
public class NNVipAdapter extends WNBaseAdapter<NNVipDetail> {

    public NNVipAdapter(Context context, List<NNVipDetail> datas, int itemId) {
        super(context, datas, itemId);
    }

    @Override
    public void convertView(WNViewHolder holder, NNVipDetail vipBean, int position) {
        ((ImageView) holder.getView(R.id.vip_gv_img)).setImageResource(vipBean.getImgId());
        ((TextView) holder.getView(R.id.vip_gv_describe)).setText(vipBean.getDescribe());
    }
}
