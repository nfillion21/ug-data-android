package pgm.poolp.ugdata.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skills")
data class Skill(
    @PrimaryKey @ColumnInfo(name = "id") val skillId: String,
    val name: String,
    val description: String
)
