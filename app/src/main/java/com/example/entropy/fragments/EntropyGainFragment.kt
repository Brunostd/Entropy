package com.example.entropy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.entropy.R
import com.example.entropy.databinding.FragmentEntropyGainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EntropyGainFragment : Fragment() {
    private var _binding: FragmentEntropyGainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: EntropyGainFragmentArgs by navArgs()
    private var listCalculo: MutableList<Double> = arrayListOf()
    private var masterCalculo: Double = 0.0
    private var count: Int = 0

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

            var auxDivisao = calculo1/calculo2
            var auxCalculo = auxDivisao * entropySecundary

            if (count < args.repetitionsGain.toInt()){
                listCalculo.add(auxCalculo)
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Calculo")
                    .setMessage("Seu calculo foi confirmado, por favor informe os calculos restantes")
                    .setPositiveButton("Ok") { dialog, which ->
                        // Respond to positive button press
                    }
                    .show()
            }
            count++
            if (count == args.repetitionsGain.toInt()){
                listCalculo.forEach{
                    masterCalculo += it
                }
                masterCalculo = entropyMain - masterCalculo
                binding.buttonCalculateGain.isEnabled = false
                binding.entropyGainResult.text = masterCalculo.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}