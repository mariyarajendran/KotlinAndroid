package absa.cgs.com.ui.screens.register

import absa.cgs.com.kotlinplayground.R
import absa.cgs.com.ui.screens.base.BaseActivity
import absa.cgs.com.ui.screens.mainbaseactivity.MainView

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class CustomerRegister : BaseActivity() {
    private var imageViewss: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBarTitle()
        setContentView(R.layout.activity_customer_register)


        imageViewss = findViewById(R.id.imageview_text) as ImageView
        Glide.with(this).load("https://image.shutterstock.com/image-vector/sample-stamp-grunge-texture-vector-260nw-1389188327.jpg")
                .into(imageViewss!!)


    }


    private fun setActionBarTitle() {
        setTitle(R.string.add_customer)
    }

}
