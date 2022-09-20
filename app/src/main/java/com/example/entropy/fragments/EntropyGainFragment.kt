package com.example.entropy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.entropy.R
import com.example.entropy.databinding.FragmentEntropyGainBinding
import com.example.entropy.viewmodel.EntropyGainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class EntropyGainFragment : Fragment() {
    private var _binding: FragmentEntropyGainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: EntropyGainViewModel by viewModel()
    private val args: EntropyGainFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntropyGainBinding.inflate(inflater, container, false)
        val view = binding.root

        setListener()

        return view
    }

    private fun setListener() {
        binding.backEntropyGain.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonCalculateGain.setOnClickListener {
            var entropyMain      = binding.entropyMain.text.toString().toDouble()
            var calculo1         = binding.calculo1Gain.text.toString().toDouble()
            var calculo2         = binding.calculo2Gain.text.toString().toDouble()
            var entropySecundary = binding.entropySecundary.text.toString().toDouble()

            viewModel.calcularGain(
                entropyMain,
                calculo1,
                calculo2,
                entropySecundary,
                args.repetitionsGain.toInt()
            ).observe(viewLifecycleOwner){ result ->
                if ( result != 0.0){
                    binding.buttonCalculateGain.isEnabled = false
                    binding.entropyGainResult.text = result.toString()
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