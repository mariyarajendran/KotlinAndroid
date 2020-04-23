package absa.cgs.com.ui.screens.authentication

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.authentication.AuthChildfragments.AuthLoginFragment
import absa.cgs.com.ui.screens.base.BaseActivity
import absa.cgs.com.utils.CommonUtils
import android.os.Bundle
import javax.inject.Inject

class AuthenticationBaseActivity : BaseActivity() {
    override fun onClickListeners() {

    }

    private val fragmentManager = supportFragmentManager
    private val authLoginFragment = AuthLoginFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication_base)
        activityComponent().inject(this)
        init()
    }

    override fun init() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.authFragmentLayout, authLoginFragment)
        fragmentTransaction.commit()
    }
}
