package pgm.poolp.ugdata.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Skill(
    @PrimaryKey (autoGenerate = false) val skillId: String,
    val name: String,
    val description: String
)
