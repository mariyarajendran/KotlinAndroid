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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBarTitle()
        setContentView(R.layout.activity_customer_register)
        init()


    }


    private fun init() {
        cardview_more_details_badge?.setOnClickListener {
            moreDetailsVisibility(1)
        }

        cardview_box_details_badge.setOnClickListener {
            moreDetailsVisibility(2)
        }
    }


    private fun moreDetailsVisibility(event: Int) {
        when (event) {
            1 -> {
                //event one (1) Hide and show more details
                if (moreDetailsBool) {
                    cardview_more_details.visibility = View.VISIBLE
                    updateMoreDetailBool(false, resources.getString(R.string.hide_more_details), event)
                } else {
                    cardview_more_details.visibility = View.GONE
                    updateMoreDetailBool(true, resources.getString(R.string.show_more_details), event)
                }
            }

            2 -> {
                //event one (1) Hide and show box details
                if (boxDetailsBool) {
                    cardview_box_details.visibility = View.VISIBLE
                    updateMoreDetailBool(false, resources.getString(R.string.hide_Box_Details), event)
                } else {
                    cardview_box_details.visibility = View.GONE
                    updateMoreDetailBool(true, resources.getString(R.string.Show_Box_Details), event)
                }
            }
        }
    }


    private fun updateMoreDetailBool(boolean: Boolean, textTitle: String, event: Int) {
        when (event) {
            //event one (1) more details
            1 -> {
                moreDetailsBool = boolean
                textview_show_more_detail_badge.setText(textTitle)
            }
            //event one (1) box details
            2 -> {
                boxDetailsBool = boolean
                textview_show_box_detail_badge.setText(textTitle)
            }
        }


    }

    private fun setActionBarTitle() {
        setTitle(R.string.add_customer)
    }

}
