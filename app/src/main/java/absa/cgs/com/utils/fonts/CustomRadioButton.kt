package absa.cgs.com.utils.fonts

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.Button
import android.widget.RadioButton


class CustomRadioButton : RadioButton {

    constructor(context: Context) : super(context) {
        setFont()
    }

    constructor(context: Context, set: AttributeSet) : super(context, set) {
        setFont()
    }

    constructor(context: Context, set: AttributeSet, defaultStyle: Int) : super(context, set, defaultStyle) {
        setFont()
    }

    private fun setFont() {

        val typeface = Typeface.createFromAsset(context.assets, "fonts/Lato-Medium.ttf")
        setTypeface(typeface) //function used to set font

    }
}