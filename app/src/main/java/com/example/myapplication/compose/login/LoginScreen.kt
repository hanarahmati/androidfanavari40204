package com.example.myapplication.compose.login

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.R
import com.example.myapplication.ui.theme.md_theme_light_primary
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.layout.ContentScale
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.md_theme_light_onPrimary
import com.example.myapplication.ui.theme.md_theme_light_onSurface
import com.example.myapplication.ui.theme.md_theme_light_onSurfaceVariant
import com.example.myapplication.ui.theme.md_theme_light_outline
import com.example.myapplication.ui.theme.md_theme_light_tertiaryContainer

@Composable
fun LoginScreen(openDashboard: () -> Unit) {
    MaterialTheme() {
        ConstraintLayout {
            val (image, loginForm) = createRefs()

            val bottomImageGuideline =
                createGuidelineFromTop(0.33f)
            val topLoginGuideline =
                createGuidelineFromTop(0.2f)

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(bottomImageGuideline)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }

            ) {
                HeaderView()
            }
            Card(
                shape = RoundedCornerShape(
                    topStart = 40.dp,
                    topEnd = 40.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = md_theme_light_tertiaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp)
                    .constrainAs(loginForm) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(topLoginGuideline)
                    },
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp)
                        .verticalScroll(rememberScrollState())
                ) {

                    val loginText = "Log in to your account."
                    val loginWord = "Log in"
                    val loginAnnotatedString = buildAnnotatedString {
                        append(loginText)
                        addStyle(
                            style = SpanStyle(
                                color = md_theme_light_onSurfaceVariant,
//                                        fontFamily = FontFamily(Font(R.font.))
                            ),
                            start = 0,
                            end = loginText.length
                        )
                        addStyle(
                            style = SpanStyle(
                                color = md_theme_light_primary,
//                                        fontFamily = FontFamily(Font(R.font.helvetica_neue_medium))
                            ),
                            start = 0,
                            end = loginWord.length
                        )
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(
                                top = 10.dp,
                                bottom = 20.dp
                            ),
                        text = loginAnnotatedString,
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Email Address",
                        style = MaterialTheme.typography.subtitle1.
                        copy(color = md_theme_light_onSurfaceVariant),
                        modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                    )

                    CustomStyleTextField(
                        "Email Address",
                        android.R.drawable.ic_dialog_email,
                        KeyboardType.Email,
                        VisualTransformation.None
                    )

                    Text(
                        text = "Password",
                        style = MaterialTheme.typography.
                        subtitle1.copy(color = md_theme_light_onSurfaceVariant),
                        modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                    )
                    CustomStyleTextField(
                        "Password",
                        android.R.drawable.star_off,
                        KeyboardType.Password,
                        PasswordVisualTransformation()
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        text = "Forgot Password",
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.subtitle2.copy(color = md_theme_light_primary)
                    )
                    Button(
                        onClick = openDashboard,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .padding(top = 30.dp, bottom = 34.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = md_theme_light_primary)
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                            text = "Login",
                            color = Color.White,
                            style = MaterialTheme.typography.button
                        )
                    }

                    val signInText = "Don't have an account? Sign In"
                    val signInWord = "Sign In"
                    val signInAnnotatedString = buildAnnotatedString {
                        append(signInText)
                        addStyle(
                            style = SpanStyle(
                                color = md_theme_light_outline,
//                                        fontFamily = FontFamily(Font(R.font.helvetica_neue_regular))
                            ),
                            start = 0,
                            end = signInText.length
                        )
                        addStyle(
                            style = SpanStyle(
                                color = md_theme_light_primary,
//                                        fontFamily = FontFamily(Font(R.font.helvetica_neue_medium))
                            ),
                            start = signInText.indexOf(signInWord),
                            end = signInText.length
                        )
                    }

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = signInAnnotatedString,
                        style = TextStyle(
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        /*}
    }*/
    }
}

@Composable
fun CustomStyleTextField(
    placeHolder: String,
    leadingIconId: Int,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = textState.value,
        onValueChange = { valueChanged ->
            textState.value = valueChanged
        },

        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        placeholder = { Text(text = placeHolder, color = md_theme_light_outline) },
        leadingIcon = {
            Row(
                modifier = Modifier.wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Image(
                        modifier = Modifier
                            .padding(
                                start = 10.dp,
                                end = 10.dp
                            )
                            .size(18.dp),
                        bitmap = ImageBitmap.imageResource(id = leadingIconId),  // material icon
                        colorFilter = ColorFilter.tint(md_theme_light_primary),
                        contentDescription = "custom_text_field"
                    )
                    Canvas(
                        modifier = Modifier.height(24.dp)
                    ) {
                        // Allows you to draw a line between two points (p1 & p2) on the canvas.
                        drawLine(
                            color = md_theme_light_outline,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = 2.0F
                        )
                    }
                }
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = md_theme_light_onSurface,
            unfocusedBorderColor = md_theme_light_onSurface,
            focusedLabelColor = md_theme_light_outline,
            trailingIconColor = md_theme_light_outline,
            disabledTextColor = md_theme_light_outline,
            backgroundColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp),
        textStyle = TextStyle(color = md_theme_light_outline, fontSize = 16.sp),
        visualTransformation = visualTransformation
    )

}


@Composable
fun HeaderView() {
    Image(
        modifier = Modifier.fillMaxSize(),
        bitmap = ImageBitmap.imageResource(id = R.drawable.login_bg),
        contentScale = ContentScale.FillWidth,
        contentDescription = "header_view_login_bg"
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 40.dp)
    ) {
        Image(
            modifier = Modifier.wrapContentWidth(),
            bitmap = ImageBitmap.imageResource(id = R.drawable.flower_logo),
            contentDescription = "header_view_flower_logo"
        )
        Text(
            text = "FloraGoGo",
            color = md_theme_light_onPrimary,
            style = TextStyle(
                fontSize = 40.sp,
//                fontFamily = FontFamily(Font(R.font.josefin_sans_semibold_italic)),
                letterSpacing = 2.sp
            )
        )
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun DefaultPreview() {
    LoginScreen(openDashboard = {})
}