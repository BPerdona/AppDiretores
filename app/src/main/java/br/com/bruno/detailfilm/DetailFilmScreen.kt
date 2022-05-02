package br.com.bruno.detailfilm

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FilmDetailScreen(){
     Scaffold(
         floatingActionButton = {
             FloatingActionButton(onClick = { /*TODO*/ })
             {
                 Icon(imageVector = Icons.Default.Done, contentDescription = "Confirm Button")
             }
         }
     ) {
         FilmDetailForm(id = 1)
     }
}


//Composable with film form
@Composable
fun FilmDetailForm(
    id: Int
) {
    val checkedState = remember { mutableStateOf(true) }
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
                value = "Clube da Luta",
                onValueChange = {
                    //TODO
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp, start = 6.dp, end = 6.dp),
                label = {
                    Text(text = "Genre")
                },
                value = "Ação",
                onValueChange = {
                    //TODO
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp, start = 6.dp, end = 6.dp),
                label = {
                    Text(text = "Duration")
                },
                value = "1h 20min",
                onValueChange = {
                    //TODO
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(bottom = 14.dp, start = 6.dp, end = 6.dp),
                label = {
                    Text(text = "Synopsis")
                },
                value = "\"Um homem deprimido que sofre de insônia conhece um estranho vendedor chamado Tyler Durden e se vê morando em uma casa suja depois que seu perfeito apartamento é destruído. A dupla forma um clube com regras rígidas onde homens lutam. A parceria perfeita é comprometida quando uma mulher, Marla, atrai a atenção de Tyler.\"",
                onValueChange = {
                    //TODO
                }
            )
            Row() {
                Text(
                    modifier = Modifier
                        .padding(start = 6.dp),
                    text = "Watched: ",
                    style = MaterialTheme.typography.subtitle1.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )
                Checkbox(
                    modifier = Modifier
                        .padding(start = 2.dp, end = 2.dp),
                    checked = checkedState.value,
                    onCheckedChange = { },
                )
            }
        }
        if (id != -1)
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Film")
            }
    }
}


@Preview
@Composable
fun FilmDetailFormPreview(){
    FilmDetailScreen()
}