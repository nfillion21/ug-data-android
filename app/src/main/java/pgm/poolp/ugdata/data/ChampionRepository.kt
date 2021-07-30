package pgm.poolp.ugdata.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChampionRepository @Inject constructor(private val championDao: ChampionDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allChampions: Flow<List<Champion>> = championDao.getChampions()

    fun getChampion(championId: String) = championDao.getChampion(championId)
    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    /*
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(champion: Champion) {
        championDao.insert(champion)
    }
    */
}
