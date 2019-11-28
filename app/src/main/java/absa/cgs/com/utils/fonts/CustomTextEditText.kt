package absa.cgs.com.utils.fonts

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class CustomTextEditText : TextInputEditText {

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

        val typeface = Typeface.createFromAsset(context.assets, "fonts/Lato-Bold.ttf")
        setTypeface(typeface) //function used to set font

    }
}