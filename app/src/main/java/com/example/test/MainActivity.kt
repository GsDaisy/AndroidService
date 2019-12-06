package com.example.test

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var editText: EditText
    internal lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText) as EditText
        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
            val name = editText.text.toString()

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
