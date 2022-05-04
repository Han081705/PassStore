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
    private lateinit var DB: DBHelper1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var username = binding.plaintextLoginUsername.text.toString()
        var password = binding.passwordLoginPassword.text.toString()
        var btnlogin = binding.buttonLoginLogin
        DB = DBHelper1(this)

        btnlogin.setOnClickListener{
            Log.d("TAG", "onCreate: ${username}, ${password}")
            Log.d("TAG", "onCreate: ${DB.checkusernamepassword(username, password)}")
            update()
            if (username == "" || password == "")
            {
                Toast.makeText(this@LoginActivity, "Please enter all the fields", Toast.LENGTH_SHORT).show()
            }
            else {
                val checkuserpass = DB.checkusernamepassword(username, password)
                if (checkuserpass) {
                    Toast.makeText(this@LoginActivity, "Sign in successfull", Toast.LENGTH_SHORT)
                        .show()
                    val intent1 = Intent(this, MainActivity::class.java)
                    startActivity(intent1)
                }
            }
        }

        binding.buttonLoginRegister.setOnClickListener {
            registerActivity()
        }
    }

    private fun update(){
        var username = binding.plaintextLoginUsername.text.toString()
        var password = binding.passwordLoginPassword.text.toString()
    }

    private fun registerActivity() {
        val intent2 = Intent(this, RegistrationActivity::class.java)
        startActivity(intent2)
    }
}