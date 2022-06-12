package com.example.samoapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.samoapp.R
import com.example.samoapp.Repository.DatabaseUtil
import com.example.samoapp.databinding.ActivityMainBinding
import com.example.samoapp.databinding.CustomBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSettings: ImageButton
    private lateinit var mRecycle: RecyclerView

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

        mRecycle = findViewById(R.id.main_recycleview)

        binding.floatingActionButton.setOnClickListener{showBottonSheetDialog()}
    }

    private fun showBottonSheetDialog() {
        val dialog = BottomSheetDialog(this)
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        val sheetBinding: CustomBottomSheetBinding =
            CustomBottomSheetBinding.inflate(layoutInflater, null, false)

        sheetBinding.btnDepositBottomsheetactivity.setOnClickListener{
            val it = Intent(this, RegisterDepositActivity::class.java)
            it.putExtra("userId", mUserId)
            startActivity(it)
        }

        dialog.setContentView(sheetBinding.root)
        dialog.show()
    }

    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            val taskDAO = DatabaseUtil
                .getInstance(applicationContext)
                .getTaskDAO()

            val userWithTasks = taskDAO.findByUserId(mUserId)

            //val taskAdapter = TaskAdapter(userWithTasks.tasks)

            //mRecycle.layoutManager = LinearLayoutManager(this@MainActivity)
            //mRecycle.adapter = taskAdapter

        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_mainactivity_settings -> {
                val it = Intent(this, SettingsActivity::class.java)
                startActivity(it)
            }
        }
    }
}

