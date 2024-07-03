package com.young.androiddemo.ui.page.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.young.androiddemo.R
import com.young.androiddemo.bean.DemoTitleBean
import com.young.androiddemo.constants.NavigateRouter
import com.young.androiddemo.data.AppSystemSetManage
import com.young.androiddemo.data.navList
import com.young.androiddemo.demo.compose.utils.ClickHelper
import com.young.androiddemo.test.TestActivity
import com.young.androiddemo.ui.theme.Color333333
import com.young.androiddemo.ui.theme.Color999999
import com.young.androiddemo.ui.theme.ColorF5F5F5
import com.young.androiddemo.ui.theme.ColorFFFFFF
import com.young.androiddemo.ui.theme.ColorMain
import com.young.androiddemo.ui.theme.DiyTheme
import com.young.androiddemo.ui.theme.ThemeLiveData
import com.young.androiddemo.ui.theme.backgroundColorDark
import com.young.androiddemo.ui.theme.backgroundColorLight
import com.young.androiddemo.widget.CancelButton
import com.young.androiddemo.widget.CommonAppBar
import com.young.androiddemo.widget.RoundedCornerButton
import com.young.commonlibrary.LogHelper
import com.young.commonlibrary.ext.click
import com.young.commonlibrary.store.DataStoreHelper
import com.young.commonlibrary.utils.JsonHelper
import com.young.commonlibrary.widgets.SlideAnimatedNavHost
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class, ExperimentalLayoutApi::class)
@Composable
fun HomePageScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val dataList = remember { mutableStateListOf<DemoTitleBean>() }
    //让界面能够滑动
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        item {
            //流式布局
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                dataList.forEachIndexed { index, demoTitleBean ->
                    //卡片布局
                    Card(
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable {
                                ClickHelper.click(context, demoTitleBean)


//                                Toast
//                                    .makeText(context, dataList[index].title, Toast.LENGTH_SHORT)
//                                    .show()
//                                val externalFilesDir = PathHelper.getExternalFilesDir(context = context)
//                                val rootFile = File(externalFilesDir, "test")
//                                rootFile.mkdirs()
//                                val file = File(rootFile, "test.docx")
//
//                                /**
//                                 * https://blog.csdn.net/YDHIT/article/details/131262257
//                                 */
//
//                                try {
//                                    LogHelper.logI("MainUi===file.length()::${file.length()}", "MainActivity===")
//                                    val contentUri = FileProvider.getUriForFile(context, "com.young.androiddemo.fileprovider", file)
////                                    val contentUri =FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)
//                                    val intent = Intent(Intent.ACTION_VIEW)
//                                    intent.setDataAndType(contentUri,"application/vnd.ms-word")
////                                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
//                                    context.startActivity(intent)
//                                } catch (e: Exception) {
//                                    e.printStackTrace()
//                                }
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = ColorF5F5F5,
                            contentColor = ColorMain,
                        ),
                        border = BorderStroke(color = ColorMain, width = 0.5.dp),//边框
                        shape = RoundedCornerShape(5.dp)//圆角
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 5.dp, top = 4.dp, end = 5.dp, bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = demoTitleBean.title, fontSize = 14.sp)
                        }
                    }
                }


            }
        }

    }

    /**
     * LaunchedEffect 是一个 Composable 函数，用于在单独的协程作用域中执行副作用。
     * 此函数可用于执行可能需要很长时间的操作（例如网络调用或动画），而不会阻塞 UI 线程。
     * 它需要两个参数 key 和 coroutineScope 块。
     * 在 key 参数中，你可以传递任何状态，因为它是 Any 类型。
     * 在 coroutineScope 块中，您可以传递任何挂起或非挂起的函数。
     * LaunchEffect 将始终在可组合函数中只运行一次。如果要再次运行 LaunchEffect，
     * 则必须在 key 参数中传递随时间变化的任何状态（mutableStateOf ，StateFlow）。
     * // 定义一个 LaunchedEffect 来异步执行长时间运行的操作，
     *     // 如果 isLoading.value 发生变化，LaunchedEffect 将取消并重新启动
     *     LaunchedEffect(isLoading.value) {
     *         if (isLoading.value) {
     *             val newData = fetchData()  // 执行长时间运行的操作，例如从网络获取数据
     *             data.value = newData       // 使用新数据更新状态
     *             isLoading.value = false
     *         }
     *     }
     * 原文链接：https://blog.csdn.net/pepsimaxin/article/details/134805526
     */
    LaunchedEffect(UInt) {
        dataList.addAll(JsonHelper.readLocalJson(context, Array<DemoTitleBean>::class.java, "demo_title.json").toMutableList())
    }

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomePageUI(
    modifier: Modifier = Modifier,
) {

}

@Composable
fun AddrBookScreen(index: Int) {
    var showDialog by remember { mutableStateOf(false) }
    Text(text = index.toString())
//    GeneralDialog(
//        dialogState = showDialog,
//        title = "请谨慎操作",
//        message = "清除所有数据\n包括文件夹、笔记、内容等",
//        isWaring = true,
//        positiveBtnText = "确定",
//        onPositiveBtnClicked = {
//
//        },
//        negativeBtnText = stringResource(id = R.string.cancel),
//        onNegativeBtnClicked = {
//
//        }
//    )
    if (showDialog) {
        DiyDialog(
            content = "自从白玉兰奖的入围名单揭晓以来，网络上的热议便如潮水般汹涌而来。在众多备受瞩目的作品中，电视剧《追风者》自然成为了讨论的焦点。尤其是剧中的两位主演王阳和王一博，他们的名字频频出现在热搜榜上，牵动着无数粉丝的心。",
            onDismissRequest = {
                showDialog = false
            },
            onNegativeClicked = {
                showDialog = false
            },
            onPositiveClicked = {
                showDialog = false
            }
        )
    }
}

@Composable
fun DiyDialog(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    containerColor: Color = ColorFFFFFF,
    title: String = "温馨提示",
    content: String = "",
    onDismissRequest: (() -> Unit)? = null,
    onPositiveClicked: (() -> Unit)? = null,
    onNegativeClicked: (() -> Unit)? = null,
) {
    Dialog(
        onDismissRequest = {
            onDismissRequest?.invoke()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                shape = shape,
                colors = CardDefaults.cardColors(containerColor = containerColor)
            ) {
                Spacer(modifier = modifier.height(25.dp))
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color333333,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                )
                Spacer(modifier = modifier.height(30.dp))
                Text(
                    text = content,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    color = Color333333,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp),
                )
                Spacer(modifier = modifier.height(30.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    RoundedCornerButton(
                        onClick = {
                            onNegativeClicked?.invoke()
                        },
                        text = "取消",
                        containerColor = ColorF5F5F5,
                        textColor = Color999999,
                        modifier = modifier
                            .fillMaxHeight()
                            .padding(horizontal = 25.dp)
                            .weight(.5f)
                    )
                    RoundedCornerButton(
                        onClick = {
                            onPositiveClicked?.invoke()
                        },
                        text = "确定",
                        modifier = modifier
                            .fillMaxHeight()
                            .padding(horizontal = 25.dp)
                            .weight(.5f)
                    )
                }
                Spacer(modifier = modifier.height(25.dp))
            }
        }
    }
}