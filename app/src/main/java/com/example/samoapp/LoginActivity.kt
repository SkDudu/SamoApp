package com.example.samoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mRegisterAcc: TextView
    private lateinit var mForgotpass: TextView

    private lateinit var mLoginEmail: EditText
    private lateinit var mLoginPass: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportActionBar?.hide();

        setContentView(R.layout.activity_login)

        mLoginEmail = findViewById(R.id.editText_loginactivity_email)
        mLoginPass = findViewById(R.id.editText_loginactivity_password)

        mForgotpass = findViewById(R.id.textView_loginactivity_forgotPass)
        mForgotpass.setOnClickListener(this)

        mRegisterAcc = findViewById(R.id.textView_loginactivity_registerAccount)
        mRegisterAcc.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view?.id){
            R.id.textView_loginactivity_registerAccount ->{
                val it = Intent(this, RegisterAccount::class.java)
                startActivity(it)
            }
            R.id.textView_loginactivity_forgotPass -> {
                val it =Intent(this, ForgotPassActivity::class.java)
                startActivity(it)
            }
        }
    }
}