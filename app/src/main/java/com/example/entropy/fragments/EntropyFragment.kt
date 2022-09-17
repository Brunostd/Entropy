package com.example.entropy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.entropy.R
import com.example.entropy.databinding.FragmentEntropyBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.math.abs
import kotlin.math.log
import kotlin.math.nextDown

class EntropyFragment : Fragment() {
    private var _binding: FragmentEntropyBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: EntropyFragmentArgs by navArgs()
    private var entropy: Double = 0.0
    private var listEntropy: MutableList<Double> = arrayListOf()
    private var masterEntropy: Double = 0.0

    private var auxCalculo: Double = 0.0
    private var count: Int = 0

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
            var auxString1 = binding.calculo1.text.toString()
            var auxString2 = binding.calculo2.text.toString()
            var a = auxString1.toDouble()
            var b = auxString2.toDouble()
            auxCalculo = (a/b)
            var auxLog = log(auxCalculo, 2.0)
            auxCalculo = -auxCalculo
            var negative = abs(auxCalculo)
            entropy = negative*auxLog

            if (count < args.repetitionsLog.toInt()){
                listEntropy.add(entropy)
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Calculo")
                    .setMessage("Seu calculo foi confirmado, por favor informe os calculos restantes")
                    .setPositiveButton("Ok") { dialog, which ->
                        // Respond to positive button press
                    }
                    .show()
            }
            count++
            if (count == args.repetitionsLog.toInt()){
                listEntropy.forEach {
                    masterEntropy += it
                    binding.buttonCalculo.isEnabled = false
                }
                var positive = abs(masterEntropy)
                binding.result.text = masterEntropy.toString().substring(1)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}