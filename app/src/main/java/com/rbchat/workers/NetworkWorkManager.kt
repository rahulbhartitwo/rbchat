package com.rbchat.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class NetworkWorkManager(context: Context , workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val isSuccess = isNetwork()
        val outputData = workDataOf("is_success" to isSuccess)
        return Result.success(outputData)
        return Result.success()

    }
    private fun isNetwork(): Boolean {
        return true
    }
}