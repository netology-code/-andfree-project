package ru.netology.netologyvoiceassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myText = "Hello, world!"
        val myNumber = 42
        val myFloatingNumber = 3.14

        val outputText = myText + " " + myNumber + " " + myFloatingNumber

        val textView = findViewById<TextView>(R.id.text_output)
        textView.text = outputText
    }
}