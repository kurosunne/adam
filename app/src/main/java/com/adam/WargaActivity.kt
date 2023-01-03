package com.adam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.NumberFormat
import java.util.*

class WargaActivity : AppCompatActivity() {
    private lateinit var bnView : BottomNavigationView
    private lateinit var hotlineFragment : WargaHotlineFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warga)

        bnView = findViewById(R.id.warga_bnView)

        bnView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.to_berita_warga->{
                    true
                }
                R.id.to_laporan_warga->{
                    true
                }
                R.id.to_tambah_warga->{
                    true
                }
                R.id.to_hotline_warga->{
                    swapToHotlineFragment()
                    true
                }
                R.id.to_akun_warga->{
                    true
                }else->{
                    false
                }
            }
        }
    }

    private fun swapToHotlineFragment(){
        hotlineFragment = WargaHotlineFragment()
        val bundle = Bundle()
        hotlineFragment.arguments = bundle
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.warga_frame, hotlineFragment)
        fragmentManager.commit()
    }
}