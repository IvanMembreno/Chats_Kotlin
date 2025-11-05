package com.example.chats_kotlin.Fragmentos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.chats_kotlin.Constantes
import com.example.chats_kotlin.OpcionesLoginActivity
import com.example.chats_kotlin.R
import com.example.chats_kotlin.databinding.ActivityMainBinding
import com.example.chats_kotlin.databinding.FragmentPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FragmentPerfil : Fragment() {

    private lateinit var binding: FragmentPerfilBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentPerfilBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnCerrarSesion.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(context, OpcionesLoginActivity::class.java))
            activity?.finishAffinity()
        }
        return binding.root
    }

    private fun cargarInformacion() {
        var ref = FirebaseDatabase.getInstance().getReference("Usuarios")
        ref.child(("${firebaseAuth}"))
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val nombres = "${snapshot.child("nombres").value}"
                    val email = "${snapshot.child("email").value}"
                    val proveedor = "${snapshot.child("proveedor").value}"
                    var t_register = "${snapshot.child("tiempoR").value}"
                    val imagen = "${snapshot.child("imagen").value}"

                    // Condicion
                    if(t_register == "null") {
                        t_register = "0"
                    }

                    // CONVERSION A FECHA
                    val fecha = Constantes.formatoFecha(t_register.toLong())

                    // SETTEAR
                    binding.txtNombres.text = nombres
                    binding.txtEmail.text = email
                    binding.txtTRegistro.text = proveedor
                    binding.txtProveedor.text = fecha

                    try {
                        Glide.with(mContext)
                            .load(imagen)
                            .placeholder(R.drawable.icon_image_perfil)
                            .into(binding.ivPerfil)

                    }catch (e: Exception) {
                        Toast.makeText(mContext, "${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }
}