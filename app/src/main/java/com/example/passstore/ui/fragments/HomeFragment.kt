package com.example.passstore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.passstore.App
import com.example.passstore.HomeAdapter
import com.example.passstore.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    
    private var _binding: FragmentHomeBinding? = null
    lateinit var adapter: HomeAdapter
    lateinit var convertList: List<App>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}