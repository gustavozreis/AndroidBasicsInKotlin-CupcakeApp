package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentNameBinding
import com.example.cupcake.model.OrderViewModel

/**
 * [NameFragment] asks for the users name
 */

class NameFragment : Fragment() {

    private var binding: FragmentNameBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentNameBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            nameFragment = this@NameFragment
        }
    }

    // navega para a nova tela e seta o nome do usuário
    fun goToNextScreen() {
        val userName: String = binding?.userName?.text.toString()
        sharedViewModel.setName(userName)
        findNavController().navigate(R.id.action_nameFragment_to_summaryFragment)
    }

    // cancela ordem e volta para o início
    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_nameFragment_to_startFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}