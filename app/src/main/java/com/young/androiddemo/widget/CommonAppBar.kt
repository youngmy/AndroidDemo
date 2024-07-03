package com.young.androiddemo.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.young.androiddemo.ui.theme.ColorMain

@Preview
@Composable
fun CommonAppBar(
    modifier: Modifier = Modifier,
    navigationClick: () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {
        IconButton(
            onClick = {
                navigationClick()
            }
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Menu", tint = Color.White)
        }
    },
    centerTitle: String = "我是标题",
    centerTitleColor: Color = Color.White,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    title: @Composable () -> Unit = {
        Text(
            text = centerTitle,
            textAlign = TextAlign.Center,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = centerTitleColor,
            maxLines = 1, overflow = TextOverflow.Ellipsis
        )
    },
    actionsIcon: @Composable () -> Unit = {},
    height: Dp = 50.dp,
    color: Color = ColorMain,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(color = color),
    ) {
        Row(
            modifier = modifier
                .fillMaxHeight()
                .width(70.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navigationIcon()
        }
        Row(
            modifier = modifier
                .fillMaxHeight()
                .weight(1F),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            title()
        }
        Row(
            modifier = modifier
                .fillMaxHeight()
                .width(70.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            actionsIcon()
        }
    }
}