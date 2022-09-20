package com.example.entropy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.entropy.R
import com.example.entropy.databinding.FragmentEntropyBinding
import com.example.entropy.viewmodel.EnytropyViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs
import kotlin.math.log
import kotlin.math.nextDown

class EntropyFragment : Fragment() {
    private var _binding: FragmentEntropyBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: EnytropyViewModel by viewModel()
    private val args: EntropyFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntropyBinding.inflate(inflater, container, false)
        val view = binding.root

        setListener()

        return view
    }

    private fun setListener() {

        binding.backEntropy.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonCalculo.setOnClickListener {
            var auxString1 = binding.calculo1.text.toString().toDouble()
            var auxString2 = binding.calculo2.text.toString().toDouble()
            viewModel.calculate(auxString1, auxString2, args.repetitionsLog.toInt()).observe(viewLifecycleOwner){ result ->
                if (result != 0.0){
                    binding.result.text = result.toString().substring(1)
                } else{
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Calculo")
                        .setMessage("Seu calculo foi confirmado, por favor informe os calculos restantes")
                        .setPositiveButton("Ok") { dialog, which ->
                            // Respond to positive button press
                        }
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}