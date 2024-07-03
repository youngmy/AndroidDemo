package com.young.androiddemo.demo.compose.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.young.androiddemo.ui.theme.AndroidDemoTheme
import com.young.androiddemo.ui.theme.Color666666
import com.young.androiddemo.ui.theme.ColorMain
import com.young.androiddemo.widget.CommonAppBar
import com.young.androiddemo.widget.PagerTabIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class TabRowIndicatorActivity : ComponentActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, TabRowIndicatorActivity::class.java))
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

    @Preview
    @Composable
    fun RootLayout(modifier: Modifier = Modifier) {


        Scaffold(
            topBar = {
                CommonAppBar(
                    navigationClick = {
                        finish()
                    },
                    centerTitle = "标题"
                )
            }
        ) { paddingValues ->// 内容区域，可以使用paddingValues来添加内边距
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                HomeTab()
            }

        }
    }

    @Preview
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun HomeTab(
        modifier: Modifier = Modifier
    ) {
        val titleList = listOf("笔记", "句子", "科普", "名称")
        val pagerState = rememberPagerState(initialPage = 0,pageCount={
            titleList.size
        })
        val scope = rememberCoroutineScope()

        ScrollableTabRow(
            modifier = modifier
                .fillMaxWidth(),
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,//ScrollableTabRow的开始和结束边缘与其内部选项卡之间的距离（默认是有52dp的一个padding）
            containerColor = Color.White,
            indicator = { tabPositions ->
                if (tabPositions.isNotEmpty()) {
                    PagerTabIndicator(tabPositions = tabPositions, pagerState = pagerState)
                }
            },
            divider = {}
        ) {

            titleList.forEachIndexed { index, title ->
                val selected = (pagerState.currentPage == index)
                Tab(
                    selected = selected,
                    selectedContentColor = ColorMain,
                    unselectedContentColor = Color666666,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .padding(9.dp)
                    )
                }
            }

        }

        HorizontalPager(
//            pageCount = titleList.size,
            state=pagerState,
            beyondBoundsPageCount = 20, //当前页面前后预加载的页面数量
            modifier = modifier
        ) {page->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(color= ColorMain)
            ){

            }

        }

    }


}