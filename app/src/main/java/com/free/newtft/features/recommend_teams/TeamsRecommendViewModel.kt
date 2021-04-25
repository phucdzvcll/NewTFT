package com.free.newtft.features.recommend_teams

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.free.common_android.AppDispatchers
import com.free.common_android.BaseViewModel
import com.free.domain.entities.TeamsRecommendEntity
import com.free.domain.usecases.base.UseCaseParams
import com.free.domain.usecases.show_champ.GetListTeamsRecommendUseCase
import com.free.domain.usecases.show_champ.GetTraitsOfTeamsRecommendUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamsRecommendViewModel(
    private val appDispatchers: AppDispatchers,
    private val getListTeamsRecommendUseCase: GetListTeamsRecommendUseCase,
    private val getTraitsOfTeamsRecommendUseCase: GetTraitsOfTeamsRecommendUseCase
) : BaseViewModel() {

    val teamsRecommendEntity: MutableLiveData<List<TeamsRecommendEntity>> = MutableLiveData()
    var jobTeamsRecommend: Job? = null

    fun getListTeamsRecommend() {
        teamsRecommendEntity.value = listOf(
            TeamsRecommendEntity(
                id = 0,
                rank = "",
                listChamps = listOf(
                    TeamsRecommendEntity.Champ(
                        id = "",
                        name = "",
                        traits = listOf(""),
                        cost = 0
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
                teamsRecommendEntity.value = resuld
            })
        }
    }


    var jobTraitsRecommend: Job? = null
    fun test() {
        jobTraitsRecommend?.cancel()
        jobTraitsRecommend = viewModelScope.launch(appDispatchers.main) {
            val itemResult = withContext(appDispatchers.io) {
                getTraitsOfTeamsRecommendUseCase.execute(
                    GetTraitsOfTeamsRecommendUseCase.GetTraitsOfTeamsRecommendUseCaseParam(
                        listOf("Adept", "Brawler")
                    )
                )
            }
            itemResult.either({
                Log.d("suu", "failure")
            }, { resuld ->
                Log.d("suu", "failure")
            })
        }
    }

}