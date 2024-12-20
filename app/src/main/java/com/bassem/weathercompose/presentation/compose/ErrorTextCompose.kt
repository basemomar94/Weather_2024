package com.bassem.weathercompose.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.bassem.weathercompose.R

@Preview(showBackground = true)
@Composable
private fun ErrorTextComposePreview() {
    ErrorTextCompose(message = "Test Error")
}

@Composable
fun ErrorTextCompose(message: String?) {
    val errorMessage = message ?: stringResource(id = R.string.unexpected_error)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.default_padding)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.sad_emoj),
                contentDescription = stringResource(R.string.unexpected_error),
                modifier = Modifier.size(dimensionResource(R.dimen.icon_size_xlarge))
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.default_padding)))

            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
        }
    }
}
