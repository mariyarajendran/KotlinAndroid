package absa.cgs.com.ui.screens.register

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.base.BaseActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_customer_register.*

class CustomerRegister : BaseActivity() {

    var moreDetailsBool: Boolean = true
    var boxDetailsBool: Boolean = true
    var billingDetailsBool: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBarTitle()
        setContentView(R.layout.activity_customer_register)
        init()


    }


    private fun init() {
        cardviewMoreDetailsBadge?.setOnClickListener {
            moreDetailsVisibility(1)
        }

        cardviewBoxDetailsBadge.setOnClickListener {
            moreDetailsVisibility(2)
        }

        cardviewBillingDetailsBadge.setOnClickListener {
            moreDetailsVisibility(3)
        }
    }


    private fun moreDetailsVisibility(event: Int) {
        when (event) {
            1 -> {
                //event one (1) Hide and show more details
                if (moreDetailsBool) {
                    cardviewMoreDetails.visibility = View.VISIBLE
                    updateMoreDetailBool(false, resources.getString(R.string.hide_more_details), event)
                } else {
                    cardviewMoreDetails.visibility = View.GONE
                    updateMoreDetailBool(true, resources.getString(R.string.show_more_details), event)
                }
            }

            2 -> {
                //event one (1) Hide and show box details
                if (boxDetailsBool) {
                    cardviewBoxDetails.visibility = View.VISIBLE
                    updateMoreDetailBool(false, resources.getString(R.string.hide_Box_Details), event)
                } else {
                    cardviewBoxDetails.visibility = View.GONE
                    updateMoreDetailBool(true, resources.getString(R.string.Show_Box_Details), event)
                }
            }

            3 -> {
                //event one (1) Hide and show box details
                if (billingDetailsBool) {
                    cardviewBillingDetails.visibility=View.VISIBLE
                    updateMoreDetailBool(false, resources.getString(R.string.hide_billing_details), event)
                } else {
                    cardviewBillingDetails.visibility=View.GONE
                    updateMoreDetailBool(true, resources.getString(R.string.show_billing_detail), event)
                }
            }
        }
    }


    private fun updateMoreDetailBool(boolean: Boolean, textTitle: String, event: Int) {
        when (event) {
            //event one (1) more details
            1 -> {
                moreDetailsBool = boolean
                textviewShowMoreDetailBadge.setText(textTitle)
            }
            //event one (1) box details
            2 -> {
                boxDetailsBool = boolean
                textviewShowBoxDetailBadge.setText(textTitle)
            }
            3 -> {
                billingDetailsBool = boolean
                textviewShowBillingDetailBadge.setText(textTitle)
            }
        }


    }

    private fun setActionBarTitle() {
        setTitle(R.string.add_customer)
    }

}
