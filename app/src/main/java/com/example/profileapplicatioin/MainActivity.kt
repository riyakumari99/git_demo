package com.example.profileapplicatioin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.profileapplicatioin.ui.theme.ProfileApplicatioinTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        ProfileApplicatioinTheme {
            MainScreen()
        }

        }
    }
}
@Composable
fun MainScreen(){

    var currentScreen by remember {mutableStateOf("Home")  }

    Column(modifier = Modifier.fillMaxSize()){

        Box(
            modifier = Modifier.weight(1f)
        ){
            when(currentScreen) {
                "Home" -> HomeScreen()
                "Projects" -> ProjectScreen()
                "Skills" -> SkillScreen()
                "Contact" -> ContactScreen()

            }

        }
        NavigationBar {
            NavigationBarItem(
                selected = currentScreen == "Home",
                onClick = { currentScreen = "Home" },
                icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                label = { Text("Home") }
            )

                NavigationBarItem(
                    selected = currentScreen == "Projects",
                    onClick = { currentScreen = "Projects" },
                    icon = { Icon(Icons.Default.List, contentDescription = "Home") },
                    label = { Text("Projects") }
                )

                    NavigationBarItem(
                        selected = currentScreen == "Skills",
                        onClick = { currentScreen = "Skills" },
                        icon = { Icon(Icons.Default.Build, contentDescription = "Home") },
                        label = { Text("Skills") }
                    )

                        NavigationBarItem(
                            selected = currentScreen == "Contact",
                            onClick = { currentScreen = "Contact" },
                            icon = {
                                Icon(
                                    Icons.Default.AccountCircle,
                                    contentDescription = "Home"
                                )
                            },
                            label = { Text("Contact") }
                        )
                    }
                }
            }

