package com.example.passstore

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.passstore.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private var DB : DBHelper1 = DBHelper1(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLoginLogin.setOnClickListener{
            var res : Cursor = DB.getData()
//            if(res.getString(0).equals(binding.plaintextLoginUsername.text)) {
//                val intent1 = Intent(this, MainActivity::class.java)
//                startActivity(intent1)
//            }else{
//                Toast.makeText(this, "Information is Incorrect", Toast.LENGTH_SHORT).show()
//            }
            Log.d("TAG", "onCreate: ${res.getString(0)}")

        }

        binding.buttonLoginRegister.setOnClickListener {
            registerActivity()
        }
    }

    private fun registerActivity() {
        val intent2 = Intent(this, RegistrationActivity::class.java)
        startActivity(intent2)
    }
}