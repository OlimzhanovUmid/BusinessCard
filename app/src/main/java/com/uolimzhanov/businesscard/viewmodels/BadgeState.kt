package com.uolimzhanov.businesscard.viewmodels

import com.uolimzhanov.businesscard.model.entity.Badge
import com.uolimzhanov.businesscard.model.entity.SortOrder

data class BadgeState(
    val badges: List<Badge> = emptyList(),
    val badgeUrl: String = "",
    val badgeName: String = "",
    val badgeDescription: String = "",
    val badgeDate: String = "",
    val isFavorite: Boolean = false,
    val sortOrder: SortOrder = SortOrder.NAME,
    val isShowingBadge: Boolean = false
)