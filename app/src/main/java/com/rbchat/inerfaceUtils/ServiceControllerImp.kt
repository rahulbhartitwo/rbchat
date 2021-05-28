package com.rbchat.inerfaceUtils

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ServiceControllerImp : ServiceController {

    override fun firebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    override fun firebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance();

    override fun googleSignInClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("854972603384-0v67q5m5qb21gkl4ptma8rcliu6r2hc0.apps.googleusercontent.com")
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context , gso)
    }
}