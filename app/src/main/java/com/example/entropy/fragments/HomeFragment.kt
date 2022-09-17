package com.example.entropy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.entropy.R
import com.example.entropy.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        setView()
        setListener()

        return view
    }

    private fun setView() {
        binding.button.isEnabled = true
    }

    private fun setListener() {
        binding.button.setOnClickListener {
            if (binding.repeticoesLog.text.toString().isNotEmpty()){
                var aux = binding.repeticoesLog.text.toString()
                val action = HomeFragmentDirections.actionHomeFragmentToEntropyFragment(aux)
                findNavController().navigate(action)
            }
        }

        binding.buttonGain.setOnClickListener {
            if (binding.repeticoesEntropia.text.toString().isNotEmpty()){
                var aux = binding.repeticoesEntropia.text.toString()
                val action = HomeFragmentDirections.actionHomeFragmentToEntropyGainFragment(aux)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}