package pgm.poolp.ugdata.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ShareCompat
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pgm.poolp.ugdata.R
import pgm.poolp.ugdata.data.Champion
import pgm.poolp.ugdata.databinding.FragmentChampionDetailBinding
import pgm.poolp.ugdata.ui.ChampionDetailFragment.Callback
import pgm.poolp.ugdata.viewmodels.ChampionDetailViewModel

/**
 * A fragment representing a single Plant detail screen.
 */
@AndroidEntryPoint
class ChampionDetailFragment : Fragment() {

    private val championDetailViewModel: ChampionDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentChampionDetailBinding>(
            inflater,
            R.layout.fragment_champion_detail,
            container,
            false
        ).apply {
            viewModel = championDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            callback = Callback { champion ->
                champion?.let {
                    hideAppBarFab(fab)
                    //championDetailViewModel.addPlantToGarden()
                    //Snackbar.make(root, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG)
                        //.show()
                    /*
                    val skills = championDetailViewModel.championWithSkills
                    val skills2 = championDetailViewModel.championWithSkills
                     */

                }
            }


            galleryNav.setOnClickListener { navigateToGallery() }

            var isToolbarShown = false

            // scroll change listener begins at Y = 0 when image is fully collapsed
            plantDetailScrollview.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                    // User scrolled past image to height of toolbar and the title text is
                    // underneath the toolbar, so the toolbar should be shown.
                    val shouldShowToolbar = scrollY > toolbar.height

                    // The new state of the toolbar differs from the previous state; update
                    // appbar and toolbar attributes.
                    if (isToolbarShown != shouldShowToolbar) {
                        isToolbarShown = shouldShowToolbar

                        // Use shadow animator to add elevation if toolbar is shown
                        appbar.isActivated = shouldShowToolbar

                        // Show the plant name if toolbar is shown
                        toolbarLayout.isTitleEnabled = shouldShowToolbar
                    }
                }
            )

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }
        }
        setHasOptionsMenu(true)

        /*
        championDetailViewModel.championWithSkills.observe(viewLifecycleOwner) { result ->

            val skills = championDetailViewModel.championWithSkills
            val skills2 = championDetailViewModel.championWithSkills
        }
        */

        return binding.root
    }

    private fun navigateToGallery() {
        /*
        championDetailViewModel.champion.value?.let { champion ->
            val direction =
                ChampionDetailFragmentDirections.actionChampionDetailFragmentToGalleryFragment(cham.name)
            findNavController().navigate(direction)
        }
        */
    }

    // FloatingActionButtons anchored to AppBarLayouts have their visibility controlled by the scroll position.
    // We want to turn this behavior off to hide the FAB when it is clicked.
    //
    // This is adapted from Chris Banes' Stack Overflow answer: https://stackoverflow.com/a/41442923
    private fun hideAppBarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }

    fun interface Callback {
        fun add(champion: Champion?)
    }
}
