package com.atulya.bookmarkbutler.ui.pages.addurl

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddLinkPage(
    modifier: Modifier = Modifier,
    addLinkPageViewModel: AddLinkPageViewModel = viewModel(),
    onSaved: () -> Unit
) {

    var tempTag by remember { mutableStateOf("") }


    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                GlobalScope.launch {
                    addLinkPageViewModel.saveBookmark()
                    withContext(Dispatchers.Main) {
                        onSaved()
                    }
                }
            }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = addLinkPageViewModel.bookmark.name,
                label = { Text("Name") },
                onValueChange = { text ->
                    addLinkPageViewModel.bookmark = addLinkPageViewModel.bookmark.copy(name = text)
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = addLinkPageViewModel.bookmark.url,
                label = { Text("Url") },
                onValueChange = { text ->
                    addLinkPageViewModel.bookmark = addLinkPageViewModel.bookmark.copy(url = text)
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = addLinkPageViewModel.bookmark.description ?: "",
                label = { Text("Description (Optional)") },
                onValueChange = { text ->
                    addLinkPageViewModel.bookmark =
                        addLinkPageViewModel.bookmark.copy(description = text)
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = tempTag,
                label = { Text("Enter tags") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    if (!addLinkPageViewModel.bookmark.tags.contains(tempTag)) addLinkPageViewModel.bookmark.tags.add(
                        tempTag
                    )
                    tempTag = ""
                }),
                onValueChange = { text ->
                    tempTag = text
                },
                modifier = Modifier.fillMaxWidth()
            )

            FlowRow(modifier = Modifier.padding(8.dp)) {
                addLinkPageViewModel.bookmark.tags.forEach { tag ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.padding(2.dp)
                    ) {

                        Text(
                            tag,
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 8.dp)
                                .clickable {
                                    addLinkPageViewModel.bookmark.tags.remove(tag)
                                }
                        )

                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun AddLinkPagePreview() {
//    AddLinkPage(onSaveBookmark = {})
}