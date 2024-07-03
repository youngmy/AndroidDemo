package com.young.commonlibrary.ext

import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

/**
 * Bitmap转化成文件，并保存
 */
fun Bitmap.translationToFile(saveFile: File, format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG) {
    val out = FileOutputStream(saveFile)
    compress(format, 100, out)
    out.flush()
    out.close()
}