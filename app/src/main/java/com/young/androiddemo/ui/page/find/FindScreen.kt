package com.young.androiddemo.ui.page.find

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun FindScreen(
    modifier: Modifier = Modifier
) {
//    WidgetTabList(
//        leftModifier = {
//            modifier
//                .width(100.dp)
//                .fillMaxHeight()
//                .background(color = Color.Red)
//        },
//        tabList = ('A'..'Z').toList(),
//        itemList = ('A'..'Z').map { "$it$it" }.toList(),
//        tabItemLayout = {
//            Text(
//                text = it.toString(),
////                textAlign = TextAlign.Center,
////                lineHeight = 50,
//                modifier
//                    .fillMaxWidth()
//                    .height(50.dp))
//        }
//    )
}


private const val TAG = "WidgetTabList==="

enum class TabState {
    Normal, PreCheck, Check, NextCheck
}

enum class ItemType {
    Head, Content
}

//@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T, E> WidgetTabList(
    modifier: Modifier = Modifier,
    leftModifier: RowScope.() -> Modifier = { Modifier },
    rightModifier: RowScope.() -> Modifier = { Modifier },
    tabList: List<T>,
    itemList: List<E>,
//    findTabIndex: (Int, E) -> Int,
//    findItemIndex: (Int, T) -> Int,
//    getItemType: (E) -> ItemType,
    tabItemLayout: @Composable LazyItemScope.(
        T//, TabState
    ) -> Unit,
    itemHeadLayout: @Composable LazyItemScope.(E) -> Unit,
    itemContentLayout: @Composable LazyItemScope.(E) -> Unit
) {

    var isUserClick by remember {
        mutableStateOf(false)
    }
    var index by remember {
        mutableIntStateOf(0)
    }

    val listState = rememberLazyListState()
    val tabListState = rememberLazyListState()

//    val realIndex by remember(itemList) {
//        derivedStateOf() {
//            val fvIndex = listState.firstVisibleItemIndex
//
//            val i = if (itemList.isEmpty()) {
//                0
//            } else if (fvIndex >= itemList.size) {
//                findTabIndex(fvIndex, itemList.last())
//            } else {
//                findTabIndex(fvIndex, itemList[fvIndex])
//            }
//            Log.d(TAG, "derivedStateOf $fvIndex i = $i")
//            i
//        }
//    }

//    LaunchedEffect(key1 = realIndex) {
//        Log.d(TAG, "Content: index=$index;realIndex=$realIndex;isUserClick=$isUserClick")
//        if (!isUserClick && index != realIndex) {
//            index = realIndex
//            val fi = tabListState.firstVisibleItemIndex
//            val count = tabListState.layoutInfo.visibleItemsInfo.size
//            val step = tabListState.layoutInfo.viewportSize.height / 2f
//            Log.d(TAG, "Content: fi = $fi count=$count index = $index  tabHeight.value=${step}")
//            if (fi + count <= index + 1) {
//                tabListState.scrollBy(step)
//            } else if (index < fi) {
//                tabListState.scrollToItem(index)
//            }
//        }
//        isUserClick = false
//    }

    val scope = rememberCoroutineScope()
    Row(modifier = modifier) {
        LazyColumn(
            modifier = leftModifier(),
            state = tabListState,
        ) {
            itemsIndexed(tabList, { i, _ -> i }) { i, item ->

                Log.d(TAG, "click tab $i $item")
//                val tabState = when (i) {
//                    index -> TabState.Check
//                    index - 1 -> TabState.PreCheck
//                    index + 1 -> TabState.NextCheck
//                    else -> TabState.Normal
//                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable {
//                            Log.d(TAG, "click tab $i")
//                            if (index == i) return@clickable
//                            index = i
//                            isUserClick = true
//                            scope.launch {
//                                val itemIndex = findItemIndex(index, tabList[index])
//                                listState.scrollToItem(itemIndex)
//                            }
                        }
                ) {
                    tabItemLayout(item)
//                    tabItemLayout(item, tabState)


                }
            }
        }

        LazyColumn(modifier = rightModifier(), state = listState) {
//            itemList.forEach {
//                val type = getItemType(it)
//                when (type) {
//                    ItemType.Head -> {
////                            stickyHeader {
//                        item {
//                            itemHeadLayout(it)
//                        }
////                            }
//                    }
//
//                    ItemType.Content -> {
//                            itemContentLayout(it)
//
//
//                    }
//                }
//            }
        }
    }
}