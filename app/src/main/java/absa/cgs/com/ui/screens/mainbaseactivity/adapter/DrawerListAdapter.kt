package absa.cgs.com.ui.screens.mainbaseactivity.adapter

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.mainbaseactivity.MainModel
import absa.cgs.com.ui.screens.mainbaseactivity.Model.NavigationDataModel
import absa.cgs.com.ui.screens.mainbaseactivity.OnListItemClickInterface
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DrawerListAdapter(internal var activity: Activity, private val mypojos: List<NavigationDataModel>, val onListItemClickInterface: OnListItemClickInterface) : RecyclerView.Adapter<DrawerListAdapter.DemoAdapter>() {

    var mainModel: MainModel? = null
    var rowIndex = 0

    inner class DemoAdapter(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var linearLayout: LinearLayout


        init {
            title = view.findViewById(R.id.tv_title) as TextView
            linearLayout = view.findViewById(R.id.lv_drawer_item) as LinearLayout
            mainModel = MainModel()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoAdapter {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_drawer_item, parent, false)
        return DemoAdapter(itemView)
    }

    override fun onBindViewHolder(holder: DemoAdapter, position: Int) {
        holder.title.setText(mypojos.get(position).navigationTitle)
        bindTextColorOnClick(position, holder)

        holder.linearLayout.setOnClickListener {
            assignRowIndex(position)
            onListItemClickInterface.OnSelectedItemClickListener(mypojos.get(position).navigationTitle!!, position)
            notifyDataSetChanged()
            //notifyItemChanged(position)
        }

    }

    private fun assignRowIndex(position: Int) {
        rowIndex = position
    }

    private fun bindTextColorOnClick(position: Int, holder: DemoAdapter) {
        if (rowIndex == position) {
            holder.title.setTextColor(activity.resources.getColor(R.color.colorSecondary))
        } else {
            holder.title.setTextColor(activity.resources.getColor(R.color.colorWhite))
        }
    }


    override fun getItemCount(): Int {
        return mypojos.size
    }
}