package com.uolimzhanov.businesscard.model

import com.uolimzhanov.businesscard.model.entity.Badge
import com.uolimzhanov.businesscard.model.entity.SortOrder

sealed interface BadgeEvent {
    data object UpsertBadge: BadgeEvent
    data class DeleteBadge(val badge: Badge): BadgeEvent
    data class UnLikeBadge(val badge: Badge): BadgeEvent
    data class SortBadges(val sortOrder: SortOrder): BadgeEvent
    data object ShowBottomSheet: BadgeEvent
    data object HideBottomSheet: BadgeEvent
}