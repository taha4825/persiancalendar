package com.rax.advertisingcalendar.ui.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rax.advertisingcalendar.R
import com.rax.advertisingcalendar.databinding.FragmentSettingsBinding
import com.rax.advertisingcalendar.ui.MainActivity
import com.rax.advertisingcalendar.ui.preferences.interfacecalendar.InterfaceCalendarFragment
import com.rax.advertisingcalendar.ui.preferences.locationathan.LocationAthanFragment
import com.rax.advertisingcalendar.ui.preferences.widgetnotification.WidgetNotificationFragment
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @author MEHDI DIMYADI
 * MEHDIMYADI
 */
class PreferencesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentSettingsBinding.inflate(inflater, container, false).apply {
        val mainActivity = activity as MainActivity
        mainActivity.setTitleAndSubtitle(getString(R.string.settings), "")
        val tabs = listOf(
            R.string.pref_header_interface_calendar to InterfaceCalendarFragment::class.java,
            R.string.pref_header_widget_location to WidgetNotificationFragment::class.java,
            R.string.pref_header_location_athan to LocationAthanFragment::class.java
        )
        viewPager.adapter = object : FragmentStateAdapter(this@PreferencesFragment) {
            override fun getItemCount() = tabs.size
            override fun createFragment(position: Int) = tabs[position].second.newInstance()
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, i -> tab.setText(tabs[i].first) }.attach()
    }.root
}
