package com.example.dialpadxml

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var numberDisplay: TextView
    private var number: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberDisplay = findViewById(R.id.number_display)
        val grid = findViewById<GridLayout>(R.id.dialpad_grid)

        val dialKeys = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#")

        for (i in 0 until grid.childCount) {
            val button = grid.getChildAt(i) as Button
            button.text = dialKeys[i]
            button.setOnClickListener {
                number += dialKeys[i]
                numberDisplay.text = number
            }
        }

        findViewById<Button>(R.id.btn_clear).setOnClickListener {
            number = ""
            numberDisplay.text = ""
        }

        findViewById<Button>(R.id.btn_call).setOnClickListener {
            if (number.isNotEmpty()) {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:$number")
                try {
                    startActivity(callIntent)
                } catch (e: SecurityException) {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter a number to call", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
