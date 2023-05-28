package com.example.bloodbank

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloodbank.ui.theme.BloodBankTheme

class DisplayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodBankTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    var fullname = intent.getStringExtra("Fullname")
                    var address = intent.getStringExtra("Address")
                    var bloogrp = intent.getStringExtra("BloodGroup")
                    var date = intent.getStringExtra("Date")

                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "Appointment Fixed",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        if (fullname != null) {
                            if (address != null) {
                                if (bloogrp != null) {
                                    if (date != null) {
                                        DisplayDetails(
                                            fullname = fullname,
                                            address = address,
                                            bloodgrp = bloogrp,
                                            date = date
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(50.dp))

                        Button(
                            onClick = {
                                var intent = Intent(context, MainActivity::class.java)
                                Toast.makeText(context, "Signed Out", Toast.LENGTH_SHORT).show()
                                context.startActivity(intent)
                            }, Modifier.width(250.dp),
                            colors = ButtonDefaults.buttonColors(Color(47, 88, 129, 255))
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "")
                            Text(text = "Sign Out")
                        }

                    }

                }
            }
        }
    }
}

@Composable
fun DisplayDetails(
    fullname: String,
    address: String,
    bloodgrp: String,
    date: String
) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Fullname: $fullname",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            Text(
                text = "Address: $address",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            Text(
                text = "Blood Group: $bloodgrp",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            Text(
                text = "Date: $date",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}
