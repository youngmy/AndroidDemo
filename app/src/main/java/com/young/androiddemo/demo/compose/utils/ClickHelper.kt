package com.young.androiddemo.demo.compose.utils

import android.content.Context
import com.young.androiddemo.bean.DemoTitleBean
import com.young.androiddemo.demo.compose.activity.MultiLanguageActivity
import com.young.androiddemo.demo.compose.activity.TabRowIndicatorActivity
import com.young.androiddemo.demo.jetpack.activity.MaterialDemoActivity

object ClickHelper {

    fun click(context: Context, demoTitleBean: DemoTitleBean) {
        when (demoTitleBean.event) {
            "tab_row_indicator" -> {
                TabRowIndicatorActivity.startActivity(context)
            }

            "multi_language" -> {
                MultiLanguageActivity.startActivity(context)
            }
            "material_widget"->{
                MaterialDemoActivity.startActivity(context)
            }

            else -> {

            }
        }
    }

}