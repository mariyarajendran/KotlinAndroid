package absa.cgs.com.ui.screens.register

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.base.BaseActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_customer_register.*

class CustomerRegister : BaseActivity() {

    var moreDetailsBool: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBarTitle()
        setContentView(R.layout.activity_customer_register)
        init()


    }


    private fun init() {
        cardview_more_details_badge?.setOnClickListener {
            moreDetailsVisibility()
        }
    }


    private fun moreDetailsVisibility() {
        if (moreDetailsBool) {
            cardview_more_details.visibility = View.VISIBLE
            updateMoreDetailBool(false, resources.getString(R.string.hide_more_details))
        } else {
            cardview_more_details.visibility = View.GONE
            updateMoreDetailBool(true, resources.getString(R.string.show_more_details))
        }
    }


    private fun updateMoreDetailBool(boolean: Boolean, textTitle: String) {
        moreDetailsBool = boolean
        textview_show_more_detail_badge.setText(textTitle)
    }

    private fun setActionBarTitle() {
        setTitle(R.string.add_customer)
    }

}
