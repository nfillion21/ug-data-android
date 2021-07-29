package pgm.poolp.ugdata.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "champions")
data class Champion(
    @PrimaryKey @ColumnInfo(name = "id") val championId: String,
    val name: String,
    val imageUrl: String = ""
)
