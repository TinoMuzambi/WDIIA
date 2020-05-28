package com.example.whosedutyisitanyway

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.core.view.get
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*

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

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView2)
        val recylerAdapter = RecyclerAdapter2(devotions)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = recylerAdapter

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private val simpleCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, 0) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition
            recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)

            val settings = getSharedPreferences("PREFS", 0)

            var p1 = settings.getString("p1", "Dad")
            var p2 = settings.getString("p2", "Mom")
            var p3 = settings.getString("p3", "Tino")
            var p4 = settings.getString("p4", "Eugene")
            var p5 = settings.getString("p5", "David")

            if (fromPosition == 0 && toPosition == 1) {
                p1 = recyclerView[toPosition].textView.text.toString()
                recyclerView[toPosition].rowCountTextView.text = 1.toString()
                p2 = recyclerView[fromPosition].textView.text.toString()
                recyclerView[fromPosition].rowCountTextView.text = 2.toString()
            }
            else if (fromPosition == 1 && toPosition == 2) {
                p2 = recyclerView[toPosition].textView.text.toString()
                recyclerView[toPosition].rowCountTextView.text = 2.toString()
                p3 = recyclerView[fromPosition].textView.text.toString()
                recyclerView[fromPosition].rowCountTextView.text = 3.toString()
            }
            else if (fromPosition == 1 && toPosition == 0) {
                p1 = recyclerView[fromPosition].textView.text.toString()
                recyclerView[fromPosition].rowCountTextView.text = 1.toString()
                p2 = recyclerView[toPosition].textView.text.toString()
                recyclerView[toPosition].rowCountTextView.text = 2.toString()
            }
            else if (fromPosition == 2 && toPosition == 1) {
                p2 = recyclerView[fromPosition].textView.text.toString()
                recyclerView[fromPosition].rowCountTextView.text = 2.toString()
                p3 = recyclerView[toPosition].textView.text.toString()
                recyclerView[toPosition].rowCountTextView.text = 3.toString()
            }

            val editor = settings.edit()
            editor.putString("p1", p1)
            editor.putString("p2", p2)
            editor.putString("p3", p3)
            editor.putString("p4", p4)
            editor.putString("p5", p5)
            editor.commit()

            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    }
}
