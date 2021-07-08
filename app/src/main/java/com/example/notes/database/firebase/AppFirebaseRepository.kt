package com.example.notes.database.firebase

import androidx.lifecycle.LiveData
import com.example.notes.database.DatabaseRepository
import com.example.notes.model.AppNote
import com.example.notes.utils.EMAIL
import com.example.notes.utils.PASSWORD
import com.google.firebase.auth.FirebaseAuth

class AppFirebaseRepository : DatabaseRepository {
    private val auth = FirebaseAuth.getInstance()


    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    override suspend fun insert(note: AppNote) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: AppNote) {
        TODO("Not yet implemented")
    }

    override fun connectToFirebaseDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        auth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                auth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener{
                        onFail(it.message.toString())
                    }
            }
    }

    override fun signOutFirebase() {
        auth.signOut()
    }
}