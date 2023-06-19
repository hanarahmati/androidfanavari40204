package com.example.myapplication.compose.flower

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.Flowers
import com.example.myapplication.data.FlowersData
import com.example.myapplication.ui.theme.md_theme_light_background
import com.example.myapplication.ui.theme.md_theme_light_onPrimaryContainer
import com.example.myapplication.ui.theme.md_theme_light_onSurfaceVariant
import com.example.myapplication.ui.theme.md_theme_light_primary
import com.example.myapplication.ui.theme.md_theme_light_surfaceVariant


@Composable
private fun FlowerCard(flower: Flowers) {
    MaterialTheme {
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = md_theme_light_surfaceVariant,
            modifier = Modifier
                .padding(10.dp)
                .width(180.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Image(
                    painter = painterResource(id = flower.image),
                    contentDescription = null,
                    modifier = Modifier.size(140.dp),
                )
                Row(modifier = Modifier.padding(20.dp)) {
                    Column(modifier = Modifier.weight(1f)) {

                        Text(
                            text = flower.name,
                            style = TextStyle(
                                color = md_theme_light_onSurfaceVariant,
                                fontSize = 14.sp
                            )
                        )
                        Text(
                            text = flower.price,
                            style = TextStyle(
                                color = md_theme_light_onPrimaryContainer,
                                fontSize = 16.sp
                            )
                        )
                    }

                    IconButton(
                        onClick = { },
                        modifier = Modifier.background(
                            color = md_theme_light_primary,
                            shape = RoundedCornerShape(10.dp)
                        )
                    ) {
                        Icon(
                            Icons.Default.Add, tint = md_theme_light_background,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true, device = "id:pixel_5", showSystemUi = true)
@Composable
fun PopularFlowersList() {
    LazyColumn(
    verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = FlowersData.list,
            itemContent = { flowers ->
            FlowerCard(flowers)
        })
    }
}