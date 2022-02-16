package com.android;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.BRMicro.SerialPort;
import com.android.dev.PSAMAPI;
import com.huayu.io.Platform;
import com.km.serial.GPIO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android_gpiocontrol_api.GpioControl;

/**
 * <pre>
 *     @author : SandWorlds
 *     e-mail : 18600395998@163.com
 *     time   : 2022/02/16
 *     desc   : 设备 上电方法
 *     version: 1.0
 * </pre>
 */
public class Devices {
    private static final String TAG = "Devices";
    private static final String KEY_RFID_POWER_ACTION = "android.intent.action.RfidPower";
    /**
     * 高通38 8.1系统上下电广播
     */
    public static final String ACTION_D38_BROADCAST = "android.intent.action.controlUHF";
    /**
     * 高通38 8.1系统广播字段
     */
    public static final String EXT_NAME_GAOTONG_D38 = "openUHF";
    public static final int EXT_POWER_UP_GAOTONG_D38 = 1;
    public static final int EXT_POWER_DOWN_GAOTONG_D38 = 0;

    /**
     * LG43-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param cmd       指令
     */
    public static void lg43(boolean isPowerUp, String cmd) {
        writeFile("LG43", isPowerUp, "/sys/class/gpio_switch/usb_dc_en", cmd);
        writeFile("LG43", isPowerUp, "/sys/class/gpio_switch/gpio_fucn2", cmd);
    }

    /**
     * RM_7088-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param cmd       指令
     */
    public static void rm7088(boolean isPowerUp, String cmd) {
        writeFile("RM_7088", isPowerUp, "/sys/class/misc/mtgpio/pin", cmd);
    }

    /**
     * RM_FOT80J-设备上下电方法
     *
     * @param cmd 指令
     */
    public static void fot80J(int cmd) {
        GPIO.setGpio(24, cmd);
        GPIO.setGpio(6, cmd);
        GPIO.setGpio(7, cmd);
    }

