package com.example.pab_23_01

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Button
import android.os.Looper
import android.os.Handler

class DuaActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dua)

        val pbLoading = findViewById<ProgressBar>(R.id.pbLoading)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        val data = intent.getStringExtra("data_hasil")
        tvHasil.text = data

        btnKembali.setOnClickListener {
            finish()
        }

        Thread(Runnable {
            var progress = 0
            while (progress < 100) {
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progress++
                val p = progress
                val handler = Handler(Looper.getMainLooper())
                handler.post { pbLoading.progress = p }

            }

        }).start()


    }
}