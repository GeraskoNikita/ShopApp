package com.example.shopapp.ui.fragment.product_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.shopapp.databinding.ItemProductBinding
import com.example.shopapp.domain.models.Product

class ProductListAdapter(private val onClick: (Product) -> Unit) :
    ListAdapter<Product, ProductListAdapter.ProductListHolder>(
        ProductDiffUtilCallback()
    ) {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListHolder {
        return ProductListHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ProductListHolder,
        position: Int
    ) {
        holder.onBint(getItem(position))
    }


    class ProductDiffUtilCallback() : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class ProductListHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBint(product: Product) {
            with(binding) {
                tvTitle.text = product.title
                tvCategory.text = product.category
                tvPrice.text = "${product.price} $"
                ivProduct.load(product.image) {
                    crossfade(true)
                }

                root.setOnClickListener {
                    onClick(product)
                }

            }

        }
    }
}