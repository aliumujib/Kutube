package com.abdulmujibaliu.koutube.utils

import android.app.Activity
import com.irozon.sneaker.Sneaker

/**
 * Created by ABDUL-MUJEEB ALIU on 09/12/2017.
 */
class MessageUtils {

    companion object {
        fun errorMessage(activity: Activity, message: String) {
            Sneaker.with(activity)
                    .setTitle("Error!!")
                    .setMessage(message)
                    .sneakError()
        }

        fun successMessage(activity: Activity, message: String) {
            Sneaker.with(activity)
                    .setTitle("Success!!")
                    .setMessage(message)
                    .sneakSuccess()
        }

        fun warningMessage(activity: Activity, message: String) {
            Sneaker.with(activity)
                    .setTitle("Warning!!")
                    .setMessage(message)
                    .sneakWarning()
        }
    }

}