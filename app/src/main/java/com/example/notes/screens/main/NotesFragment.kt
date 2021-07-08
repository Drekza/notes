package com.example.notes.screens.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.notes.R
import com.example.notes.databinding.FragmentAddNoteBinding
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.databinding.FragmentStartBinding
import com.example.notes.model.AppNote
import com.example.notes.utils.APP_ACTIVITY
import com.example.notes.utils.AppPreference
import com.example.notes.utils.REPOSITORY
import com.example.notes.utils.showToast
import kotlinx.android.synthetic.main.fragment_notes.*


class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var notesFragmentViewModel: NotesFragmentViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotesAdapter
    private lateinit var listObserver: Observer<List<AppNote>>


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
        setHasOptionsMenu(true)
        notesFragmentViewModel = ViewModelProvider(this).get(NotesFragmentViewModel::class.java)

        adapter = NotesAdapter()
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter

        listObserver = Observer {
            val list = it.asReversed()
            adapter.setList(list)
        }

        notesFragmentViewModel.allNotes.observe(this, listObserver)

        binding.btnAddNote.setOnClickListener{
            APP_ACTIVITY.navController.navigate(R.id.action_notesFragment_to_addNoteFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        notesFragmentViewModel.allNotes.removeObserver(listObserver)
        recyclerView.adapter = null
    }

    companion object{
        fun onNoteClicked(note: AppNote){
            val bundle = Bundle()
            bundle.putSerializable("Note", note)
            APP_ACTIVITY.navController.navigate(R.id.action_notesFragment_to_noteFragment, bundle)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnExit -> {
                AppPreference.setInitUser(false)
                notesFragmentViewModel.signOut()
                APP_ACTIVITY.navController.navigate(R.id.action_notesFragment_to_startFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}