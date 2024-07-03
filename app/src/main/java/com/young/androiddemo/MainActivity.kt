package com.young.androiddemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.young.androiddemo.constants.NavigateRouter
import com.young.androiddemo.data.AppSystemSetManage
import com.young.androiddemo.ui.page.common.HomeEntry
import com.young.androiddemo.ui.page.main.MainPageScreen
import com.young.androiddemo.ui.theme.AndroidDemoTheme
import com.young.androiddemo.ui.theme.Color999999
import com.young.androiddemo.ui.theme.ColorMain
import com.young.androiddemo.ui.theme.DiyTheme
import com.young.androiddemo.ui.theme.ThemeLiveData
import com.young.androiddemo.widget.CancelButton
import com.young.androiddemo.widget.RoundedCornerButton
import com.young.commonlibrary.LogHelper
import com.young.commonlibrary.utils.NetConnectManager
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    companion object {
        private const val TAG = "MainActivity==="
        lateinit var mActivity: MainActivity
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
//    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeEntry()
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        installSplashScreen()
//        super.onCreate(savedInstanceState)
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//        mActivity = this
//        setContent {
//            navController = rememberNavController()
////            ThemeLiveData.postValue(if (AppSystemSetManage.darkUI){DiyTheme.Theme.Light}else{DiyTheme.Theme.Dark})
//            // val toastState = remember { ToastUIState() }
//            AndroidDemoTheme {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                ) {
//                    MainPageScreen(navController)
////                    HomePageUI()
////                    RootLayout()
////                    ToastUI(toastState)
//                }
//            }
////            observeEvent(key = EventBus.NavController) {
////                val route = it as String
////                navController.navigate(route)
////            }
////            /** toast */
////            observeEvent(key = EventBus.ShowToast) {
////                lifecycleScope.launch {
////                    val data = it as ToastModel
////                    toastState.show(data)
////                }
////            }
////            Test.test()
//            DecodeBase64Json.test()
//        }
//
//        LogHelper.logI("网络是否连接= ${NetConnectManager.isConnected()} & 网络类型= ${NetConnectManager.getConnectType().value}", TAG)
//        NetConnectManager.addNetTypeChangeListener {
//            LogHelper.logI("网络类型[监听]= ${it.value}", TAG)
//        }
//        NetConnectManager.addNetStatusChangeListener {
//            LogHelper.logI("网络是否已连接[监听]= $it", TAG)
//        }
//
//        onBackPressedDispatcher.addCallback {
////           exitProcess(0)
//            // 回退时, 如果已在 Home首页, 则1.5秒内连击退出
//            if (navController.currentDestination?.route == NavigateRouter.HomePage.HOME) {
//                mBackTime = System.currentTimeMillis()
//            } else {
//                finish()
//            }
//        }
//
//
//    }

    // 1.5秒内的连击 退出应用
    private var mBackTime by Delegates.observable(0L) { _, old, new ->
        if (new - old > 1500) {
            Toast.makeText(mActivity, "再按一次退出", Toast.LENGTH_SHORT).show()
        } else {
            finish()
        }
    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun RootLayout(modifier: Modifier = Modifier) {

        Scaffold(
            modifier = modifier
                .background(DiyTheme.colors.background)
                .systemBarsPadding(),
//            topBar = {
//                CommonAppBar(
//                    navigationIcon = {
//
//                    },
//                    centerTitle = "标题",
//                    actionsIcon = {
//                        IconButton(onClick = {
//                            TestActivity.startActivity(this@MainActivity)
//                        }) {
//                            Icon(
//                                Icons.Default.Settings, contentDescription = "",
//                                modifier = modifier
//                                    .size(26.dp),
//                                tint = Color.White
//                            )
//                        }
//                    }
//                )
//
//            }
        ) { paddingValues ->// 内容区域，可以使用paddingValues来添加内边距
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                MainUi()
            }
        }


    }


}





@Composable
fun DemoUi(modifier: Modifier = Modifier) {

    val titleList = listOf("全部", "精选", "特价")
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        TabRow(
            modifier = modifier.height(50.dp),
            containerColor = Color.White,
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .width(10.dp)
                        .height(4.dp),
                    color = ColorMain
                )

            }
        ) {
            titleList.forEachIndexed { index, title ->
                Tab(
                    modifier = modifier.height(50.dp),
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                    },
                    selectedContentColor = ColorMain,
                    unselectedContentColor = Color999999,
                ) {
                    Text(modifier = modifier, text = title)
                }
            }
        }

    }
}


@Preview(showBackground = true)
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainUi(modifier: Modifier = Modifier) {

    //让界面能够滑动
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        item {
            RoundedCornerButton(
                modifier = modifier
                    .padding(10.dp)
            ) {
                AppSystemSetManage.setDarkMode(true)
                ThemeLiveData.postValue(DiyTheme.Theme.Light)
            }
            CancelButton(
                modifier = modifier
                    .padding(10.dp)
            ) {
                AppSystemSetManage.setDarkMode(false)
                ThemeLiveData.postValue(DiyTheme.Theme.Dark)

            }
        }

    }




}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidDemoTheme {
        Greeting("Android")
    }
}