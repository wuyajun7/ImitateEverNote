package com.vpdemo2;

/**
 * Created by wuyajun on 16/4/28.
 */
public class NNVipData {

    //每种等级图片数量
    public static int[] VIP_LVL_ICO = {
            3, 4, 6
    };

    //每种等级标题
    public static String[] VIP_LVL_TIT = {
            "标准会员", "卖家会员", "买卖通会员"
    };
    //每种等级标题
    public static int[] VIP_LVL_TIT_COLS = {
            0XFFA16D2C, 0XFF2E5985, 0XFF7D2727
    };

    public static int[] VIP_LVL_COLS = {
            0XFFF5B452, 0XFF5581B5, 0XFFC74542
    };

    //每种等级需要的图片
    public static int[] VIP_IMG_ARR = {
            R.drawable.vip_1_company,
            R.drawable.vip_2_sale,
            R.drawable.vip_3_4s,
            R.drawable.vip_4_top,
            R.drawable.vip_5_generalize,
            R.drawable.vip_6_group
    };

    //每种等级需要的文案
    public static String[] VIP_DETAIL_ARR = {
            "公司认证",
            "自营车成本价",
            "4S名录",
            "资源置顶",
            "平台推广",
            "群发特权"
    };
}
