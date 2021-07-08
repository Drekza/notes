package com.example.notes.database.firebase

import androidx.lifecycle.LiveData
import com.example.notes.database.DatabaseRepository
import com.example.notes.model.AppNote
import com.example.notes.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AppFirebaseRepository : DatabaseRepository {
    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    init{
        AUTH = FirebaseAuth.getInstance()
    }

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val noteId = DB_REFERENCE.push().key.toString()
        val noteMap = hashMapOf<String, Any>()
        noteMap[FIREBASE_ID] = noteId
        noteMap[NOTE_NAME] = note.name
        noteMap[NOTE_TEXT] = note.text

        DB_REFERENCE.child(noteId)
            .updateChildren(noteMap)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{
                showToast(it.message.toString())
            }

    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        DB_REFERENCE.child(note.firebaseId).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener{
                showToast(it.message.toString())
            }
    }

    override fun connectToFirebaseDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener{
                        onFail(it.message.toString())
                    }
            }
    }

    override fun signOutFirebase() {
        AUTH.signOut()
    }
}