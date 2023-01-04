package com.adam

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class LaporanFragment : Fragment() {
    private lateinit var rv: RecyclerView
    private lateinit var listlaporan: ArrayList<Laporan>
    private lateinit var laporanAdapter: LaporanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listlaporan = arrayListOf()
        listlaporan.add(Laporan("dummy","01-01-2002","Surabaya","Ingfo"))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_laporan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv=view.findViewById(R.id.recycler_view)
        val verticalLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        rv.layoutManager = verticalLayoutManager

        laporanAdapter = LaporanAdapter(context as Activity, R.layout.fragment_laporan, listlaporan)
        rv.adapter=laporanAdapter
        rv.addItemDecoration(DividerItemDecoration(rv.context, DividerItemDecoration.VERTICAL))
        laporanAdapter.notifyDataSetChanged()
    }

}