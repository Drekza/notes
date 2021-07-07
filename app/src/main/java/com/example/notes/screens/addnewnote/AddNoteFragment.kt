package com.example.notes.screens.addnewnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNoteBinding
import com.example.notes.model.AppNote
import com.example.notes.utils.APP_ACTIVITY
import com.example.notes.utils.showToast


class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var addNoteFragmentViewModel: AddNoteFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        addNoteFragmentViewModel = ViewModelProvider(this).get(AddNoteFragmentViewModel::class.java)

        binding.btnAddNewNote.setOnClickListener {
            val name = binding.inputNoteName.text.toString()
            val text = binding.inputNoteText.text.toString()

            if(name.isEmpty()){
                showToast(getString(R.string.toast_enter_name))
            } else {
                addNoteFragmentViewModel.insert(AppNote(name = name, text = text)){
                    APP_ACTIVITY.navController.navigate(R.id.action_addNoteFragment_to_notesFragment)
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}