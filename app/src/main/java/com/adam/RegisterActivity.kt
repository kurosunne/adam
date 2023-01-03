package com.adam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class RegisterActivity : AppCompatActivity() {
    lateinit var etEmail: EditText
    lateinit var etNama: EditText
    lateinit var etPassword: EditText
    lateinit var etConfirm: EditText
    lateinit var spRole : Spinner
    lateinit var btLogin: Button
    lateinit var btRegister: Button
    val WS_HOST = "http://10.0.2.2:3000/api"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etEmail = findViewById(R.id.register_etEmail)
        etNama = findViewById(R.id.register_etNama)
        etPassword = findViewById(R.id.register_etPassword)
        etConfirm = findViewById(R.id.register_etConfirm)
        spRole = findViewById(R.id.register_spRole)
        btRegister = findViewById(R.id.register_btRegister)
        btLogin = findViewById(R.id.register_btLogin)

        var adapter  = ArrayAdapter.createFromResource(this, R.array.role,R.layout.spinner_item)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spRole.setAdapter(adapter);

        btRegister.setOnClickListener {
            btRegisterClicked()
        }
        btLogin.setOnClickListener {
            btLoginClicked()
        }
    }

    fun btRegisterClicked(){
        val strReq = object:StringRequest(
            Method.POST,
            "$WS_HOST/register",
            Response.Listener {

            },
            Response.ErrorListener {

            }
        ){
            override fun getParams(): MutableMap<String,String>? {
                var role : String
                if (spRole.selectedItem.toString()=="Warga"){
                    role = "0"
                }else{
                    role = "1"
                }
                val params = HashMap<String, String>()
                params["email"] = etEmail.text.toString()
                params["fullName"] = etNama.text.toString()
                params["password"] = etPassword.text.toString()
                params["access"] = role
                return params
            }
        }
        val queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(strReq)
    }

    fun btLoginClicked(){
        var go = Intent(this,MainActivity::class.java)
        startActivity(go)
    }
}