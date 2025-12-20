package com.example.shopapp.ui.product_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.shopapp.data.model.ProductDto
import com.example.shopapp.databinding.ItemProductBinding

class ProductListAdapter(private val onClick: (ProductDto) -> Unit) :
    ListAdapter<ProductDto, ProductListAdapter.ProductListHolder>(
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


    class ProductDiffUtilCallback() : DiffUtil.ItemCallback<ProductDto>() {
        override fun areItemsTheSame(
            oldItem: ProductDto,
            newItem: ProductDto
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductDto,
            newItem: ProductDto
        ): Boolean {
            return oldItem == newItem
        }

    }

    inner class ProductListHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBint(product: ProductDto) {
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