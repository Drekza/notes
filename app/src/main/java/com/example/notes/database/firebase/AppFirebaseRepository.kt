package com.example.notes.database.firebase

import androidx.lifecycle.LiveData
import com.example.notes.database.DatabaseRepository
import com.example.notes.model.AppNote
import com.example.notes.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AppFirebaseRepository : DatabaseRepository {
    private val auth = FirebaseAuth.getInstance()
    private val dbReference = FirebaseDatabase.getInstance().reference.child(auth.currentUser?.uid.toString())

    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val noteId = dbReference.push().key.toString()
        val noteMap = hashMapOf<String, Any>()
        noteMap[FIREBASE_ID] = noteId
        noteMap[NOTE_NAME] = note.name
        noteMap[NOTE_TEXT] = note.text

        dbReference.child(noteId)
            .updateChildren(noteMap)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{
                showToast(it.message.toString())
            }

    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        dbReference.child(note.firebaseId).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{
                showToast(it.message.toString())
            }
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