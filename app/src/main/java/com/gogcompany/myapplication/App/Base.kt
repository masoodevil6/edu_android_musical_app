package com.gogcompany.myapplication.App

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import androidx.fragment.app.FragmentManager
import com.gogcompany.myapplication.DataBase.DBApp

@SuppressLint("StaticFieldLeak")
object Base:Application() {

    lateinit var activity: Activity;
    lateinit var activitySplash: Activity;
    lateinit var fragmentManager: FragmentManager;
    lateinit var dbApp: DBApp;
    lateinit var sharePref: SharePref;

    override fun onCreate() {
        super.onCreate();

        dbApp = DBApp.getInstance(activity);
    }


}