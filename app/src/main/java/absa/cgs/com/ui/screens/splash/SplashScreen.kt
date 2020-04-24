package absa.cgs.com.ui.screens.splash

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.authentication.AuthenticationBaseActivity
import absa.cgs.com.ui.screens.base.BaseActivity
import absa.cgs.com.ui.screens.mainbaseactivity.MainActivity
import android.os.Bundle
import android.os.Handler


class SplashScreen : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        injection()
        init()
    }


    override fun init() {
        forSomeDelay()
    }

    private fun injection() {
        activityComponent().inject(this)
    }


    private fun forSomeDelay() {
        Handler().postDelayed({
            finish()
            when (sessionUtils.loginSessionData) {

                "" -> {
                    navigationRoutes(AuthenticationBaseActivity::class.java)
                }

                null -> {
                    navigationRoutes(AuthenticationBaseActivity::class.java)
                }

                else -> {
                    navigationRoutes(MainActivity::class.java)
                }
            }

        }, 1000)
    }
}
