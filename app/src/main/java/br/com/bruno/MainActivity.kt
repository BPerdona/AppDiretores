package br.com.bruno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.bruno.detailfilm.FilmDetailScreen
import br.com.bruno.filmlist.FilmScreen
import br.com.bruno.ui.theme.AppFilmeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppFilmeTheme {
                App()
            }
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()
    Scaffold() {
        NavHost(navController = navController, startDestination = "filmlist"){
            composable("filmlist"){
                FilmScreen(navController)
            }
            composable(
                route = "filmdetail?id={id}",
                arguments = listOf(navArgument("id"){
                    defaultValue = -1
                    type = NavType.IntType
                })
            ){ navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getInt("id")
                //TODO
                FilmDetailScreen(navController)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppFilmeTheme {

    }
}