package com.example.test

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            val name = editText.toString()

            val intent = Intent(applicationContext, MyService::class.java)
            intent.putExtra("command", "show")
            intent.putExtra("name", name)
            startService(intent) //It isn't bring up the Activity. This starts the Service.
        }
    } //onCreate

    override fun onNewIntent(intent: Intent) {
        processCommand(intent)
        super.onNewIntent(intent)
    }

    private fun processCommand(intent: Intent?) {
        if (intent != null) {
            val command = intent.getStringExtra("command")
            val name = intent.getStringExtra("name")
            Toast.makeText(this, "Received Data from Service : $command, $name", Toast.LENGTH_LONG).show()
        }

    }
}
