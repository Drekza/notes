package com.example.notes.database.firebase

import androidx.lifecycle.LiveData
import com.example.notes.model.AppNote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllNotesLiveData : LiveData<List<AppNote>>() {
    private val auth = FirebaseAuth.getInstance()
    private val dbReference = FirebaseDatabase.getInstance().reference.child(auth.currentUser?.uid.toString())
    private val listener = object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot.children.map {
                it.getValue(AppNote::class.java) ?: AppNote()
            }
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    }

    override fun onActive() {
        dbReference.removeEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        dbReference.addValueEventListener(listener)
        super.onInactive()
    }
}