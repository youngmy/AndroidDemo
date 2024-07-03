package com.young.commonlibrary.utils

import android.content.Context
import java.io.File

object PathHelper {

    /**
     * 获取外部文件目录
     */
    fun getExternalFilesDir(context: Context): File? {
        return context.getExternalFilesDir(null)
    }

}