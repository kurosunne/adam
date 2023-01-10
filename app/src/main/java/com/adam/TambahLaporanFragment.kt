package com.adam

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts

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

        val locationLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                lapor_etLokasi.setText(it.data?.getStringExtra("location__judul"))
                lapor_etLokasi.tag = it.data?.getStringExtra("locatiob__id")
            }
        }
        
        lapor_cbKonfirmasi.setOnCheckedChangeListener { compoundButton, b ->
            lapor_btLapor.isEnabled = b
        }
        lapor_etLokasi.setOnFocusChangeListener { view, b ->
            if(b==true) locationLauncher.launch(Intent(context,SearchLocationActivity::class.java))
        }

        lapor_btLapor.setOnClickListener {

        }
    }
}