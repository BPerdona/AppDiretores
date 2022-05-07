package br.com.bruno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.bruno.detailfilm.DetailFilmViewModel
import br.com.bruno.detailfilm.FilmDetailScreen
import br.com.bruno.filmlist.FilmListViewModel
import br.com.bruno.filmlist.FilmScreen
import br.com.bruno.ui.theme.AppFilmeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Creating the ViewModel instance for the screens
        val detailFilmViewModel: DetailFilmViewModel by viewModels()
        val filmListViewModel: FilmListViewModel by viewModels()

        setContent {
            AppFilmeTheme {
                App(
                    detailFilmViewModel,
                    filmListViewModel
                )
            }
        }
    }
}

@Composable
fun App(
    detailFilmViewModel: DetailFilmViewModel,
    filmViewModel: FilmListViewModel
){
    val navController = rememberNavController()
    Scaffold() {
        NavHost(navController = navController, startDestination = "filmlist"){
            composable("filmlist"){
                FilmScreen(navController, filmViewModel)
            }
            composable(
                route = "filmdetail?id={id}",
                arguments = listOf(navArgument("id"){
                    defaultValue = -1
                    type = NavType.IntType
                })
            ){ navBackStackEntry ->
                val id = navBackStackEntry.arguments?.getInt("id") ?: -1
                val film = filmViewModel.getFilm(id)
                FilmDetailScreen(
                    navController,
                    detailFilmViewModel,
                    filmViewModel::insertFilm,
                    filmViewModel::updateFilm,
                    filmViewModel::removeFilm,
                    film
                )
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