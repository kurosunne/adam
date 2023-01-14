package com.adams

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class WargaHotlineFragment : Fragment() {
    lateinit var btPolisi : Button
    lateinit var btPemadam : Button
    lateinit var btSAR : Button
    lateinit var btAmbulan : Button
    lateinit var btListrik : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btPolisi = view.findViewById(R.id.warga_hotline_btPolisi)
        btPemadam = view.findViewById(R.id.warga_hotline_btPemadam)
        btSAR = view.findViewById(R.id.warga_hotline_btSAR)
        btAmbulan = view.findViewById(R.id.warga_hotline_btAmbulan)
        btListrik = view.findViewById(R.id.warga_hotline_btListrik)

        btPolisi.setOnClickListener {
            callNumber("110")
        }
        btPemadam.setOnClickListener {
            callNumber("113")
        }
        btSAR.setOnClickListener {
            callNumber("115")
        }
        btAmbulan.setOnClickListener {
            callNumber("118")
        }
        btListrik.setOnClickListener {
            callNumber("123")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_warga_hotline, container, false)
    }

    fun callNumber(num:String){
        val number: Uri = Uri.parse("tel:"+num)
        val callIntent = Intent(Intent.ACTION_DIAL, number)
        startActivity(callIntent)
    }
}