package com.uolimzhanov.businesscard.viewmodels

import com.uolimzhanov.businesscard.model.repository.BadgeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BadgeViewModel @Inject constructor(
    private val repository: BadgeRepository
){
}