package com.uolimzhanov.businesscard.viewmodels

import com.uolimzhanov.businesscard.model.entity.Badge
import com.uolimzhanov.businesscard.model.entity.SortOrder

data class BadgeState(
    val badges: List<Badge> = emptyList(),
    val sortOrder: SortOrder = SortOrder.NAME,
    val selectedBadge: Badge? = null,
    val isShowingBadge: Boolean = false,
)