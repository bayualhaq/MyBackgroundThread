package com.bayu.mybackgroundthread

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.btn_start)
        val tvStatus = findViewById<TextView>(R.id.tv_status)

//        val executor = Executors.newSingleThreadExecutor()
//        val handler = Handler(Looper.getMainLooper())


        btnStart.setOnClickListener {
//            executor.execute
            lifecycleScope.launch(Dispatchers.Default) {
                try {
                    //simulasi compresing
                    for (i in 0..10) {
                        delay(500)
                        val persen = i * 10
//                        handler.post
                        withContext(Dispatchers.Main) {
                            if (persen == 100) {
                                tvStatus.setText(R.string.task_completed)
                            } else {
                                tvStatus.text =
                                    String.format(getString(R.string.compressing), persen)
                            }
                        }

                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

        }
    }
}