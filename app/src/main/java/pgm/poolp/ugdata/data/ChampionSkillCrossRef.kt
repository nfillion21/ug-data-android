package pgm.poolp.ugdata.data

import androidx.room.*

@Entity(
    primaryKeys = ["championId", "skillId"],

    /*
    foreignKeys = [
        ForeignKey(entity = Champion::class, parentColumns = ["championId"], childColumns = ["championId"]),
        ForeignKey(entity = Skill::class, parentColumns = ["skillId"], childColumns = ["skillId"]),
    ],
    */
    //indices = [/*Index("championId"), */Index("skillId")]
)
data class ChampionSkillCrossRef(
    val championId: String,
    val skillId: String,
)