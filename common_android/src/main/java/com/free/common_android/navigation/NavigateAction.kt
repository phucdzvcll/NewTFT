package com.free.common_android.navigation

sealed class NavigateAction {
    object BackAction : NavigateAction()
    abstract class ToAction : NavigateAction()
}
