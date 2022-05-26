package com.example.passstore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.passstore.*
import com.example.passstore.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var adapter: HomeAdapter
    private var layoutManager: RecyclerView.LayoutManager? = null
    private val binding get() = _binding!!
    private lateinit var DB: DBHelper2

    companion object {
        val TAG = "HomeFragment"
        val BUNDLE_DETAIL_FRAGMENT = "go to detail frag"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.floatingActionButton.setOnClickListener {
            root.findNavController().navigate(R.id.action_nav_home_to_addFragment)
        }
        
        return root
    }

    override fun onResume() {
        super.onResume()

        val mainActivity = activity as MainActivity
        mainActivity.update()
        DB = DBHelper2(context)

        val companion = "what's up"

        adapter = HomeAdapter(mainActivity.getDataList())
        binding.recyclerViewHomeList.adapter = adapter
        binding.recyclerViewHomeList.layoutManager = LinearLayoutManager(activity)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun append(list: List<App>, element: App): List<App> {
        val list: MutableList<App> = list.toMutableList()
        list.add(element)
        return list.toList()
    }
}