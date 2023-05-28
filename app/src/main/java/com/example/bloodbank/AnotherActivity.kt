package com.example.bloodbank

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloodbank.ui.theme.BloodBankTheme
import java.util.Date

class AnotherActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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
                    var username = intent.getStringExtra("Username")
                    if (username != null) {
                        CustomScaffold(username = username, onBackClick = {
                            var intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        })
                    } else {
                        CustomScaffold(username = "", onBackClick = {
                            var intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(username: String, onBackClick: () -> Unit) {
    Scaffold(topBar = { CustomTopBar(username, onBackClick) },
        content = { pad -> MainContent(pad) }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainContent(pad: PaddingValues) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        var name by remember {
            mutableStateOf("")
        }
        var address by remember {
            mutableStateOf("")
        }
        var bloodgrp by remember {
            mutableStateOf("")
        }
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .padding(20.dp)
                .padding(pad)
                .consumedWindowInsets(pad),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Appointment Details",
                modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp))

            Appointment(
                name,
                address,
                bloodgrp,
                onNameChange = { name = it },
                onAddressChange = { address = it },
                onBloodGroupChange = { bloodgrp = it }
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(username: String, onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement =
                Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        end = 50
                            .dp
                    )
            ) {
                Button(onClick = onBackClick) {
                    Icon(
                        Icons.Filled.ArrowBack, contentDescription = "", modifier = Modifier
                            .size(30.dp)
                    )
                }

                Text(text = username)
                Icon(Icons.Default.Person, contentDescription = "")

            }
        },
        modifier = Modifier.drawBehind {
            drawLine(
                Color.LightGray,
                Offset(0f, size.height),
                Offset(size.width, size.height),
                5f
            )
        }
    )
}

@Composable
fun ColumnScope.Appointment(
    fullname: String,
    address: String,
    bloodgrp: String,
    onNameChange: (String) -> Unit,
    onAddressChange: (String) -> Unit,
    onBloodGroupChange: (String) -> Unit

) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CustomField2(
            value = fullname,
            label = "Fullname",
            placeholder = "Enter the Fullname",
            onValueChange = onNameChange,
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomField2(
            value = address,
            label = "Address",
            placeholder = "Enter the Address",
            onValueChange = onAddressChange,
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        BloodGroupDropdown(
            selectedBloodGroup = bloodgrp,
            onBloodGroupSelected = onBloodGroupChange
        )

        Spacer(modifier = Modifier.height(20.dp))


        val year: Int
        val month: Int
        val day: Int
        var context = LocalContext.current

        val calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        calendar.time = Date()

        val date = remember { mutableStateOf("") }
        val datePickerDialog = DatePickerDialog(
            LocalContext.current,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                date.value = "$dayOfMonth/$month/$year"
            }, year, month, day
        )
        Button(
            onClick = { datePickerDialog.show() },
            colors = ButtonDefaults.buttonColors(Color(47, 88, 129, 255))
        ) {
            Icon(Icons.Default.DateRange, contentDescription = "")
            Text(text = "Pick A Date")
        }

        Spacer(modifier = Modifier.height(200.dp))

        Button(
            onClick = {
                var intent = Intent(context, DisplayActivity::class.java)
                intent.putExtra("Fullname", fullname)
                intent.putExtra("Address", address)
                intent.putExtra("BloodGroup", bloodgrp)
                intent.putExtra("Date", date.value)
                context.startActivity(intent)
            },
            Modifier.width(250.dp),
            colors = ButtonDefaults.buttonColors(Color(47, 88, 129, 255))
        ) {
            Icon(Icons.Default.Check, contentDescription = "")
            Text(text = "Confirm")
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomField2(
    value: String,
    label: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val textFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Color.Black,
        disabledTextColor = Color.Black,
        placeholderColor = Color.Black,
        cursorColor = Color.Black,
        focusedIndicatorColor = Color.Black,
        unfocusedIndicatorColor = Color.Black,
        disabledIndicatorColor = Color.Black,
        errorIndicatorColor = Color.Black
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = {
            Text(
                text = placeholder
            )
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        colors = textFieldColors
    )

}

@Composable
fun BloodGroupDropdown(
    selectedBloodGroup: String,
    onBloodGroupSelected: (String) -> Unit
) {
    val bloodGroups = listOf(
        "A+",
        "A-",
        "B+",
        "B-",
        "AB+",
        "AB-",
        "O+",
        "O-"
    )

    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(4.dp))
            .width(300.dp)
    ) {

        val displayText =
            if (selectedBloodGroup.isEmpty()) "Select Blood Group" else selectedBloodGroup

        Text(
            text = displayText,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = !expanded })
                .background(Color.White)
                .padding(16.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(200.dp)
                .border(1.dp, Color.Black, shape = RoundedCornerShape(4.dp))
        ) {
            bloodGroups.forEach { bloodGroup ->
                DropdownMenuItem(
                    text = { Text(text = bloodGroup) },
                    onClick = {
                        onBloodGroupSelected(bloodGroup)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun CustomButton(
    buttonText: String,
    textColor: Color = Color.White,
    backgroundColor: Color = Color(0, 149, 246),
    onClick: () -> Unit = {},
    isLogo: Boolean = false
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isLogo) {
            Icon(
                Icons.Filled.Send, contentDescription =
                "facebook logo", modifier = Modifier.size(25.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            buttonText,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun showDate() {
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )

    CustomButton(buttonText = "Select Last Donation Date", onClick = {
        datePickerDialog.show()
    })
}

