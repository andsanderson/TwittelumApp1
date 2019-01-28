package b.com.caelum.twittelumapp.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Base64


object Carregador {

    fun decodifica(foto: String):Bitmap{
        val decode : ByteArray = Base64.decode(foto,Base64.DEFAULT)
        val bitmap=BitmapFactory.decodeByteArray(decode,0,decode.size)

        val matrix = Matrix()
        matrix.postRotate(90.0F)

        return Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height,matrix,true)
    }

}