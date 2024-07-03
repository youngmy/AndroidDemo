package com.young.commonlibrary.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.young.commonlibrary.ext.mApplication
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

// 定义接口，方便Kotlin委托实现
interface IDataStoreOwner {
    val context: Context get() = mApplication

    val dataStore: DataStore<Preferences>  //空实现

    fun intPreference(default: Int? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<Int>> =
        PreferenceProperty(::intPreferencesKey, default)

    fun doublePreference(default: Double? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<Double>> =
        PreferenceProperty(::doublePreferencesKey, default)

    fun longPreference(default: Long? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<Long>> =
        PreferenceProperty(::longPreferencesKey, default)

    fun floatPreference(default: Float? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<Float>> =
        PreferenceProperty(::floatPreferencesKey, default)

    fun booleanPreference(default: Boolean? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<Boolean>> =
        PreferenceProperty(::booleanPreferencesKey, default)

    fun stringPreference(default: String? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<String>> =
        PreferenceProperty(::stringPreferencesKey, default)

    fun stringSetPreference(default: Set<String>? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<Set<String>>> =
        PreferenceProperty(::stringSetPreferencesKey, default)

//    fun byteArrayPreference(default: ByteArray? = null): ReadOnlyProperty<IDataStoreOwner, DataStorePreference<ByteArray>> =
//        PreferenceProperty(::byteArrayPreferencesKey, default)

    //自定义类，封装 KV 对象，内部使用 DataStorePreference 具体操作 KV 的存取
    class PreferenceProperty<V>(
        private val key: (String) -> Preferences.Key<V>,
        private val default: V? = null,
    ) : ReadOnlyProperty<IDataStoreOwner, DataStorePreference<V>> {
        //做缓存，如果已经取出了，不需要重复取
        private var cache: DataStorePreference<V>? = null

        override fun getValue(owner: IDataStoreOwner, property: KProperty<*>): DataStorePreference<V> =
            cache ?: DataStorePreference(owner.dataStore, key(property.name), default).also { cache = it }
    }

    companion object {
//        internal lateinit var application: Application
    }
}
