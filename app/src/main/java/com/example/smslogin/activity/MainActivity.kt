package com.example.smslogin.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.telephony.SmsManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.smslogin.R
import com.example.smslogin.utils.MyPreferences

class MainActivity : AppCompatActivity() {

    val PERMISSION_REQUEST=100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        object : CountDownTimer(3000,1000){
            override fun onFinish() {
                checkPermission()
            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }.start()
    }

    private fun checkPermission(){
        if( ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED){
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                requestPermissions(
                    arrayOf(Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS), PERMISSION_REQUEST)
            }
        }
        else{
            proceedFurther()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_REQUEST->
                if(grantResults.isNotEmpty()){
                    val sendSmsPermission=grantResults[0]==PackageManager.PERMISSION_GRANTED
                    val readSmsPermission=grantResults[0]==PackageManager.PERMISSION_GRANTED
                    val receiveSmsPermission=grantResults[0]==PackageManager.PERMISSION_GRANTED

                    if(sendSmsPermission && readSmsPermission && receiveSmsPermission){
                        proceedFurther()
                    }
                    else{
                        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                            requestPermissions(
                                arrayOf(Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS), PERMISSION_REQUEST)
                        }
                    }
                }
        }
    }

    private fun proceedFurther(){
        //not logged in
        if(!MyPreferences.getInstance(applicationContext).isLoggedIn){
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this,HomePageActivity::class.java))
            finish()
        }
    }

}