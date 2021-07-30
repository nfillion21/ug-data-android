package pgm.poolp.ugdata.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pgm.poolp.ugdata.data.ChampionRepository
import javax.inject.Inject

@HiltViewModel
class ChampionDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    championRepository: ChampionRepository,
    //private val gardenPlantingRepository: GardenPlantingRepository,
) : ViewModel() {

    val championId: String = savedStateHandle.get<String>(CHAMPION_ID_SAVED_STATE_KEY)!!
    val champion = championRepository.getChampion(championId).asLiveData()
    /*
    val isPlanted = gardenPlantingRepository.isPlanted(plantId).asLiveData()

    fun addPlantToGarden() {
        viewModelScope.launch {
            gardenPlantingRepository.createGardenPlanting(plantId)
        }
    }

    fun hasValidUnsplashKey() = (BuildConfig.UNSPLASH_ACCESS_KEY != "null")
    */

    companion object {
        private const val CHAMPION_ID_SAVED_STATE_KEY = "championId"
    }
}
