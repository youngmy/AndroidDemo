package com.young.commonlibrary.ext

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import com.young.commonlibrary.view.IconTextSpan

/**
 * 字符串直接调用富文本
 */
@SuppressLint("ObsoleteSdkInt")
fun String.toFromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(this)
    }
}

fun String.setIconSpan(context: Context, tag: String, bgColorResId: Int): SpannableString {
    val stringBuilder = StringBuilder()
    stringBuilder.append(
        if (tag.isEmpty()) {
            "\u0020"
        } else {
            var content = ""
            for (i in 0..tag.length) {
                content += "\u0020"
            }
            content
        }
    )
    val iconTextSpan = IconTextSpan(context, content = tag, bgColorResId = bgColorResId)
    stringBuilder.append(this)
    val spannableString = SpannableString(stringBuilder.toString())
    spannableString.setSpan(iconTextSpan, 0, tag.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
}