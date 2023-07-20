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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import com.atulya.bookmarkbutler.model.Link

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddLinkPage(
    modifier: Modifier = Modifier
) {

    var link by remember { mutableStateOf(Link()) }
    var tempTag by remember { mutableStateOf("") }

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = link.name,
                label = { Text("Name") },
                onValueChange = { text ->
                    link = link.copy(name = text)
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = link.url,
                label = { Text("Url") },
                onValueChange = { text ->
                    link = link.copy(url = text)
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = link.description ?: "",
                label = { Text("Description (Optional)") },
                onValueChange = { text ->
                    link = link.copy(description = text)
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = tempTag,
                label = { Text("Enter tags") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    link.tags.add(tempTag)
                    tempTag = ""
                }),
                onValueChange = { text ->
                    tempTag = text
                },
                modifier = Modifier.fillMaxWidth()
            )

            FlowRow(modifier = Modifier.padding(8.dp)) {
                link.tags.forEach { tag ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.padding(2.dp)
                    ) {

                        Text(
                            tag,
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 4.dp)
                                .clickable {
                                    link.tags.remove(tag)
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
    AddLinkPage()
}