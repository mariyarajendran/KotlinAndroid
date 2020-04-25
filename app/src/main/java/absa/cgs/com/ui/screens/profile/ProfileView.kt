package absa.cgs.com.ui.screens.profile

import android.os.Bundle

interface ProfileView {


    fun setActionBarTitle(string: Int)
    fun changeFragment(position: Int, bundle: Bundle)
}