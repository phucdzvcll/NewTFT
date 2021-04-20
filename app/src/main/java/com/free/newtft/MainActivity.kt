package com.free.newtft

import android.os.Bundle
import com.free.common_android.BaseActivity
import com.free.common_android.replaceFragment
import com.free.newtft.view.MainFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = MainFragment.newInstance()
        replaceFragment(fragment, R.id.frameHome)
    }
}