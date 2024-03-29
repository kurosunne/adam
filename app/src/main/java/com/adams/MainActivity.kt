package com.adams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var etEmail:EditText
    lateinit var etPassword:EditText
    lateinit var btLogin:Button
    lateinit var btRegister:Button
    val WS_HOST = "https://adam.mikhaelchris.my.id/api"
    lateinit var db : AppDatabase
    val coroutine = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById(R.id.login_etEmail)
        etPassword = findViewById(R.id.login_etPassword)
        btLogin = findViewById(R.id.login_btLogin)
        btRegister = findViewById(R.id.login_btRegister)
        db = AppDatabase.build(this)

        var goWarga = Intent(this,WargaActivity::class.java)
        var goPolisi = Intent(this,PolisiActivity::class.java)
        coroutine.launch {
            if (db.userDao.getLength()>0){
                var temp : List<UserEntity> = db.userDao.fetch()
                if (temp.get(0).access.equals("0")){
                    startActivity(goWarga)
                }else{
                    startActivity(goPolisi)
                }
            }
        }

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
                        Log.d("tes",it.toString())
                        var obj : JSONObject = JSONObject(it.toString())
                        Toast.makeText(this, obj.getString("message"), Toast.LENGTH_SHORT).show()
                        if (obj.getString("message").equals("logged in")){
                            coroutine.launch {
                                if (db.userDao.getLength()>0){
                                    db.userDao.deleteAll()
                                }
                                var temp : UserEntity = UserEntity(obj.getString("email"), obj.getString("fullName"), obj.getString("access"))
                                db.userDao.insert(temp)
                            }
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