package com.example.androidstudio_koala_template

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidstudio_koala_template.ui.theme.AndroidStudioKoalaTemplateTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidStudioKoalaTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main() {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Selecciona un icono") }
    var selectedIcon by remember { mutableStateOf<@Composable (() -> Unit)?>(null) }
    var min by remember { mutableIntStateOf(1) }
    var max by remember { mutableIntStateOf(10) }
    var sliderValue by remember { mutableStateOf(min.toFloat()) } // Valor actual del Slider

    val icons = listOf(
        Icons.Default.Add,
        Icons.Default.Star,
        Icons.Default.Notifications,
        Icons.Default.Favorite,
        Icons.Default.AccountBox,
        Icons.Default.AccountCircle,
        Icons.Default.Build,
        Icons.Default.Call,
        Icons.Default.Check,
        Icons.Default.CheckCircle,
        Icons.Default.Close,
        Icons.Default.Create,
        Icons.Default.DateRange,
        Icons.Default.Delete,
        Icons.Default.Email,
        Icons.Default.Face,
        Icons.Default.Home,
        Icons.Default.Info,
        Icons.Default.LocationOn,
        Icons.Default.Lock,
        Icons.Default.Person
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Repte 01 Joan Linares",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = Color.Blue
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .clickable { expanded = !expanded }
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = selectedOption,
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            icons.forEach { icon ->
                DropdownMenuItem(
                    text = { Text(icon.name) },
                    onClick = {
                        selectedOption = icon.name
                        selectedIcon = { Icon(icon, contentDescription = null, tint = Color.Yellow) }
                        expanded = false
                    }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            TextField(
                value = min.toString(),
                onValueChange = { min = it.toIntOrNull() ?: min },
                label = { Text("Min") },
                modifier = Modifier.width(100.dp)
            )
            Spacer(modifier = Modifier.size(100.dp))
            TextField(
                value = max.toString(),
                onValueChange = { max = it.toIntOrNull() ?: max },
                label = { Text("Max") },
                modifier = Modifier.width(100.dp)
            )
        }

        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = min.toFloat()..max.toFloat(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Min: $min")
            Text(text = "Slider Value: ${sliderValue.toInt()}")
            Text(text = "Max: $max")
        }
        Spacer(modifier = Modifier.heightIn(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Button(
                onClick = {
                    selectedIcon = {
                        if (selectedOption != "Selecciona un icono") {
                            Icon(
                                imageVector = icons.firstOrNull { it.name == selectedOption } ?: Icons.Default.Add,
                                contentDescription = null,
                                tint = Color.Yellow
                            )
                        }
                    }
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Text(text = "Enviar")
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(1.dp)
                .background(Color.Gray)
                .padding(vertical = 16.dp)
        )

        if (selectedIcon != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.Black, shape = androidx.compose.foundation.shape.CircleShape)
                ) {
                    selectedIcon?.invoke()
                }
                Text(
                    text = sliderValue.toInt().toString(),
                    color = Color.Blue,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true
    , showSystemUi = true)
@Composable
fun GreetingPreview() {
    AndroidStudioKoalaTemplateTheme {
        Main()
    }
}