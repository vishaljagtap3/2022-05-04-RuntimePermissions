package com.bitcode.runtimepermissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //checkSelfPermission(Manifest.permission.CALL_PHONE)
        var state = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        if(state == PackageManager.PERMISSION_GRANTED) {
            mt("Call Phone Available...")
        }
        else {
            //Request permission
            /*requestPermissions(
                arrayOf(
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                REQUEST_CODE
            )*/
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for( (index, permission) in permissions.withIndex()) {
            if(grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                mt("$permission is Granted")
            }
            else {
                mt("$permission is NOT Granted")
            }
        }
    }

    private fun mt(text : String) {
        Log.e("permissions", text)
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

}