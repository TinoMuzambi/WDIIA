package com.example.whosedutyisitanyway

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    var dishes = arrayOf("Tino", "Eugene", "David")
    var devotionals = arrayOf("Dad", "Mom", "Tino", "Eugene", "David")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settings = getSharedPreferences("PREFS", 0)

        val washing = settings.getString("wash", "David")
        val rinsing = settings.getString("rinse", "Eugene")
        val drying = settings.getString("dry", "Tino")

        dishes = arrayOf(washing.toString(), rinsing.toString(), drying.toString())

        val currPerson = settings.getString("p1", "Dad")
        val person2 = settings.getString("p2", "Mom")
        val person3 = settings.getString("p3", "Tino")
        val person4 = settings.getString("p4", "Eugene")
        val person5 = settings.getString("p5", "David")

        devotionals = arrayOf(currPerson.toString(), person2.toString(), person3.toString(),
            person4.toString(), person5.toString())

        val cal = Calendar.getInstance()
        val currDay = cal.get(Calendar.DAY_OF_MONTH)
        val lastDay = settings.getInt("day", 0)

        if (lastDay != currDay) {
            val editor = settings.edit()
            editor.putInt("day", currDay)
            editor.commit()

            updateDuties()
        }
        else {
            val dishesTextView = findViewById<TextView>(R.id.dishesText)
            var out = ""
            dishes.forEach {
                out+= it +"\n"
            }
            dishesTextView.setText("Dishes:\n" + out)

            val devotionalTextView = findViewById<TextView>(R.id.devotionalText)
            out = ""
            devotionals.forEach {
                out+= it +"\n"
            }
            devotionalTextView.setText("Devotions:\n" + out)
        }
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

        val settings = getSharedPreferences("PREFS", 0)
        val editor = settings.edit()
        editor.putString("wash", dishes[0])
        editor.putString("rinse", dishes[1])
        editor.putString("dry", dishes[2])



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

        editor.putString("p1", devotionals[0])
        editor.putString("p2", devotionals[1])
        editor.putString("p3", devotionals[2])
        editor.putString("p4", devotionals[3])
        editor.putString("p5", devotionals[4])

        editor.commit()
        
    }
}
