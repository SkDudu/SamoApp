package com.example.samoapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.samoapp.R

class SettingsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBack: ImageButton
    private lateinit var mLogoff: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportActionBar?.hide();

        setContentView(R.layout.activity_settings)

        mBack = findViewById(R.id.btn_settingsactivity_back)
        mBack.setOnClickListener(this)

        mLogoff = findViewById(R.id.btn_logoff_action)
        mLogoff.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_settingsactivity_back -> {
                val it = Intent(this, MainActivity::class.java)
                startActivity(it)
                finish()
            }
        }

        when(view?.id){
            R.id.btn_logoff_action -> {
                val it = Intent(this, LoginActivity::class.java)
                startActivity(it)
                finish()
            }
        }
    }
}