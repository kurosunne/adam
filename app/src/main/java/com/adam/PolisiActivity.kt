package com.adam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class PolisiActivity : AppCompatActivity() {
    private lateinit var navbar: BottomNavigationView
    private lateinit var fragmentLaporan: LaporanFragment
    private lateinit var fragmentEditakun: EditAkunPolisiFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polisi)

        navbar = findViewById(R.id.navbar_police)
        fragmentLaporan()


        navbar.setOnItemSelectedListener{
            when(it.itemId){
                R.id.to_laporan_polisi->{
                fragmentLaporan()
                }
                R.id.to_edit_account_polisi->{
                fragmentEditAkun()
                }
            }
            true
        }
    }

    fun fragmentLaporan(){
        val fragmentManager = supportFragmentManager.beginTransaction()

        fragmentLaporan = LaporanFragment()

        fragmentManager.replace(R.id.PolisiFragment, fragmentLaporan)
        fragmentManager.commit()
    }

    fun fragmentEditAkun(){
        val fragmentManager = supportFragmentManager.beginTransaction()

        fragmentEditakun = EditAkunPolisiFragment()

        fragmentManager.replace(R.id.PolisiFragment, fragmentEditakun)
        fragmentManager.commit()
    }

}