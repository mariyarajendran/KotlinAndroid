package absa.cgs.com.ui.screens.register

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.base.BaseActivity
import absa.cgs.com.ui.screens.mainbaseactivity.MainView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CustomerRegister : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBarTitle()
        setContentView(R.layout.activity_customer_register)
    }


    private fun setActionBarTitle() {
        setTitle(R.string.add_customer)
    }

}
