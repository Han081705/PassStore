package com.example.passstore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.passstore.App
import com.example.passstore.DBHelper2
import com.example.passstore.MainActivity
import com.example.passstore.R
import com.example.passstore.databinding.FragmentDetailsBinding


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private lateinit var DB : DBHelper2

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mainActivity = activity as MainActivity
        DB = DBHelper2(activity)

        val app: App? = arguments?.getParcelable(HomeFragment.BUNDLE_DETAIL_FRAGMENT)

        binding.textVIewAppDetailsName.text = app?.name
        binding.textViewAppDetailsEmail.text = app?.appEmail
        binding.textViewAppDetailsPassword.text = app?.appPass
        binding.textViewAppDetailsUser.text = app?.appUser

        binding.buttonDetailFragmentDelete.setOnClickListener {
            DB.deleteUserDetails(mainActivity.getUserId(), app?.name, app?.appUser, app?.appPass, app?.appEmail)
            root.findNavController().navigate(R.id.action_nav_details_to_nav_home)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}