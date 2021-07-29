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
import pgm.poolp.ugdata.data.UGDataRoomDatabase

class ChampionDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(CHAMPION_KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val championType = object : TypeToken<List<Champion>>() {}.type
                        val championList: List<Champion> = Gson().fromJson(jsonReader, championType)

                        val database = UGDataRoomDatabase.getInstance(applicationContext)
                        database.championDao().insertAll(championList)

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
        private const val TAG = "ChampionDatabaseWorker"
        const val CHAMPION_KEY_FILENAME = "CHAMPION_DATA_FILENAME"
    }
}
