package com.inaki.fruitmvvmapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.inaki.fruitmvvmapp.databinding.FragmentFruitsBinding
import com.inaki.fruitmvvmapp.utils.ResponseState
import com.inaki.fruitmvvmapp.viewmodel.FruitViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FruitsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FruitsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentFruitsBinding

    private val viewModel: FruitViewModel by viewModels()

    private lateinit var fruitsViewModel: FruitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        fruitsViewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(FruitViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFruitsBinding.inflate(inflater, container, false)

        /**
         * Right here I am observing the livedata in the view model
         *
         * I am passing the lifecycleOwner, and the curly braces is my Observer
         */
        viewModel.searchFruit.observe(viewLifecycleOwner) { uiState ->
            when(uiState.state) {
                ResponseState.LOADING -> { // TODO show loading spinner
                     }
                ResponseState.SUCCESS -> {
                    // todo navigate to the next fragment
                }
                ResponseState.ERROR -> {
                    // todo display error to the user "invalid fruit"
                }
            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FruitsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}