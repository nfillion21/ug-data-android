package pgm.poolp.ugdata.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM champions ORDER BY name")
    fun getChampions(): Flow<List<Champion>>

    @Transaction
    @Query("SELECT * FROM champions ORDER BY name")
    fun getChampionsWithSkills(): Flow<List<ChampionWithSkills>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(champion: Champion)

    @Query("DELETE FROM champions")
    suspend fun deleteAll()

    @Query("SELECT * FROM champions WHERE championId = :championId")
    fun getChampion(championId: String): Flow<Champion>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(champions: List<Champion>)
}
