package absa.cgs.com.ui.screens.authentication

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.authentication.AuthChildfragments.AuthLoginFragment
import absa.cgs.com.ui.screens.base.BaseActivity
import android.os.Bundle

class AuthenticationBaseActivity : BaseActivity() {

    private val fragmentManager = supportFragmentManager
    private val authLoginFragment = AuthLoginFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication_base)
        init()
    }

    private fun init() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.authFragmentLayout, authLoginFragment)
        fragmentTransaction.commit()
    }
}
