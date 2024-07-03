package com.young.androiddemo.widget

import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.young.androiddemo.R
import com.young.androiddemo.demo.compose.activity.MultiLanguageActivity
import com.young.androiddemo.ui.theme.Color666666
import com.young.androiddemo.ui.theme.ColorF5F5F5
import com.young.androiddemo.ui.theme.ColorMain
import com.young.commonlibrary.utils.LanguageType
import com.young.commonlibrary.utils.MultiLanguageHelper

@Composable

fun MainColorButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorMain,
            contentColor = Color.White,
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp, 15.dp, 15.dp, 0.dp)
            .height(45.dp)
    ) {
        Text(text = text, fontSize = 15.sp)
    }
}

/**
 * 圆角按钮
 */
@Composable
fun RoundedCornerButton(
    modifier: Modifier = Modifier,
    height: Dp = 45.dp,
    containerColor: Color = ColorMain,
    textColor: Color = Color.White,
    minWidth: Dp = 1.dp,
    minHeight: Dp = 1.dp,
    shapeSize: Dp = 26.dp,
    text: String = stringResource(id = R.string.btn_msg),
    fontSize: TextUnit = 15.sp,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .padding(0.dp)
            .defaultMinSize(minWidth, minHeight)
            .fillMaxWidth()
            .height(height),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(shapeSize),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        onClick = onClick
    ) {
        Text(text = text, fontSize = fontSize, color = textColor)
    }
}


/**
 * 取消按钮
 */
@Composable
fun CancelButton(
    modifier: Modifier = Modifier,
    height: Dp = 45.dp,
    containerColor: Color = ColorF5F5F5,
    textColor: Color = Color666666,
    shapeSize: Dp = 26.dp,
    text: String = stringResource(id = R.string.cancel),
    fontSize: TextUnit = 15.sp,
    onClick: () -> Unit
) {
    RoundedCornerButton(
        modifier = modifier,
        height = height,
        containerColor = containerColor,
        shapeSize = shapeSize,
        text = text,
        fontSize = fontSize,
        textColor = textColor,
        onClick = onClick
    )
}
/**


) {
RoundedCornerButton(
AppResId.String.Cancel,
modifier,
containerColor = WordsFairyTheme.colors.immerseBackground,

)
}

 */