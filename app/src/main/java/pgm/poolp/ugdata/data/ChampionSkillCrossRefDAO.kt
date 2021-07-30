package pgm.poolp.ugdata.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface ChampionSkillCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(championSkillCrossRef: List<ChampionSkillCrossRef>)
}
