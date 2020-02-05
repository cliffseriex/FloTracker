package com.aaif_seriex.flo.util;


import android.os.Build;
import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;

public class Config {
    public static final String URL = "https://www.personalityforge.com/api/chat/";
    public static final String apiKey = "6nt5d1nJHkqbkphe";
    public static final String chatBotID = "61622";
    public static final String externalID = "makkhay1";
    public String appUrl = "https://kumawoodapp.com";

    public String getAppUrl(){
        return this.appUrl;
    }

    public String getDeviceId() {

        /********* Get device unique ID using Pseudo ID method... works on phones and tablets********/
        String DevIDShort = "35" + //we make this look like a valid IMEI
                Build.BOARD + Build.BRAND +
                Build.CPU_ABI + Build.DEVICE +
                Build.DISPLAY + Build.HOST +
                Build.ID + Build.MANUFACTURER +
                Build.MODEL + Build.PRODUCT +
                Build.TAGS + Build.TYPE +
                Build.USER + Build.getRadioVersion() + Build.SERIAL; //13 digits
        DevIDShort = MD5(DevIDShort);
        Log.d("m_szDevIDShort: ", "m_szDevIDShort:   " + DevIDShort);
        return DevIDShort;

    }
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
                int count=is.read(bytes, 0, buffer_size);
                if(count==-1)
                    break;
                os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
}
