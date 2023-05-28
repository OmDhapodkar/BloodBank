package com.example.bloodbank

//import androidx.compose.foundation.layout.BoxScopeInstance.align
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloodbank.ui.theme.BloodBankTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BloodBankTheme {
                // A surface container using the 'background' color from the theme
                // GitHub Sync Practice Hello ... From Github Changed
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    BloodBankLogin()
                }
            }
        }
    }
}

@Composable
fun BloodBankLogin() {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.8f)
    ) {
        Image(
            painterResource(id = R.drawable.bloodbg2),
            modifier = Modifier
                .fillMaxSize()
                .blur(1.dp),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Blood Bank Background"
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.2f)
                .padding(30.dp)
                .clip(CutCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.background)
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            LoginHeader()
            LoginFields(
                username,
                email,
                password,
                onUsernameChange = { username = it },
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onForgotPasswordClick = {})
            LoginFooter(onSignInClick = {
                var intent = Intent(context, AnotherActivity::class.java)
                intent.putExtra("Username", username)
                context.startActivity(intent)
            },
                onSignUpClick = {})
        }
    }
}

@Composable
fun LoginHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Welcome Back",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.White
        )
        Text(
            text = "Sign In to Our Blood Bank",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.White
        )
    }

}

@Composable
fun ColumnScope.LoginFields(
    username: String,
    email: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Column() {
        CustomField(
            value = username,
            label = "Username",
            placeholder = "Enter the Username",
            onValueChange = onUsernameChange,
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomField(
            value = email,
            label = "Email",
            placeholder = "Enter the Email Address",
            onValueChange = onEmailChange,
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomField(
            value = password,
            label = "Password",
            placeholder = "Enter the Password",
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Go
            )

        )

        TextButton(
            onClick = { onForgotPasswordClick },
            Modifier
                .align(Alignment.End)

        ) {
            Text(text = "Forgot Password ?", color = Color.White)
        }
    }

}

@Composable
fun LoginFooter(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = onSignInClick,
            Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Icon(Icons.Default.Done, contentDescription = "")
            Text(text = "Sign In")
        }
        TextButton(onClick = onSignUpClick) {
            Text(text = "Don't have an Account, Click Here!", color = Color.White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomField(
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
        disabledTextColor = Color.White,
        placeholderColor = Color.Black,
        cursorColor = Color.Black,
        focusedIndicatorColor = Color.White,
        unfocusedIndicatorColor = Color.Black,
        disabledIndicatorColor = Color.White,
        errorIndicatorColor = Color.White
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

