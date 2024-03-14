/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.tcreatesllc.animchallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.tcreatesllc.animchallenge.R
import com.tcreatesllc.animchallenge.presentation.theme.AnimChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    AnimChallengeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            progressIndicator()
        }
    }
}

@Composable
fun progressIndicator(
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(15.dp)
    ) {

        val infiniteTransition = rememberInfiniteTransition(label = "")

        val gradient = Brush.verticalGradient(
            colorStops = arrayOf(
                0.0f to Color.Transparent,
                //0.3f to Color(0xFF000000),
                1.0f to Color(0xFF0cdc35)
            )
        )


        val rotation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween<Float>(
                    durationMillis = 1500,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Restart
            ), label = ""
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.power_svgrepo_com),
                contentDescription = null,
                tint = Color.LightGray
            )
            Box() {
                Row() {
                    Text(
                        fontWeight = FontWeight.Light,
                        text = "45",
                        fontSize = 35.sp
                    )
                    Text(
                        modifier = Modifier.align(Alignment.Bottom).padding(bottom = 7.dp, start = 1.dp),
                        text = "%",
                        fontSize = 12.sp,

                    )
                }
            }

        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawArc(
                color = Color(0xFF373a37),
                0f,
                360f,
                topLeft = Offset(x = 0.dp.toPx(), y = 0.dp.toPx()),
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(15.dp.toPx(), cap = StrokeCap.Round)
            )
            rotate(rotation) {
                drawArc(
                    brush = gradient,
                    -110f,
                    270f,
                    topLeft = Offset(x = 0.dp.toPx(), y = 0.dp.toPx()),
                    useCenter = false,
                    size = Size(size.width, size.height),
                    style = Stroke(15.dp.toPx(), cap = StrokeCap.Round)
                )
            }
        }
    }
}


@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    progressIndicator()
}