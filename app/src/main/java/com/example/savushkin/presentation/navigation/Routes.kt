package com.example.savushkin.presentation.navigation

sealed class Routes(val route : String) {

    data object AuthorizationPage : Routes(route = ROUTE_AUTHORIZATION_PAGE)
    data object AllRequestsPage : Routes(route = ROUTE_ALL_REQUESTS_PAGE)
    data object RequestPage : Routes(route = ROUTE_REQUEST_PAGE)
    data object DirectoryPage : Routes(route = ROUTE_DIRECTORY_PAGE)

    private companion object {
        const val ROUTE_AUTHORIZATION_PAGE = "AuthorizationPage"
        const val ROUTE_ALL_REQUESTS_PAGE = "AllRequestsPage"
        const val ROUTE_REQUEST_PAGE = "RequestPage"
        const val ROUTE_DIRECTORY_PAGE = "DirectoryPage"
    }
}