package absa.cgs.com.ui.screens.profile

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.base.BaseActivity
import absa.cgs.com.ui.screens.profile.profilechildfragment.updatebank.UpdateBankFragment
import absa.cgs.com.ui.screens.profile.profilechildfragment.updatebank.ViewBankProofFragment
import absa.cgs.com.ui.screens.profile.profilechildfragment.updatenominee.UpdateNomineeFragment
import absa.cgs.com.ui.screens.profile.profilechildfragment.updateprofile.UpdateProfileFragment
import absa.cgs.com.utils.CommonEnumUtils
import android.os.Bundle

class ProfileBaseActivity : BaseActivity(), ProfileView {

    private val fragmentManager = supportFragmentManager
    private val updateProfileFragment = UpdateProfileFragment()
    private val updateNomineeFragment = UpdateNomineeFragment()
    private val updateBankFragment = UpdateBankFragment()
    private val viewBankProofFragment = ViewBankProofFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_base)
        init()
    }

    override fun init() {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val PROFILE = bundle.getString(CommonEnumUtils.PROFILE.toString())
            val NOMINEE = bundle.getString(CommonEnumUtils.NOMINEE.toString())
            val BANK = bundle.getString(CommonEnumUtils.BANK.toString())

            if (PROFILE.equals(CommonEnumUtils.PROFILE.toString())) {
                val args = Bundle()
                changeFragment(0, args)
            }

            if (NOMINEE.equals(CommonEnumUtils.NOMINEE.toString())) {
                val args = Bundle()
                changeFragment(1, args)
            }

            if (BANK.equals(CommonEnumUtils.BANK.toString())) {
                val args = Bundle()
                changeFragment(2, args)
            }

        }


    }


    override fun setActionBarTitle(string: Int) {
        setTitle(string)
    }

    override fun changeFragment(position: Int, bundle: Bundle) {
        when (position) {
            0 -> {
                setActionBarTitle(R.string.ProfileDetailsString)
                ///
                val fragmentTransaction = fragmentManager.beginTransaction()
                updateProfileFragment.arguments = bundle
                fragmentTransaction.replace(R.id.profileFragmentLayout, updateProfileFragment)
                fragmentTransaction.commit()
            }
            1 -> {
                setActionBarTitle(R.string.updateNomineeDetailsHintString)
                ///
                val fragmentTransaction = fragmentManager.beginTransaction()
                updateNomineeFragment.arguments = bundle
                fragmentTransaction.replace(R.id.profileFragmentLayout, updateNomineeFragment)
                fragmentTransaction.commit()
            }
            2 -> {
                setActionBarTitle(R.string.updateBankDetailsHintString)
                ///
                val fragmentTransaction = fragmentManager.beginTransaction()
                updateBankFragment.arguments = bundle
                fragmentTransaction.replace(R.id.profileFragmentLayout, updateBankFragment)
                fragmentTransaction.commit()
            }

            3 -> {
                setActionBarTitle(R.string.updateProofDetailsHintString)
                ///
                val fragmentTransaction = fragmentManager.beginTransaction()
                viewBankProofFragment.arguments = bundle
                fragmentTransaction.replace(R.id.profileFragmentLayout, viewBankProofFragment)
                fragmentTransaction.commit()
            }

        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.profileFragmentLayout)
        if (fragment !is ProfileBaseActivity.OnBackPressedListner || !(fragment as ProfileBaseActivity.OnBackPressedListner).onBackPressed()) {

        }
    }

    interface OnBackPressedListner {
        fun onBackPressed(): Boolean
    }
}
