package br.com.bruno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.bruno.detailfilm.DetailFilmViewModel
import br.com.bruno.detailfilm.FilmDetailScreen
import br.com.bruno.directorlist.DirectorScreen
import br.com.bruno.directorlist.DirectorViewModel
import br.com.bruno.filmlist.FilmListViewModel
import br.com.bruno.filmlist.FilmScreen
import br.com.bruno.ui.theme.AppFilmeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Creating the ViewModel instance for the screens
        val directorViewModel: DirectorViewModel by viewModels()
        val detailFilmViewModel: DetailFilmViewModel by viewModels()
        val filmListViewModel: FilmListViewModel by viewModels()


        setContent {
            AppFilmeTheme {
                App(
                    detailFilmViewModel,
                    filmListViewModel,
                    directorViewModel
                )
            }
        }
    }
}

@Composable
fun App(
    detailFilmViewModel: DetailFilmViewModel,
    filmViewModel: FilmListViewModel,
    directorViewModel: DirectorViewModel
){
    val navController = rememberNavController()
    Scaffold() {
        NavHost(navController = navController, startDestination = "director"){
            composable("director"){
                DirectorScreen(navController, directorViewModel)
            }
            composable(
                route = "director/{idDir}",
                arguments = listOf(navArgument("idDir"){
                    defaultValue = -1
                    type = NavType.IntType
                })
            ){
                val idDir = it.arguments?.getInt("idDir") ?: -1
                val films = directorViewModel.getDirectorFilms(idDir)
                FilmScreen(
                    navController,
                    filmViewModel,
                    films,
                    idDir
                )
            }
            composable(
                route = "director/{idDir}/detail?filmDetail={idFilm}",
                arguments = listOf(
                    navArgument("idDir"){
                        defaultValue = -1
                        type = NavType.IntType
                    },
                    navArgument("idFilm"){
                        defaultValue = -1
                        type = NavType.IntType
                    })
            ){ navBackStackEntry ->
                val idDir = navBackStackEntry.arguments?.getInt("idDir") ?: -1
                val idFilm = navBackStackEntry.arguments?.getInt("idFilm") ?: -1
                val film = directorViewModel.getExpecificFilm(idDir, idFilm)
                FilmDetailScreen(
                    navController,
                    detailFilmViewModel,
                    filmViewModel::insertFilm,
                    filmViewModel::updateFilm,
                    filmViewModel::removeFilm,
                    directorViewModel::refreshFilms,
                    filmViewModel,
                    film,
                    idDir
                )
            }
        }
    }

}
