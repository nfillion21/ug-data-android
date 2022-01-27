package pgm.poolp.ugdata.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pgm.poolp.ugdata.data.Champion
import pgm.poolp.ugdata.data.ChampionRepository
import javax.inject.Inject

@HiltViewModel
class ChampionViewModel @Inject internal constructor(
    championRepository: ChampionRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allChampions: LiveData<List<Champion>> = championRepository.allChampions.asLiveData()
}