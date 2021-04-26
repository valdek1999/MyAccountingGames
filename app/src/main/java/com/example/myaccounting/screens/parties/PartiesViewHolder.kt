package com.example.myaccounting.screens.parties

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myaccounting.R

class PartiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val editImage: ImageView = itemView.findViewById(R.id.edit_image)
    val titleTextView: TextView = itemView.findViewById(R.id.titleView)
    val infoTextView: TextView = itemView.findViewById(R.id.infoView)
    val addPartyImageView: ImageView = itemView.findViewById(R.id.addPartyImage)

}