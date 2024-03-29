package com.adams

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class EditAkunPolisiFragment : Fragment() {
    private lateinit var btEdit: Button
    private lateinit var etEmail: EditText
    private lateinit var etNama: EditText
    private lateinit var btLogout: Button
    private lateinit var etPassword: EditText
    private var oldemail=""
    private var newemail=""
    val WS_HOST = "https://adam.mikhaelchris.my.id/api"
    lateinit var db : AppDatabase
    val coroutine = CoroutineScope(Dispatchers.IO)

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
        btLogout = view.findViewById(R.id.btLogoutPolisi)
        etPassword = view.findViewById(R.id.etPasswordPolisi)
        db = AppDatabase.build(view.context)

        coroutine.launch{
            var temp : List<UserEntity> = db.userDao.fetch()
            etEmail.setText(temp.get(0).email)
            etNama.setText(temp.get(0).fullName)
            oldemail = etEmail.text.toString()
        }

        btEdit.setOnClickListener{
            if(etNama.toString().isEmpty() || etEmail.toString().isEmpty()){
                Toast.makeText(view.context,"Semua Field Harus Terisi", Toast.LENGTH_SHORT).show()
            }
            else{
                val strReq = object:StringRequest(
                    Method.PUT,
                    "$WS_HOST/user",
                    Response.Listener {
//                        coroutine.launch{
//                            var temp: List<UserEntity> = db.userDao.fetch()
//                            etEmail.setText(temp.get(0).email)
//                            etNama.setText(temp.get(0).fullName)
//                        }
                        etEmail.setText(newemail)
                        Toast.makeText(view.context,"Berhasil update akun",Toast.LENGTH_SHORT).show()
                    },
                    Response.ErrorListener {
                        Toast.makeText(view.context, it.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                ){
                    override fun getParams(): MutableMap<String,String>? {
                        val params = HashMap<String, String>()
                        params["oldEmail"] = oldemail
                        params["email"] = etEmail.text.toString()
                        newemail = etEmail.text.toString()
                        params["password"] = etPassword.text.toString()
                        params["fullName"] = etNama.text.toString()
                        return params
                    }
                }
                val queue: RequestQueue = Volley.newRequestQueue(view.context)
                queue.add(strReq)
            }
        }

        btLogout.setOnClickListener{
            coroutine.launch {
                db.userDao.deleteAll()
                activity?.let{
                    val intent = Intent (it, MainActivity::class.java)
                    it.startActivity(intent)
                }
            }
        }
    }
}