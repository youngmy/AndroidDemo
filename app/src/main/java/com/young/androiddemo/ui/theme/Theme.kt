@file:Suppress("DEPRECATION", "NAME_SHADOWING")

package com.young.androiddemo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.young.androiddemo.data.AppSystemSetManage
import com.young.commonlibrary.LogHelper
import com.young.commonlibrary.ext.observeAsState
import com.young.commonlibrary.store.DataStoreHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80,
//    onBackground = ColorMain,
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40,
//    onBackground = ColorMain,
//
//    /* Other default colors to override
//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F),
//    */
//)

private val LightColorScheme = AndroidDemoColors(
    statusBarColor = statusBarColorLight,
    navigationBarColor = ColorMain,
    themeUi = themeColor,
//    themeAccent = themeAccent,
    background = backgroundColorLight,
//    backgroundSecondary = backgroundSecondaryColorLight,
//    whiteBackground = whiteBackgroundColorLight,
//    immerseBackground = immerseBackgroundColorLight,
//    dialogBackground = dialogBackgroundLight,
//    itemBackground = itemBackgroundLight,
//    itemImmerse = immerseBackgroundColorLight,
//    editBackground = editBackgroundLight,
//    textPrimary = textPrimaryLight,
//    textSecondary = textSecondaryLight,
    textWhite = ColorFFFFFF,
    textBlack = Color333333,
//    selectColor = themeAccentColor,
//    icon = grey,
//    iconBlack = black,
//    success = green,
//    info = blue,
//    error = red2,
//    btnBgPrimary = themeColor,
//    btnBgSecond = themeColor,
//    placeholder = white3,
)

private val DarkColorScheme = AndroidDemoColors(
    statusBarColor = statusBarColorDark,
    navigationBarColor = Color000000,
    themeUi = themeColor,
//    themeAccent = themeAccent,
    background = backgroundColorDark,
//    backgroundSecondary = backgroundSecondaryColorDark,
//    whiteBackground = whiteBackgroundColorDark,
//    immerseBackground = immerseBackgroundColorDark,
//    dialogBackground = dialogBackgroundDark,
//    itemBackground = itemBackgroundDark,
//    itemImmerse = itemBackgroundDark,
//    editBackground = editBackgroundDark,
//    textPrimary = textPrimaryDark,
//    textSecondary = textSecondaryDark,
    textWhite = ColorFFFFFF,
    textBlack = Color333333,
//    selectColor = themeAccentColor,
//    icon = grey,
//    iconBlack = grey,
//    success = green,
//    info = blueDark,
//    error = red2,
//    btnBgPrimary = themeColor,
//    btnBgSecond = themeColor,
//    placeholder = grey1,
)

@Stable
object DiyTheme{
    val colors:AndroidDemoColors
    @Composable
    get() = LocalAppColors.current

    enum class Theme{
        Light,Dark
    }
}


@Stable
class AndroidDemoColors(
    statusBarColor: Color,
    navigationBarColor: Color,
    themeUi: Color,
//    themeAccent: Color,
    background: Color,
//    backgroundSecondary: Color,
//    whiteBackground: Color,
//    immerseBackground: Color,
//    dialogBackground: Color,
//    itemBackground: Color,
//    itemImmerse: Color,
//    editBackground: Color,
//    textPrimary: Color,
//    textSecondary: Color,
    textWhite: Color,
    textBlack: Color,
//    selectColor: Color,
//    icon: Color,
//    iconBlack: Color,
//    success: Color,
//    info: Color,
//    error: Color,
//    btnBgPrimary: Color,
//    btnBgSecond: Color,
//    placeholder: Color,
){
    var statusBarColor: Color by mutableStateOf(statusBarColor)
        internal set
    var navigationBarColor: Color by mutableStateOf(navigationBarColor)
        internal set

    var themeUi: Color by mutableStateOf(themeUi)
        internal set
//    var themeAccent: Color by mutableStateOf(themeAccent)
//        internal set
    var background: Color by mutableStateOf(background)
        internal set
//    var backgroundSecondary: Color by mutableStateOf(backgroundSecondary)
//        private set
//    var whiteBackground: Color by mutableStateOf(whiteBackground)
//        private set
//    var immerseBackground: Color by mutableStateOf(immerseBackground)
//        private set
//    var dialogBackground: Color by mutableStateOf(dialogBackground)
//        private set
//    var itemBackground: Color by mutableStateOf(itemBackground)
//        private set
//    var itemImmerse: Color by mutableStateOf(itemImmerse)
//        private set
//    var editBackground: Color by mutableStateOf(editBackground)
//        private set
//    var textPrimary: Color by mutableStateOf(textPrimary)
//        internal set
//    var textSecondary: Color by mutableStateOf(textSecondary)
//        private set
    var textWhite: Color by mutableStateOf(textWhite)
        private set
    var textBlack: Color by mutableStateOf(textBlack)
        private set
//    var selectColor: Color by mutableStateOf(selectColor)
//        private set
//    var icon: Color by mutableStateOf(icon)
//        private set
//    var iconBlack: Color by mutableStateOf(iconBlack)
//        private set
//    var success: Color by mutableStateOf(success)
//        private set
//    var info: Color by mutableStateOf(info)
//        private set
//    var error: Color by mutableStateOf(error)
//        private set
//    var primaryBtnBg: Color by mutableStateOf(btnBgPrimary)
//        internal set
//    var secondBtnBg: Color by mutableStateOf(btnBgSecond)
//        private set
//    var placeholder: Color by mutableStateOf(placeholder)
//        private set
}

var LocalAppColors = compositionLocalOf {
    LightColorScheme
}

