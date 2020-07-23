package com.rax.advertisingcalendar.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.rax.advertisingcalendar.utils.setChangeDateWorker
import com.rax.advertisingcalendar.utils.update
import com.rax.advertisingcalendar.utils.updateStoredPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateWorker(val context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.Main) {
        try {
            setChangeDateWorker(context)
            updateStoredPreference(applicationContext)
            update(applicationContext, true)
            Result.success()
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.failure()
        }
    }
}
