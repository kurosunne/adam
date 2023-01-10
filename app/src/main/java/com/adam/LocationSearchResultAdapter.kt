package com.adam

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class LocationSearchResultAdapter(
    context: Context,
    val results:MutableList<Location>
) : ArrayAdapter<Location>(context, R.layout.item_location_result ,results ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v:View? = convertView
        lateinit var holder:LocationHolder
        if(v == null){
            val vi = (context as Activity).layoutInflater
            v = vi.inflate(R.layout.item_location_result, parent, false) as View
            holder = LocationHolder(
                v.findViewById(R.id.location_tvTitle),
                v.findViewById(R.id.location_tvAddress)
            )
            v.tag = holder
        }
        else{
            holder = v.tag as LocationHolder
        }
        val location = results[position]
        holder.location_tvTitle.text = location.title
        holder.location_tvTitle.tag = location.id
        holder.location_tvAddress.text = location.address

        return v
    }
}

data class LocationHolder(
    val location_tvTitle:TextView,
    val location_tvAddress:TextView,
)