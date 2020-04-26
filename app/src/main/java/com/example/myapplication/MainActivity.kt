package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val amount = getAmountOfWords("Hello, I'm David")
        Log.d("++", "Amount: $amount")
    }

    private fun getAmountOfWords(text : String) : Int {
        if (text.isEmpty())
            return 0
        return text.split("\\s+").size;
    }
}
