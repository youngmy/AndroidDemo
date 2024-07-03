package com.young.commonlibrary.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.internal.Primitives
import java.io.BufferedReader
import java.io.InputStreamReader

object JsonHelper {

    fun <T> readLocalJson(context: Context, classOfT: Class<T>, fileName:String): T {
        var jsonString: String?
        val resultString = java.lang.StringBuilder()
        try {
            val bufferedReader = BufferedReader(InputStreamReader(context.resources.assets.open(fileName)))
            while (bufferedReader.readLine().also { jsonString = it } != null) {
                resultString.append(jsonString)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return fromJson(resultString.toString(),classOfT)
    }

    private fun <T> fromJson(json: String, classOfT: Class<T>): T {
        val gson = Gson()
        val mObject = gson.fromJson(json, classOfT)
        return Primitives.wrap(classOfT).cast(mObject)!!
    }

}