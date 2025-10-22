package com.example.chats_kotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chats_kotlin.databinding.EmailLoginAntivityBinding

class LoginEmailActivity1 : AppCompatActivity() {

    private lateinit var binding: EmailLoginAntivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmailLoginAntivityBinding.inflate((layoutInflater))
        setContentView(binding.root)
//        enableEdgeToEdge()

    }
}