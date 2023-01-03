package com.adam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class PolisiActivity : AppCompatActivity() {
    private lateinit var navbar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polisi)

        navbar = findViewById(R.id.navbar_police)

        navbar.setOnItemSelectedListener{
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