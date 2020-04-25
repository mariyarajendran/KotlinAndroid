package absa.cgs.com.ui.screens.profile.profilechildfragment.updatebank

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.base.BaseFragment
import absa.cgs.com.ui.screens.profile.ProfileBaseActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class UpdateBankFragment : BaseFragment(), ProfileBaseActivity.OnBackPressedListner {


    lateinit var profileBaseActivity: ProfileBaseActivity

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentComponent().inject(this)
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.updatebankfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    override fun init() {
        profileBaseActivity = activity as ProfileBaseActivity
        onClickListeners()
    }

    override fun onBackPressed(): Boolean {
        activity!!.finish()
        return true
    }
}