    /**
     * RM_KT100-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     */
    public static void kt100(boolean isPowerUp) {
        try {
            GpioControl mGpioControl = new GpioControl();
            if (isPowerUp) {
                mGpioControl.openGpio();
            } else {
                mGpioControl.closeGpio();
            }
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * RM_HD05-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     */
    public static void hd05(boolean isPowerUp) {
        try {
            SerialPort mSerialPort = new SerialPort();
            if (isPowerUp) {
                mSerialPort.zigbeepoweron();
                mSerialPort.rfidPoweron();
                mSerialPort.switch2Channel(13);
            } else {
                mSerialPort.zigbeepoweroff();
                mSerialPort.rfidPoweroff();
            }
        } catch (ExceptionInInitializerError | NoClassDefFoundError e) {
            if (isPowerUp) {
                Log.d(TAG, "RM_HD05" + "-上电异常:" + e.getMessage());
            } else {
                Log.d(TAG, "RM_HD05" + "-断电异常:" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    /**
     * RM_HY35-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     */
    public static void hy35(boolean isPowerUp) {
        try {
            if (isPowerUp) {
                Platform.initIO();
                Platform.openRfidPower();
            } else {
                Platform.closeRfidPower();
            }
        } catch (ExceptionInInitializerError | NoClassDefFoundError e) {
            if (isPowerUp) {
                Log.d(TAG, "RM_HY35" + "-上电异常:" + e.getMessage());
            } else {
                Log.d(TAG, "RM_HY35" + "-断电异常:" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    /**
     * RM_XT07-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param cmd       指令
     */
    public static void xt07(boolean isPowerUp, String cmd) {
        writeFile("RM_XT07", isPowerUp, "/sys/devices/soc.0/xt_dev.68/xt_dc_in_en", cmd);
        writeFile("RM_XT07", isPowerUp, "/sys/devices/soc.0/xt_dev.68/xt_vbat_out_en", cmd);
        writeFile("RM_XT07", isPowerUp, "/sys/devices/soc.0/xt_dev.68/xt_gpio_112", "0");
    }

    /**
     * RM_KT45-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param cmd       指令
     */
    public static void kt45(boolean isPowerUp, String cmd) {
        writeFile("RM_KT45", isPowerUp, "/sys/class/misc/mtgpio/pin", cmd);
    }


    /**
     * RM_KT55-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param pinCmd    指令
     * @param gpio1Cmd  指令
     * @param gpio2Cmd  指令
     */
    public static void kt55(boolean isPowerUp, String pinCmd, String gpio1Cmd, String gpio2Cmd) {
        writeFile("RM_KT55", isPowerUp, "/sys/class/misc/mtgpio/pin", pinCmd);
        writeFile("RM_KT55", isPowerUp, "/sys/class/misc/aw9523/gpio", gpio1Cmd);
        writeFile("RM_KT55", isPowerUp, "/sys/class/misc/aw9523/gpio", gpio2Cmd);
    }

    /**
     * RM_7095-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param cmd       指令
     */
    public static void rm7095(boolean isPowerUp, String cmd) {
        writeFile("RM_7095", isPowerUp, "/sys/class/misc/mtgpio/pin", cmd);
    }


    /**
     * RM_XG-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param cmd       指令
     */
    public static void xg(boolean isPowerUp, String cmd) {
        writeFile("RM_XG", isPowerUp, "/sys/devices/virtual/misc/mtgpio/pin", "-wdout99 " + cmd);
        writeFile("RM_XG", isPowerUp, "/sys/devices/virtual/misc/mtgpio/pin", "-wdout121 " + cmd);
        writeFile("RM_XG", isPowerUp, "/sys/devices/virtual/misc/mtgpio/pin", "-wdout122 " + cmd);
    }

    /**
     * RM_ZD07-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param cmd       指令
     */
    public static void zd07(boolean isPowerUp, String cmd) {
        writeFile("RM_ZD07", isPowerUp, "/sys/devices/platform/c110sysfs/rfid_power", cmd);
    }


    /**
     * RM_JA04-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     */
    public static void ja04(boolean isPowerUp) {
        if (isPowerUp) {
            PSAMAPI.PsamPowerOn();
        } else {
            PSAMAPI.PsamPowerOff();
        }
    }

    /**
     * RM_XTst-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param cmd       指令
     */
    public static void xtSt(boolean isPowerUp, String cmd) {
        writeFile("RM_XTst", isPowerUp, "/sys/devices/soc.0/xt_dev.68/xt_dc_in_en", cmd);
        writeFile("RM_XTst", isPowerUp, "/sys/devices/soc.0/xt_dev.68/xt_vbat_out_en", cmd);
        writeFile("RM_XTst", isPowerUp, "/sys/devices/soc.0/xt_dev.68/xt_uart_b", cmd);
        writeFile("RM_XTst", isPowerUp, "/sys/devices/soc.0/xt_dev.68/xt_gpio_112", "0");
        writeFile("RM_XTst", isPowerUp, "/sys/devices/soc.0/xt_dev.68/xt_uart_a", "0");
    }

    /**
     * RM_XTs917-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param cmd       指令
     */
    public static void xts917(boolean isPowerUp, String cmd) {
        writeFile("RM_XTs917", isPowerUp, "/sys/devices/soc/soc:xt_dev/xt_usb_dcdc_2A5_en", cmd);
        writeFile("RM_XTs917", isPowerUp, "/sys/devices/soc/soc:xt_dev/xt_gpio_bb2", cmd);
        writeFile("RM_XTs917", isPowerUp, "/sys/devices/soc/soc:xt_dev/xt_gpio_bb3", cmd);
        writeFile("RM_XTs917", isPowerUp, "/sys/devices/soc/soc:xt_dev/xt_ldo_bb_en", cmd);
        writeFile("RM_XTs917", isPowerUp, "/sys/devices/soc/soc:xt_dev/xt_switch_vbat_1A5_en", cmd);
    }

    /**
     * RM_SD60-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param gpio1Cmd  指令
     * @param gpio2Cmd  指令
     */
    public static void sd60(boolean isPowerUp, String gpio1Cmd, String gpio2Cmd) {
        writeFile("RM_SD60", isPowerUp, "/sys/class/misc/aw9523/gpio", gpio1Cmd);
        writeFile("RM_SD60", isPowerUp, "/sys/class/misc/aw9523/gpio", gpio2Cmd);
    }

    /**
     * RM_IW80M-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     */
    public static void iw80M(boolean isPowerUp, Context mContext) {
        Intent intent = new Intent(KEY_RFID_POWER_ACTION);
        intent.putExtra(KEY_RFID_POWER_ACTION, isPowerUp);
        mContext.sendBroadcast(intent);
    }


    /**
     * RM_XTs9-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param cmd       指令
     */
    public static void xts9(boolean isPowerUp, String cmd) {
        writeFile("RM_XTs9", isPowerUp, "/sys/cgp_ctrl/cgp_on", cmd);
        writeFile("RM_XTs9", isPowerUp, "/sys/cgp_ctrl/cgp_switch_vbat", cmd);
        writeFile("RM_XTs9", isPowerUp, "/sys/cgp_ctrl/cgp_uart_switch", "0");
        writeFile("RM_XTs9", isPowerUp, "/sys/cgp_ctrl/cgp_vbus_5v", cmd);
    }

    /**
     * RM_105U-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param cmd       指令
     */
    public static void rm105U(boolean isPowerUp, String cmd) {
        writeFile("RM_105U", isPowerUp, "/sys/devices/platform/odm/odm:exp_dev/gps_com_switch", cmd);
        writeFile("RM_105U", isPowerUp, "/sys/bus/platform/devices/odm:exp_dev/psam_en", cmd);
    }

    /**
     * RM_SD50-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     * @param cmd       指令
     */
    public static void sd50(boolean isPowerUp, String cmd) {
        if (isPowerUp) {
            writeFile("RM_SD50", true, "/sys/bus/platform/drivers/mediatek-pinctrl/10005000.pinctrl/mt_gpio", "mode75 0");
        }
        writeFile("RM_SD50", isPowerUp, "/sys/bus/platform/drivers/mediatek-pinctrl/10005000.pinctrl/mt_gpio", "out75 " + cmd);
    }


    /**
     * RM_SUPOINPDA-设备上下电方法
     *
     * @param isPowerUp 是否是上电
     */
    public static void supOinPoa(boolean isPowerUp, Context mContext) {
        //发广播上电
        if (isPowerUp) {
            mContext.sendBroadcast(new Intent(ACTION_D38_BROADCAST).putExtra(EXT_NAME_GAOTONG_D38,
                    EXT_POWER_UP_GAOTONG_D38));
        } else {
            mContext.sendBroadcast(new Intent(ACTION_D38_BROADCAST).putExtra(EXT_NAME_GAOTONG_D38,
                    EXT_POWER_DOWN_GAOTONG_D38));
        }
    }

    private static void writeFile(String deviceName, boolean isPowerUp, String path, String value) {
        File deviceCmd = new File(path);
        if (deviceCmd.exists()) {
            BufferedWriter ctrlFile;
            try {
                ctrlFile = new BufferedWriter(new FileWriter(deviceCmd, false));
                ctrlFile.write(value);
                ctrlFile.flush();
                ctrlFile.close();
            } catch (IOException e) {
                if (isPowerUp) {
                    Log.d(TAG, deviceName + "-上电IO异常:" + e.getMessage());
                } else {
                    Log.d(TAG, deviceName + "-断电IO异常:" + e.getMessage());
                }
                e.printStackTrace();
            }

        }
    }
}
