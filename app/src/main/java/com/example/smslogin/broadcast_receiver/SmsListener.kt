package com.example.smslogin.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import com.example.smslogin.activity.OtpActivity
import com.example.smslogin.utils.ProjectUtils


class SmsListener: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        println("message received")
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent!!.action) {
            println("intent fulfilled")
            val bundle = intent.extras
            try {
                if (bundle != null) {
                    val pdusObj =
                        bundle["pdus"] as Array<Any>?
                    for (i in pdusObj!!.indices) {
                        val currentMessage: SmsMessage = SmsMessage.createFromPdu(pdusObj[i] as ByteArray)
                        val phoneNumber: String =
                            currentMessage.displayOriginatingAddress
                        val message: String = currentMessage.displayMessageBody
                        val mobile = phoneNumber.replace("\\s".toRegex(), "")
                        val body = message.replace("\\s".toRegex(), " ")
                        var otp=body.split(' ')[0]
                        println(otp)
                       (ProjectUtils.getContext() as OtpActivity).onOtpReceived(otp)

                    }
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}