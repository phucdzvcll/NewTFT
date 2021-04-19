package com.free.common_android

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.free.common_android.navigation.NavigateAction
import com.free.common_android.navigation.NavigateViewModel
import org.koin.android.viewmodel.ext.android.viewModel

abstract class BaseActivity : AppCompatActivity() {

    private val navigateViewModel: NavigateViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        navigateViewModel.navigateLiveData.observe(this, Observer { action ->
            when (action) {
                is NavigateAction.BackAction -> {
                    navigateBack()
                }
                is NavigateAction.ToAction -> {
                    onNavigateTo(action)
                }

            }
        })
    }

    open fun onNavigateTo(action: NavigateAction.ToAction) {

    }

    open fun navigateBack() {
        onBackPressed()
    }

}