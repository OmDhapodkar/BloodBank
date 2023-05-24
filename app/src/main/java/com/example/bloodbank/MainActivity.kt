package com.example.bloodbank

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloodbank.ui.theme.BloodBankTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodBankTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    BloodBank()
                }
            }
        }
    }
}

enum class BloodGroup(val displayName: String) {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-")
}

data class UserData(
    val name: String,
    val contact: String,
    val address: String,
    val bloodGroup: BloodGroup
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodBank( modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf(BloodGroup.A_POSITIVE) }
    var userData by remember { mutableStateOf<UserData?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = contact,
            onValueChange = { contact = it },
            label = { Text("Contact") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Select Blood Group:")
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                BloodGroup.values().take(6).forEach { group ->
                    Button(
                        onClick = { bloodGroup = group },
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text(text = group.displayName)
                    }
                }
            }
        }

        Button(
            onClick = {
                userData = UserData(name, contact, address, bloodGroup)
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Submit")
        }
    }

    if (userData != null) {
        DisplayUserData(userData = userData!!)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUserData(userData: UserData) {
    Scaffold(topBar = { CustomTopBar() },
        content = { pad -> MainContent(pad,userData) }
    )
}

@Composable
fun MainContent(paddingValues: PaddingValues,userData: UserData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Name: ${userData.name}")
        Text(text = "Contact: ${userData.contact}")
        Text(text = "Address: ${userData.address}")
        Text(text = "Blood Group: ${userData.bloodGroup.displayName}")
    }
}

@Composable
fun CustomTopBar() {
    Text(text = "Blood Bank")
}