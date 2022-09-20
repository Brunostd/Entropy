package com.example.entropy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EntropyGainViewModel:  ViewModel(){
    var listCalculo: MutableList<Double> = arrayListOf()
    var masterCalculo = MutableLiveData<Double>().apply {
        this.value = 0.0
    }
    var count: Int = 0

    fun calcularGain(
        entropyMain: Double,
        calculo1: Double,
        calculo2: Double,
        entropySecundary: Double,
        repetitionsGain: Int
    ): LiveData<Double>{

        var auxDivisao = calculo1/calculo2
        var auxCalculo = auxDivisao * entropySecundary

        if (count < repetitionsGain){
            listCalculo.add(auxCalculo)
        }
        count++
        if (count == repetitionsGain){
            listCalculo.forEach{
                masterCalculo.value = masterCalculo.value?.plus(it)
            }
            masterCalculo.value = entropyMain - masterCalculo.value!!
        }
        return masterCalculo
    }
}