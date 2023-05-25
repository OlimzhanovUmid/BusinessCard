package com.uolimzhanov.businesscard.model

import com.uolimzhanov.businesscard.model.entity.Badge
import com.uolimzhanov.businesscard.model.entity.SortOrder

sealed interface BadgeEvent {
    object UpsertBadge: BadgeEvent
    data class DeleteBadge(val badge: Badge): BadgeEvent
    data class UnLikeBadge(val badge: Badge): BadgeEvent
    data class SortBadges(val sortOrder: SortOrder): BadgeEvent
    object ShowBottomSheet: BadgeEvent
    object HideBottomSheet: BadgeEvent
}