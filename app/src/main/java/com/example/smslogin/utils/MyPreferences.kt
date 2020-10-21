package com.example.smslogin.utils

import android.content.Context
import android.content.SharedPreferences

class MyPreferences(context: Context) {
    private var sharedPreferences:SharedPreferences = context.getSharedPreferences("OtpLogin", Context.MODE_PRIVATE)

    var isLoggedIn:Boolean
        get()=sharedPreferences!!.getBoolean("isLoggedIn",false) ?: false
        set(value) {
            val prefeditor=sharedPreferences!!.edit()
            prefeditor.putBoolean("isLoggedIn",value)
            prefeditor.apply()
        }

    companion object{
        private var myPreferences: MyPreferences?=null
        val TAG="Preferences"
        fun getInstance(context: Context): MyPreferences {
            if(myPreferences ==null){
                myPreferences = MyPreferences(context)
            }
            return myPreferences!!
        }

    }
}