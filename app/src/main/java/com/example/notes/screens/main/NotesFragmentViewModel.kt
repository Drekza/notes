package com.example.notes.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notes.utils.REPOSITORY

class NotesFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes
}