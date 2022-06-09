package com.example.samoapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.samoapp.R
import com.example.samoapp.Repository.DatabaseUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mRegisterAcc: TextView
    private lateinit var mForgotpass: TextView

    private lateinit var mLoginEmail: EditText
    private lateinit var mLoginPass: EditText

    private lateinit var mLoginExec: Button

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

        mLoginExec = findViewById(R.id.btn_login_action)
        mLoginExec.setOnClickListener(this)
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

            R.id.btn_login_action -> ExecLogin()
        }
    }

    private fun ExecLogin() {
        val email = mLoginEmail.text.toString()
        val password = mLoginPass.text.toString()

        var isFormFilled = true

        if(email.isBlank()){
            mLoginEmail.error = "Campo obrigatório"
            isFormFilled = false
        }

        if(password.isBlank()){
            mLoginPass.error = "Campo obrigatório"
            isFormFilled = false
        }

        if(isFormFilled){
            GlobalScope.launch {
                val userDAO = DatabaseUtil.getInstance(applicationContext).getUserDAO()
                val user = userDAO.findByEmail(email)
                if(user != null){
                    if(user.password == password){
                        val it = Intent(applicationContext, MainActivity::class.java)
                        it.putExtra("userId", user.id)
                        startActivity(it)
                        finish()
                    }
                }
            }
        }
    }
}