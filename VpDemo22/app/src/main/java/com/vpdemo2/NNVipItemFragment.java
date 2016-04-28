package com.vpdemo2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
public class NNVipItemFragment extends Fragment implements View.OnClickListener {

    private Context mContext;

    private TextView mVipTitle;
    private NoScrollGridView mVipGv;
    private TextView mVipSortDetail;

    private NNVipBean vipBean;
    private List<NNVipDetail> vipBeans;
    private NNVipAdapter vipAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vip_item_layout, null);

        Bundle data = getArguments();//获得从activity中传递过来的值
        if (data != null) {
            vipBean = (NNVipBean) data.getSerializable("vipModel");
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();

        initViews();
    }

    private void initViews() {
        mVipTitle = (TextView) getView().findViewById(R.id.vip_title);
        mVipGv = (NoScrollGridView) getView().findViewById(R.id.vip_gv);
        mVipSortDetail = (TextView) getView().findViewById(R.id.vip_sort_detail);
        mVipSortDetail.setOnClickListener(this);

        vipBeans = new ArrayList<>();
        vipBeans.clear();
        vipBeans.addAll(vipBean.getVipBeans());

        vipAdapter = new NNVipAdapter(mContext, vipBeans, R.layout.vip_gv_item);
        mVipGv.setAdapter(vipAdapter);

        mVipTitle.setText(vipBean.getTitle());
        mVipSortDetail.setText("查看" + vipBean.getTitle() + "全部特权 >>");
        mVipSortDetail.setTextColor(vipBean.getSortDetailColor());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vip_sort_detail:
                break;
        }
    }
}
