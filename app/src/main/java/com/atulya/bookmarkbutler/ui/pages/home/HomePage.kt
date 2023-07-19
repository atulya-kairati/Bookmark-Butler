package com.atulya.bookmarkbutler.ui.pages.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.atulya.bookmarkbutler.ui.components.TopToolBar
import com.atulya.bookmarkbutler.ui.components.UnderConstruction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    onSearchClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    onAddClicked: () -> Unit,
) {


    val scrHeight = LocalConfiguration.current.screenHeightDp

    val barHeight = (scrHeight / 16 + 16).dp

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClicked) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {

        Box(modifier = modifier, contentAlignment = Alignment.TopStart) {

            Box(
                modifier = Modifier.padding(top = barHeight)
            ) {
                UnderConstruction(pageName = "HomePage", modifier = Modifier.padding(it))
            }

            TopToolBar(onSearchClicked = onSearchClicked, onSettingClicked = onSettingsClicked)
        }
    }
}
