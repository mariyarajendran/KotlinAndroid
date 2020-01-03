package absa.cgs.com.di.component


import absa.cgs.com.di.annotation.PerActivity
import absa.cgs.com.di.module.ActivityModule
import absa.cgs.com.ui.screens.mainbaseactivity.MainActivity
import absa.cgs.com.ui.screens.register.CustomerRegister
import dagger.Component


@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(customerRegister: CustomerRegister)


}
