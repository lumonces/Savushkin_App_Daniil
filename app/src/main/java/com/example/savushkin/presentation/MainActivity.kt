package com.example.savushkin.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.savushkin.presentation.allRequestsPage.AllRequestsPage
import com.example.savushkin.presentation.authorizationPage.AuthorizationPage
import com.example.savushkin.presentation.directoryPage.DirectoryPage
import com.example.savushkin.presentation.navigation.Routes
import com.example.savushkin.presentation.navigation.rememberNavigationState
import com.example.savushkin.presentation.requestPage.RequestPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content(context = this)
        }
    }
}

@Composable
fun Content(vm : MainViewModel = viewModel(), context : Context) {
    val navigationState = rememberNavigationState()
    NavHost(
        navController = navigationState.navHostController,
        startDestination = Routes.AuthorizationPage.route
    ) {
        composable(Routes.AuthorizationPage.route) {
            AuthorizationPage(
                vm = vm,
                navigateToAllRequestsPage = {
                    navigationState.navigateTo(Routes.AllRequestsPage.route)
                },
                context = context
            )
        }
        composable(Routes.AllRequestsPage.route) {
            AllRequestsPage(
                vm = vm,
                navigateToRequestPage = {
                    navigationState.navigateTo(Routes.RequestPage.route)
                },
                navigateToDirectoryPage = {
                    navigationState.navigateTo(Routes.DirectoryPage.route)
                }
            )
        }
        composable(Routes.RequestPage.route) {
            RequestPage(vm = vm)
        }
        composable(Routes.DirectoryPage.route) {
            DirectoryPage(vm = vm)
        }
    }
}