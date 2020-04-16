package absa.cgs.com.di.component


import absa.cgs.com.di.annotation.PerActivity
import absa.cgs.com.di.module.ActivityModule
import absa.cgs.com.ui.screens.mainbaseactivity.MainActivity
import absa.cgs.com.ui.screens.register.CustomerRegister
import absa.cgs.com.ui.screens.splash.SplashScreen
import absa.cgs.com.utils.CommonUtils
import absa.cgs.com.utils.DialogUtils
import dagger.Component


@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(customerRegister: CustomerRegister)
    fun inject(splashScreen: SplashScreen)


    fun commonUtils(): CommonUtils
    fun dialogUtils(): DialogUtils



}
