package com.free.newtft.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.free.common_jvm.extension.defaultEmpty

object BindingCommon {
    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url.defaultEmpty())
            .into(imageView)
    }
}