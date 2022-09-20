package com.example.entropy.di

import com.example.entropy.viewmodel.EntropyGainViewModel
import com.example.entropy.viewmodel.EnytropyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val entropyModule = module{
    viewModelOf(::EnytropyViewModel)
}

val entropyGainModule = module {
    viewModel{ EntropyGainViewModel() }
}