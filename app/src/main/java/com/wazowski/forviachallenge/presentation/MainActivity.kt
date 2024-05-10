package com.wazowski.forviachallenge.presentation

import android.os.*
import androidx.activity.*
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
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
import com.wazowski.forviachallenge.common.Constants.LONG_ANIMATION_DURATION
import com.wazowski.forviachallenge.common.Constants.MEDIUM_ANIMATION_DURATION
import com.wazowski.forviachallenge.common.Constants.SHORT_ANIMATION_DURATION
import com.wazowski.forviachallenge.presentation.details.*
import com.wazowski.forviachallenge.presentation.home.*
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForviaChallengeTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                val snackbarHostState = remember { SnackbarHostState() }

                val scope = rememberCoroutineScope()

                val homeUiState = homeViewModel.uiState.collectAsState()
                val detailsUiState = detailsViewModel.uiState.collectAsState()
                val isConnected by mainViewModel.isConnected.collectAsState()

                val isHomeRoute by remember {
                    derivedStateOf { navBackStackEntry?.destination?.route == "home" }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }, topBar = {
                        AnimatedVisibility(
                            visible = isHomeRoute,
                            enter = fadeIn(tween(LONG_ANIMATION_DURATION)),
                            exit = fadeOut(tween(SHORT_ANIMATION_DURATION))
                        ) {
                            TopBar(isConnected = isConnected) { enabled ->
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Debug mode ${if (enabled) "enabled" else "disabled"}",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        }
                    }) { _ ->
                        NavHost(
                            navController,
                            startDestination = "home",
                        ) {
                            composable(route = "home", enterTransition = {
                                fadeIn(tween(MEDIUM_ANIMATION_DURATION))
                            }, exitTransition = {
                                fadeOut(tween(SHORT_ANIMATION_DURATION))
                            }, popEnterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.End, tween(
                                        MEDIUM_ANIMATION_DURATION
                                    )
                                )
                            }) {
                                HomeScreen(uiState = homeUiState) { appId ->
                                    navController.navigate("app/$appId")
                                }
                            }
                            composable(
                                route = "app/{id}",
                                arguments = listOf(navArgument("id") {
                                    type = NavType.IntType
                                }),
                                enterTransition = {
                                    slideIntoContainer(
                                        AnimatedContentTransitionScope.SlideDirection.Start,
                                        tween(MEDIUM_ANIMATION_DURATION)
                                    )
                                },
                                popExitTransition = {
                                    slideOutOfContainer(
                                        AnimatedContentTransitionScope.SlideDirection.End,
                                        tween(SHORT_ANIMATION_DURATION)
                                    )
                                },
                            ) { entry ->
                                val id = entry.arguments?.getInt("id")

                                id?.let {
                                    scope.launch {
                                        detailsViewModel.getAppById(appId = id)
                                    }
                                }

                                DetailsScreen(uiState = detailsUiState, onBackPressed = {
                                    navController.popBackStack()
                                })
                            }
                        }
                    }
                }

                LaunchedEffect(
                    key1 = isConnected,
                ) {
                    homeViewModel.getAppsList()
                }
            }
        }
    }

    @Composable
    private fun TopBar(
        isConnected: Boolean, onDebugModeChange: (Boolean) -> Unit
    ) {
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
                    homeViewModel.getAppsList(isInDebugMode = isInDebugMode)
                }) {
                    Icon(imageVector = Icons.Filled.Refresh, contentDescription = "Refresh")
                }
                IconButton(onClick = {
                    coroScope.launch {
                        mainViewModel.deleteAll()
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Refresh")
                }
            }
        })
    }
}