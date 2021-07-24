package pgm.poolp.ugdata.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import pgm.poolp.ugdata.data.Champion
import pgm.poolp.ugdata.data.ChampionRepository

class ChampionViewModel(private val repository: ChampionRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<Champion>> = repository.allWords.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(champion: Champion) = viewModelScope.launch {
        repository.insert(champion)
    }
}

class WordViewModelFactory(private val repository: ChampionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChampionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChampionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
