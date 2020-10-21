package com.example.smslogin.utils

import android.content.Context
import android.widget.EditText
import java.util.regex.Pattern

object ProjectUtils {

    lateinit var mContext:Context
    /**
     * checks if the given phone number is valid or not
     * @param number : mobile number
     * @return true if the number is valid else false
     */
    fun isPhoneNumberValid(number: String): Boolean {
        val  regexStr= "^((0)|(91)|(00)|[7-9]){1}[0-9]{3,14}$"
        val pattern = Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(number)
        return !(number.length < 10 || number.length > 11 || !matcher.matches())
    }

    /**
     * checks if any text box is null or not
     * @param text : view for which validation is to be checked
     * @return true if not null
     */
    fun isEditTextFilled(text: EditText): Boolean {
        return text.text != null && text.text.toString().trim { it <= ' ' }.isNotEmpty()
    }

    fun setContext(context:Context){
        mContext=context
    }

    fun getContext():Context{
        return mContext
    }
}