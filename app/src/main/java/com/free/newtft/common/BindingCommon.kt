package com.free.newtft.common

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingCommon {
    @BindingAdapter("imgUrl")
    @JvmStatic
    fun loadImage(imageView: AppCompatImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}