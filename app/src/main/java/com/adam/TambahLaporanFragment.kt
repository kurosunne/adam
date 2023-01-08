package com.adam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class TambahLaporanFragment : Fragment() {
    private lateinit var lapor_etJudul:EditText
    private lateinit var lapor_spCategory:Spinner
    private lateinit var lapor_etLokasi:EditText
    private lateinit var lapor_mtDeskripsi:EditText
    private lateinit var lapor_cbKonfirmasi:CheckBox
    private lateinit var lapor_btLapor:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tambah_laporan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lapor_etJudul = view.findViewById<EditText>(R.id.lapor_etJudul)
        lapor_spCategory = view.findViewById<Spinner>(R.id.lapor_spCategory)
        lapor_etLokasi = view.findViewById<EditText>(R.id.lapor_etLokasi)
        lapor_mtDeskripsi = view.findViewById<EditText>(R.id.lapor_mtDeskripsi)
        lapor_cbKonfirmasi = view.findViewById<CheckBox>(R.id.lapor_cbKonfirmasi)
        lapor_btLapor = view.findViewById<Button>(R.id.lapor_btLapor)

        lapor_cbKonfirmasi.setOnCheckedChangeListener { compoundButton, b ->

        }

        lapor_btLapor.setOnClickListener {

        }
    }
}