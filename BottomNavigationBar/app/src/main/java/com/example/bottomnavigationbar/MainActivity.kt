package com.example.bottomnavigationbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationbar.ui.theme.BottomNavigationBarTheme
import java.security.AccessController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationBarTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Scaffold (
                    topBar = { TopBar()},
                    bottomBar = { BottomNavigation(navController = navController)}
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}
@Composable
fun Navigation(navController: NavHostController)
{
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route){
        composable(NavigationItem.Home.route){
            HomeScreen()
        }
        composable(NavigationItem.Music.route){
            MusicScreen()
        }
        composable(NavigationItem.Tip.route){
            Tip()
        }
        composable(NavigationItem.Movie.route){
            MovieScreen()
        }
        composable(NavigationItem.Profile.route){
            Profile()
        }
    }
}
@Composable
fun TopBar() {
    TopAppBar(
        contentColor = Color.White,
        backgroundColor = colorResource(id = R.color.colorPrimary),
        title = { Text(
            text = "Anything app",
            fontWeight = FontWeight.Bold
        )},
    )
}
@Composable
fun BottomNavigation(navController: NavHostController) {
    var items = listOf(
        NavigationItem.Home,
        NavigationItem.Music,
        NavigationItem.Movie,
        NavigationItem.Tip,
        NavigationItem.Profile
    )
    androidx.compose.material.BottomNavigation(
        backgroundColor = colorResource(id = R.color.colorPrimary),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =  navBackStackEntry ?. destination ?. route
        items.forEach {
                item -> BottomNavigationItem(
            selected = currentRoute == item.route,
            onClick = {
                navController.navigate(item.route) {
                    navController.graph.startDestinationRoute ?. let {
                            route -> popUpTo(route) {
                        saveState = true
                    }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(painter = painterResource(
                    id = item.icon),
                    contentDescription = item.title)
            },
            label = {
                Text(text = item.title)
            },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.White.copy(0.4f),
            alwaysShowLabel = true
        )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BottomNavigationBarTheme {

    }
}