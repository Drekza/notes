package com.example.notes.utils

import com.example.notes.MainActivity
import com.example.notes.database.DatabaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository
lateinit var EMAIL: String
lateinit var PASSWORD: String
lateinit var AUTH: FirebaseAuth
lateinit var DB_REFERENCE: DatabaseReference
lateinit var CURRENT_ID: String
const val TYPE_FIREBASE = "type_firebase"
const val TYPE_ROOM = "type_room"
const val FIREBASE_ID = "firebaseId"
const val NOTE_NAME = "name"
const val NOTE_TEXT = "text"
