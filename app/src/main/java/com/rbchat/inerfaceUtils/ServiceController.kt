package com.rbchat.inerfaceUtils

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

interface ServiceController {
    fun firebaseAuth() : FirebaseAuth
    fun firebaseFirestore() : FirebaseFirestore
    fun googleSignInClient(context: Context)  : GoogleSignInClient

}