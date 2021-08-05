package pgm.poolp.ugdata.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pgm.poolp.ugdata.data.ChampionRepository
import pgm.poolp.ugdata.data.SkillRepository
import javax.inject.Inject

@HiltViewModel
class SkillDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    skillRepository: SkillRepository,
) : ViewModel() {

    val skillId: String = savedStateHandle.get<String>(SKILL_ID_SAVED_STATE_KEY)!!
    val skill = skillRepository.getSkill(skillId).asLiveData()
    val skillWithChampions = skillRepository.getSkillWithChampions(skillId).asLiveData()

    companion object {
        private const val SKILL_ID_SAVED_STATE_KEY = "skillId"
    }
}
