package com.young.commonlibrary.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

/**
 * 默认为全局一个分区文件，如果想要多分区，那么不要直接使用 DataStoreOwner 可以用 EasyDataStore 来统一管理快速实现
 * name: String = IDataStoreOwner.application.packageName
 */
open class DataStoreOwner(name: String = "user_data") : IDataStoreOwner {
    private val Context.dataStore by preferencesDataStore(name) //通过内置扩展初始化，赋值给扩展属性
    override val dataStore get() = context.dataStore //扩展属性再赋值给 dataStore 的变量
}