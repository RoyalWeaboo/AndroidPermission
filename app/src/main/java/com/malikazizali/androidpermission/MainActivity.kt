package com.malikazizali.androidpermission

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.malikazizali.androidpermission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.btnView.setOnClickListener {
            Glide.with(this)
                .load("https://i.ibb.co/zJHYGBP/binarlogo.jpg")
                .circleCrop()
                .into(binding.iv1)
        }

        binding.btnCheck.setOnClickListener {
            val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val res = cm.activeNetwork
            if (res!=null) {
                binding.status.setText("Connected")
                binding.status.setTextColor(Color.parseColor("#05A800"))
            } else{
                binding.status.setText("Disconnected")
                binding.status.setTextColor(Color.parseColor("#FF0000"))
            }
        }

    }
}