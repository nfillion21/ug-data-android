package pgm.poolp.ugdata.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pgm.poolp.ugdata.data.ChampionSkillCrossRef
import pgm.poolp.ugdata.data.UGDataRoomDatabase

class ChampionSkillDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(CHAMPION_SKILL_KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val championSkillCrossRefType = object : TypeToken<List<ChampionSkillCrossRef>>() {}.type
                        val championSkillCrossRefList: List<ChampionSkillCrossRef> = Gson().fromJson(jsonReader, championSkillCrossRefType)

                        val database = UGDataRoomDatabase.getInstance(applicationContext)
                        database.championSkillCrossRefDao().insertAll(championSkillCrossRefList)

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "ChampionSkillDatabaseWorker"
        const val CHAMPION_SKILL_KEY_FILENAME = "CHAMPION_SKILL_DATA_FILENAME"
    }
}
