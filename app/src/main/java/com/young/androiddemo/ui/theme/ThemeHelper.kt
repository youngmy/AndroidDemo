package com.young.androiddemo.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.young.commonlibrary.LogHelper

object ThemeLiveData : MutableLiveData<DiyTheme.Theme>()

/** 切换主题 */
val themeChanges: LiveData<DiyTheme.Theme> = ThemeLiveData.map {
    LogHelper.logI("===it===$it","切换主题")
    it
}