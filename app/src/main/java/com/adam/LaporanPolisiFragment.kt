package com.adam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class LaporanPolisiFragment : Fragment() {
    private lateinit var rv: RecyclerView
    private var listlaporan: MutableList<Laporan> = mutableListOf()
    private lateinit var laporanAdapter: LaporanAdapter
    val WS_HOST = "http://10.0.2.2:3000/api"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_laporan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv=view.findViewById(R.id.recycler_view)
        val verticalLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        rv.layoutManager = verticalLayoutManager
        val strReq = object: StringRequest(
            Method.GET,
            "$WS_HOST/laporan",
            Response.Listener {
                val res = JSONObject(it)
                listlaporan.clear()

                val laporans = res.getJSONArray("laporan")
                for (i in 0 until laporans.length()){
                    val laporan = laporans.getJSONObject(i)
                    val laporan_judul = laporan.getString("judul")
                    val laporan_lokasi = laporan.getString("location_nama")
                    val laporan_kategori = laporan.getString("kategori")
                    val kategori = getResources().getStringArray(R.array.laporan_kategori);

                    // val data= resources.getStringArray(R.array.laporan_kategori)
                    // Log.d("array", data.toString())
                    val laporan_deskripsi = laporan.getString("deskripsi")
                    listlaporan.add(Laporan(laporan_judul,kategori[laporan_kategori.toInt()],laporan_lokasi,laporan_deskripsi))
                }

                laporanAdapter.notifyDataSetChanged()
            },
            Response.ErrorListener {
                Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"]="Mozilla/5.0"
                return headers
            }
        }
        val queue: RequestQueue = Volley.newRequestQueue(context)
        queue.add(strReq)


        laporanAdapter = LaporanAdapter(listlaporan){

        }

        rv.adapter = laporanAdapter
        rv.layoutManager = LinearLayoutManager(context)
        laporanAdapter.notifyDataSetChanged()

    }

}