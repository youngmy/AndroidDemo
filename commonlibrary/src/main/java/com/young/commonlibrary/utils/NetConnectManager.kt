package com.young.commonlibrary.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper

/**
 * 参考：https://juejin.cn/post/7324345717908717587
 * 步骤1、<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 * 步骤2、NetConnectManager.init(this)
 * 步骤3
 * LogHelper.logI("网络是否连接= ${NetConnectManager.isConnected()} & 网络类型= ${NetConnectManager.getConnectType().value}",TAG)
 * NetConnectManager.addNetTypeChangeListener {
 * LogHelper.logI( "网络类型[监听]= ${it.value}",TAG)
 * }
 * NetConnectManager.addNetStatusChangeListener {
 * LogHelper.logI( "网络是否已连接[监听]= $it",TAG)
 * }
 */
@Suppress("DEPRECATION")
object NetConnectManager {

    private var mConnectivityManager: ConnectivityManager? = null
    private val mainHandler = Handler(Looper.getMainLooper())
    private val mNetTypeListener = mutableListOf<(type: ConnectType) -> Unit>()
    private val mNetStateListener = mutableListOf<(isAvailable: Boolean) -> Unit>()
    private var mCurrentConnectType: ConnectType? = null
    private var mIsNetAvailable: Boolean? = null

    /**
     * 初始化
     */
    fun init(context: Context) {
        mConnectivityManager = context.getSystemService(ConnectivityManager::class.java)
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
        mConnectivityManager?.registerDefaultNetworkCallback(DefaultNetConnectCallback())
//        }else{
//            context.registerReceiver(
//                NetConnectReceiver(),
//                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
//            )
//        }
    }

    /**
     * 注册网络类型监听
     */
    fun addNetTypeChangeListener(listener: (type: ConnectType) -> Unit) {
        mNetTypeListener.add(listener)
    }

    /**
     * 反注册网络类型监听
     */

    fun removeNetTypeChangeListener(listener: (type: ConnectType) -> Unit) {
        mNetTypeListener.remove(listener)
    }

    /**
     * 注册网络状态监听
     */
    fun addNetStatusChangeListener(listener: (isAvailable: Boolean) -> Unit) {
        mNetStateListener.add(listener)
    }

    /**
     * 反注册网络状态监听
     */
    fun removeNetStatusChangeListener(listener: (isAvailable: Boolean) -> Unit) {
        mNetStateListener.remove(listener)
    }

    /**
     * 获取当前网络类型
     */
    fun getConnectType(): ConnectType {
        if (mConnectivityManager == null) {
            throw UninitializedPropertyAccessException("请先调用init()初始化")
        }
        return mCurrentConnectType ?: mConnectivityManager?.getNetworkCapabilities(
            mConnectivityManager?.activeNetwork
        ).let {
            return if (it?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true) {
                ConnectType.Mobile
            } else if (it?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true) {
                ConnectType.Wifi
            } else {
                ConnectType.None
            }
        }
    }

    /**
     * 获取当前是否网络已连接
     */
    fun isConnected(): Boolean {
        if (mConnectivityManager == null) {
            throw UninitializedPropertyAccessException("请先调用init()初始化")
        }
        return (mIsNetAvailable
            ?: mConnectivityManager?.getNetworkCapabilities(mConnectivityManager?.activeNetwork)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) == true
    }

    private class DefaultNetConnectCallback : ConnectivityManager.NetworkCallback() {

        override fun onLost(network: Network) {
            super.onLost(network)
            mCurrentConnectType = ConnectType.None
            mainHandler.postDelayed({
                if (mCurrentConnectType == ConnectType.None && mIsNetAvailable == true) {
                    mIsNetAvailable = false
                    mNetStateListener.forEach { it.invoke(false) }
                    mNetTypeListener.forEach { it(ConnectType.None) }
                }
            }, 500)
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            mainHandler.post {
                val isConnected =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                val isCellular =
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                val isWifi =
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)

                if (isConnected) {
                    val newConnectType =
                        if (isCellular) ConnectType.Mobile else if (isWifi) ConnectType.Wifi else ConnectType.None
                    if (mIsNetAvailable == null || mIsNetAvailable == false) {
                        mIsNetAvailable = true
                        mNetStateListener.forEach { it(true) }
                    }
                    if (mCurrentConnectType != newConnectType) {
                        mCurrentConnectType = newConnectType
                        mNetTypeListener.forEach { it(newConnectType) }
                    }
                }
            }
        }
    }

    private class NetConnectReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val activityNetworkInfo =
                context?.getSystemService(ConnectivityManager::class.java)?.allNetworkInfo?.firstOrNull {
                    (it.type == ConnectType.Mobile.value || it.type == ConnectType.Wifi.value) && it.isConnected
                }
            if (activityNetworkInfo != null) {
                if (mIsNetAvailable == null || mIsNetAvailable == false) {
                    mIsNetAvailable = true
                    mNetStateListener.forEach { it(true) }
                }
                ConnectType.convert2Type(activityNetworkInfo.type).let { connectType ->
                    if (connectType != mCurrentConnectType) {
                        mCurrentConnectType = connectType
                        mNetTypeListener.forEach { it(connectType) }
                    }
                }
                return
            }
            mCurrentConnectType = ConnectType.None
            mainHandler.postDelayed({
                if (mCurrentConnectType == ConnectType.None && mIsNetAvailable == true) {
                    mIsNetAvailable = false
                    mNetStateListener.forEach { it(false) }
                    mNetTypeListener.forEach { it(ConnectType.None) }
                }
            }, 500)

        }
    }


}


sealed class ConnectType(val value: Int) {
    object Mobile : ConnectType(0)
    object Wifi : ConnectType(1)
    object None : ConnectType(-1)

    companion object {
        fun convert2Type(value: Int): ConnectType {
            return when (value) {
                Mobile.value -> Mobile
                Wifi.value -> Wifi
                else -> None
            }
        }
    }

}