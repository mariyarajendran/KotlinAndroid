package absa.cgs.com.utils

import absa.cgs.com.di.annotation.PerActivity
import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.register.model.RadioButtonDataModel
import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.custom_radio_group_dialog.*
import javax.inject.Inject

@PerActivity
class DialogUtils @Inject constructor(private val activity: Activity) {


    interface onRadioButtonEventListener {
        fun onRadioTitleListener(radioButtonListDataModel: List<RadioButtonDataModel>, title: String)

    }


    fun showLoadingDialog(): ProgressDialog {
        val progressDialog = ProgressDialog(activity)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }

    fun radioButtonAlertDialog(radioButtonListDataModel: List<RadioButtonDataModel>, listener: onRadioButtonEventListener) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_radio_group_dialog)
        dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.window?.setGravity(Gravity.CENTER)

        dialog.customRadioGroupTileHint?.setText(radioButtonListDataModel.get(1).title)
        dialog.customRadioButtonOne?.setText(radioButtonListDataModel.get(2).title)
        dialog.customRadioButtonTwo?.setText(radioButtonListDataModel.get(3).title)
        dialog.show()
        dialog.customRadioGroup?.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                var radioButtonId = dialog.customRadioGroup.checkedRadioButtonId
                if (radioButtonId > 0) {
                    when (radioButtonId) {
                        R.id.customRadioButtonOne -> {
                            listener.onRadioTitleListener(radioButtonListDataModel, dialog.customRadioButtonOne.text.toString())
                            dialog.dismiss()
                        }
                        R.id.customRadioButtonTwo -> {
                            listener.onRadioTitleListener(radioButtonListDataModel, dialog.customRadioButtonTwo.text.toString())
                            dialog.dismiss()
                        }
                    }
                }
            }
        })

    }

}