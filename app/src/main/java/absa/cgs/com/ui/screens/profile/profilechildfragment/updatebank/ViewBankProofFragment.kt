package absa.cgs.com.ui.screens.profile.profilechildfragment.updatebank

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.base.BaseFragment
import absa.cgs.com.ui.screens.profile.ProfileBaseActivity
import absa.cgs.com.utils.CommonEnumUtils
import absa.cgs.com.utils.SingletonUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_kotlin_play_ground.*
import kotlinx.android.synthetic.main.editexpensefragment.*
import kotlinx.android.synthetic.main.viewbankfragment.*


class ViewBankProofFragment : BaseFragment(), ProfileBaseActivity.OnBackPressedListner {

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
        return inflater.inflate(R.layout.viewbankfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    override fun init() {
        profileBaseActivity = activity as ProfileBaseActivity
        onClickListeners()
        setImaggData()
    }

    override fun onClickListeners() {
        super.onClickListeners()
        viewProofDetailsImg.setOnTouchListener(ImageMatrixTouchHandler(context))

    }

    override fun onBackPressed(): Boolean {
        val args = Bundle()
        profileBaseActivity.changeFragment(2, args)
        return true
    }

    private fun setImaggData() {
        if (arguments != null) {

            var PAN = arguments!!.getString(CommonEnumUtils.PAN.toString())
            var ADDRESS = arguments!!.getString(CommonEnumUtils.ADDRESS.toString())

            if (PAN != null) {
                Glide.with(this)
                        .load(SingletonUtils.instance.IMAGE_URL + PAN)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .placeholder(R.drawable.banner_image)
                        .error(R.drawable.banner_image)
                        .into(viewProofDetailsImg)
            }

            if (ADDRESS != null) {
                Glide.with(this)
                        .load(SingletonUtils.instance.IMAGE_URL + ADDRESS)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .placeholder(R.drawable.banner_image)
                        .error(R.drawable.banner_image)
                        .into(viewProofDetailsImg)
            }


        }
    }

    override fun onResume() {
        super.onResume()
        setImaggData()
    }


}

