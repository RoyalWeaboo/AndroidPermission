package com.malikazizali.androidpermission

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.telephony.SmsManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.malikazizali.androidpermission.databinding.ActivityCameraAndGaleryBinding

class CameraAndGalery : AppCompatActivity() {
    lateinit var binding : ActivityCameraAndGaleryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraAndGaleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTakePic.setOnClickListener {
            val permissionCheck = checkSelfPermission(Manifest.permission.CAMERA)

            if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            }else{
                requestCameraPermission()
            }
        }
    }

    fun openCamera(){
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 200 && data != null){
            binding.iv.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestCameraPermission() {
        requestPermissions(arrayOf(Manifest.permission.CAMERA), 201)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            201 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    permissions[0] == Manifest.permission.CAMERA
                ) {
                    Toast.makeText(this, "Permissions Permitted", Toast.LENGTH_LONG)
                        .show()

                } else {
                    Toast.makeText(this, "Permissions Denied", Toast.LENGTH_LONG)
                        .show()
                }
            }
            else -> {
                Toast.makeText(this, "The request code doesn't match", Toast.LENGTH_LONG).show()
            }
        }
    }
}