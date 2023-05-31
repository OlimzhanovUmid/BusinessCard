package com.uolimzhanov.businesscard.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uolimzhanov.businesscard.R

@Composable
fun InfoScreen(
    modifier: Modifier,
    paddingValues: PaddingValues
){
    val scrollState = rememberScrollState()
    Column (
        modifier = modifier
            .fillMaxSize().padding(paddingValues)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top
    )  {
        Text(
            text = stringResource(R.string.info_text),
            Modifier.padding(all = 16.dp))
    }
}