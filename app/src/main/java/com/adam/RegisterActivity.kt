package com.adam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
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
    val WS_HOST = "https://adam.mikhaelchris.my.id/api"
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
        if (etNama.text.toString().isEmpty() || etEmail.text.toString().isEmpty() || etPassword.text.toString().isEmpty() || etConfirm.text.toString().isEmpty()){
            Toast.makeText(this,"Semua Field Harus Terisi",Toast.LENGTH_SHORT).show()
        }else{
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches()){
                Toast.makeText(this,"Format Email Salah",Toast.LENGTH_SHORT).show()
            }else{
                if (!etPassword.text.toString().equals(etConfirm.text.toString())){
                    Toast.makeText(this,"Password & Confirm Password Harus Sama",Toast.LENGTH_SHORT).show()
                }
                else{
                    val strReq = object:StringRequest(
                        Method.POST,
                        "$WS_HOST/register",
                        Response.Listener {
                            etNama.text.clear()
                            etEmail.text.clear()
                            etPassword.text.clear()
                            etConfirm.text.clear()
                            Toast.makeText(this,"Berhasil Membuat Akun",Toast.LENGTH_SHORT).show()
                        },
                        Response.ErrorListener {
                            Toast.makeText(this,"Panjang password minimal 8",Toast.LENGTH_SHORT).show()
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
            }
        }
    }

    fun btLoginClicked(){
        var go = Intent(this,MainActivity::class.java)
        startActivity(go)
    }
}