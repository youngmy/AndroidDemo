package com.young.commonlibrary.application

import android.app.Application
import com.young.commonlibrary.ext.injectApp
import com.young.commonlibrary.store.DataStoreHelper
import com.young.commonlibrary.utils.MultiLanguageHelper
import com.young.commonlibrary.utils.NetConnectManager

open class BaseApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        injectApp()
        NetConnectManager.init(this)
        MultiLanguageHelper.init(this)
        DataStoreHelper.init(this)
    }



}