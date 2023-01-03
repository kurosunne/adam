package com.adam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class RegisterActivity : AppCompatActivity() {
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etConfirm: EditText
    lateinit var spRole : Spinner
    lateinit var btLogin: Button
    lateinit var btRegister: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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

    }

    fun btLoginClicked(){
        var go = Intent(this,MainActivity::class.java)
        startActivity(go)
    }
}