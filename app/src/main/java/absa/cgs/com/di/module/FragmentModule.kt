package absa.cgs.com.di.module

import absa.cgs.com.di.annotation.ActivityContext
import android.content.Context
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule constructor(private val fragment: Fragment) {

    @Provides
    internal fun provideFragment(): Fragment = fragment

    @Provides
    @ActivityContext
    internal fun provideFragmentContext(): Context = fragment.context!!
}