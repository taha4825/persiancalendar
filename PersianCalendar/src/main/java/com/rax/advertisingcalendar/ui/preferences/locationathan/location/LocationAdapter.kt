package com.rax.advertisingcalendar.ui.preferences.locationathan.location

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rax.advertisingcalendar.*
import com.rax.advertisingcalendar.databinding.ListItemCityNameBinding
import com.rax.advertisingcalendar.entities.CityItem
import com.rax.advertisingcalendar.utils.language
import com.rax.advertisingcalendar.utils.layoutInflater

class LocationAdapter(
    private val locationPreferenceDialog: LocationPreferenceDialog,
    private val cities: List<CityItem>
) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemCityNameBinding.inflate(parent.context.layoutInflater, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(cities[position])

    override fun getItemCount(): Int = cities.size

    inner class ViewHolder(private val binding: ListItemCityNameBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(cityEntity: CityItem) = binding.let {
            it.root.setOnClickListener(this)
            when (language) {
                LANG_EN_IR, LANG_EN_US, LANG_JA -> {
                    it.city.text = cityEntity.en
                    it.country.text = cityEntity.countryEn
                }
                LANG_CKB -> {
                    it.city.text = cityEntity.ckb
                    it.country.text = cityEntity.countryCkb
                }
                LANG_AR -> {
                    it.city.text = cityEntity.ar
                    it.country.text = cityEntity.countryAr
                }
                else -> {
                    it.city.text = cityEntity.fa
                    it.country.text = cityEntity.countryFa
                }
            }
            Unit
        }

        override fun onClick(view: View) =
            locationPreferenceDialog.selectItem(cities[adapterPosition].key)
    }
}
