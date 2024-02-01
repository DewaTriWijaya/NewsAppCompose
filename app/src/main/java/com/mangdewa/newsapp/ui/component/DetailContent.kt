package com.mangdewa.newsapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mangdewa.newsapp.R
import com.mangdewa.newsapp.utils.DateFormatter.formatDate

@Composable
fun DetailContent(
    title: String,
    author: String,
    description: String,
    urlToImage: String,
    publishedAt: String,
    favorite: () -> Unit,
    setFav: Boolean,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    AsyncImage(
                        model = urlToImage,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = Color.White,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onBackClick() }
            )
        }
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(8.dp)
        )
        Text(text = description, modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                    Text(text = formatDate(publishedAt)!!)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )
                    Text(text = author)
                }
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (setFav) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clickable { favorite() }
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clickable { favorite() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetail() {
    DetailContent(
        title = "National security trial begins for Jimmy Lai, Hong Kong's pro-democracy media mogul - ABC News",
        author = "Karson Yiu",
        description = "The Apple Daily founder was charged under China's national security law.",
        urlToImage = "https://i.abcnewsfe.com/a/b3f411de-079a-4961-b0fc-04629b0cf748/jimmy-lai-file_1702882282424_hpMain_16x9.jpg?w=992",
        publishedAt = "2023-12-18T12:15:38Z",
        favorite = { },
        setFav = false,
        onBackClick = { }
    )
}