package pgm.poolp.ugdata.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skills")
data class Skill(
    @PrimaryKey val skillId: String,
    val name: String,
    val description: String
)
