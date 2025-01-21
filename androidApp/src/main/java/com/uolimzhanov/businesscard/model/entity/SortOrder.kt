package com.uolimzhanov.businesscard.model.entity

import androidx.annotation.StringRes
import com.uolimzhanov.businesscard.R

enum class SortOrder(@StringRes val textId: Int) {
    NAME(R.string.sort_name),
    NAME_ASC(R.string.sort_name),
    DATE(R.string.sort_date),
    DATE_ASC(R.string.sort_date);
}