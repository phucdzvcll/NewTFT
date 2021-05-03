package com.free.newtft.features.main.build_team.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.free.common_android.AppDispatchers
import com.free.common_android.BaseViewModel
import com.free.domain.entities.ChampOfTeamBuilderEntity
import com.free.domain.usecases.base.UseCaseParams
import com.free.domain.usecases.team_builder.GetListChampsBuilderUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamBuilderViewModel(
    private val appDispatchers: AppDispatchers,
    private val getListChampsBuilderUseCase: GetListChampsBuilderUseCase
) : BaseViewModel() {
    var champBuilderJob: Job? = null
    val champsBuilderLiveData = MutableLiveData<List<ChampOfTeamBuilderEntity>>()
    fun getListChampBuilder() {
        champsBuilderLiveData.value = listOf()
        champBuilderJob?.cancel()
        champBuilderJob = viewModelScope.launch(appDispatchers.main) {
            val result = withContext(appDispatchers.io) {
                getListChampsBuilderUseCase.execute(UseCaseParams.Empty)
            }
            result.either({
                Log.e("", "")
            }, {
                champsBuilderLiveData.value = it
            })
        }
    }
}