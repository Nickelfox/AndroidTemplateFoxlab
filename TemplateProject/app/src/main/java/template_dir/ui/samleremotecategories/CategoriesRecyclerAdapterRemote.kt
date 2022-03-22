package template_package.ui.samleremotecategories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import template_package.com.R
import template_package.data.models.CategoriesModel
import template_package.com.databinding.ListItemCategoryBinding

class CategoriesRecyclerAdapterRemote : RecyclerView.Adapter<CategoriesRecyclerAdapterRemote.ViewHolder>() {

    private var list = mutableListOf<CategoriesModel>()
    private var listener: InteractionListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    fun serInteractionListener(interactionListener: InteractionListener?) {
        listener = interactionListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    fun addAll(list: List<CategoriesModel>?) {
        list?.let {
            this.list = it.toMutableList()
        }
    }

    inner class ViewHolder(private val binding: ListItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(categories: CategoriesModel, position: Int) {
            binding.tvText.text = itemView.context.getString(R.string.sample_text, position)
            Glide.with(binding.ivImage)
                .load(categories.imageUrl)
                .placeholder(R.drawable.placeholder_icon)
                .into(binding.ivImage)
            itemView.setOnClickListener { listener?.onCategoryClick(categories) }
        }
    }

    interface InteractionListener {
        fun onCategoryClick(category: CategoriesModel)
    }
}