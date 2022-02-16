package com.android;

import android.content.Context;
import android.os.SystemClock;

/**
 * 上电操作
 *
 * @author Lei
 */
public class Power {

    public static void powerOn(Context mContext, String deviceName) {
        switch (deviceName) {
            case "RM_LG43_HSL1":
                Devices.lg43(true, "on");
                break;
            case "RM_7088_MT2":
                Devices.rm7088(true, "-wdout94 1");
                break;
            case "RM_FOT80J_MT1":
                Devices.fot80J(1);
                break;
            case "RM_KT100_MT3":
                Devices.kt100(true);
                break;
            case "RM_HD05_MT1":
                Devices.hd05(true);
                break;
            case "RM_HY35_MT0":
                Devices.hy35(true);
                break;
            case "RM_XT07_sWK2":
            case "RM_XT07_HSL1":
                Devices.xt07(true, "1");
                break;
            case "RM_KT45_MT2":
                Devices.kt45(true, "-wdout64 1");
                break;
            case "RM_KT55G57_MT2":
                Devices.kt55(true, "-wdout 88 1", "5on", "7on");
                break;
            case "RM_KT55G67_MT2":
                Devices.kt55(true, "-wdout 88 1", "6on", "7on");
                break;
            case "RM_7095_MT2":
                Devices.rm7095(true, "-wdout 119 1");
                break;
            case "RM_XG_MT1":
                Devices.xg(true, "1");
                break;
            case "RM_ZD07_SAC2":
                Devices.zd07(true, "1");
                break;
            case "RM_JA04__MT1":
                Devices.ja04(true);
                break;
            case "RM_XTst_HSL1":
                Devices.xtSt(true, "1");
                break;
            case "RM_XTs917_MSM2":
                Devices.xts917(true, "1");
                break;
            case "RM_SD60_MT0":
                Devices.sd60(true, "9on", "14on");
                break;
            case "RM_IW80M_MT0":
                Devices.iw80M(true, mContext);
                SystemClock.sleep(360);
                break;
            case "RM_XTs9_HSL1":
                Devices.xts9(true, "1");
                break;
            case "RM_105U_sWK0":
                Devices.rm105U(true, "1");
                break;
            case "RM_SD50_MT0":
                Devices.sd50(true, "1");
                break;
            case "RM_SUPOINPDA_HSL1":
                Devices.supOinPoa(true, mContext);
                SystemClock.sleep(100);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + deviceName);
        }


    }

    public static void powerOff(Context mContext, String deviceName) {
        switch (deviceName) {
            case "RM_LG43_HSL1":
                Devices.lg43(false, "off");
                break;
            case "RM_7088_MT2":
                Devices.rm7088(false, "-wdout94 0");
                break;
            case "RM_FOT80J_MT1":
                Devices.fot80J(0);
                break;
            case "RM_KT100_MT3":
                Devices.kt100(false);
                break;
            case "RM_HD05_MT1":
                Devices.hd05(false);
                break;
            case "RM_HY35_MT0":
                Devices.hy35(false);
                break;
            case "RM_XT07_sWK2":
            case "RM_XT07_HSL1":
                Devices.xt07(false, "0");
                break;
            case "RM_KT45_MT2":
                Devices.kt45(false, "-wdout64 0");
                break;
            case "RM_KT55G57_MT2":
                Devices.kt55(false, "-wdout 88 0", "5off", "7off");
                break;
            case "RM_KT55G67_MT2":
                Devices.kt55(false, "-wdout 88 0", "6off", "7off");
                break;
            case "RM_7095_MT2":
                Devices.rm7095(false, "-wdout 119 0");
                break;
            case "RM_XG_MT1":
                Devices.xg(false, "0");
                break;
            case "RM_ZD07_SAC2":
                Devices.zd07(false, "0");
                break;
            case "RM_JA04__MT1":
                Devices.ja04(false);
                break;
            case "RM_XTst_HSL1":
                Devices.xtSt(false, "0");
                break;
            case "RM_XTs917_MSM2":
                Devices.xts917(false, "0");
                break;
            case "RM_SD60_MT0":
                Devices.sd60(false, "9off", "14off");
                break;
            case "RM_IW80M_MT0":
                Devices.iw80M(false, mContext);
                break;
            case "RM_XTs9_HSL1":
                Devices.xts9(false, "0");
                break;
            case "RM_105U_sWK0":
                Devices.rm105U(false, "0");
                break;
            case "RM_SD50_MT0":
                Devices.sd50(false, "0");
                break;
            case "RM_SUPOINPDA_HSL1":
                Devices.supOinPoa(false, mContext);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + deviceName);
        }
    }
}