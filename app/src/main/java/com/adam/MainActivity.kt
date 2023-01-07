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
import org.json.JSONObject
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var etEmail:EditText
    lateinit var etPassword:EditText
    lateinit var btLogin:Button
    lateinit var btRegister:Button
    val WS_HOST = "http://10.0.2.2:3000/api"
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
        if (etEmail.text.toString().isEmpty() || etPassword.text.toString().isEmpty()){
            Toast.makeText(this,"Semua Field Harus Terisi",Toast.LENGTH_SHORT).show()
        }else {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()) {
                Toast.makeText(this,"Format Email Salah",Toast.LENGTH_SHORT).show()
            }else{
                val strReq = object : StringRequest(
                    Method.POST,
                    "$WS_HOST/login",
                    Response.Listener {
                        var obj : JSONObject = JSONObject(it.toString())
                        Toast.makeText(this, obj.getString("message"), Toast.LENGTH_SHORT).show()
                        if (obj.getString("message").equals("logged in")){
                            if (obj.getString("access").equals("0")){
                                var go = Intent(this,WargaActivity::class.java)
                                startActivity(go)
                            }else{
                                var go = Intent(this,PolisiActivity::class.java)
                                startActivity(go)
                            }
                        }
                    },
                    Response.ErrorListener {
                        Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                ) {
                    override fun getParams(): MutableMap<String, String>? {
                        val params = HashMap<String, String>()
                        params["email"] = etEmail.text.toString()
                        params["password"] = etPassword.text.toString()
                        return params
                    }
                }
                val queue: RequestQueue = Volley.newRequestQueue(this)
                queue.add(strReq)
            }
        }
    }

    fun btRegisterClicked(){
        var go = Intent(this,RegisterActivity::class.java)
        startActivity(go)
    }
}