package com.isa.project2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.covid_global_item.*
import kotlinx.android.synthetic.main.covid_global_item.txtMeninggal
import kotlinx.android.synthetic.main.covid_global_item.txtPositif
import kotlinx.android.synthetic.main.covid_global_item.txtSembuh
import kotlinx.android.synthetic.main.covid_provinsi_item.*

class CovidGlobalAdapter(private val context: Context, private val items: List<CovidGlobalItem>, private val listener: (CovidGlobalItem)-> Unit): RecyclerView.Adapter<CovidGlobalAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    )= ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.covid_global_item, parent, false))

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View): RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(item: CovidGlobalItem, listener: (CovidGlobalItem) -> Unit){
            txtNegara.text = item.attributes.countryRegion
            txtPositif.text = item.attributes.confirmed.toString()
            txtSembuh.text = item.attributes.recovered.toString()
            txtMeninggal.text = item.attributes.deaths.toString()

            containerView.setOnClickListener{ listener(item) }
        }
    }
}