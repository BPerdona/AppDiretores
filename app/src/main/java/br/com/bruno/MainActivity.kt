package br.com.bruno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppFilmeTheme {

    }
}