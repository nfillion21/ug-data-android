package pgm.poolp.ugdata.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import pgm.poolp.ugdata.utilities.CHAMPION_DATA_FILENAME
import pgm.poolp.ugdata.utilities.DATABASE_NAME
import pgm.poolp.ugdata.utilities.SKILL_DATA_FILENAME
import pgm.poolp.ugdata.workers.ChampionDatabaseWorker
import pgm.poolp.ugdata.workers.ChampionDatabaseWorker.Companion.CHAMPION_KEY_FILENAME
import pgm.poolp.ugdata.workers.SkillDatabaseWorker
import pgm.poolp.ugdata.workers.SkillDatabaseWorker.Companion.SKILL_KEY_FILENAME

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */
@Database(entities = [Champion::class, Skill::class], version = 1, exportSchema = false)
abstract class UGDataRoomDatabase : RoomDatabase() {

    abstract fun championDao(): ChampionDao
    abstract fun skillDao(): SkillDao

    companion object {
        @Volatile
        private var instance: UGDataRoomDatabase? = null

        fun getInstance(context: Context): UGDataRoomDatabase {
            return instance ?: synchronized(this)
            {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): UGDataRoomDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                UGDataRoomDatabase::class.java,
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

                val requestChampions = OneTimeWorkRequestBuilder<ChampionDatabaseWorker>()
                    .setInputData(workDataOf(CHAMPION_KEY_FILENAME to CHAMPION_DATA_FILENAME))
                    .build()
                WorkManager.getInstance(context).enqueue(requestChampions)

                val requestSkills = OneTimeWorkRequestBuilder<SkillDatabaseWorker>()
                    .setInputData(workDataOf(SKILL_KEY_FILENAME to SKILL_DATA_FILENAME))
                    .build()
                WorkManager.getInstance(context).enqueue(requestSkills)

                /*
                TODO build the same for skills, then I can test many-to-many table data connections
                 */
            }
        }
    }
}