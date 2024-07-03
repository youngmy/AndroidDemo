package com.young.androiddemo.ui.page.service

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.asFlow
import kotlin.random.Random

@Composable
fun ServiceScreen(
    modifier: Modifier = Modifier
) {
    //竖向瀑布流
     LazyVerticalStaggeredGridDemo()
    //横向瀑布流
//    LazyHorizontalStaggeredGridDemo()

}

@Composable
fun LazyVerticalStaggeredGridDemo(
    modifier: Modifier=Modifier
) {
    LazyVerticalStaggeredGrid(
        columns =StaggeredGridCells.Fixed(2),//
//        columns =StaggeredGridCells.Adaptive(110.dp),
        modifier = modifier.fillMaxSize(),
        state = rememberLazyStaggeredGridState(),
        contentPadding = PaddingValues(10.dp),//布局内容的外间距
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalItemSpacing = 10.dp,
        flingBehavior = ScrollableDefaults.flingBehavior(),
        userScrollEnabled = true,//瀑布流是否可滑动
    ) {
        items(items) { item ->
            VerticalRandomColorBox(item = item)
        }
    }
}

@Composable
fun LazyHorizontalStaggeredGridDemo(
    modifier: Modifier=Modifier
) {
    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Adaptive(110.dp),
        modifier = modifier.fillMaxSize(),
        state = rememberLazyStaggeredGridState(),
        contentPadding = PaddingValues(16.dp),
        horizontalItemSpacing = 16.dp,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        flingBehavior = ScrollableDefaults.flingBehavior(),
        userScrollEnabled = true,//瀑布流是否可滑动
    )
    {

        items(items) { item ->
            HorizontalRandomColorBox(item = item)
        }
    }
}


data class ListItem(
    val height: Dp,
    val color: Color
)

var items = (1..100).map {
    ListItem(
        height = Random.nextInt(100,300).dp,
        color = Color(Random.nextLong(0xFFFFFF)).copy(alpha = 1f)
    )
}

@Composable
fun VerticalRandomColorBox(
    item:ListItem,
    modifier: Modifier=Modifier
){
    Box (
        modifier=modifier
            .fillMaxWidth()
            .height(item.height)
            .clip(RoundedCornerShape(10.dp))
            .background(item.color)
    )

}


@Composable
fun HorizontalRandomColorBox(
    item: ListItem,
   modifier: Modifier=Modifier
){
    Box(
        modifier = modifier
            .width(200.dp)//宽度可以随意调整
            .fillMaxHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(item.color)
    )

}