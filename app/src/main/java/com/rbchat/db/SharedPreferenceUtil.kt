package com.rbchat.db

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.rbchat.utils.Constant

class SharedPreferenceUtil
private constructor(val context: Context) {
    val TAG = SharedPreferenceUtil::class.java.simpleName

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(Constant.PREFRENCE_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: SharedPreferenceUtil? = null
            fun getInstance(ctx: Context): SharedPreferenceUtil {
            if (instance == null) {
                instance = SharedPreferenceUtil(ctx)
            }
            return instance!!
        }
    }

    var login: Boolean
        get() = sharedPreferences["login", false]!!
        set(value) = sharedPreferences.set("login", value)

    var accessToken: String
        get() = sharedPreferences["accessToken", ""]!!
        set(value) = sharedPreferences.set("accessToken", value)

    var userId: String
        get() = sharedPreferences["user_id", ""]!!
        set(value) = sharedPreferences.set("user_id", value)

    var email: String
        get() = sharedPreferences["email", ""]!!
        set(value) = sharedPreferences.set("email", value)

    var mobile_number: String
        get() = sharedPreferences["mobile_number", ""].toString()
        set(value) = sharedPreferences.set("mobile_number", value)

    var username: String
        get() = sharedPreferences["username", ""]!!
        set(value) = sharedPreferences.set("username", value)
    var versionName: String
        get() = sharedPreferences["versionName", ""]!!
        set(value) = sharedPreferences.set("versionName", value)

    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key , value) }
            is Int -> edit { it.putInt(key , value) }
            is Boolean -> edit { it.putBoolean(key , value) }
            is Float -> edit { it.putFloat(key , value) }
            is Long -> edit { it.putLong(key , value) }
            else -> Log.e(TAG, "Setting shared pref failed for key: $key and value: $value ")
        }
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    inline operator fun <reified T : Any> SharedPreferences.get(key: String , defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    fun deletePreferences() {
        editor.clear()
        editor.apply()
    }
//    fun deletePreferences() {
//        editor.revi.clear()
//        editor.apply()
//    }
}