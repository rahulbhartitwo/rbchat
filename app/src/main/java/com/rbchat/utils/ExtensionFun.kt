package com.rbchat.utils

import android.R
import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.work.*
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.rbchat.BuildConfig
import com.rbchat.utils.MainApplication.Companion.myApplication
import com.rbchat.workers.NetworkWorkManager

fun snackbar(context: Context , message: String , color: Int = R.color.black) {
    val snackbar =
        Snackbar.make(
            (context as Activity).findViewById(R.id.content) ,
            message ,
            Snackbar.LENGTH_LONG
        )
    val sbView = snackbar.view
    sbView.setBackgroundColor(ContextCompat.getColor(context , color))
    snackbar.show()
}

fun debugToast(context: Context , msg: String , duration: Int = Toast.LENGTH_SHORT) {
    if(BuildConfig.DEBUG)
     Toast.makeText(context , msg , duration).show()
}

//  One Time Request
fun oneTimeWorkManager(): OneTimeWorkRequest {
    val constraints = Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresCharging(true)
        .setRequiresStorageNotLow(true)
//            .setRequiresDeviceIdle(true)
        .build()

    val oneTimeWorkRequest = OneTimeWorkRequestBuilder<NetworkWorkManager>()
        .setConstraints(constraints)
//            .setInputData(inputData)
        .build()
    WorkManager.getInstance().enqueue(oneTimeWorkRequest)
    return oneTimeWorkRequest
}

fun deviceInfo(): PackageInfo {
    val manager: PackageManager = myApplication.packageManager
    var info: PackageInfo? = null
    try {
        info = manager.getPackageInfo(myApplication.packageName , 0)
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return info!!
}

fun updateApp(myFirestore: FirebaseFirestore) {
    val androidInfo: MutableMap<String , Any> = HashMap()
    androidInfo.put(Constant.forceUpdate , false)
    androidInfo.put(Constant.versionName , deviceInfo().versionName)
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P)
        androidInfo.put(Constant.versionName , deviceInfo().longVersionCode)
    else (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.P)
        androidInfo.put(Constant.versionName , deviceInfo().versionCode)

    myFirestore.collection(Constant.updateApp).add(androidInfo)
        .addOnSuccessListener { }
        .addOnFailureListener { }
}

