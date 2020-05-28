package com.example.whosedutyisitanyway

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(dishes : List<String>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var washing = dishes[0]
    private var rinsing = dishes[1]
    private var drying = dishes[2]
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.row_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.rowCountTextView.text = (position + 1).toString()

        val dishes = arrayOf(washing, rinsing, drying)

        holder.textView.text = dishes[position]

        when {
            dishes[position] == "Tino" -> {
                holder.imageView.setImageResource(R.mipmap.tino)
            }
            dishes[position] == "Eugene" -> {
                holder.imageView.setImageResource(R.mipmap.eugene)
            }
            dishes[position] == "David" -> {
                holder.imageView.setImageResource(R.mipmap.david)
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
        var textView: TextView = itemView.findViewById(R.id.textView)
        var rowCountTextView: TextView = itemView.findViewById(R.id.rowCountTextView)

    }
}