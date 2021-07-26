package pgm.poolp.ugdata.data

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import pgm.poolp.ugdata.utilities.CHAMPION_DATA_FILENAME
import pgm.poolp.ugdata.utilities.DATABASE_NAME
import pgm.poolp.ugdata.workers.ChampionDatabaseWorker
import pgm.poolp.ugdata.workers.ChampionDatabaseWorker.Companion.KEY_FILENAME

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */
@Database(entities = [Champion::class], version = 1, exportSchema = false)
abstract class ChampionRoomDatabase : RoomDatabase() {

    abstract fun championDao(): ChampionDao

    companion object {
        @Volatile
        private var instance: ChampionRoomDatabase? = null

        fun getInstance(context: Context): ChampionRoomDatabase {
            return instance ?: synchronized(this)
            {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ChampionRoomDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ChampionRoomDatabase::class.java,
                DATABASE_NAME
            )
                // Wipes and rebuilds instead of migrating if no Migration object.
                // Migration is not part of this codelab.
                //.fallbackToDestructiveMigration()
                .addCallback(ChampionDatabaseCallback(context))
                .build()
        }

        private class ChampionDatabaseCallback(
            private val context: Context
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                val request = OneTimeWorkRequestBuilder<ChampionDatabaseWorker>()
                    .setInputData(workDataOf(KEY_FILENAME to CHAMPION_DATA_FILENAME))
                    .build()
                WorkManager.getInstance(context).enqueue(request)
            }
        }
    }
}