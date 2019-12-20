package absa.cgs.com.ui.screens.customer

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.register.CustomerRegister
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_customer_screen.*


class CustomerFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_customer_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customerRegistration()
    }

    private fun customerRegistration() {
        fcCustomerRegisterFActionButton.setOnClickListener {
            val intent = Intent(activity, CustomerRegister::class.java)
            startActivity(intent)

        }


    }

}
