package com.example.shopapp.ui.fragment.product_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil3.load
import com.example.shopapp.R
import com.example.shopapp.data.model.ProductDto
import com.example.shopapp.databinding.FragmentProductDetailsBinding
import com.example.shopapp.domain.models.Product
import com.example.shopapp.ui.models.UIState
import com.example.shopapp.ui.fragment.product_details.viewmodel.ProductDetailsViewModel

import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: ProductDetailsFragmentArgs by navArgs()

    // Создаём ViewModel с productId через фабрику
    private val viewModel: ProductDetailsViewModel by viewModel {
        parametersOf(args.productId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is UIState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.productDetails.isVisible = false
                        }

                        is UIState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.productDetails.isVisible = true
                            loadData(state.data)
                        }

                        is UIState.Error -> {
                            binding.progressBar.isVisible = false
                            binding.productDetails.isVisible = false
                            Toast.makeText(
                                requireContext(),
                                "Ошибка: ${state.message}",
                                Toast.LENGTH_SHORT
                            ).show() // ← не забудь .show()!
                        }
                    }
                }
            }
        }
    }

    private fun loadData(data: Product) {
        with(binding) {
            productName.text = data.title
            productPrice.text = "${data.price} $"
            productDescription.text = data.description
            productImage.load(data.image)
            productRating.text = "${data.rating.rate} (${data.rating.count})"

            btnCancel.setOnClickListener { view ->
                parentFragmentManager.popBackStack()
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}