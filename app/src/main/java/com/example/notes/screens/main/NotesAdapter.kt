package com.example.notes.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.model.AppNote
import kotlinx.android.synthetic.main.note_item.view.*

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    private var notesList = emptyList<AppNote>()

    class NotesHolder(view: View) : RecyclerView.ViewHolder(view){
        val noteName: TextView = view.item_note_name
        val noteText: TextView = view.item_note_text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)

        return NotesHolder(view)
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.noteText.text = notesList[position].text
        holder.noteName.text = notesList[position].name
    }

    override fun getItemCount() = notesList.size

    fun setList(list: List<AppNote>){
        notesList = list
        notifyDataSetChanged()
    }
}