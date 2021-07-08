package com.example.notes.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.model.AppNote
import com.example.notes.screens.main.NotesAdapter
import com.example.notes.screens.main.NotesFragmentViewModel
import com.example.notes.utils.APP_ACTIVITY
import com.example.notes.utils.showToast


class NoteFragment : Fragment() {


    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteFragmentViewModel: NoteFragmentViewModel
    private lateinit var currentNote: AppNote


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(layoutInflater, container, false)

        currentNote = arguments?.getSerializable("Note") as AppNote

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        initialization()
    }

    private fun initialization() {
        noteFragmentViewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)
        setHasOptionsMenu(true)
        binding.noteName.text = currentNote.name
        binding.noteText.text = currentNote.text
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnDelete -> {
                noteFragmentViewModel.deleteNote(currentNote){
                    showToast("Note is successfully deleted")
                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_notesFragment)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}