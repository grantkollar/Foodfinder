package app.foodfinderapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import app.foodfinderapp.util.ConfigData
import com.google.firebase.auth.FirebaseAuth

//import com.baidu.mapapi.CoordType
//import com.baidu.mapapi.SDKInitializer


class Application: Application() {


    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        FirebaseAuth.getInstance()

        if (ConfigData.getIsNight()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }


        //SDK interface
        //SDKInitializer.initialize(context);

        //SDKInitializer.setCoordType(CoordType.BD09LL);
    }

}