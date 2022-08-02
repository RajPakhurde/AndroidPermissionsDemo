package com.example.persmissiondemo

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var btnTakeper = findViewById<Button>(R.id.btnTakeper)
        btnTakeper.setOnClickListener {
            requestpermission()
        }
    }
    private fun hasReadExternalStorage() =
        ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED

    private fun requestpermission(){
        var permissionToRequest = mutableListOf<String>()

        if(!hasReadExternalStorage()){
            permissionToRequest.add(Manifest.permission.READ_PHONE_NUMBERS)
        }
        if(permissionToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this,permissionToRequest.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==0 && grantResults.isNotEmpty()){
            for(i in grantResults.indices){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.i("SecondActivity","${permissions[i]} granted...")
                }
            }
        }
    }

}