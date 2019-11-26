package absa.cgs.com.screens.MainBaseActivity

import absa.cgs.com.kotlinplayground.R
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class DrawerListAdapter(internal var activity: Activity, internal var titles: ArrayList<String>) : BaseAdapter() {

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }// TODO Auto-generated constructor stub

    override fun getCount(): Int {
        // TODO Auto-generated method stub
        return titles.size
    }

    override fun getItem(position: Int): Any {
        // TODO Auto-generated method stub
        return position
    }

    override fun getItemId(position: Int): Long {
        // TODO Auto-generated method stub
        return position.toLong()
    }

    inner class Holder {
        internal var tv_title: TextView? = null
        internal var im_icon: ImageView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // TODO Auto-generated method stub
        val holder = Holder()
        val view: View
        view = inflater!!.inflate(R.layout.layout_drawer_item, null)

        holder.tv_title = view.findViewById<View>(R.id.tv_title) as TextView
        holder.tv_title!!.text = titles[position]
        return view
    }

    companion object {
        private var inflater: LayoutInflater? = null
    }

}