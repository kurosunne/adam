package com.adam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditAkunPolisiFragment : Fragment() {
    private lateinit var btEdit: Button
    private lateinit var etEmail: EditText
    private lateinit var etNama: EditText
    private lateinit var etPassword: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_akun_polisi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btEdit = view.findViewById(R.id.btEditAkunPolisi)
        etEmail = view.findViewById(R.id.etEmailPolisi)
        etNama = view.findViewById(R.id.etNamaPolisi)
        etPassword = view.findViewById(R.id.etPasswordPolisi)

        btEdit.setOnClickListener{
            if(etNama.toString().isEmpty() || etEmail.toString().isEmpty() || etPassword.toString().isEmpty()){
                Toast.makeText(activity,"Semua Field Harus Terisi", Toast.LENGTH_SHORT).show()
            }
        }
    }

}