package com.example.samoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton

class ForgotPassActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportActionBar?.hide();

        setContentView(R.layout.activity_forgot_pass)

        mBack = findViewById(R.id.btn_forgotpassactivity_back)
        mBack.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_forgotpassactivity_back -> {
                val it = Intent(this, LoginActivity::class.java)
                startActivity(it)
                finish()
            }
        }
    }
}