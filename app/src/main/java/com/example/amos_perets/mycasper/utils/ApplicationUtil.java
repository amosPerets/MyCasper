package com.example.amos_perets.mycasper.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ApplicationUtil extends Application {

    private static ApplicationUtil applicationUtil;
    private static Context context;

    public void onCreate() {
        super.onCreate();
        ApplicationUtil.context = getApplicationContext();
        getInstance();
    }

    private static ApplicationUtil getInstance(){

        if(applicationUtil == null){
            applicationUtil = new ApplicationUtil();
        }

        return applicationUtil;
    }

    public static Context getAppContext(){
        return context;
    }

    public static void closeAppNow(String msgLog){
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Log.v("***ERROR***", msgLog);
        getAppContext().startActivity(homeIntent);
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    public static void showToast(String msg){
        Toast.makeText(getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
