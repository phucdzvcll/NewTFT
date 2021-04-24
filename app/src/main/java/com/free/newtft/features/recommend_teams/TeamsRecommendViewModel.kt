package com.free.newtft.features.recommend_teams

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.free.common_android.AppDispatchers
import com.free.common_android.BaseViewModel
import com.free.domain.entities.TeamsRecommendListEntity
import com.free.domain.usecases.base.UseCaseParams
import com.free.domain.usecases.show_champ.GetListTeamsRecommendUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamsRecommendViewModel(
    private val appDispatchers: AppDispatchers,
    private val getListTeamsRecommendUseCase: GetListTeamsRecommendUseCase
) : BaseViewModel() {

    val teamsRecommendListEntity: MutableLiveData<TeamsRecommendListEntity> = MutableLiveData()
    var jobTeamsRecommend: Job? = null

    fun getListTeamsRecommend() {
        teamsRecommendListEntity.value = TeamsRecommendListEntity(
            listOf(
                TeamsRecommendListEntity.TeamsRecommend(
                    id = 0,
                    rank = "",
                    listChamps = listOf(
                        TeamsRecommendListEntity.TeamsRecommend.Champ(
                            id = "",
                            name = "",
                            traits = listOf(""),
                            cost = 0
                        )
                    )
                )
            )
        )
        jobTeamsRecommend?.cancel()
        jobTeamsRecommend = viewModelScope.launch(appDispatchers.main) {
            val itemResult = withContext(appDispatchers.io) {
                getListTeamsRecommendUseCase.execute(UseCaseParams.Empty)
            }
            itemResult.either({
                Log.d("suu", "failure")
            }, { resuld ->
                teamsRecommendListEntity.value = resuld
            })
        }
    }

}