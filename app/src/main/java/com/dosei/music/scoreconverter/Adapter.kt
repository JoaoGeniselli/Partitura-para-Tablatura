package com.dosei.music.scoreconverter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_score_position.view.*

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    var lastSelected: ViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_score_position, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position % 2 == 0) {
            holder.itemView.view_line?.visibility = View.VISIBLE
        } else {
            holder.itemView.view_line?.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            lastSelected?.itemView?.image_view_note?.visibility = View.GONE
            lastSelected = holder
            lastSelected?.itemView?.image_view_note?.visibility = View.VISIBLE
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

