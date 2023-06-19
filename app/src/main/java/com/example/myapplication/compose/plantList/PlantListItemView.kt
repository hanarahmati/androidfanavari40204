package com.example.myapplication.compose.plantList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.myapplication.R
import com.example.myapplication.compose.utils.SunflowerImage
import com.example.myapplication.data.Plant
import com.example.myapplication.data.PlantsData
import com.example.myapplication.ui.theme.card

@Composable
fun PlantListItem(plant: Plant, onClick: () -> Unit) {
    ImageListItem(name = plant.name, imageUrl = plant.imageUrl,
        onClick = onClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageListItem(name: String, imageUrl: String,
                  onClick: () -> Unit) {
    Card(
        shape = shapes.card,
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
            .padding(bottom = dimensionResource(id = R.dimen.card_bottom_margin)),
        onClick = onClick,
    ) {
        Column(Modifier.fillMaxWidth()) {
            SunflowerImage(
                model = imageUrl,
                contentDescription = stringResource(R.string.a11y_plant_item_image),
                Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.plant_item_image_height)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = name,
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.margin_normal))
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}


@Preview
@Composable
private fun PlantListItemViewPreview(
    @PreviewParameter(PlantListItemViewPreviewParamProvider::class)
    plant: Plant
) {
    PlantListItem(plant = PlantsData.list[0], onClick = {} )
}

private class PlantListItemViewPreviewParamProvider :
    PreviewParameterProvider<Plant> {
    override val values: Sequence<Plant> =
        sequenceOf(
            PlantsData.list[0]
        )
}