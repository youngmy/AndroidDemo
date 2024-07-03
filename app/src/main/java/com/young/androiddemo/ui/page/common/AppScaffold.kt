package com.young.androiddemo.ui.page.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.young.androiddemo.ui.page.main.category.CategoryPage
import com.young.androiddemo.ui.page.main.collect.CollectPage
import com.young.androiddemo.ui.page.main.home.HomePage
import com.young.androiddemo.ui.page.main.profile.ProfilePage
import com.young.androiddemo.ui.widgets.BottomNavBarView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
//    val scaffoldState = rememberScaffoldState()


    Scaffold(
        modifier = modifier
//            .statusBarsPadding()
//            .navigationBarsPadding()
        ,
        bottomBar = {
            when (currentDestination?.route) {
                RouteName.HOME -> BottomNavBarView(navController = navController)
                RouteName.CATEGORY -> BottomNavBarView(navController = navController)
                RouteName.COLLECTION -> BottomNavBarView(navController = navController)
                RouteName.PROFILE -> BottomNavBarView(navController = navController)
            }
        },
        content = { paddingValues ->
//        var homeIndex = remember { 0 }
//        var categoryIndex = remember { 0 }
            Box(
                modifier = modifier
                    .padding(paddingValues)
            ) {
                NavHost(
                    modifier = modifier
                        .background(MaterialTheme.colorScheme.background),
                    navController = navController,
                    startDestination = RouteName.HOME
                ) {
                    //首页
                    composable(route = RouteName.HOME) {
                        HomePage(
                            navController//, scaffoldState
                        )
                    }
                    //分类
                    composable(route = RouteName.CATEGORY) {
                        CategoryPage(navController)
                    }
                    //收藏
                    composable(route = RouteName.COLLECTION) {
                        CollectPage(
                            navController//, scaffoldState
                        )
                    }
                    //我的
                    composable(route = RouteName.PROFILE) {
                        ProfilePage(navController,
//                            scaffoldState
                        )
                    }

                }




//
//                //WebView
//                composable(route = RouteName.WEB_VIEW + "/{webData}",
//                    arguments = listOf(navArgument("webData") { type = NavType.StringType })
//                ) {
//                    val args = it.arguments?.getString("webData")?.fromJson<WebData>()
//                    if (args != null) {
//                        WebViewPage(webData = args, navCtrl = navCtrl)
//                    }
//                }
//
//                //登录
//                composable(route = RouteName.LOGIN) {
//                    LoginPage(navCtrl, scaffoldState)
//                }
//
//                //文章搜索页
//                composable(
//                    route = RouteName.ARTICLE_SEARCH + "/{id}",
//                    arguments = listOf(navArgument("id") { type = NavType.IntType })
//                ) {
//                    SearchPage(navCtrl, scaffoldState)
//                }
//            }
            }
        },
        snackbarHost = {
//            SnackbarHost(
//                hostState = scaffoldState.snackbarHostState
//            ) { data ->
//                println("actionLabel = ${data.actionLabel}")
//                AppSnackBar(data = data)
//            }
        }
    )

}