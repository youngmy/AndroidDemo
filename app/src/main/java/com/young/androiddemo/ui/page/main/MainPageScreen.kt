package com.young.androiddemo.ui.page.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.young.androiddemo.constants.NavigateRouter
import com.young.androiddemo.data.navList
import com.young.androiddemo.test.TestActivity
import com.young.androiddemo.ui.page.find.FindScreen
import com.young.androiddemo.ui.page.home.AddrBookScreen
import com.young.androiddemo.ui.page.home.HomePageScreen
import com.young.androiddemo.ui.page.service.ServiceScreen
import com.young.androiddemo.ui.theme.Color999999
import com.young.androiddemo.ui.theme.ColorF5F5F5
import com.young.androiddemo.ui.theme.ColorFFFFFF
import com.young.androiddemo.ui.theme.ColorMain
import com.young.androiddemo.ui.theme.DiyTheme
import com.young.androiddemo.widget.CommonAppBar
import com.young.commonlibrary.ext.click
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainPageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = NavigateRouter.HomePage.HOME,
    ) {
        composable(
            NavigateRouter.HomePage.HOME
        ) {
            MainPageUI()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainPageUI(
    modifier: Modifier = Modifier,
) {
//    ThemeLiveData.postValue(if (AppSystemSetManage.darkUI){DiyTheme.Theme.Light}else{DiyTheme.Theme.Dark})
    val mContext = LocalContext.current
    var selectIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val pageState = rememberPagerState(initialPage = 0) {
        4
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier
//            .onSizeChanged { viewModel.fullSize = it }
            .background(DiyTheme.colors.background)
            .systemBarsPadding(),
        topBar = {
            CommonAppBar(
                navigationIcon = {

                },
                centerTitle = navList[selectIndex].title,
                actionsIcon = {
                    IconButton(onClick = {
                        TestActivity.startActivity(mContext)
                    }) {
                        Icon(
                            Icons.Default.Settings, contentDescription = "",
                            modifier = modifier
                                .size(26.dp),
                            tint = Color.White
                        )
                    }
                }
            )

        },
        bottomBar = {
            NavigationBar(
                modifier = modifier
                    .height(60.dp),
                containerColor = ColorFFFFFF,
                tonalElevation = 0.dp
            ) {
                navList.forEachIndexed { index, navigationItem ->
                    Column(
                        modifier = modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .click {
                                selectIndex = index
                                scope.launch {
                                    pageState.scrollToPage(index)
                                }
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = navigationItem.icon,
                            contentDescription = "",
                            modifier = modifier
                                .size(28.dp),
                            tint = if (selectIndex == index) ColorMain
                            else Color999999
                        )
                        Text(
                            text = navigationItem.title,
                            modifier = modifier,
                            fontSize = 13.sp,
                            color = if (selectIndex == index) ColorMain
                            else Color999999
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pageState,
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = ColorF5F5F5),
            contentPadding = PaddingValues(horizontal = 0.dp),
            userScrollEnabled = false
        ) { page ->
            when (page) {
                0 -> {
                    HomePageScreen()
                }
                1 -> {
                    ServiceScreen()
                }
                2->{
                    FindScreen()
                }
                else -> {
                    AddrBookScreen(page)
                }
            }

        }
        LaunchedEffect(pageState) {
            snapshotFlow { pageState.currentPage }.collect { page ->
                selectIndex = page
            }
        }
    }
}