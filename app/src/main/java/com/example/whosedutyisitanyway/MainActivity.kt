package com.example.whosedutyisitanyway

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class MainActivity : AppCompatActivity() {

    private var dishes = arrayOf("Tino", "Eugene", "David")
    private var devotionals = arrayOf("Dad", "Mom", "Tino", "Eugene", "David")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dateText = findViewById<TextView>(R.id.dateTextView)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDate.now()

            val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
            val formatted = current.format(formatter)
            dateText.text = formatted
        } else {
            dateText.text = getString(R.string.beatiful_day)
        }


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
            val dishesContentTextViewView = findViewById<TextView>(R.id.dishesContentTextView)
            var out = "Washing - " + dishes[0] + "\nRinsing - " +
                    dishes[1] + "\nDrying - " + dishes[2]
            dishesContentTextViewView.text = out

            val devotionsContentTextViewView = findViewById<TextView>(R.id.devotionsContentTextView)
            out = "Today it's " + devotionals[0]
            devotionsContentTextViewView.text = out
        }
    }

    override fun onRestart() {
        super.onRestart()
        val dateText = findViewById<TextView>(R.id.dateTextView)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDate.now()

            val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
            val formatted = current.format(formatter)
            dateText.text = formatted
        } else {
            dateText.text = getString(R.string.beatiful_day)
        }

        val settings = getSharedPreferences("PREFS", 0)
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
            val dishesContentTextViewView = findViewById<TextView>(R.id.dishesContentTextView)
            var out = "Washing - " + dishes[0] + "\nRinsing - " +
                    dishes[1] + "\nDrying - " + dishes[2]
            dishesContentTextViewView.text = out

            val devotionsContentTextViewView = findViewById<TextView>(R.id.devotionsContentTextView)
            out = "Today it's " + devotionals[0]
            devotionsContentTextViewView.text = out
        }
    }

    private fun updateDuties() {
        var temp = dishes[2]
        dishes[2] = dishes[1]
        dishes[1] = dishes[0]
        dishes[0] = temp
        val dishesContentTextViewView = findViewById<TextView>(R.id.dishesContentTextView)
        var out = "Washing - " + dishes[0] + "\nRinsing - " +
                dishes[1] + "\nDrying - " + dishes[2]
        dishesContentTextViewView.text = out

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

        val devotionsContentTextViewView = findViewById<TextView>(R.id.devotionsContentTextView)
        out = "Today it's " + devotionals[0]
        devotionsContentTextViewView.text = out

        editor.putString("p1", devotionals[0])
        editor.putString("p2", devotionals[1])
        editor.putString("p3", devotionals[2])
        editor.putString("p4", devotionals[3])
        editor.putString("p5", devotionals[4])

        editor.commit()
        
    }
}
