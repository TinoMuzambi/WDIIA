package com.example.whosedutyisitanyway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EditDevotions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_devotions)

        val settings = getSharedPreferences("PREFS", 0)

        val p1 = settings.getString("p1", "Dad")
        val p2 = settings.getString("p2", "Mom")
        val p3 = settings.getString("p3", "Tino")
        val p4 = settings.getString("p4", "Eugene")
        val p5 = settings.getString("p5", "David")
        val devotions = listOf(p1.toString(), p2.toString(), p3.toString(), p4.toString(), p5.toString())

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels

        window.setLayout((width * 0.8).toInt(), (height * 0.5).toInt())

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val recylerAdapter = RecylerAdapter(devotions)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = recylerAdapter
    }
}
