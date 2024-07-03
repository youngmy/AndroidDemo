package com.young.androiddemo.test

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.young.androiddemo.R
import com.young.androiddemo.demo.view.SimpleDialogFragment
import com.young.commonlibrary.LogHelper
import com.young.commonlibrary.datastore.EasyDataStore
import com.young.commonlibrary.ext.setIconSpan
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class TestActivity : AppCompatActivity() {

    companion object {

        private const val TAG = "TestActivity==="
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, TestActivity::class.java))
        }
    }

//    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "count-preferences")
//    val COUNTER = intPreferencesKey("counter")

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        setIconSpan()

        val tv = findViewById<TextView>(R.id.tv)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener {
//            lifecycleScope.launch {
////                dataStore.edit { preferences ->
////                    // 获取当前存储在dataStore内key为COUNTER的键值
////                    val currentCounterValue = preferences[COUNTER] ?: 0
////                    // 将改键值+1
////                    preferences[COUNTER] = currentCounterValue + 1
////                }
//                counter++
//                EasyDataStore.put("counter", counter)
//                tv.text = EasyDataStore.get("counter", 0).toString()
//            }
            // 创建弹窗实例
            SimpleDialogFragment().show(supportFragmentManager, "SimpleDialogFragment")

        }

        lifecycleScope.launch {
//            dataStore.data
//                .map {
//                    it[COUNTER] ?: 0
//                }.collect {
//                    tv.text = it.toString()
//                }
            counter = EasyDataStore.get("counter", 0)
            tv.text = counter.toString()
        }
        /**
         *
         * //存
         * viewModelScope.launch(Dispatchers.IO) {
         *   EasyDataStore.put("counter", Random.nextInt(0, 10))
         *
         *   val languages = listOf("zh", "en", "hk", "vn", "sg")
         *   EasyDataStore.put("language", languages.random())
         * }
         *
         * //取
         * val counterDeferred = async(Dispatchers.IO) {
         *   EasyDataStore.get("counter", -1)
         * }
         *
         */

    }



    /**
     * TextView文字前设置标签
     */
    private fun setIconSpan() {
        val commodityTitle = findViewById<TextView>(R.id.tv_commodity_title).apply {
            text = "得力大容量男生笔袋双层文具盒初中生高中生大学生ins日系多功能笔盒".setIconSpan(
                this@TestActivity,
                "天猫",
                com.young.commonlibrary.R.color.color_ff0036
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}