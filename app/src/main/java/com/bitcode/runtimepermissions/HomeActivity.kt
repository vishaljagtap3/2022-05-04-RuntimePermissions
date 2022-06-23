package com.bitcode.runtimepermissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bitcode.runtimepermissions.databinding.HomeActivityBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding
    private val REQUEST_CODE_CALL_PHONE = 1
    private val REQUEST_CODE_READ_EXTERNAL_STORAGE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpListeners()
    }

    private fun setUpListeners() {

        binding.btnMakeCall.setOnClickListener {
            if(Util.checkPermissionState(this, Manifest.permission.CALL_PHONE)) {
                initCall()
            }
            else {
                Util.requestPermission(
                    this,
                    Manifest.permission.CALL_PHONE,
                    REQUEST_CODE_CALL_PHONE
                )
            }
        }

        binding.btnBackupStorage.setOnClickListener {
            if(Util.checkPermissionState(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                backUpData()
            }
            else {
                Util.requestPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    REQUEST_CODE_READ_EXTERNAL_STORAGE
                )
            }
        }

    }

    private fun backUpData() {
        mt("Backing up data...")
    }

    private fun actionNoCallPermission() {
        mt("Can not user support facility! No permissions granted...")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {

            REQUEST_CODE_CALL_PHONE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initCall()
                } else {
                    actionNoCallPermission()
                }
            }

            REQUEST_CODE_READ_EXTERNAL_STORAGE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    backUpData()
                } else {
                    actionNoStoragePermission()
                }
            }
        }
    }

    private fun actionNoStoragePermission() {
        mt("Can not backup data! No permission!")
    }


    private fun initCall() {
        mt("Calling BitCode....")
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMakeCall.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                mt("Calling BitCode")
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    REQUEST_CODE_CALL_PHONE
                )
            }
        }

        binding.btnBackupStorage.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                mt("Backing up data...")
            }
            else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_CODE_READ_EXTERNAL_STORAGE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {

            REQUEST_CODE_CALL_PHONE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mt("Calling BitCode...")
                } else {
                    mt("You will not get support facility!")
                }
            }

            REQUEST_CODE_READ_EXTERNAL_STORAGE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mt("Backing up data...")
                } else {
                    mt("Can not backup data! Please go to settings and enable permission!")
                }
            }
        }
    }*/

    private fun mt(text: String) {
        Log.e("tag", text)
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}