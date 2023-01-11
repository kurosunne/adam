package com.adam

import android.app.Activity
import android.content.Intent
import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class SearchLocationActivity : AppCompatActivity() {
    private lateinit var location_btBack:ImageButton
    private lateinit var location_btSearch:ImageButton
    private lateinit var location_etName:EditText
    private lateinit var location_lsResult:ListView
    private lateinit var locationListAdapter:LocationSearchResultAdapter
    val API_KEY="zfq75h81VmgMqBa9vpjkbhm27G8VKur9tPN19e1jNUg"
    var listAddress:MutableList<Location> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_location)
        location_btBack = findViewById(R.id.location_btBack)
        location_btSearch = findViewById(R.id.location_btSearch)
        location_etName = findViewById(R.id.location_etName)
        location_lsResult = findViewById(R.id.location_lsResult)
        locationListAdapter = LocationSearchResultAdapter(this,listAddress)
        location_lsResult.adapter = locationListAdapter

        location_btBack.setOnClickListener {
            finish()
        }

        location_lsResult.setOnItemClickListener { adapterView, view, i, l ->
            val item = adapterView.getItemAtPosition(i) as Location
            val resultIntent = Intent().apply {
                putExtra("location__judul", item.address)
                putExtra("location__position", item.position)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
        location_btSearch.setOnClickListener {
            if(location_etName.text.isNotEmpty()){
                val strReq = object: StringRequest(
                    Method.GET,
                    "https://autosuggest.search.hereapi.com/v1/autosuggest?at=-7.250445,112.768845&limit=10&lang=id&q=${location_etName.text}&apiKey=${API_KEY}",
                    Response.Listener {
                        val res = JSONObject(it)
                        listAddress.clear()
                        val locations = res.getJSONArray("items")
                        for (i in 0 until locations.length()){
                            val location = locations.getJSONObject(i)
                            val lng:Double = location.getJSONObject("position").getDouble("lng")
                            val lat:Double = location.getJSONObject("position").getDouble("lat")
                            Log.d("Location", "${lng}, ${lat}")
                            val location__judul = location.getString("title")
                            val location__position = "${lng}, ${lat}"
                            val location__alamat = location.getJSONObject("address").getString("label")
                            listAddress.add(Location(location__judul, location__position,location__alamat))
                        }
                        locationListAdapter.notifyDataSetChanged()
                    },
                    Response.ErrorListener {
                        Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                ){
                    override fun getHeaders(): MutableMap<String, String> {
                        val headers = HashMap<String, String>()
                        headers["User-Agent"]="Mozilla/5.0"
                        return headers
                    }
                }
                val queue: RequestQueue = Volley.newRequestQueue(this)
                queue.add(strReq)
            }
        }
    }
}