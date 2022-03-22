package template_package.ui.samleremotecategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import template_package.com.databinding.FragmentCategoryGridBinding
import template_package.data.models.CategoriesModel
import template_package.utils.showSnackBar

//TODO: remove this whole package if you are USING ~~~~~~~ LOCAL DATABASE ~~~~~~~
@AndroidEntryPoint
class CategoryGridFragmentRemote : Fragment(),
    CategoriesRecyclerAdapterRemote.InteractionListener {

    private var _binding: FragmentCategoryGridBinding? = null
    private val binding get() = _binding!!
    private val viewModelRemote: SampleCategoriesViewModelRemote by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModelRemote.state.observe(viewLifecycleOwner) {
            when (it) {
                is SampleCategoriesViewModelRemote.UIState.Success -> {
                    val adapter = CategoriesRecyclerAdapterRemote()
                    adapter.addAll(it.data)
                    adapter.serInteractionListener(this)
                    binding.rvCategoryList.adapter = adapter
                }
                is SampleCategoriesViewModelRemote.UIState.Loading -> {
                    binding.progressBar.visibility = it.visibility
                }
                is SampleCategoriesViewModelRemote.UIState.Error -> {
                    binding.root.showSnackBar(it.message)
                }
                else -> {}
            }
        }
    }

    override fun onCategoryClick(category: CategoriesModel) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}