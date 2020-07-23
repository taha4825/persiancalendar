package com.rax.advertisingcalendar

import android.app.Application
import com.rax.advertisingcalendar.utils.initUtils

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ReleaseDebugDifference.mainApplication(this)
        initUtils(applicationContext)
    }
}
