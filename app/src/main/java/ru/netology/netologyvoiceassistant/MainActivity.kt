package ru.netology.netologyvoiceassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "start of onCreate function")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myText = "Hello, world!"
        val myNumber = 42
        val myFloatingNumber = 3.14

        val outputText = myText + " " + myNumber + " " + myFloatingNumber

        val textView = findViewById<TextView>(R.id.text_output)
        textView.text = outputText

        Log.d(TAG, "end of onCreate function")
    }
}