package com.free.newtft.features.main.weapon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.free.common_android.AppDispatchers
import com.free.common_android.BaseViewModel
import com.free.domain.entities.WeaponEntity
import com.free.domain.usecases.base.UseCaseParams
import com.free.domain.usecases.show_champ.GetListWeaponUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeaponViewModel(
    private val appDispatchers: AppDispatchers,
    private val getListWeaponUseCase: GetListWeaponUseCase
) : BaseViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val weaponListLiveData = MutableLiveData<List<WeaponEntity>>()
    var job: Job? = null
    fun getListWeapon(isElement: Boolean) {
        isLoading.value = true
        weaponListLiveData.value = listOf()
        job?.cancel()
        job = viewModelScope.launch(appDispatchers.main) {
            val result = withContext(appDispatchers.io) {
                getListWeaponUseCase.execute(
                    GetListWeaponUseCase.GetListWeaponUseCaseParam(
                        isElement
                    )
                )
            }
            result.either({

            }, {
                weaponListLiveData.value = it
                isLoading.value = false
            })
        }
    }

}