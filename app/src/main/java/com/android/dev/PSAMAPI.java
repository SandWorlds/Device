package com.android.dev;

public class PSAMAPI
{
    static {
        System.loadLibrary("PsamDev"); 
    }
    
   // public static String PORT="/dev/ttyMT1";
    //public static int RATE=115200;
 
    
    public static native int PsamPowerOn();
    public static native int PsamPowerOff();
}