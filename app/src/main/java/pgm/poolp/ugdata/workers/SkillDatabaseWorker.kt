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
import pgm.poolp.ugdata.data.Champion
import pgm.poolp.ugdata.data.ChampionRoomDatabase
import pgm.poolp.ugdata.data.Skill

class SkillDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val skillType = object : TypeToken<List<Skill>>() {}.type
                        val skillList: List<Skill> = Gson().fromJson(jsonReader, skillType)

                        val database = ChampionRoomDatabase.getInstance(applicationContext)
                        database.skillDao().insertAll(skillList)

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
        private const val TAG = "SkillDatabaseWorker"
        const val KEY_FILENAME = "SKILL_DATA_FILENAME"
    }
}
