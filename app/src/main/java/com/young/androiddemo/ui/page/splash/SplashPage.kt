package com.young.androiddemo.ui.page.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.young.androiddemo.ui.theme.ColorFFFFFF
import com.young.androiddemo.ui.theme.ColorMain
import kotlinx.coroutines.delay


@Composable
fun SplashPage(
    modifier: Modifier = Modifier,
    onNextPage: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
//                color = AppTheme.colors.themeUi
                color = ColorMain
            ),
        contentAlignment = Alignment.Center
    ) {
        LaunchedEffect(Unit) {
            delay(1500)
            onNextPage.invoke()
        }
        Text(
            text = "Welcome",
            fontSize = 32.sp,
            color = ColorFFFFFF,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
    }
}