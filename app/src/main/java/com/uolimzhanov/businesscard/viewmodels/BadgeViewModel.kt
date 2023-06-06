package com.uolimzhanov.businesscard.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uolimzhanov.businesscard.model.BadgeEvent
import com.uolimzhanov.businesscard.model.entity.SortOrder
import com.uolimzhanov.businesscard.model.repository.BadgeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class BadgeViewModel @Inject constructor(
    private val repository: BadgeRepository
) : ViewModel() {
    private val _sortOrder = MutableStateFlow(SortOrder.NAME)
    private val _badges = _sortOrder.
    flatMapLatest { _sortType ->
        when(_sortType) {
            SortOrder.NAME -> repository.getBadgesOrderedByName()
            SortOrder.NAME_ASC -> repository.getBadgesOrderedByNameAsc()
            SortOrder.DATE -> repository.getBadgesOrderedByDate()
            SortOrder.DATE_ASC -> repository.getBadgesOrderedByDateAsc()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(BadgeState())
    val state = combine(_state, _sortOrder, _badges) { state, sortType, badges ->
        state.copy(
            badges = badges,
            sortOrder = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BadgeState())

    fun onEvent(event: BadgeEvent) {
        when(event) {
            is BadgeEvent.DeleteBadge -> {
                viewModelScope.launch {
                    repository.deleteBadge(event.badge)
                }
            }
            BadgeEvent.HideBottomSheet -> {
                _state.update { it.copy(
                    isShowingBadge = false
                ) }
            }
            BadgeEvent.ShowBottomSheet -> {
                _state.update { it.copy(
                    isShowingBadge = true
                ) }
            }
            is BadgeEvent.SortBadges -> {
                _sortOrder.value = event.sortOrder
            }
            is BadgeEvent.UnLikeBadge -> {
                viewModelScope.launch {
                    repository.upsertBadge(
                        event.badge.copy(
                            isFavorite = !event.badge.isFavorite
                        )
                    )
                }
            }
            BadgeEvent.UpsertBadge -> TODO()
        }
    }
}