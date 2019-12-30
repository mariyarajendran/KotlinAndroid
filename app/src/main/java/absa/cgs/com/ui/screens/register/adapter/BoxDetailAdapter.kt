package absa.cgs.com.ui.screens.register.adapter

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.mainbaseactivity.OnListItemClickInterface
import absa.cgs.com.ui.screens.register.callbacks.OnItemDeleteCallBack
import absa.cgs.com.ui.screens.register.model.BoxDetailsDataModel
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class BoxDetailAdapter(internal var activity: Activity, private var boxDetailsDataModel: MutableList<BoxDetailsDataModel>, var onItemDeleteCallBack: OnItemDeleteCallBack) : RecyclerView.Adapter<BoxDetailAdapter.DemoAdapter>() {


    inner class DemoAdapter(view: View) : RecyclerView.ViewHolder(view) {
        var boxName: TextView
        var boxNumber: TextView
        var boxType: TextView
        var smartCardNo: TextView
        var deleteItem: ImageView

        init {
            boxName = view.findViewById(R.id.customBoxDetailBoxName) as TextView
            boxNumber = view.findViewById(R.id.customBoxDetailBoxNumber) as TextView
            boxType = view.findViewById(R.id.customBoxDetailBoxType) as TextView
            smartCardNo = view.findViewById(R.id.customBoxDetailSmartCardNO) as TextView
            deleteItem = view.findViewById(R.id.customBoxDetailImageViewDelete) as ImageView
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoAdapter {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_boxdetail_layout, parent, false)
        return DemoAdapter(itemView)
    }

    override fun onBindViewHolder(holder: DemoAdapter, position: Int) {
        holder.boxName.setText(boxDetailsDataModel.get(position).boxName)
        holder.boxNumber.setText(boxDetailsDataModel.get(position).boxNo)
        holder.boxType.setText(boxDetailsDataModel.get(position).boxType)
        holder.smartCardNo.setText(boxDetailsDataModel.get(position).securityDeposite)
        holder.deleteItem.setOnClickListener {
            onItemDeleteCallBack.onItemDeleteListener(position)
        }
    }


    override fun getItemCount(): Int {
        return boxDetailsDataModel.size
    }

    fun notifyNewData(boxDetailsDataModel: MutableList<BoxDetailsDataModel>) {
        this.boxDetailsDataModel = boxDetailsDataModel
        notifyDataSetChanged()
    }
}