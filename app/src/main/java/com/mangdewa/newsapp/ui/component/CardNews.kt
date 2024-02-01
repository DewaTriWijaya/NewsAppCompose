package com.mangdewa.newsapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun CardNews(
    title: String,
    image: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
    ) {
        Column {
            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = title,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewCard() {
    CardNews(
        title = "National security trial begins for Jimmy Lai, Hong Kong's pro-democracy media mogul - ABC News",
        image = "Gambar",
    )
}