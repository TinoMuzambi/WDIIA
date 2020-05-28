package com.example.whosedutyisitanyway

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log.d
import androidx.core.view.get
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*
import java.util.*

class EditDishes : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_dishes)

        val settings = getSharedPreferences("PREFS", 0)

        val washing = settings.getString("wash", "David")
        val rinsing = settings.getString("rinse", "Eugene")
        val drying = settings.getString("dry", "Tino")
        val dishes = listOf(washing.toString(), rinsing.toString(), drying.toString())

        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels

        window.setLayout((width * 0.8).toInt(), (height * 0.5).toInt())

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val recylerAdapter = RecylerAdapter(dishes)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = recylerAdapter

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    val simpleCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, 0) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition

            val settings = getSharedPreferences("PREFS", 0)
            var washing = settings.getString("wash", "David")
            var rinsing = settings.getString("rinse", "Eugene")
            var drying = settings.getString("dry", "Tino")

            recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)
            if (fromPosition == 0 && toPosition == 1) {
                washing = recyclerView[toPosition].textView.text.toString()
                rinsing = recyclerView[fromPosition].textView.text.toString()
                drying = recyclerView[2].textView.text.toString()
            }
            else if (fromPosition == 1 && toPosition == 2) {
                washing = recyclerView[0].textView.text.toString()
                rinsing = recyclerView[toPosition].textView.text.toString()
                drying = recyclerView[fromPosition].textView.text.toString()
            }
            else if (fromPosition == 1 && toPosition == 0) {
                washing = recyclerView[fromPosition].textView.text.toString()
                rinsing = recyclerView[toPosition].textView.text.toString()
                drying = recyclerView[2].textView.text.toString()
            }
            else if (fromPosition == 2 && toPosition == 1) {
                washing = recyclerView[0].textView.text.toString()
                rinsing = recyclerView[fromPosition].textView.text.toString()
                drying = recyclerView[toPosition].textView.text.toString()
            }

            val editor = settings.edit()
            editor.putString("wash", washing)
            editor.putString("rinse", rinsing)
            editor.putString("dry", drying)
            editor.commit()
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            TODO("Not yet implemented")
        }

    }
}
