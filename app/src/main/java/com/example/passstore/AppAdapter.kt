package com.example.passstore

import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class AppAdapter (var dataSet: List<App>) :
    RecyclerView.Adapter<AppAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon : ImageView
        val name : TextView
        val layout : ConstraintLayout

        init {
            // putting onclicklistener in here is too complicated so well put it somewhere else
            icon = view.findViewById(R.id.imageView_appItem_icon)
            name = view.findViewById(R.id.textView_appItem_name)
            layout = view.findViewById(R.id.layout_appItem)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_app, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val app = dataSet[position]
        viewHolder.icon
        viewHolder.name.text = app.name
        viewHolder.layout.setOnClickListener {
            // Toast.makeText(it.context, "Hi, you clicked on ${hero.name}", Toast.LENGTH_SHORT).show()
            // get the context from something in the viewholder
            val context = viewHolder.layout.context
            // make the intent with context we got
            val heroDetailIntent = Intent(context, AppDetailsActivity::class.java).apply {
                // pass the whole hero object into the intent because it is parclable
                putExtra(AppDetailsActivity.EXTRA_APP, app)
            }
            //context.startActivity(heroDetailIntent)
            context.startActivity(heroDetailIntent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}