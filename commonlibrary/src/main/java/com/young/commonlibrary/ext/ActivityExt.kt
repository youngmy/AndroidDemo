@file:Suppress("DEPRECATION")

package com.young.commonlibrary.ext

import android.app.Activity
import android.content.pm.PackageInfo
import android.content.pm.PackageManager

fun Activity.getVersionCode(): Int {
    val manager: PackageManager = this.packageManager
    var code = 0
    try {
        val info: PackageInfo = manager.getPackageInfo(this.packageName, 0)
        code = info.versionCode
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return code
}

fun Activity.getVersionName(): String {
    val manager: PackageManager = this.packageManager
    var code = ""
    try {
        val info: PackageInfo = manager.getPackageInfo(this.packageName, 0)
        code = info.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return code
}