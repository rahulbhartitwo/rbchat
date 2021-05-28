package com.rbchat.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.*
import com.rbchat.R
import com.rbchat.common.BaseActivity
import com.rbchat.databinding.ActivitySignInBinding
import com.rbchat.inerfaceUtils.ServiceController
import com.rbchat.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@AndroidEntryPoint
class SignInActivity : BaseActivity() {
    var TAG = "TAG_SignInActivity"
    lateinit var binding: ActivitySignInBinding
    lateinit var client: GoogleSignInClient
    lateinit var auth: FirebaseAuth
    lateinit var fireStoreDb: FirebaseFirestore
    var userId = ""
    @Inject
    lateinit var service: ServiceController
    private val RC_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_sign_in)

        WorkManager.getInstance()
            .getWorkInfoByIdLiveData(oneTimeWorkManager().id).observe(this , { workInfo ->
                val isNetWork =
                    if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                        workInfo.outputData.getBoolean("is_success" , false)
                    } else {
                        false
                    }
                if (isNetWork) {
                    snackbar(this , resources.getString(R.string.network_online) , R.color.green)
                    initialize()
                } else {
                    snackbar(this , resources.getString(R.string.network_offline))
                }
            })
    }

    override fun initialize() {
        client = service.googleSignInClient(application)
        auth = service.firebaseAuth()
        fireStoreDb = service.firebaseFirestore()
        signIn()
    }

    private fun signIn() {
        val signInIntent = client.signInIntent
        startActivityForResult(signInIntent , RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int , resultCode: Int , data: Intent?) {
        super.onActivityResult(requestCode , resultCode , data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                Log.d(TAG , "TASK started")
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG , "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: Exception) {
                Log.w(TAG , "Google sign in failed ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken , null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this@SignInActivity) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG , "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG , "signInWithCredential:failure" , task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val email = user.email
            snackbar(this , email , R.color.green)
            userId = user.uid
            val userInfo: MutableMap<String , Any> = HashMap()
            userInfo[Constant.email] = user.email
            userInfo[Constant.username] = user.displayName
            userInfo[Constant.uid] = userId
            storeInFirestore(userInfo)
            updateApp(fireStoreDb)
        } else {
            snackbar(this , resources.getString(R.string.something_went_wrong))
        }
    }

    private fun storeInFirestore(userInfo: MutableMap<String , Any>) {
      val documentReference =  fireStoreDb.collection(Constant.users).document(userId)
        documentReference.set(userInfo).addOnSuccessListener {
            Log.d(TAG , "addOnSuccessListener")

            documentReference.get().addOnCompleteListener {
                if(it.isSuccessful){
                    preferenceUtil.apply {
                        login = true
                        email = it.result?.data?.get(Constant.email).toString()
                        username = it.result?.data?.get(Constant.username).toString()
                        userId = it.result?.data?.get(Constant.uid).toString()
                    }
                    val intent = Intent(this , MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    Log.d(TAG, "Error getting documents.", it.exception)
                }
            }.addOnFailureListener {
                Log.d(TAG , "addOnCompleteListener ${it.message}")
            }

        }.addOnFailureListener {
            Log.d(TAG , "addOnFailureListener ${it.message}")
        }

//        fireStoreDb.collection("users")
//            .add(mData)
//            .addOnSuccessListener(object : OnSuccessListener<DocumentReference?> {
//
//                override fun onSuccess(documentReference: DocumentReference?) {
//                    Log.d(TAG , "DocumentSnapshot added with ID: " + documentReference?.id)
//                }
//            })
//            .addOnFailureListener(object : OnFailureListener {
//
//                override fun onFailure(e: java.lang.Exception) {
//                    Log.w(TAG , "Error adding document" , e)
//                }
//            })
    }


}