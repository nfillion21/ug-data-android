package pgm.poolp.ugdata.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Champion(
    @PrimaryKey (autoGenerate = false) val championId: String,
    val name: String,
    val imageUrl: String
)
