package pgm.poolp.ugdata.viewmodels

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pgm.poolp.ugdata.data.Champion
import pgm.poolp.ugdata.data.ChampionRepository
import pgm.poolp.ugdata.data.Skill
import pgm.poolp.ugdata.data.SkillRepository
import javax.inject.Inject

@HiltViewModel
class SkillViewModel @Inject internal constructor(
    skillRepository: SkillRepository
) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allSkills: LiveData<List<Skill>> = skillRepository.allSkills.asLiveData()
}