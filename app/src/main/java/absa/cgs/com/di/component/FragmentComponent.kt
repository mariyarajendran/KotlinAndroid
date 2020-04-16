package absa.cgs.com.di.component


import absa.cgs.com.di.annotation.PerFragment
import absa.cgs.com.di.module.FragmentModule
import absa.cgs.com.ui.screens.authentication.AuthChildfragments.AuthLoginFragment
import dagger.Component

@PerFragment
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(authLoginFragment: AuthLoginFragment)



}

