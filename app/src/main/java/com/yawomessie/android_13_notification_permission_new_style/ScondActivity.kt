package com.yawomessie.android_13_notification_permission_new_style

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.yawomessie.android_13_notification_permission_new_style.databinding.ActivityScondBinding

class ScondActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScondBinding
    private lateinit var requestLauncher: ActivityResultLauncher<String>

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                //main activity
                startActivity(
                    Intent(
                    this@ScondActivity,
                    MainActivity::class.java
                )
                )
            }
            else {
                //show error message
                showErrorMessage()
            }
        }

        binding.okayBtn.setOnClickListener {
            askForNotificationPermission()
        }
        binding.skipBtn.setOnClickListener {
            showErrorMessage()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun askForNotificationPermission() {
        requestLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }

    private fun showErrorMessage() {
        Toast.makeText(
            this,
            "Permission is not granted",
            Toast.LENGTH_SHORT
        ).show()
    }
}