package com.application.drishtigems.utils

import android.util.Patterns
import java.math.BigInteger

object CommonUtils {
    fun isValidEmail(text: String):Boolean{
      return Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }
    fun isValidPhone(text: BigInteger):Boolean{
        return Patterns.PHONE.matcher(text.toString()).matches()
    }
}
