package pgm.poolp.ugdata.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM champion ORDER BY name")
    fun getChampions(): Flow<List<Champion>>

    @Transaction
    @Query("SELECT * FROM champion ORDER BY name")
    fun getChampionsWithSkills(): Flow<List<ChampionWithSkills>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(champion: Champion)

    @Query("DELETE FROM champion")
    suspend fun deleteAll()

    @Query("SELECT * FROM champion WHERE championId = :championId")
    fun getChampion(championId: String): Flow<Champion>

    @Transaction
    @Query("select * from champion where championId = :championId")
    fun getChampionWithSkills(championId: String): Flow<ChampionWithSkills>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(champions: List<Champion>)
}
