package com.example.samoapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.samoapp.Entity.User
import com.example.samoapp.R
import com.example.samoapp.Repository.DatabaseUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterAccount : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBack: ImageButton
    private lateinit var mRegisterName: EditText
    private lateinit var mRegisterEmail: EditText
    private lateinit var mRegisterPassword: EditText
    private lateinit var mRegisterSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportActionBar?.hide();

        setContentView(R.layout.activity_register_account)

        mBack = findViewById(R.id.btn_settingsactivity_back)
        mBack.setOnClickListener(this)

        mRegisterName = findViewById(R.id.editText_registeraccountactivity_name)
        mRegisterName.setOnClickListener(this)

        mRegisterEmail = findViewById(R.id.editText_registeraccountactivity_email)
        mRegisterEmail.setOnClickListener(this)

        mRegisterPassword = findViewById(R.id.editText_registeraccountactivity_password)
        mRegisterPassword.setOnClickListener(this)

        mRegisterSave = findViewById(R.id.btn_registeraccount_registeraccount)
        mRegisterSave.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_settingsactivity_back -> {
                val it = Intent(this, LoginActivity::class.java)
                startActivity(it)
                finish()
            }
        }
        when (view?.id) {
            R.id.btn_registeraccount_registeraccount -> doSaveAction()
        }
    }

    private fun doSaveAction() {
        val name = mRegisterName.text.toString().trim()
        val email = mRegisterEmail.text.toString().trim()
        val password = mRegisterPassword.text.toString().trim()

        var IsFormFilled = true

        IsFormFilled = IsNameFilled(name) && IsFormFilled
        IsFormFilled = IsEmailFilled(email) && IsFormFilled
        IsFormFilled = IsPasswordFilled(password) && IsFormFilled

        if (IsFormFilled) {
            val user = User(
                name = name,
                email = email,
                password = password
            )

            GlobalScope.launch {
                val userDAO = DatabaseUtil.getInstance(applicationContext).getUserDAO()
                userDAO.insert(user)
            }
            Toast.makeText(
                applicationContext,
                "Usuário $name cadastrado com sucesso!",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    private fun IsNameFilled(name: CharSequence): Boolean {
        return if (name.isBlank()) {
            mRegisterName.error = "Este campo é obrigatório!"
            false
        } else {
            true
        }
    }

    private fun IsEmailFilled(email: CharSequence): Boolean {
        return if (email.isBlank()) {
            mRegisterEmail.error = "Este campo é obrigatório!"
            false
        } else {
            true
        }
    }

    private fun IsPasswordFilled(password: CharSequence): Boolean {
        return if (password.isBlank()) {
            mRegisterPassword.error = "Este campo é obrigatório!"
            false
        } else {
            true
        }
    }
}