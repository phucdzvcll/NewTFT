package com.free.newtft.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.free.common_android.BaseFragment
import com.free.newtft.databinding.FragmentMainBinding

class MainFragment : BaseFragment() {
    private lateinit var mMainBiding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mMainBiding = FragmentMainBinding.inflate(inflater, container, false)
        return mMainBiding.root
    }
}