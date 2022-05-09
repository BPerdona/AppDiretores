package br.com.bruno.directorlist

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.bruno.entities.Director

@Composable
fun DirectorScreen(
    navController: NavController,
    directorViewModel: DirectorViewModel
){
    Scaffold {
        val directors by directorViewModel.listDirector.observeAsState(initial = listOf())
        
        Column() {
            DirectorList(
                directors = directors,
                navController = navController
            )
        }
    }
}

@Composable
fun DirectorList(
    directors: List<Director>,
    navController: NavController
){
    LazyColumn(){
        items(directors){ director ->
            DirectorItem(director){
                navController.navigate("director/${director.id}")
            }
        }
    }
}

@Composable
fun DirectorItem(
    director: Director,
    seeFilms: () -> Unit
){
    var expanded by remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .padding(3.dp)
        .clickable {
            expanded = !expanded
        }
        .animateContentSize(
            spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.DarkGray),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(75.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(director.image),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Photography of ${director.name}",
                        modifier = Modifier
                            .size(75.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(15.dp)
                            )
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1f),
                    text = director.name,
                    style = MaterialTheme.typography.h6
                        .copy(color = Color.White ,fontWeight = FontWeight.Bold)
                )
                if(expanded){
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(32.dp)
                            .clickable { seeFilms() },
                        imageVector = Icons.Default.Menu,
                        contentDescription = "See films",
                        tint = Color.White
                    )
                }
            }
            if(expanded){
                Column(
                    modifier = Modifier
                        .border(
                            width = 5.dp,
                            color = Color.DarkGray,
                            shape = RoundedCornerShape(2.dp)
                        )
                        .padding(12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(bottom = 1.dp)
                                .weight(1f),
                            text = "ID: ${director.id}",
                            style = MaterialTheme.typography.subtitle1.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            modifier = Modifier
                                .padding(bottom = 5.dp)
                                .weight(0.9f),
                            text = "Idade: ${director.age}",
                            style = MaterialTheme.typography.subtitle1.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                                .weight(1f),
                            text = "Qtd Filmes: ${director.films.size}",
                            style = MaterialTheme.typography.subtitle1.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}
