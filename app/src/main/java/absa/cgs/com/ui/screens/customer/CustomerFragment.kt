package absa.cgs.com.ui.screens.customer

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.register.CustomerRegister
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CustomerFragment : Fragment() {

    private var floatingActionButton: FloatingActionButton? = null
    private var imageViewss: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_customer_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingActionButton = view.findViewById(R.id.fcCustomerRegisterFActionButton) as FloatingActionButton


        customerRegistration()
    }

    private fun customerRegistration() {
        floatingActionButton?.setOnClickListener {
            val intent = Intent(activity, CustomerRegister::class.java)
            startActivity(intent)

        }


    }

}
