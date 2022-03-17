package templete_dir.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import templete_dir.data.models.CategoriesModel
import com.innoviti.geniemobile.databinding.FragmentCategoryGridBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryGridFragment : Fragment(),
    CategoriesRecyclerAdapter.InteractionListener {

    private var _binding: FragmentCategoryGridBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SampleCategoriesViewModel by viewModels()

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
        viewModel.observeCategories().observe(viewLifecycleOwner) {
            val adapter = CategoriesRecyclerAdapter()
            adapter.addAll(it)
            adapter.serInteractionListener(this)
            binding.rvCategoryList.adapter = adapter
        }
    }

    override fun onCategoryClick(category: CategoriesModel) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}