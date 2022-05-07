package br.com.bruno.detailfilm

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.bruno.entities.Film

@Composable
fun FilmDetailScreen(
    navController: NavController,
    detailFilmViewModel: DetailFilmViewModel,
    onInsertFilm: (Film) -> Unit,
    onUpdateFilm: (Film) -> Unit,
    onRemoveFilm: (Int) -> Unit,
    film: Film

    ){
     Scaffold(
         floatingActionButton = {
             FloatingActionButton(onClick = {
                 if(film.id == -1)
                     detailFilmViewModel.insertFilm(onInsertFilm)
                 else
                     detailFilmViewModel.updateFilm(
                         film.id,
                         onUpdateFilm
                     )

                 navController.navigate("filmlist"){
                     popUpTo("filmlist"){
                         inclusive = true
                     }
                 }
             })
             {
                 Icon(imageVector = Icons.Default.Done, contentDescription = "Confirm Button")
             }
         }
     ) {
         detailFilmViewModel.title.value = film.title
         detailFilmViewModel.genre.value = film.genre
         detailFilmViewModel.duration.value = film.duration
         detailFilmViewModel.synopsis.value = film.title
         detailFilmViewModel.watched.value = film.watched

         FilmDetailForm(
             detailFilmViewModel,
             film.id,
             onRemoveFilm,
         ) {
             navController.navigate("filmlist"){
                 popUpTo("filmlist"){
                     inclusive = true
                 }
             }

         }
     }
}


//Composable with film form
@Composable
fun FilmDetailForm(
    detailFilmViewModel: DetailFilmViewModel,
    id: Int,
    onRemoveFilm: (Int) -> Unit,
    navigateBack: () -> Unit,
) {
    //Creating values by detailFilmViewModel
    val title = detailFilmViewModel.title.observeAsState()
    val genre = detailFilmViewModel.genre.observeAsState()
    val duration = detailFilmViewModel.duration.observeAsState()
    val synopsis= detailFilmViewModel.synopsis.observeAsState()
    val watched = detailFilmViewModel.watched.observeAsState()


    //CheckBoxState
    var checkBox: Boolean = watched.value ?: false
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp, start = 6.dp, end = 6.dp, top = 6.dp),
                label = {
                    Text(text = "Title")
                },
                value = "${title.value}",
                onValueChange = { newTitle->
                    detailFilmViewModel.title.value = newTitle
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp, start = 6.dp, end = 6.dp),
                label = {
                    Text(text = "Genre")
                },
                value = "${genre.value}",
                onValueChange = { newGenre ->
                    detailFilmViewModel.genre.value = newGenre
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp, start = 6.dp, end = 6.dp),
                label = {
                    Text(text = "Duration")
                },
                value = "${duration.value}",
                onValueChange = { newDuration ->
                    detailFilmViewModel.duration.value = newDuration
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp, start = 6.dp, end = 6.dp),
                label = {
                    Text(text = "Synopsis")
                },
                value = "${synopsis.value}",
                onValueChange = { newSyn ->
                    detailFilmViewModel.synopsis.value = newSyn
                }
            )
            Row() {
                Text(
                    modifier = Modifier
                        .padding(start = 6.dp),
                    text = "Watched: ",
                    style = MaterialTheme.typography.subtitle1.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Checkbox(
                    modifier = Modifier
                        .padding(start = 2.dp, end = 2.dp),
                    checked = checkBox,
                    onCheckedChange = { newWatch ->
                        detailFilmViewModel.watched.value = newWatch
                        checkBox = !checkBox
                    },
                )
            }
        }
        if (id != -1)
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    onRemoveFilm(id)
                    navigateBack()
                }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Film")
            }
    }
}
