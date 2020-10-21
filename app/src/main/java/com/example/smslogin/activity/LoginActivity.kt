package com.example.smslogin.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import com.example.smslogin.R
import com.example.smslogin.utils.ProjectUtils
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.Credentials
import com.google.android.gms.auth.api.credentials.HintRequest
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    lateinit var etMobileNumber: AppCompatEditText
    lateinit var btnSendOtp: AppCompatButton
    lateinit var appCompatSpinner: AppCompatSpinner
    lateinit var progressBar: ProgressBar

    var otp=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindView()
        setListener()
    }

    fun bindView(){
        etMobileNumber= findViewById<AppCompatEditText>(R.id.etMobileNumber)
        appCompatSpinner= findViewById<AppCompatSpinner>(R.id.appCompatSpinner)
        btnSendOtp= findViewById<AppCompatButton>(R.id.btnSendOtp)
        progressBar= findViewById<ProgressBar>(R.id.progressBar)

        var spnList=ArrayList<String>()
        spnList.add("(+91) India")
        spnList.add("(+1) USA")
        spnList.add("(+62) Indonesia")
        spnList.add("(+55) Brazil")

        var spnAdapter=ArrayAdapter<String>(this, R.layout.spinner_item, spnList)
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        appCompatSpinner.adapter=spnAdapter

    }

    private fun setListener(){
        btnSendOtp.setOnClickListener {
            var mobileNumber=etMobileNumber.text.toString()
            if(mobileNumber.length==10 && ProjectUtils.isPhoneNumberValid(etMobileNumber.text.toString())){
                sendSms(mobileNumber)
            }
            else{
                etMobileNumber.error="Invalid mobile number"
                etMobileNumber.requestFocus()
            }
        }
    }

    private fun sendSms(mobileNumber:String){
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(
                "+91$mobileNumber", null, "${generateOtp()} is the otp for authentication.", null, null
            )
            println("message sent")
            var intent=Intent(this,OtpActivity::class.java)
            intent.putExtra("mobileNumber",mobileNumber)
            intent.putExtra("otp",otp)
            startActivity(intent)

        } catch (e: Exception) {
            println("sending failed!")
            Toast.makeText(this,"Try again..",Toast.LENGTH_SHORT)
            e.printStackTrace()
        }
    }

    private fun generateOtp():String{
         otp=((Math.random()*90000).toInt() + 10000).toString()
        println(otp)
        return otp.toString()
    }
}