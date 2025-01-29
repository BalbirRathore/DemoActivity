package com.example.demoactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import com.example.demoactivity.ui.theme.DemoActivityTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*   enableEdgeToEdge()
         setContent {
              DemoActivityTheme {
                  Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                      Greeting(
                          name = "Android",
                          modifier = Modifier.padding(innerPadding)
                      )
                  }
              }
          }*/
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoActivityTheme {
        Greeting("Android")
    }
}