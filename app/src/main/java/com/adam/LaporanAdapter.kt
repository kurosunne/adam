package com.adam

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LaporanAdapter(
    private val arrayList: Activity,
    private val layout: Int,
    private val listlaporan: ArrayList<Laporan>
): RecyclerView.Adapter<LaporanAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))

    }


    inner class CustomViewHolder(view: View):RecyclerView.ViewHolder(view) {

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = listlaporan[position]
    }

    override fun getItemCount(): Int {
        return listlaporan.size
    }

}