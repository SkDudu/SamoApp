package com.example.samoapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.samoapp.Entity.Task
import com.example.samoapp.R
import com.example.samoapp.Repository.DatabaseUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterDepositActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBack: ImageButton
    private lateinit var mCancel: TextView
    private lateinit var mRegisterDeposit: Button

    private lateinit var mTitle: EditText
    private lateinit var mDescription: EditText
    private lateinit var mValue: EditText

    private var mUserId = -1
    private var mTaskId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val valor = mValue.text.toString().trim()


        if (mTaskId != -1) {
            //atualizar a task.
        } else {
            GlobalScope.launch {
                val taskDAO = DatabaseUtil.getInstance(applicationContext).getTaskDAO()
                val task =
                    Task(
                        title = title,
                        description = descricao,
                        value = valor,
                        userId = mUserId)
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

    override fun onResume(){
        super.onResume()

        if(mTaskId != -1){
            //atualizar task existente.
            mRegisterDeposit.text = "Atualizar despesa"
        }else{
            //cadastrar task.
            mRegisterDeposit.text = "Cadastrar despesa"
        }
    }
}