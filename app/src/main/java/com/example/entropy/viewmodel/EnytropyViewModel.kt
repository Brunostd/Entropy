package com.example.entropy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.math.abs
import kotlin.math.log

class EnytropyViewModel: ViewModel() {

    private var listEntropy: MutableList<Double> = arrayListOf()
    private var auxCalculo: Double = 0.0
    private var entropy: Double = 0.0
    private var masterEntropy = MutableLiveData<Double>().apply {
        this.value = 0.0
    }
    private var count: Int = 0

    fun calculate(auxString1: Double, auxString2: Double, repetitionsLog: Int): LiveData<Double>{
        auxCalculo = (auxString1/auxString2)
        var auxLog = log(auxCalculo, 2.0)
        auxCalculo = -auxCalculo
        var negative = abs(auxCalculo)
        entropy = negative*auxLog

        if (count < repetitionsLog){
            listEntropy.add(entropy)
        }
        count++
        if (count == repetitionsLog){
            listEntropy.forEach {
                masterEntropy.value = masterEntropy.value?.plus(it)
            }
        }
        return masterEntropy
    }
}