@Composable
fun AndroidDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
//    val mDarkTheme = themeChanges.observeAsState(initial = true).value
//
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (mDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        mDarkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = ColorMain.toArgb()//colorScheme.primary.toArgb() //ColorMain.toArgb()//
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = mDarkTheme
//        }
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//    CoroutineScope(Dispatchers.Default).launch {
//        DataStoreHelper.readBooleanFlow(AppSystemSetManage.Dark_Mode, true).collect{
//            LogHelper.logI("readBooleanFlow===it===$it","切换主题")
//            ThemeLiveData.postValue(if (it){DiyTheme.Theme.Light}else{DiyTheme.Theme.Dark})
//        }
//    }

    val currentTheme = if (AppSystemSetManage.darkUI) DiyTheme.Theme.Dark else DiyTheme.Theme.Light
    LogHelper.logI("===currentTheme===$currentTheme","切换主题")
    val theme by themeChanges.observeAsState(currentTheme)
    LogHelper.logI("===theme===$theme","切换主题")
    val followSystem =false//by followSystemChanges.observeAsState(AppSystemSetManage.darkModeFollowSystem)
    val targetColors = if (followSystem){
        if (darkTheme) DarkColorScheme else LightColorScheme
    }else{
        when (theme) {
            DiyTheme.Theme.Light -> LightColorScheme
            //DiyTheme.Theme.Dark
            else -> DarkColorScheme
        }
    }

    val darkThemeMode = when (theme) {
        DiyTheme.Theme.Light -> false
        //DiyTheme.Theme.Dark
        else -> true
    }

    val statusBarColor = animateColorAsState(targetColors.statusBarColor, TweenSpec(600), label = "")
    val navigationBarColor = animateColorAsState(targetColors.background, TweenSpec(600), label = "")

    val themeUi = animateColorAsState(targetColors.themeUi, TweenSpec(600), label = "")
//    val themeAccent = animateColorAsState(targetColors.themeAccent, TweenSpec(600))
    val background = animateColorAsState(targetColors.background, TweenSpec(600), label = "")
//    val backgroundSecondary = animateColorAsState(targetColors.backgroundSecondary, TweenSpec(600))
//
//    val whiteBackground = animateColorAsState(targetColors.whiteBackground, TweenSpec(600))
//    val immerseBackground = animateColorAsState(targetColors.immerseBackground, TweenSpec(600))
//    val dialogBackground = animateColorAsState(targetColors.dialogBackground, TweenSpec(600))
//    val itemBackground = animateColorAsState(targetColors.itemBackground, TweenSpec(600))
//    val itemImmerse = animateColorAsState(targetColors.itemImmerse, TweenSpec(600))
//    val editBackground = animateColorAsState(targetColors.editBackground, TweenSpec(600))
//
//    val textPrimary = animateColorAsState(targetColors.textPrimary, TweenSpec(600))
//    val textSecondary = animateColorAsState(targetColors.textSecondary, TweenSpec(600))
    val textWhite = animateColorAsState(targetColors.textWhite, TweenSpec(600), label = "")
    val textBlack = animateColorAsState(targetColors.textBlack, TweenSpec(600), label = "")
//
//    val selectColor = animateColorAsState(targetColors.selectColor, TweenSpec(600))
//
//    val icon = animateColorAsState(targetColors.icon, TweenSpec(600))
//    val iconBlack = animateColorAsState(targetColors.iconBlack, TweenSpec(600))
//
//    val success = animateColorAsState(targetColors.success, TweenSpec(600))
//    val info = animateColorAsState(targetColors.info, TweenSpec(600))
//    val error = animateColorAsState(targetColors.error, TweenSpec(600))
//    val primaryBtnBg = animateColorAsState(targetColors.primaryBtnBg, TweenSpec(600))
//    val secondBtnBg = animateColorAsState(targetColors.secondBtnBg, TweenSpec(600))
//    val placeholder = animateColorAsState(targetColors.placeholder, TweenSpec(600))


    val appColors = AndroidDemoColors(
        statusBarColor = statusBarColor.value,
        navigationBarColor = navigationBarColor.value,
        themeUi = themeUi.value,
//        themeAccent = themeAccent.value,
        background = background.value,
//        backgroundSecondary = backgroundSecondary.value,
//
//        whiteBackground = whiteBackground.value,
//        immerseBackground = immerseBackground.value,
//        dialogBackground = dialogBackground.value,
//        itemBackground = itemBackground.value,
//        itemImmerse = itemImmerse.value,
//        editBackground = editBackground.value,
//
//        textPrimary = textPrimary.value,
//        textSecondary = textSecondary.value,
        textWhite = textWhite.value,
        textBlack = textBlack.value,
//
//        selectColor = selectColor.value,
//
//        icon = icon.value,
//        iconBlack = iconBlack.value,
//        btnBgPrimary = primaryBtnBg.value,
//        btnBgSecond = secondBtnBg.value,
//        success = success.value,
//        info = info.value,
//        error = error.value,
//        placeholder = placeholder.value
    )


    CompositionLocalProvider(LocalAppColors provides appColors) {

        val systemUiController = rememberSystemUiController()
        val statusBarColor = Color.Transparent
        val navigationColor = appColors.navigationBarColor
        SideEffect {
            systemUiController.setStatusBarColor(statusBarColor, darkIcons = !darkThemeMode)
            systemUiController.setNavigationBarColor(navigationColor, darkIcons = !darkThemeMode)
        }

        val colors = if (darkTheme) {
            darkColorScheme(
                primary = appColors.themeUi,
                onPrimary =appColors.textWhite
            )
        } else {
            lightColorScheme(primary = appColors.themeUi)
        }

        MaterialTheme(
            colorScheme = colors,
//            shapes = MyShapes,
            content = content,
            typography = Typography,
        )
    }

}