package absa.cgs.com.ui.screens.expense.expensechildfragment.expensedetails.adapter

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.apis.readexpenseapicall.model.ReadExpenseResponseModel
import absa.cgs.com.ui.screens.mainbaseactivity.OnListItemClickInterface
import absa.cgs.com.utils.CommonEnumUtils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class ExpenseDetailsAdapter(context: Context, private val readExpenseResponseModel: ReadExpenseResponseModel, val onListItemClickInterface: OnListItemClickInterface) : RecyclerView.Adapter<ExpenseDetailsAdapter.DemoAdapter>() {


    inner class DemoAdapter(view: View) : RecyclerView.ViewHolder(view) {
        var customExpenseDetailsCommentsTv: TextView
        var customExpenseDetailsAmountTv: TextView
        var customExpenseDetailsCrd: CardView
        var customExpenseDetailsDeleteImg: ImageView


        init {
            customExpenseDetailsCommentsTv = view.findViewById(R.id.customExpenseDetailsCommentsTv) as TextView
            customExpenseDetailsAmountTv = view.findViewById(R.id.customExpenseDetailsAmountTv) as TextView
            customExpenseDetailsCrd = view.findViewById(R.id.customExpenseDetailsCrd) as CardView
            customExpenseDetailsDeleteImg = view.findViewById(R.id.customExpenseDetailsDeleteImg) as ImageView
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoAdapter {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_expense_details_list, parent, false)
        return DemoAdapter(itemView)
    }

    override fun onBindViewHolder(holder: DemoAdapter, position: Int) {
        holder.customExpenseDetailsCommentsTv.text = (readExpenseResponseModel.product_details.get(position).expense_comment)
        holder.customExpenseDetailsAmountTv.text = ("Rs. " + readExpenseResponseModel.product_details.get(position).expense_amount)

        holder.customExpenseDetailsCrd.setOnClickListener {
            onListItemClickInterface.OnSelectedItemClickListener(CommonEnumUtils.VIEW.toString(), position)
        }

        holder.customExpenseDetailsDeleteImg.setOnClickListener {
            onListItemClickInterface.OnSelectedItemClickListener(CommonEnumUtils.DELETE.toString(), position)
        }


    }


    override fun getItemCount(): Int {
        return readExpenseResponseModel.product_details.size
    }
}
