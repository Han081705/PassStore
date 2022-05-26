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
    private lateinit var username : String
    private lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = binding.plaintextLoginUsername.text.toString()
        password = binding.passwordLoginPassword.text.toString()
        var btnlogin = binding.buttonLoginLogin
        DB = DBHelper1(this)

        btnlogin.setOnClickListener{
            update()
            Log.d("TAG", "onCreate: ${username}, ${password}")
            Log.d("TAG", "onCreate: ${DB.checkusernamepassword(username, password)}")
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
                    intent1.putExtra("personid", username)
                    startActivity(intent1)
                }
            }
        }

        binding.buttonLoginRegister.setOnClickListener {
            registerActivity()
        }
    }

    fun append(arr: Array<String>, element: String): Array<String> {
        val list: MutableList<String> = arr.toMutableList()
        list.add(element)
        return list.toTypedArray()
    }

    private fun update(){
        username = binding.plaintextLoginUsername.text.toString()
        password = binding.passwordLoginPassword.text.toString()
    }

    private fun registerActivity() {
        val intent2 = Intent(this, RegistrationActivity::class.java)
        startActivity(intent2)
    }
}