package com.young.androiddemo.demo.compose.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.young.androiddemo.MainActivity
import com.young.androiddemo.R
import com.young.androiddemo.ui.theme.AndroidDemoTheme
import com.young.androiddemo.ui.theme.ColorMain
import com.young.androiddemo.widget.CommonAppBar
import com.young.androiddemo.widget.MainColorButton
import com.young.androiddemo.widget.RoundedCornerButton
import com.young.commonlibrary.utils.LanguageType
import com.young.commonlibrary.utils.MultiLanguageHelper

class MultiLanguageActivity : ComponentActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, MultiLanguageActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidDemoTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    RootLayout()
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    private fun RootLayout(modifier: Modifier = Modifier) {
        val mContext = LocalContext.current
        var showDialog by remember { mutableStateOf(false) }
        Scaffold(
            topBar = {
                CommonAppBar(
                    navigationClick = {
                        finish()
                    },
                    centerTitle = "多语言切换"
                )
            }
        ) { paddingValues ->// 内容区域，可以使用paddingValues来添加内边距
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                val buttonModifier = modifier
                    .padding(15.dp, 15.dp, 15.dp, 0.dp)
                RoundedCornerButton(
                    onClick = {
                        MultiLanguage2Activity.startActivity(mContext)
                    },
                    text = "跳转到Activity",
                    modifier = buttonModifier
                )
                RoundedCornerButton(
                    onClick = {
                        MultiLanguageHelper.changeContextLocale(mContext)
                        showDialog = !showDialog
                    },
                    text = "跳转到Dialog",
                    modifier = buttonModifier
                )


                /*
       * 举例 拓展多语言
       */
//        MultiLanguageService.setExpandLocal { languageType ->
//            return@setExpandLocal when (languageType) {
//                //朝鲜
//                ExpandLanguage.CX -> {
//                    Locale("chaoxian", "CX")
//                }
//                //刚果
//                ExpandLanguage.GG -> {
//                    Locale("gangguo", "GG")
//                }
//                //其他语言
//                else -> {
//                    Locale(LanguageType.LANGUAGE_SYSTEM)
//                }
//            }
//        }

                RoundedCornerButton(
                    onClick = {
                        MultiLanguageHelper.changeLanguage(mContext, LanguageType.LANGUAGE_SYSTEM.type)
                        startActivity(
                            Intent(
                                mContext,
                                MultiLanguageActivity::class.java
                            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        )

                    },
                    text = "跟随系统",
                    modifier = buttonModifier
                )
                RoundedCornerButton(
                    onClick = {
                        MultiLanguageHelper.changeLanguage(mContext, LanguageType.LANGUAGE_JA.type)
                        startActivity(
                            Intent(
                                mContext,
                                MultiLanguageActivity::class.java
                            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        )
                    },
                    text = "日语",
                    modifier = buttonModifier
                )

                RoundedCornerButton(
                    onClick = {

                    },
                    text = "当前语言(${MultiLanguageHelper.getCurrentLanguage()})",
                    modifier = buttonModifier
                )


                Text(text = stringResource(id = R.string.test_content), fontSize = 15.sp, modifier = modifier.padding(15.dp, 15.dp, 15.dp, 0.dp))

            }
        }


        if (showDialog) {

            Dialog(
                onDismissRequest = { showDialog = false },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false
                )
            ) {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(0.dp)
                        .clickable {
                            showDialog = false
                        },
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(5.dp, 5.dp, 0.dp, 0.dp))
                            .clickable { },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(id = R.string.test_content), fontSize = 15.sp)

                        Button(onClick = { showDialog = false }) {
                            Text(text = "取消")
                        }
                    }
                }
            }
        }

    }

}