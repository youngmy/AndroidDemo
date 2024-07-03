package com.young.androiddemo.demo.compose.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.young.androiddemo.R
import com.young.androiddemo.ui.theme.AndroidDemoTheme
import com.young.androiddemo.widget.CommonAppBar
import com.young.commonlibrary.utils.MultiLanguageHelper

class MultiLanguage2Activity : ComponentActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, MultiLanguage2Activity::class.java))
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(MultiLanguageHelper.changeContextLocale(newBase!!))
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

                Text(text = stringResource(id = R.string.test_content), fontSize = 15.sp)

            }
        }
    }

}