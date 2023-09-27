package com.example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.R
import com.example.flo.databinding.ItemHomeAlbumBinding
import com.example.model.ItemData

class HomeAdapter(private val type: Int): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (type) {
            1 -> ViewHolder(
                layoutInflater.inflate(R.layout.item_home_album, parent, false)
            )
            2 -> ViewHolder(
                layoutInflater.inflate(R.layout.item_home_potcast, parent, false)
            )
            3 -> ViewHolder(
                layoutInflater.inflate(R.layout.item_home_video, parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: ItemData) {

        }
    }
}