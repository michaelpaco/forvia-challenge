package com.wazowski.forviachallenge.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.os.*
import androidx.activity.*
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.*
import androidx.navigation.compose.*
import com.wazowski.forviachallenge.common.Constants.LONG_ANIMATION_DURATION
import com.wazowski.forviachallenge.common.Constants.MEDIUM_ANIMATION_DURATION
import com.wazowski.forviachallenge.common.Constants.PADDING_M
import com.wazowski.forviachallenge.common.Constants.SHORT_ANIMATION_DURATION
import com.wazowski.forviachallenge.presentation.details.*
import com.wazowski.forviachallenge.presentation.home.*
import com.wazowski.forviachallenge.presentation.seemore.*
import com.wazowski.forviachallenge.presentation.theme.ForviaChallengeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val detailsViewModel: DetailsViewModel by viewModels()
    private val seeMoreViewModel: SeeMoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForviaChallengeTheme {
                val isSystemInDarkMode = isSystemInDarkTheme()
                val colorScheme = MaterialTheme.colorScheme

                val view = LocalView.current
                if (!view.isInEditMode) {
                    SideEffect {
                        val window = (view.context as Activity).window
                        window.statusBarColor = colorScheme.background.toArgb()
                        window.navigationBarColor = colorScheme.background.toArgb()
                        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                            !isSystemInDarkMode
                        WindowCompat.getInsetsController(
                            window,
                            view
                        ).isAppearanceLightNavigationBars = !isSystemInDarkMode
                    }
                }

                val navController = rememberNavController()

                val snackbarHostState = remember { SnackbarHostState() }

                val scope = rememberCoroutineScope()

                val isConnected by mainViewModel.isConnected.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }, topBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val isHomeRoute by remember {
                            derivedStateOf { navBackStackEntry?.destination?.route == "home" }
                        }

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
                    }) {
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
                                val homeUiState = homeViewModel.uiState.collectAsState()

                                HomeScreen(uiState = homeUiState, onCardClick = { appId ->
                                    navController.navigate("app/$appId")
                                }, onSeeMoreClick = {
                                    navController.navigate("see_more")
                                })
                            }
                            composable(
                                route = "app/{id}",
                                arguments = listOf(navArgument("id") {
                                    type = NavType.IntType
                                }),
                                enterTransition = {
                                    slideIntoContainer(
                                        AnimatedContentTransitionScope.SlideDirection.Start,
                                        tween(SHORT_ANIMATION_DURATION)
                                    )
                                },
                                exitTransition = {
                                    fadeOut(tween(LONG_ANIMATION_DURATION, easing = EaseIn))
                                },
                                popEnterTransition = {
                                    fadeIn(tween(LONG_ANIMATION_DURATION, easing = EaseIn))
                                },
                                popExitTransition = {
                                    slideOutOfContainer(
                                        AnimatedContentTransitionScope.SlideDirection.End,
                                        tween(MEDIUM_ANIMATION_DURATION)
                                    )
                                },
                            ) { entry ->
                                val detailsUiState = detailsViewModel.uiState.collectAsState()
                                val id = entry.arguments?.getInt("id")

                                LaunchedEffect(key1 = id) {
                                    id?.let {
                                        detailsViewModel.onNavigate(appId = id)
                                    }
                                }

                                DetailsScreen(uiState = detailsUiState, onCardClick = { appId ->
                                    navController.navigate("app/$appId") {
                                        launchSingleTop = true
                                    }
                                }, onBackPressed = {
                                    navController.popBackStack()
                                })
                            }
                            composable(
                                route = "see_more",
                                enterTransition = {
                                    slideIntoContainer(
                                        AnimatedContentTransitionScope.SlideDirection.Start,
                                        tween(MEDIUM_ANIMATION_DURATION)
                                    )
                                },
                                popEnterTransition = {
                                    fadeIn(tween(MEDIUM_ANIMATION_DURATION))
                                },
                                popExitTransition = {
                                    slideOutOfContainer(
                                        AnimatedContentTransitionScope.SlideDirection.End,
                                        tween(SHORT_ANIMATION_DURATION)
                                    )
                                },
                            ) {
                                val seeMoreUiState = seeMoreViewModel.uiState.collectAsState()

                                SeeMoreScreen(uiState = seeMoreUiState, onCardClick = { appId ->
                                    navController.navigate("app/$appId")
                                }, onBackPressed = {
                                    navController.popBackStack()
                                }, onFetchMoreApps = {
                                    if (!isConnected) return@SeeMoreScreen
                                    seeMoreViewModel.fetchMoreAllApps()
                                })
                            }
                        }
                    }
                }

                LaunchedEffect(
                    key1 = isConnected,
                ) {
                    if (isConnected) homeViewModel.getAppsList()
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
        CenterAlignedTopAppBar(modifier = Modifier.clickable {
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
        },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
            title = {},
            actions = {
                if (!isInDebugMode) {
                    if (!isConnected) Icon(
                        modifier = Modifier.padding(end = PADDING_M.dp),
                        imageVector = Icons.Filled.WifiOff,
                        contentDescription = Icons.Filled.WifiOff.name,
                        tint = Color.White
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