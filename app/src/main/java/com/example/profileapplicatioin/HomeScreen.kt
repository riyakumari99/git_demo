package com.example.profileapplicatioin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import com.example.profileapplicatioin.ui.theme.white
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.material3.CardDefaults.cardElevation
import com.example.profileapplicatioin.ui.theme.blue
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.ui.text.style.TextAlign
import com.example.profileapplicatioin.ui.theme.darkGray
import androidx.compose.foundation.layout.height
import android.R.string
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.profileapplicatioin.ui.theme.black
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Divider
import androidx.compose.ui.window.Dialog
import com.example.profileapplicatioin.ui.theme.lightgray
import com.example.profileapplicatioin.ui.theme.lightgray2
import com.example.profileapplicatioin.ui.theme.lightgray3
import androidx.compose.foundation.layout.Row



@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
    ){
      ProfileHeader()

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
          ){
            SectionTitle("Bio")
            ContentCard {
                Text(
                    "Hi, I am Riya, I am a professional app developer, I have develop multiple Industry level apps using kotlin and jetpack compose.",
                    lineHeight = 24.sp,
                    color = black,
                    fontSize = 16.sp
                )
            }
           SectionTitle("Education")

           ContentCard {
               Column {
                   Column(modifier = Modifier.fillMaxWidth()) {
                       Text(
                           text = "B.tech in CS",
                           color = black,
                           fontSize = 16.sp,
                           fontWeight = FontWeight.Bold
                       )
                       Text(
                           text = "Punjab Technical University",
                           color = darkGray,
                           fontSize = 14.sp
                       )
                       Text(
                           text = "2023-2027",
                           color = darkGray,
                           fontSize = 14.sp
                       )

                   }

                   Divider(
                       modifier = Modifier.padding(vertical = 8.dp),
                       color = lightgray3
                   )
                   Column(modifier = Modifier.fillMaxWidth()) {
                       Text(
                           text = "Android App Certification",
                           color = black,
                           fontSize = 16.sp,
                           fontWeight = FontWeight.Bold
                       )
                       Text(
                           text = "Google Developers",
                           color = darkGray,
                           fontSize = 14.sp
                       )
                       Text(
                           text = "2027",
                           color = darkGray,
                           fontSize = 14.sp
                       )

                   }
                   Divider(
                       modifier = Modifier.padding(vertical = 8.dp),
                       color = lightgray3
                   )
                   Column(modifier = Modifier.fillMaxWidth()) {
                       Text(
                           text = "Kotlin Masterclass",
                           color = black,
                           fontSize = 16.sp,
                           fontWeight = FontWeight.Bold
                       )
                       Text(
                           text = "Online Course platform",
                           color = darkGray,
                           fontSize = 14.sp
                       )
                       Text(
                           text = "2026",
                           color = darkGray,
                           fontSize = 14.sp
                       )

                   }
               }
           }
                   SectionTitle("Achievements")

                   ContentCard {
                       Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                           Row(
                               verticalAlignment = Alignment.Top,
                               modifier = Modifier.fillMaxWidth()

                           ) {
                               Text(
                                   text = "->",
                                   fontSize = 16.sp,
                                   color = black,
                                   modifier = Modifier.padding(end = 4.dp)
                               )
                               Text(
                                   text = "Completed 200+ Leetcode Problems",
                                   fontSize = 16.sp,
                                   color = black
                               )
                           }
                           Row(
                               verticalAlignment = Alignment.Top,
                               modifier = Modifier.fillMaxWidth()

                           ) {
                               Text(
                                   text = "->",
                                   fontSize = 16.sp,
                                   color = black,
                                   modifier = Modifier.padding(end = 4.dp)
                               )
                               Text(
                                   text = "Completed 200+ Leetcode Problems",
                                   fontSize = 16.sp,
                                   color = black
                               )
                           }
                           Row(
                               verticalAlignment = Alignment.Top,
                               modifier = Modifier.fillMaxWidth()
                           ) {

                               Text(
                                   text = "->",
                                   fontSize = 16.sp,
                                   color = black,
                                   modifier = Modifier.padding(end = 4.dp)
                               )
                               Text(
                                   text = "Completed 200+ Leetcode Problems",
                                   fontSize = 16.sp,
                                   color = black
                               )
                           }
                       }
                   }
        }
    }
}





@Composable
fun ProfileHeader(){
    Card (
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = cardColors(containerColor = white),
        elevation = cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment =   Alignment.CenterHorizontally
        ){
         Image(
            painter = painterResource(id = R.drawable.working),
             contentDescription = "profile picture",
             modifier = Modifier
                 .size(120.dp)
                 .clip(CircleShape),
                contentScale = ContentScale.Crop
           )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Riya",
                color = blue,
                style = typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Text(
                "App Developer",
                color = darkGray,
                style = typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }

    }
}
@Composable
fun SectionTitle(title: String){
 Text(
     text = title,
     fontSize = 20.sp,
     fontWeight = FontWeight.Bold,
     color = blue,
     modifier = Modifier.padding(bottom = 4.dp)
 )
}
@Composable
fun ContentCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = lightgray2),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ){
        content()
       }
    }
}