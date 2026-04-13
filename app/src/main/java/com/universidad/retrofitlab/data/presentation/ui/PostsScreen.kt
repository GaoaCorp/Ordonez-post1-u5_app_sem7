package com.universidad.retrofitlab.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.universidad.retrofitlab.presentation.viewmodel.PostsUiState
import com.universidad.retrofitlab.presentation.viewmodel.PostsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(viewModel: PostsViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(title = { Text("Posts — Unidad 5") })
    }) { padding ->
        Box(
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (val state = uiState) {
                is PostsUiState.Loading ->
                    CircularProgressIndicator(Modifier.align(Alignment.Center))

                is PostsUiState.Empty ->
                    Text(
                        "No hay posts disponibles.",
                        Modifier.align(Alignment.Center)
                    )

                is PostsUiState.Error ->
                    Column(
                        Modifier
                            .align(Alignment.Center)
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            state.message,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { viewModel.loadPosts() }) {
                            Text("Reintentar")
                        }
                    }

                is PostsUiState.Success ->
                    LazyColumn(Modifier.fillMaxSize()) {
                        items(state.posts) { post -> PostCard(post) }
                        item {
                            Button(
                                onClick = { viewModel.loadNextPage() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text("Cargar más")
                            }
                        }
                    }
            }
        }
    }
}