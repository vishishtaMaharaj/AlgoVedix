package com.vishishta.algovedixnew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

    }
    fun login(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

}

