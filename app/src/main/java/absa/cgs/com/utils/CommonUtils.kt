package absa.cgs.com.utils

import absa.cgs.com.ui.screens.apis.loginapicall.model.LoginResponseModel
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import com.google.gson.Gson
import java.io.*
import javax.inject.Inject
import javax.inject.Singleton
import android.R.array
import android.R.attr.bitmap
import java.nio.ByteBuffer


@Singleton
class CommonUtils @Inject constructor(private val context: Context, private val sessionUtils: SessionUtils) {


    fun showToastSmall(message: String) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    fun showToastLong(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    fun cutNull(format: Any?): String {
        val cut: String
        if (format == null) {
            cut = ""
        } else if (format.toString().trim { it <= ' ' }.equals("", ignoreCase = true)) {
            cut = ""
        } else if (format.toString().trim { it <= ' ' }.equals("null", ignoreCase = true)) {
            cut = ""
        } else {
            cut = format.toString()
        }
        return cut
    }

    fun putLoginSessionData(loginResponseModel: LoginResponseModel): String {
        val gson = Gson()
        val jsonString = gson.toJson(loginResponseModel)
        return jsonString
    }

    fun getLoginSessionData(): LoginResponseModel {
        val gson = Gson()
        val loginResponseModel: LoginResponseModel
        loginResponseModel = gson.fromJson(sessionUtils.loginSessionData, LoginResponseModel::class.java)
        return loginResponseModel
    }

    fun encodeImagePathToBase64(imagePath: String): String {
        try {
            val bm = BitmapFactory.decodeFile(imagePath)
            val baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG, 70, baos) //bm is the bitmap object
            val byteArrayImage = baos.toByteArray()
            return Base64.encodeToString(byteArrayImage, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }


    fun getRealPathFromURI(context: Context, contentURI: Uri): String? {
        val cursor = context.getContentResolver()!!.query(contentURI, null, null, null, null)
        if (cursor == null) { // Source is Dropbox or other similar local file path
            return contentURI.path
        } else {
            cursor!!.moveToFirst()
            val idx = cursor!!.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor!!.getString(idx)
        }
    }

    fun encodeBitmapTobase64(image: Bitmap): String {
        try {
            val baos = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val b = baos.toByteArray()
            return Base64.encodeToString(b, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }

    fun getImageUri(inImage: Bitmap): Uri {
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }


}