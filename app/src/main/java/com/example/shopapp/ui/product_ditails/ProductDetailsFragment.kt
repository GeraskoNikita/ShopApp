package com.example.shopapp.ui.product_ditails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil3.load
import com.example.shopapp.databinding.FragmentProductDetailsBinding


class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: ProductDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.productDto
        val productRating = product.ratingDto

        binding.imgProduct.load(product.image)
        binding.tvTitle.text = product.title
        binding.tvDescription.text = product.description
        binding.tvPrice.text = product.price.toString()
        binding.tvRating.text = "${productRating.rate} (${productRating.count})"

        binding.btnCancel.setOnClickListener { view ->
            parentFragmentManager.popBackStack()
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}