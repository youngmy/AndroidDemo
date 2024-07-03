package com.young.commonlibrary.ext

import android.app.Application

//注入app,用于context的注入，这个app不能用于弹窗的显示
internal  lateinit var mApplication: Application

//全局application
fun Application.injectApp() {
    mApplication = this
}
