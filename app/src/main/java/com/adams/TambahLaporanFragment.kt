package com.adams

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TambahLaporanFragment : Fragment() {
    private lateinit var lapor_etJudul:EditText
    private lateinit var lapor_spCategory:Spinner
    private lateinit var lapor_etLokasi:EditText
    private lateinit var lapor_mtDeskripsi:EditText
    private lateinit var lapor_cbKonfirmasi:CheckBox
    private lateinit var lapor_btLapor:Button
    lateinit var db : AppDatabase
    val coroutine = CoroutineScope(Dispatchers.IO)
    val WS_HOST = "https://adam.mikhaelchris.my.id/api"

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
        db = AppDatabase.build(context)

        val locationLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                lapor_etLokasi.setText(it.data?.getStringExtra("location__judul"))
                lapor_etLokasi.tag = it.data?.getStringExtra("location__position")
            }
        }
        
        lapor_cbKonfirmasi.setOnCheckedChangeListener { compoundButton, b ->
            lapor_btLapor.isEnabled = b
        }
        lapor_etLokasi.setOnFocusChangeListener { view, b ->
            if(b==true) locationLauncher.launch(Intent(context,SearchLocationActivity::class.java))
        }

        lapor_btLapor.setOnClickListener {
            var user_email = ""
            coroutine.launch {
                if (db.userDao.getLength()>0){
                    val temp : List<UserEntity> = db.userDao.fetch()
                    user_email = temp[0].email
                }
            }
            val strReq = object: StringRequest(
                Method.POST,
                "$WS_HOST/laporan",
                Response.Listener {
                    lapor_etJudul.text.clear()
                    lapor_etLokasi.text.clear()
                    lapor_mtDeskripsi.text.clear()
                    lapor_cbKonfirmasi.isChecked = false
                },
                Response.ErrorListener {
                    Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                }
            ){

                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["judul"] = lapor_etJudul.text.toString()
                    params["kategori"] = lapor_spCategory.selectedItemPosition.toString()
                    params["location_nama"] = lapor_etLokasi.text.toString()
                    params["location_position"] = lapor_etLokasi.tag.toString()
                    params["deskripsi"] = lapor_mtDeskripsi.text.toString()
                    params["user_email"] = user_email
                    return params
                }
            }
            val queue: RequestQueue = Volley.newRequestQueue(context)
            queue.add(strReq)
        }
    }
}