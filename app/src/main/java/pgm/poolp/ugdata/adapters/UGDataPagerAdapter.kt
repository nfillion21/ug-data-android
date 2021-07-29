package pgm.poolp.ugdata.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import pgm.poolp.ugdata.ui.ChampionListFragment
import pgm.poolp.ugdata.ui.SkillListFragment

const val CHAMPIONS_PAGE_INDEX = 0
const val SKILLS_PAGE_INDEX = 1

class UGDataPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        CHAMPIONS_PAGE_INDEX to { ChampionListFragment() },
        SKILLS_PAGE_INDEX to { SkillListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
