package ru.netology.netologyvoiceassistant

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name: String = "Petya"
        val surname: String = "Shumskii"
        var age: Int = 26
        var height: Double = 180.0

        age = 27
        age = 28

        age = age + 1
        age += 1
        age++

        val summary: String = "name: $name surname: $surname age: $age height: $height"

        val output: TextView = findViewById(R.id.output)
        output.text = summary

        Log.e(TAG, summary)
    }
}