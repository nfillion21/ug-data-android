package pgm.poolp.ugdata.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampionDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM champion_table ORDER BY name")
    fun getChampions(): Flow<List<Champion>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(champion: Champion)

    @Query("DELETE FROM champion_table")
    suspend fun deleteAll()

    /*
    @Query("SELECT * FROM champion_table WHERE id = :championId")
    fun getChampion(championId: String): Flow<Champion>
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(champions: List<Champion>)
}
