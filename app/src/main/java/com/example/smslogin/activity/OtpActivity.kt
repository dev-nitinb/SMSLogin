package com.example.smslogin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.example.smslogin.R
import com.example.smslogin.utils.MyPreferences
import com.example.smslogin.utils.ProjectUtils

class OtpActivity : AppCompatActivity() {
    lateinit var et1:AppCompatEditText
    lateinit var et2: AppCompatEditText
    lateinit var et3:AppCompatEditText
    lateinit var et4:AppCompatEditText
    lateinit var et5:AppCompatEditText
    lateinit var btnVerifyOtp:AppCompatButton
    lateinit var tvResend:AppCompatTextView

    lateinit var otpReceived:String
    lateinit var mobileNumber:String
    var TAG="OtpActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        mobileNumber=intent.getStringExtra("mobileNumber")
        otpReceived=intent.getStringExtra("otp")
        Log.e(TAG,"mobile $mobileNumber otp $otpReceived")

        bindView()
        setupListeners()
    }

    fun bindView(){
        et1= findViewById<AppCompatEditText>(R.id.et1)
        et2= findViewById<AppCompatEditText>(R.id.et2)
        et3= findViewById<AppCompatEditText>(R.id.et3)
        et4= findViewById<AppCompatEditText>(R.id.et4)
        et5= findViewById<AppCompatEditText>(R.id.et5)
        btnVerifyOtp= findViewById<AppCompatButton>(R.id.btnVerifyOtp)
        tvResend= findViewById<AppCompatTextView>(R.id.tvResend)

        var text="<font color=#FFFFFF>OTP auto send in </font><font color=#B74E39><bold>29</bold></font><font color=#FFFFFF> sec</font>"
        tvResend.text=Html.fromHtml(text)
        ProjectUtils.setContext(this)
    }

    private fun setupListeners(){
        
        et1.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                et1.setText("")
            }
        }
        et2.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                et2.setText("")
            }
        }
        et3.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                et3.setText("")
            }
        }
        et4.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                et4.setText("")
            }
        }
        et5.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                et5.setText("")
            }
        }

        et1.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(et1.length()==1)
                {
                    et2.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        et2.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(et2.length()==1)
                {
                    et3.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        et3.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(et3.length()==1)
                {
                    et4.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        et4.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(et4.length()==1)
                {
                    et5.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        et5.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(et5.length()==1)
                {
                    val enteredOtp=et1.text.toString()+et2.text.toString()+et3.text.toString()+et4.text.toString()+et5.text.toString()
                    Log.d("OTP",enteredOtp)
                    validateOtp(enteredOtp)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    private fun validateOtp(enteredOtp:String){
        if(enteredOtp == otpReceived){
            Toast.makeText(this@OtpActivity,"OTP Verified successfully..",Toast.LENGTH_SHORT).show()
            var intent =Intent(this,HomePageActivity::class.java)
            MyPreferences.getInstance(applicationContext).isLoggedIn=true
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }else{
            Toast.makeText(this,"Incorrect Password", Toast.LENGTH_SHORT).show()
            et1.setText("")
            et2.setText("")
            et3.setText("")
            et4.setText("")
            et5.setText("")
            et1.requestFocus()
        }
    }

    fun onOtpReceived(otp:String){
        println("onOtpReceived")
        et1.setText(otp[0].toString())
        et2.setText(otp[1].toString())
        et3.setText(otp[2].toString())
        et4.setText(otp[3].toString())
        et5.setText(otp[4].toString())
    }

}