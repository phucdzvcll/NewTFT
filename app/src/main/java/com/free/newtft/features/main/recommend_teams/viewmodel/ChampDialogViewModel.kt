package com.free.newtft.features.main.recommend_teams.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.free.common_android.AppDispatchers
import com.free.common_android.BaseViewModel
import com.free.domain.entities.ChampDialogEntity
import com.free.domain.usecases.show_champ.GetChampDetailUseCase
import com.free.domain.usecases.show_champ.GetChampDialogUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChampDialogViewModel(
    private val appDispatchers: AppDispatchers,
    private val getChampDialogUseCase: GetChampDialogUseCase
) : BaseViewModel() {
    val champDialogLiveLiveData = MutableLiveData<ChampDialogEntity>()
    var job: Job? = null
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getChampDialog(id: String) {
        isLoading.value = true
        champDialogLiveLiveData.value = ChampDialogEntity(
            name = "",
            cost = 0,
            id = "",
            items = listOf("", "", ""),
            traits = listOf(
                ChampDialogEntity.Trait("", ""),
                ChampDialogEntity.Trait("", ""),
                ChampDialogEntity.Trait("", "")
            ),
            imgUrl = "",
            coverUrl = ""
        )
        job?.cancel()
        job = viewModelScope.launch(appDispatchers.main) {
            val result = withContext(appDispatchers.io) {
                getChampDialogUseCase.execute(GetChampDetailUseCase.GetChampDetailUseCaseParam(id))
            }
            result.either({

            }, {
                champDialogLiveLiveData.value = it
                isLoading.value = false
            })
        }
    }
}