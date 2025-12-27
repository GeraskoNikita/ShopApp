package com.example.shopapp.ui.fragment.product_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle

import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle

import androidx.navigation.fragment.findNavController
import com.example.shopapp.data.model.ProductDto

import com.example.shopapp.databinding.FragmentProductListBinding
import com.example.shopapp.domain.models.Product
import com.example.shopapp.ui.models.UIState
import com.example.shopapp.ui.fragment.product_list.adapter.ProductListAdapter
import com.example.shopapp.ui.fragment.product_list.viewmodel.ProductListViewModel
import com.example.shopapp.ui.fragment.product_details.ProductDetailsFragmentArgs
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private var adapter = ProductListAdapter {}
    private val viewModel: ProductListViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductListAdapter { product ->
            onClick(product)
        }
        binding.recyclerView.adapter = adapter
        observeState()

    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.state.collect { state ->
                    when (state) {
                        is UIState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.recyclerView.isVisible = false
                        }

                        is UIState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerView.isVisible = true
                            adapter.submitList(state.data)

                        }

                        is UIState.Error -> {
                            binding.progressBar.isVisible = true
                            binding.recyclerView.isVisible = false
                            Toast.makeText(
                                requireContext(),
                                "Ошибка: ${state.message}",
                                Toast.LENGTH_SHORT
                            )

                        }
                    }
                }

            }


        }
    }

    private fun onClick(product: Product) {
        binding.progressBar.isVisible = true
        binding.recyclerView.isVisible = false
        viewLifecycleOwner.lifecycleScope.launch {
            try {

                val action =
                    ProductListFragmentDirections.Companion
                        .actionProductListFragmentToProductDitailsFragment(product.id)

                findNavController().navigate(action)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Ошибка: ${e.message}", Toast.LENGTH_SHORT)
            } finally {
                binding.progressBar.isVisible = false
                binding.recyclerView.isVisible = true
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}