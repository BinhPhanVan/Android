package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.HelloJetpackTheme
import com.example.myapplication.ui.theme.Shapes
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloJetpackTheme {
                val counter = remember{
                    mutableStateOf(0)
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    color = MaterialTheme.colors.background
                )
                {
//                    Greeting("Android")
//                    CreateCircle()
                    Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Text(text="${counter.value}",style = androidx.compose.ui.text.TextStyle(color = Color.Blue,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.ExtraBold))
                        Spacer(modifier = Modifier.height(120.dp))
                        CreateCircle(counter = counter.value){
                            newValue -> counter.value = newValue
                        }
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun CreateCircle(counter: Int=0, countUp: (Int) -> Unit)
{
    Card(modifier = Modifier
        .padding(3.dp)
        .size(105.dp)
        .clickable {
                   countUp(counter + 1)
        }
        ,
    shape = CircleShape,
    elevation = 4.dp)
    {
        Box(contentAlignment = Alignment.Center)
        {
            Text(text = "Tap")
        }
    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloJetpackTheme {
        Greeting("Android")
    }
}