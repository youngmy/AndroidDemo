package com.young.androiddemo.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


/**
 * 首页底部导航栏
 */
data class NavigationItem(
    /**
     * 名称
     */
    val title: String,
    /**
     * 图标
     */
    val icon: ImageVector,
)

/**
 * 底部导航数据
 */
val navList = listOf(
    NavigationItem(
        "主页",
        Icons.Filled.Home,
    ),
    NavigationItem(
        "服务",
        Icons.Filled.Place,
    ),
    NavigationItem(
        "发现",
        Icons.Filled.Search,
    ),
    NavigationItem(
        "我",
        Icons.Filled.Person,
    ),
)
