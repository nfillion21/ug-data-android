package pgm.poolp.ugdata.data

import androidx.room.*

data class SkillWithChampions(
    @Embedded val skill: Skill,
    @Relation(
        parentColumn = "skillId",
        entityColumn = "championId",
        associateBy = Junction(ChampionSkillCrossRef::class)
    )
    val champions: List<Champion>
)
