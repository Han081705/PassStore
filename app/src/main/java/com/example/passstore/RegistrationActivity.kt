package com.example.passstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.passstore.databinding.ActivityRegistrationBinding
import android.widget.Toast
import android.content.Intent


class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegistrationBinding
    private lateinit var nameUser : String
    private lateinit var usernameRegister : String
    private lateinit var passwordRegister : String
    private lateinit var emailRegister : String
    private lateinit var confirmPassword : String
    private lateinit var userInfoList: ArrayList<Users>
    private var emailList = arrayListOf<String>()
    private var usernameList = arrayListOf<String>()
    private var DB : DBHelper1 = DBHelper1(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        update()

        binding.buttonRegisterCompleteRegister.setOnClickListener {
            update()

            if(RegistrationUtil.validatePassword(passwordRegister, confirmPassword) && RegistrationUtil.validateEmail(
                    emailRegister, emailList) && RegistrationUtil.validateName(nameUser) && RegistrationUtil.validateUsername(
                    usernameRegister, usernameList) && writeJSONtoFile(usernameRegister, passwordRegister)){
                binding.textRegisterIncorrectCondition.text = ""
                Toast.makeText(this, "Successfully Created", Toast.LENGTH_SHORT).show()
                Thread.sleep(1000)
                loginActivity()
            }
            else{
                if(!RegistrationUtil.validateName(nameUser)){
                    binding.textRegisterIncorrectCondition.text = "Name is not filled in"
                }
                else if(!RegistrationUtil.validateUsername(usernameRegister, usernameList)){
                    binding.textRegisterIncorrectCondition.text = "Username does not match condition or already used"
                }
                else if(!RegistrationUtil.validateEmail(emailRegister, emailList)){
                    binding.textRegisterIncorrectCondition.text = "Email is not filled in or does not have correct domain or already used"
                }
                else if(!RegistrationUtil.validatePassword(passwordRegister, confirmPassword)){
                    binding.textRegisterIncorrectCondition.text = "Password does not match the condition or Confirm Password"
                }
            }
        }
    }

    fun update()
    {
        nameUser = binding.editTextRegisterName.text.toString()
        usernameRegister = binding.editTextRegisterUsername.text.toString()
        emailRegister = binding.emailRegisterEmail.text.toString()
        passwordRegister = binding.passwordRegisterPassword.text.toString()
        confirmPassword = binding.passwordRegisterConfirmPassword.text.toString()
    }

    private fun writeJSONtoFile(username : String, password : String) : Boolean{
        var checkInsertData = DB.insertData(username, password)
        return checkInsertData
    }

    private fun loginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}