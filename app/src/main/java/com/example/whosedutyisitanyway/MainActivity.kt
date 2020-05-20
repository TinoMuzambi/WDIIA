package com.example.whosedutyisitanyway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val dishes = arrayOf("Tino", "Eugene", "David")
    val devotionals = arrayOf("Dad", "Mom", "Tino", "Eugene", "David")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateDuties()
    }

    fun updateDuties() {
        var temp = dishes[2]
        dishes[2] = dishes[1]
        dishes[1] = dishes[0]
        dishes[0] = temp
        val dishesTextView = findViewById<TextView>(R.id.dishesText)
        var out = ""
        dishes.forEach {
            out+= it +"\n"
        }
        dishesTextView.setText("Dishes:\n" + out)


        temp = devotionals[4]
        devotionals[4] = devotionals[3]
        devotionals[3] = devotionals[2]
        devotionals[2] = devotionals[1]
        devotionals[1] = devotionals[0]
        devotionals[0] = temp

        val devotionalTextView = findViewById<TextView>(R.id.devotionalText)
        out = ""
        devotionals.forEach {
            out+= it +"\n"
        }
        devotionalTextView.setText("Devotions:\n" + out)
        
    }
}
