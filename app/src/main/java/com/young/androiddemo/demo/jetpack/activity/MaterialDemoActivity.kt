package com.young.androiddemo.demo.jetpack.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.young.androiddemo.R

class MaterialDemoActivity:AppCompatActivity() {

    companion object {

        private const val TAG = "MaterialDemoActivity==="
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, MaterialDemoActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_demo)
    }

}