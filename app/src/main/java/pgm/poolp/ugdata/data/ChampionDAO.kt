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
    @Query("SELECT * FROM champion_table ORDER BY champion ASC")
    fun getAlphabetizedWords(): Flow<List<Champion>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Champion)

    @Query("DELETE FROM champion_table")
    suspend fun deleteAll()
}
