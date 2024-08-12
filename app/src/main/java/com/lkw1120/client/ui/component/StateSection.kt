package com.lkw1120.client.ui.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lkw1120.client.R
import org.json.JSONObject

@Composable
fun LoadingState(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun ErrorState(
    modifier: Modifier = Modifier,
    message: String?,
) {
    val context = LocalContext.current

    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp.dp
    val height = configuration.screenHeightDp.dp

    LaunchedEffect(Unit) {
        val json = message?.let { JSONObject(it) }
        val errorMessage = json?.getString("message")?:"Unknown Error"
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .width(width * 0.5f)
                .wrapContentHeight(),
            painter = painterResource(id = R.drawable.img_octobiwan),
            contentDescription = null,
        )
    }
}

@Composable
fun EmptyState(
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp.dp
    val height = configuration.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .width(width * 0.5f)
                .wrapContentHeight(),
            painter = painterResource(id = R.drawable.img_inspectocat),
            contentDescription = null,
        )
    }
}