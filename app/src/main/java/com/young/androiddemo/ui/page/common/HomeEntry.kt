package com.young.androiddemo.ui.page.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.young.androiddemo.ui.page.splash.SplashPage
import com.young.androiddemo.ui.theme.AndroidDemoTheme

@Preview
@Composable
fun HomeEntry() {
    //是否闪屏页
    var isSplash by remember { mutableStateOf(true) }
//    AppTheme {
    AndroidDemoTheme {
        if (isSplash) {
            SplashPage { isSplash = false }
        } else {
            AppScaffold()
        }
    }
}