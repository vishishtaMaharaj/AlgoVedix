package com.vishishta.algovedixnew


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_two)
    }
    fun toTest(view: View) {
        val intent = Intent(this, QuestionsPane::class.java)
        startActivity(intent)
    }




}