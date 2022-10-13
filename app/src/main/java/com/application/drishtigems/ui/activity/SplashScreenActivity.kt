package com.application.drishtigems.ui.base.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.application.drishtigems.R
import com.application.drishtigems.ui.activity.MainActivity

import com.application.drishtigems.ui.base.fragment.LoginScreen

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }, 2000)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //Hide Action Bar
        supportActionBar?.hide()
    }
}