package ru.netology.netologyvoiceassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.SimpleAdapter
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.wolfram.alpha.WAEngine

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var requestInput: TextInputEditText

    lateinit var podsAdapter: SimpleAdapter

    val pods = mutableListOf<HashMap<String, String>>(
        HashMap<String, String>().apply {
            put("Title", "Test title")
            put("Content", "Test content")
        }
    )

    lateinit var progressBar: ProgressBar

    lateinit var waEngine: WAEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initWolframEngine()
    }

    private fun initViews() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        requestInput = findViewById<TextInputEditText>(R.id.text_input_edit)

        val podsList = findViewById<ListView>(R.id.pods_list)
        podsAdapter = SimpleAdapter(
            applicationContext,
            pods,
            R.layout.item_pod,
            arrayOf("Title", "Content"),
            intArrayOf(R.id.title, R.id.content)
        )
        podsList.adapter = podsAdapter

        progressBar = findViewById<ProgressBar>(R.id.progress_bar)
    }

    fun initWolframEngine() {
        waEngine = WAEngine()
        waEngine.appID = "DEMO"
        waEngine.addFormat("plaintext")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_stop -> {
                Log.d(TAG, "action_stop")
                return true
            }
            R.id.action_clear -> {
                Log.d(TAG, "action_clear")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}