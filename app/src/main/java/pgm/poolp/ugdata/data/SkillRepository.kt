package pgm.poolp.ugdata.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SkillRepository @Inject constructor(private val skillDao: SkillDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allSkills: Flow<List<Skill>> = skillDao.getSkills()
    fun getSkill(skillId: String) = skillDao.getSkill(skillId)
    fun getSkillWithChampions(championId: String) = skillDao.getSkillWithChampions(championId)
}
