package com.adams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LaporanAdapter(
    private val listlaporan: MutableList<Laporan>
): RecyclerView.Adapter<LaporanAdapter.LaporanHolder>(){
    constructor(listlaporan: MutableList<Laporan>, function: () -> Unit) : this(listlaporan)

    class LaporanHolder(view: View):RecyclerView.ViewHolder(view){
        val laporan_judul = view.findViewById<TextView>(R.id.judulLaporan)
        val laporan_kategori = view.findViewById<TextView>(R.id.kategoriLaporan)
        val laporan_lokasi = view.findViewById<TextView>(R.id.lokasiLaporan)
        val laporan_deskripsi = view.findViewById<TextView>(R.id.deskripsiLaporan)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanHolder {
        var i = LayoutInflater.from(parent.context)
        return LaporanHolder(i.inflate(R.layout.laporan, parent, false))
    }

    override fun onBindViewHolder(holder: LaporanHolder, position: Int) {
        val item = listlaporan[position]
        holder.laporan_judul.text = item.namaLaporan
        holder.laporan_deskripsi.text = item.deskripsiLaporan
        holder.laporan_lokasi.text = item.lokasiLaporan
        holder.laporan_kategori.text = item.kategoriLaporan
    }

    override fun getItemCount(): Int {
        return listlaporan.size
    }

}