package com.tinomuzambi.whosedutyisitanyway

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter2(devotions : List<String>) : RecyclerView.Adapter<RecyclerAdapter2.ViewHolder>() {
    
    private var p1 = devotions[0]
    private var p2 = devotions[1]
    private var p3 = devotions[2]
    private var p4 = devotions[3]
    private var p5 = devotions[4]
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter2.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.row_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter2.ViewHolder, position: Int) {
        holder.rowCountTextView.text = (position + 1).toString()
        
        val devotions = arrayOf(p1, p2, p3, p4, p5)

        holder.textView.text = devotions[position]

        when {
            devotions[position] == "Dad" -> {
                holder.imageView.setImageResource(R.mipmap.dad)
            }
            devotions[position] == "Mom" -> {
                holder.imageView.setImageResource(R.mipmap.mom)
            }
            devotions[position] == "Tino" -> {
                holder.imageView.setImageResource(R.mipmap.tino)
            }
            devotions[position] == "Eugene" -> {
                holder.imageView.setImageResource(R.mipmap.eugene)
            }
            devotions[position] == "David" -> {
                holder.imageView.setImageResource(R.mipmap.david)
            }
        }
    }
    
    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
        var textView: TextView = itemView.findViewById(R.id.textView)
        var rowCountTextView: TextView = itemView.findViewById(R.id.rowCountTextView)

    }

}