package com.kinto.hilt

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.edit
import com.hilt.PrismPrefs
import com.hilt.data.response.Toyota
import com.kinto.domain.repository.UserRepository
import com.kinto.domain.usecase.GetUserUseCase
import com.kinto.domain.usecase.SignUpUseCase
import com.kinto.hilt.ui.theme.HiltComposeUITheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var car: Car

    @Inject
    lateinit var toyota: Toyota

    @Inject
    lateinit var userUseCase: GetUserUseCase

    @Inject
    lateinit var signUpUseCase: SignUpUseCase

    @PrismPrefs
    @Inject
    lateinit var prismPrefs: SharedPreferences


//    @Inject
//    lateinit var graphQlClient:

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prismPrefs.edit {
            putString("idToken", "TOKEN4444")
            apply()
        }
        setContent {
            LaunchedEffect(Unit) {
                try {
                    println("is>>>>>>>${signUpUseCase.get("teryli@lyricspad.net").isExisting}")
                    println("message>>>>>>>${signUpUseCase.get("teryli@lyricspad.net").message}")
                } catch (e: java.lang.Exception) {
                }

            }
            HiltComposeUITheme {
                println(userUseCase.get())

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(toyota.name())
                }
            }
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
    HiltComposeUITheme {
        Greeting("Android")
    }
}