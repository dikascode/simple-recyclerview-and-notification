package com.dikascode.simplerecyclerviewnotification.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dikascode.simplerecyclerviewnotification.databinding.ItemRowBinding
import com.dikascode.simplerecyclerviewnotification.model.SimpleNote

class RecyclerViewAdapter(private var simpleNoteList: ArrayList<SimpleNote>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return  ItemViewHolder(ItemRowBinding.inflate(
            LayoutInflater.from(parent.context)
            , parent,
            false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val notes = simpleNoteList[position]

        holder.binding.apply {
            snNumber.text = notes.sn.toString()
            tvNote.text = notes.note
        }
    }

    override fun getItemCount(): Int = simpleNoteList.size
}