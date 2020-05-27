package com.example.whosedutyisitanyway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EditDishes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_dishes)

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels

        window.setLayout((width * 0.8).toInt(), (height * 0.8).toInt())

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val recylerAdapter = RecylerAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = recylerAdapter
    }
}
