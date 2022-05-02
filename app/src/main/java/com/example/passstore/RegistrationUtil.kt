package com.example.passstore

object RegistrationUtil {

    var existingUsers = arrayListOf<String>("tree", "doggy", "unicorn", "boba")
    var existingEmail = arrayListOf<String>("tree@gmail.com", "doggy@gmail.com")

    fun validateUsername(username : String, existingUsers : ArrayList<String>) : Boolean {
        return ((username !in existingUsers) && (username.length >= 3))
    }

    fun validatePassword(password : String, confirmpassword :String): Boolean {
        var minlength : Boolean = false
        var onedigit : Boolean = false
        var onecapital : Boolean = false
        var confirmsame : Boolean = false

        if(password == confirmpassword)
        {
            confirmsame = true
        }
        if(password.length >= 8)
        {
            minlength = true
        }
        for(i in password)
        {
            if(i.isUpperCase())
            {
                onecapital = true
            }
            if(i.isDigit())
            {
                onedigit = true
            }
        }

        return (minlength && onedigit && onecapital && confirmsame)
    }

    fun validateName(name : String) : Boolean {
        return (name != "")
    }

    fun validateEmail(email : String, existingUsers: ArrayList<String>) : Boolean {
        return ((email !in existingUsers) && (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) && (email != ""))
    }
}