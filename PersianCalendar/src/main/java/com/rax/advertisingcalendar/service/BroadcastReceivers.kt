package com.rax.advertisingcalendar.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.rax.advertisingcalendar.BROADCAST_ALARM
import com.rax.advertisingcalendar.BROADCAST_RESTART_APP
import com.rax.advertisingcalendar.BROADCAST_UPDATE_APP
import com.rax.advertisingcalendar.KEY_EXTRA_PRAYER_KEY
import com.rax.advertisingcalendar.utils.loadApp
import com.rax.advertisingcalendar.utils.startAthan
import com.rax.advertisingcalendar.utils.startEitherServiceOrWorker
import com.rax.advertisingcalendar.utils.update

/**
 * Startup broadcast receiver
 */
class BroadcastReceivers : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        when (intent?.action) {
            Intent.ACTION_BOOT_COMPLETED, TelephonyManager.ACTION_PHONE_STATE_CHANGED, BROADCAST_RESTART_APP -> startEitherServiceOrWorker(
                context
            )

            Intent.ACTION_DATE_CHANGED, Intent.ACTION_TIMEZONE_CHANGED -> {
                update(context, true)
                loadApp(context)
            }

            Intent.ACTION_TIME_CHANGED, Intent.ACTION_SCREEN_ON, BROADCAST_UPDATE_APP -> {
                update(context, false)
                loadApp(context)
            }

            BROADCAST_ALARM -> {
                val prayTimeKey = intent.getStringExtra(KEY_EXTRA_PRAYER_KEY) ?: return
                startAthan(context, prayTimeKey)
            }
        }
    }
}
