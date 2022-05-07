package br.com.bruno.filmlist

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.Spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bruno.entities.Film
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController


//Union of the other composable
@Composable
fun FilmScreen(
    navController: NavController,
    filmListViewModel: FilmListViewModel,
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("filmdetail")
            }){
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Film")
            }
        }
    ) {
        //Creating the values
        val filmList by filmListViewModel.listFilm.observeAsState(initial = listOf())

        Column() {
            FilmList(
                filmList,
                navController
            )
        }
    }
}

//Display all the Film itens in list
@Composable
fun FilmList(
    films: List<Film>,
    navController: NavController
){
    LazyColumn(){
        items(films){ film ->
            FilmItem(film){
                navController.navigate("filmdetail?id=${film.id}")
            }
        }
    }
}

//Composable of Film Item
@Composable
fun FilmItem(
    film: Film,
    toEdit: () -> Unit
){
    var visto: String
    if(film.watched)
        visto="Sim"
    else
        visto="Não"
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(2.dp)
            .clickable {
                expanded = !expanded
            }
            .animateContentSize(
                spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ){
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .clip(CircleShape)
                        .size(70.dp)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "${film.title[0].uppercase()}",
                        style = MaterialTheme.typography.h3
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1f),
                    text = film.title,
                    style = MaterialTheme.typography.h6
                        .copy(fontWeight = FontWeight.Bold)
                )
                if(!expanded){
                    if(film.watched){
                        Icon(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(32.dp),
                            imageVector = Icons.Default.Check,
                            contentDescription = "Watched"
                        )
                    }else{
                        Icon(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(32.dp),
                            imageVector = Icons.Default.Close,
                            contentDescription = "Not Watched"
                        )
                    }
                }
                if(expanded){
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(32.dp)
                            .clickable {
                                toEdit()
                            },
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit"
                    )
                }
            }
            if(expanded){
                Column(
                    modifier = Modifier.padding(6.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 1.dp),
                        text = "ID: ${film.id}",
                        style = MaterialTheme.typography.subtitle1.copy(color = Color.White, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 1.dp),
                        text = "Genero: ${film.genre}",
                        style = MaterialTheme.typography.subtitle1.copy(color = Color.White, fontWeight = FontWeight.Bold)
                    )

                    Text(
                        modifier = Modifier.padding(bottom = 1.dp),
                        text = "Duração: ${film.duration}",
                        style = MaterialTheme.typography.subtitle1.copy(color = Color.White, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 6.dp),
                        text = "Visto: ${visto}",
                        style = MaterialTheme.typography.subtitle1.copy(color = Color.White, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Sinopse:",
                        style = MaterialTheme.typography.subtitle1.copy(color = Color.White, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "${film.synopsis}",
                        style = MaterialTheme.typography.subtitle2.copy(color = Color.LightGray)
                    )
                }
            }
        }
    }
}
