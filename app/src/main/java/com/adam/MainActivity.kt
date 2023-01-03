package com.adam

import android.content.Intent
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
    lateinit var etEmail:EditText
    lateinit var etPassword:EditText
    lateinit var btLogin:Button
    lateinit var btRegister:Button
    val WS_HOST = "http://10.0.2.2:8000/api"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById(R.id.login_etEmail)
        etPassword = findViewById(R.id.login_etPassword)
        btLogin = findViewById(R.id.login_btLogin)
        btRegister = findViewById(R.id.login_btRegister)

        btLogin.setOnClickListener {
            btLoginClicked()
        }
        btRegister.setOnClickListener {
            btRegisterClicked()
        }
    }
    fun btLoginClicked(){
        var go = Intent(this,WargaActivity::class.java)
        startActivity(go)
//        val strReq = object : StringRequest(
//            Method.POST,
//            "$WS_HOST/getwarga",
//            Response.Listener {
//                val obj: JSONArray = JSONArray(it)
//                if (obj.length() == 0) {
//                    Toast.makeText(this, "User Tidak Ada", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this, "User Ada", Toast.LENGTH_SHORT).show()
//                }
//            },
//            Response.ErrorListener {
//                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
//            }
//        ) {
//            override fun getParams(): MutableMap<String, String>? {
//                val params = HashMap<String, String>()
//                params["username"] = etEmail.text.toString()
//                return params
//            }
//        }
//        val queue: RequestQueue = Volley.newRequestQueue(this)
//        queue.add(strReq)
    }

    fun btRegisterClicked(){
        var go = Intent(this,RegisterActivity::class.java)
        startActivity(go)
    }
}