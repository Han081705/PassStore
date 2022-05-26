package com.example.passstore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.passstore.DBHelper2
import com.example.passstore.MainActivity
import com.example.passstore.R
import com.example.passstore.databinding.FragmentAddBinding



class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private lateinit var DB : DBHelper2
    private lateinit var appNameInput : String
    private lateinit var appEmailInput : String
    private lateinit var appUsernameInput : String
    private lateinit var appPasswordInput : String

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mainActivity = activity as MainActivity
        DB = DBHelper2(activity)

        update()

        binding.buttonAddSave.setOnClickListener {
            update()
            if(appNameInput != null && appEmailInput != null && appPasswordInput != null && appUsernameInput != null)
            {
                DB.insertUserDetails(mainActivity.getUserId(), appNameInput, "image", appUsernameInput, appPasswordInput, appEmailInput)
                Toast.makeText(activity, "Successfully Added", Toast.LENGTH_SHORT).show()
                Thread.sleep(1000)
                root.findNavController().navigate(R.id.action_addFragment_to_nav_home2)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun update(){
        appNameInput = binding.editTextAddName.text.toString()
        appEmailInput = binding.editTextAddEmail.text.toString()
        appUsernameInput = binding.editTextAddUser.text.toString()
        appPasswordInput = binding.editTextAddPassword.text.toString()
    }
}