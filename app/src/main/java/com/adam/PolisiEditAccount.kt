package com.adam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView

class PolisiEditAccount : AppCompatActivity() {
    private lateinit var navbar: BottomNavigationView
    private lateinit var editName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polisi_edit_account)

        navbar = findViewById(R.id.navbar_edit_police)
        editName = findViewById(R.id.editNamaPolisi)


        navbar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.to_laporan_polisi->{

                }
                R.id.to_edit_account_polisi->{

                }
                R.id.do_logout_polisi->{

                }
            }
            true
        }
    }
}