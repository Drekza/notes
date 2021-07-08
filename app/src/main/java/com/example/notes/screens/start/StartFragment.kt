package com.example.notes.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.databinding.FragmentStartBinding
import com.example.notes.utils.*
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_start.*


class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private lateinit var startFragmentViewModel : StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        startFragmentViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        if(AppPreference.getInitUser()){
            startFragmentViewModel.initDatabase(AppPreference.getDbType()){
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_notesFragment)
            }
        } else {
            initialization()
        }

    }

    private fun initialization() {
        binding.btnRoom.setOnClickListener {
            startFragmentViewModel.initDatabase(AppPreference.getDbType()){
                AppPreference.setInitUser(true)
                AppPreference.setDbType(TYPE_ROOM)
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_notesFragment)
            }
        }
        binding.btnFirebase.setOnClickListener {
            binding.inputEmail.visibility = View.VISIBLE
            binding.inputPassword.visibility = View.VISIBLE
            binding.btnFirebaseLogin.visibility = View.VISIBLE

            binding.btnFirebaseLogin.setOnClickListener {
                val inputEmail = binding.inputEmail.text.toString()
                val inputPassword = binding.inputPassword.text.toString()

                if(inputEmail.isNotEmpty() && inputPassword.isNotEmpty()){
                    EMAIL = inputEmail
                    PASSWORD = inputPassword
                    startFragmentViewModel.initDatabase(TYPE_FIREBASE){
                        AppPreference.setInitUser(true)
                        AppPreference.setDbType(TYPE_FIREBASE)
                        APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_notesFragment)
                    }

                } else {
                    showToast(getString(R.string.toast_fill_text))
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}