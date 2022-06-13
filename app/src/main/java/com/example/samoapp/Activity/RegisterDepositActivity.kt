package com.example.samoapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.samoapp.Entity.Task
import com.example.samoapp.R
import com.example.samoapp.Repository.DatabaseUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterDepositActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBack: ImageButton
    private lateinit var mRegisterDeposit: Button

    private lateinit var mTitle: EditText
    private lateinit var mDescription: EditText
    private lateinit var mValue: EditText

    private var mUserId = -1
    private var mTaskId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        supportActionBar?.hide();

        setContentView(R.layout.activity_register_deposit)

        mUserId = intent.getIntExtra("userId", -1)
        mTaskId = intent.getIntExtra("taskId", -1)

        mBack = findViewById(R.id.imageButton_registerdeposit_back)
        mBack.setOnClickListener(this)

        mRegisterDeposit = findViewById(R.id.button_registerexpense_registerdeposit)
        mRegisterDeposit.setOnClickListener(this)

        mTitle = findViewById(R.id.editText_registerdeposit_title)
        mDescription = findViewById(R.id.editText_registerdeposit_description)
        mValue = findViewById(R.id.editText_registerdeposit_value)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.imageButton_registerdeposit_back -> {
                val it = Intent(this, MainActivity::class.java)
                startActivity(it)
                finish()
            }
        }

        when (view?.id) {
            R.id.button_registerexpense_registerdeposit -> DoRegisterAction()
        }
    }

    private fun DoRegisterAction() {
        val title = mTitle.text.toString().trim()
        val descricao = mDescription.text.toString().trim()
        val type = "Depósito"
        val valorFloat = mValue.text.toString().toFloat() //valor da operação em float
        val valorString = "R$ " + mValue.text.toString().trim() //mostrar o valor em string no item da recycleview
        val saldo = mValue.text.toString().toFloat()

        var IsFormFilled = true

        IsFormFilled = IsTitleFilled(title) && IsFormFilled
        IsFormFilled = IsValorFilled(title) && IsFormFilled

        if (IsFormFilled) {
            val task = Task(
                    title = title,
                    description = descricao,
                    value = valorString,
                    type = type,
                    saldo = valorFloat,
                    userId = mUserId
            )

            GlobalScope.launch {
                val taskDAO = DatabaseUtil.getInstance(applicationContext).getTaskDAO()
                taskDAO.insert(task)
            }

            Toast.makeText(
                applicationContext,
                "Despesa $title cadastrada com sucesso",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    private fun IsValorFilled(valor: String): Boolean {
        return if (valor.isBlank()) {
            mValue.error = "Este campo é obrigatório!"
            false
        } else {
            true
        }
    }

    private fun IsTitleFilled(title: String): Boolean {
        return if (title.isBlank()) {
            mTitle.error = "Este campo é obrigatório!"
            false
        } else {
            true
        }
    }
}
