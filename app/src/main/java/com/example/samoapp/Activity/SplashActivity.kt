package com.example.samoapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import com.example.samoapp.R
import com.example.samoapp.Repository.DatabaseUtil

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportActionBar?.hide();

        setContentView(R.layout.activity_splash)

        val handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({
            val it = Intent(SplashActivity@this, LoginActivity::class.java)
            startActivity(it)
            finish()
        }, 3000L)

        DatabaseUtil.getInstance(applicationContext)

    }
}