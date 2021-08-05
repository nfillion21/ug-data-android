package pgm.poolp.ugdata.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SkillDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("select * from skill order by name")
    fun getSkills(): Flow<List<Skill>>

    @Transaction
    @Query("select * from skill order by name")
    fun getSkillsWithChampions(): Flow<List<SkillWithChampions>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(skill: Skill)

    @Query("delete from skill")
    suspend fun deleteAll()

    @Query("select * from skill where skillId = :skillId")
    fun getSkill(skillId: String): Flow<Skill>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(skills: List<Skill>)

    @Transaction
    @Query("select * from skill where skillId = :skillId")
    fun getSkillWithChampions(skillId: String): Flow<SkillWithChampions>
}
