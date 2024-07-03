package com.example.savushkin.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions



class MainActivity : ComponentActivity() {

    private val vm: MainViewModel by viewModels()
    private val scanLauncher = registerForActivityResult(ScanContract()){ result ->
        vm.scanResult(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content(vm = vm, launchScan = { launchScan() })
        }
    }

    private fun launchScan() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.EAN_13)
        options.setPrompt("Наведите камеру на штрих-код")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        scanLauncher.launch(options)
    }
}

@Composable
fun Content(vm : MainViewModel = viewModel(), launchScan : () -> Unit) {
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
                }
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
            RequestPage(vm = vm, launchScan = launchScan)
        }
        composable(Routes.DirectoryPage.route) {
            DirectoryPage(vm = vm)
        }
    }
}