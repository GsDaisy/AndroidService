package com.example.test

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

import java.io.PrintWriter
import java.io.StringWriter

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate() is called.")
        Toast.makeText(this, "onCreate() is called.", Toast.LENGTH_LONG).show()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand() is called")
        Toast.makeText(this, "onStartCommand() is called.", Toast.LENGTH_LONG).show()

        if (intent == null) {
            return Service.START_STICKY
        } else {
            processCommand(intent)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun processCommand(intent: Intent) {
        val command = intent.getStringExtra("command")
        val name = intent.getStringExtra("name")

        Log.d(TAG, "Received Data : $command / $name")
        Toast.makeText(this, "Received Data : $command / $name", Toast.LENGTH_LONG).show()

        try {
            Thread.sleep(5000)
        } catch (e: Exception) {

            val sw = StringWriter()
            e.printStackTrace(PrintWriter(sw))
            val errorMsg = sw.toString()

            Log.d("ERROR", errorMsg)
        }

        //showIntent To MainActivity
        val showIntent = Intent(applicationContext, MainActivity::class.java)

        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        showIntent.putExtra("command", "show")
        showIntent.putExtra("name", name!! + " from service.")
        startActivity(showIntent)

    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() is called.")
        Toast.makeText(this, "onDestroy() is called.", Toast.LENGTH_LONG).show()


    }

    companion object {
        private val TAG = "MyService"
    }
}
