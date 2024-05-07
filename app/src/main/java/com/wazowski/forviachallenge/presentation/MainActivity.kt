package com.wazowski.forviachallenge.presentation

import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForviaChallengeTheme {
                val navController = rememberNavController()
                val appList by viewModel.appsList.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold() { innerPadding ->
                        NavHost(
                            navController,
                            startDestination = "home",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("home") {
                                LazyColumn() {
                                    items(items = appList, key = { app -> app.id }) {
                                        Spacer(modifier = Modifier.height(16.dp))
                                        Text(text = it.name)
                                        Text(text = it.vername)
                                        Spacer(modifier = Modifier.height(16.dp))
                                    }
                                }
                            }
                        }
                    }
                }

                LaunchedEffect(key1 = true) {
                    viewModel.getAppsList()
                }
            }
        }
    }
}