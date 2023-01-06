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
    private lateinit var laporagnwargaFragment: LaporanWargaFragment
    private lateinit var tambahlaporanFragment: TambahLaporanFragment
    private lateinit var editakunFragment: EditAkunWargaFragment
    private lateinit var homeFragment : HomeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warga)

        bnView = findViewById(R.id.warga_bnView)
        swapToHomeFragment()

        bnView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.to_berita_warga->{
                    swapToHomeFragment()
                    true
                }
                R.id.to_laporan_warga->{
                    swaptoLaporanFragment()
                    true
                }
                R.id.to_tambah_warga->{
                    swaptoTambahLaporanFragment()
                    true
                }
                R.id.to_hotline_warga->{
                    swapToHotlineFragment()
                    true
                }
                R.id.to_akun_warga->{
                    swaptoeditakunFragment()
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

    private fun swapToHomeFragment(){
        homeFragment = HomeFragment()
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.warga_frame, homeFragment)
        fragmentManager.commit()
    }

    private fun swaptoLaporanFragment(){
        laporagnwargaFragment = LaporanWargaFragment()
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.warga_frame, laporagnwargaFragment)
        fragmentManager.commit()
    }

    private fun swaptoTambahLaporanFragment(){
        tambahlaporanFragment = TambahLaporanFragment()
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.warga_frame, tambahlaporanFragment)
        fragmentManager.commit()
    }

    private fun swaptoeditakunFragment(){
        editakunFragment = EditAkunWargaFragment()
        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.warga_frame, editakunFragment)
        fragmentManager.commit()
    }
}