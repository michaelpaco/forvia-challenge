package com.wazowski.forviachallenge.presentation

import android.os.*
import androidx.activity.*
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.*
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForviaChallengeTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }

                val scope = rememberCoroutineScope()

                val appList by viewModel.appsList.collectAsState()
                val isConnected by viewModel.isConnected.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }, topBar = {
                        TopBar(isConnected = isConnected) { enabled ->
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Debug mode ${if (enabled) "enabled" else "disabled"}",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    }) { innerPadding ->
                        NavHost(
                            navController,
                            startDestination = "home",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable(route = "home") {
                                HomeScreen(appList = appList, onCardClick = { appId ->
                                    navController.navigate("app/$appId")
                                })
                            }
                            composable(
                                route = "app/{id}", arguments = listOf(navArgument("id") {
                                    type = NavType.StringType
                                    nullable = false
                                    defaultValue = ""
                                })
                            ) { entry ->
                                val id = entry.arguments?.getString("id") ?: ""

                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = id)
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }

                LaunchedEffect(key1 = isConnected) {
                    viewModel.getAppsList()
                }
            }
        }
    }

    @Composable
    private fun TopBar(isConnected: Boolean, onDebugModeChange: (Boolean) -> Unit) {
        val coroScope = rememberCoroutineScope()
        var touchCount by remember { mutableIntStateOf(0) }
        var lastTouchTime by remember { mutableLongStateOf(0L) }
        var isInDebugMode by remember {
            mutableStateOf(false)
        }

        CenterAlignedTopAppBar(title = {
            Text(text = "Home", modifier = Modifier.clickable {
                val currentTime = System.currentTimeMillis()
                if ((currentTime - lastTouchTime) > 2000) {
                    touchCount = 0
                }
                lastTouchTime = currentTime
                touchCount++
                if (touchCount == 7) {
                    isInDebugMode = !isInDebugMode
                    onDebugModeChange(isInDebugMode)
                    touchCount = 0
                }
            })
        }, actions = {
            if (!isInDebugMode) {
                if (!isConnected) Icon(
                    modifier = Modifier.padding(end = 16.dp),
                    imageVector = Icons.Filled.WifiOff,
                    contentDescription = "No connection"
                )
            } else {
                IconButton(onClick = {
                    viewModel.getAppsList(isInDebugMode = isInDebugMode)
                }) {
                    Icon(imageVector = Icons.Filled.Refresh, contentDescription = "Refresh")
                }
                IconButton(onClick = {
                    coroScope.launch {
                        viewModel.deleteAll()
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Refresh")
                }
            }
        })
    }
}