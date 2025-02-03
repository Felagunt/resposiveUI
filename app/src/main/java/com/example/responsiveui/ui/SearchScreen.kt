package com.example.responsiveui.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onTopicClick: (String) -> Unit
    //wm
) {
    //ui state collect
    val searchQuery = "" //by viewmodel
    SearchScreen(
        modifier,
        onBackClick = onBackClick,
        onTopicClick = onTopicClick,
        searchQuery = searchQuery,
        onSearchQueryChanged = {},
        onSearchTriggered = {},
    )
}

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onTopicClick: (String) -> Unit = {},
    searchQuery: String = "",
    onSearchQueryChanged: (String) -> Unit = {},
    onSearchTriggered: (String) -> Unit = {},
) {
    Column(modifier = modifier) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        SearchToolbar(
            onBackClick = onBackClick,
            searchQuery = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered
        )

    }
}

@Composable
private fun SearchToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit,
    searchQuery: String = ""
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
        SearchTextField(
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
            searchQuery = searchQuery
        )
    }
}

@Composable
private fun SearchTextField(
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit,
    searchQuery: String
) {
    val focusRequest = remember {
        FocusRequester()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSearchExplicitlyTriggered = {
        keyboardController?.hide()
        onSearchTriggered(searchQuery)
    }

    TextField(
        onValueChange = {
            onSearchQueryChanged(it)
        },
        value = searchQuery,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.onSurface
            )
        },
        trailingIcon = {

        },
        modifier = Modifier,
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchExplicitlyTriggered()
            }
        ),
        maxLines = 1,
        singleLine = true
    )
}
