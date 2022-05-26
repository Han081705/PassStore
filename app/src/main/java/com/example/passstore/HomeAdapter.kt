package com.example.passstore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.passstore.ui.fragments.HomeFragment
import com.squareup.picasso.Picasso

class HomeAdapter (var dataSet: List<App>) :

    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon : ImageView
        val name : TextView
        val layout : ConstraintLayout

        init {
            icon = view.findViewById(R.id.imageView_appItem_icon)
            name = view.findViewById(R.id.textView_appItem_name)
            layout = view.findViewById(R.id.layout_appItem)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_app, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val app = dataSet[position]
        Picasso.get().load(app.icon).into(viewHolder.icon)
        viewHolder.name.text = app.name
        viewHolder.layout.setOnClickListener {
            val bundle = Bundle()
            //TODO: find out how to make the navigation work i think you need to get the binding from homefragment in here
            bundle.putParcelable(HomeFragment.BUNDLE_DETAIL_FRAGMENT, app)
            viewHolder.itemView.findNavController().navigate(R.id.action_nav_home_to_nav_details, bundle)
//            val context = viewHolder.layout.context
//            val appDetailIntent = Intent(context, AppDetailsActivity::class.java).apply {
//                putExtra(AppDetailsActivity.EXTRA_APP, app)
//            }
//            context.startActivity(appDetailIntent)
        }
    }
    override fun getItemCount() = dataSet.size

}