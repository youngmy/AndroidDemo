package com.young.androiddemo.demo.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.young.androiddemo.R
import com.young.commonlibrary.LogHelper

class SimpleDialogFragment : DialogFragment()  {

    companion object {
        const val TAG = "SimpleDialogFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置 Style
        setStyle(STYLE_NO_FRAME, R.style.DialogFragmentNoPaddingStyle)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_simple_dialog,container,false)
        return view
    }

    override fun onStart() {
        super.onStart()
        val window=dialog?.window
        // 设置宽度为铺满
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        // 设置布局内容显示在底部
        window?.setGravity(Gravity.BOTTOM)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        LogHelper.logI("===onDismiss===",TAG)
    }

}