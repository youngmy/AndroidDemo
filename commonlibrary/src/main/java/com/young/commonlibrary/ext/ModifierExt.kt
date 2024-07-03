package com.young.commonlibrary.ext

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

/**
 * 自定义Modifier扩展函数
 */

/**
 * 隐藏软键盘
 * example Modifier.autoCloseKeyboard()
 */
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.autoCloseKeyboard(): Modifier = composed {
    val keyboardController = LocalSoftwareKeyboardController.current
    pointerInput(this) {
        detectTapGestures(onPress = {
            keyboardController?.hide()
        })
    }
}

/**
 * 自定义去掉水波纹的点击拓展函数
 *
 * example Modifier.click{ doSomething() }
 */
fun Modifier.click(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        onClick = onClick,
        interactionSource = remember {
            MutableInteractionSource()
        }
    )

}
