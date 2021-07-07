package com.example.notes.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNoteBinding
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.databinding.FragmentStartBinding
import com.example.notes.utils.APP_ACTIVITY
import kotlinx.android.synthetic.main.fragment_notes.*


class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var notesFragmentViewModel: NotesFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        initialization()
    }

    private fun initialization() {
        notesFragmentViewModel = ViewModelProvider(this).get(NotesFragmentViewModel::class.java)

        binding.btnAddNote.setOnClickListener{
            APP_ACTIVITY.navController.navigate(R.id.action_notesFragment_to_addNoteFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}