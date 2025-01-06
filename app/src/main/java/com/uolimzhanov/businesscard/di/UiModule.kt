package com.uolimzhanov.businesscard.di

import com.uolimzhanov.businesscard.viewmodels.BadgeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::BadgeViewModel)
}
