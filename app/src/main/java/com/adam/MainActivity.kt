package com.adam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    lateinit var etUsername:EditText
    lateinit var etPassword:EditText
    lateinit var btLogin:Button
    lateinit var btRegister:Button
    val WS_HOST = "http://10.0.2.2:8000/api"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.login_etUsername)
        etPassword = findViewById(R.id.login_etPassword)
        btLogin = findViewById(R.id.login_btLogin)
        btRegister = findViewById(R.id.login_btRegister)

        btLogin.setOnClickListener {
            val strReq = object:StringRequest(
                Method.POST,
                "$WS_HOST/getwarga",
                Response.Listener {
                    val obj: JSONArray = JSONArray(it)
                    if (obj.length()==0){
                        Toast.makeText(this,"User Tidak Ada",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"User Ada",Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener {
                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
                }
            ){
                override fun getParams(): MutableMap<String,String>? {
                    val params = HashMap<String, String>()
                    params["username"] = etUsername.text.toString()
                    return params
                }
            }
            val queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(strReq)
        }
    }

    fun getData(){
        val strReq = object : StringRequest(
            Method.GET,
            "$WS_HOST/mahasiswa",
            Response.Listener {
//                val obj:JSONArray = JSONArray(it)
//                mahasiswa.clear()
//                for (i in 0 until obj.length()) {
//                    val o = obj.getJSONObject(i)
//                    val m = Mahasiswa(o.getString("nrp"), o.getString("nama"), o.getInt("umur"))
//                    mahasiswa.add(m)
//                    Toast.makeText(this,mahasiswa.get(mahasiswa.size-1).toString(),Toast.LENGTH_SHORT).show()
//
//                }
            },
            Response.ErrorListener {
                Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
            }
        ){}
        val queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(strReq)
    }
}