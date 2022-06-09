package com.example.samoapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.samoapp.R
import com.example.samoapp.databinding.ActivityMainBinding
import com.example.samoapp.databinding.CustomBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSettings: ImageButton
    private lateinit var mFAB: FloatingActionButton

    private lateinit var binding: ActivityMainBinding

    private var mUserId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        supportActionBar?.hide();

        setContentView(binding.root)

        mSettings = findViewById(R.id.btn_mainactivity_settings)
        mSettings.setOnClickListener(this)

        binding.floatingActionButton.setOnClickListener{showBottonSheetDialog()}
    }

    private fun showBottonSheetDialog() {
        val dialog = BottomSheetDialog(this)

        val sheetBinding: CustomBottomSheetBinding =
            CustomBottomSheetBinding.inflate(layoutInflater, null, false)

        dialog.setContentView(sheetBinding.root)
        dialog.show()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_mainactivity_settings -> {
                val it = Intent(this, SettingsActivity::class.java)
                startActivity(it)
            }
        }

        when (view?.id) {
            R.id.floatingActionButton -> {

            }
        }
    }
}

