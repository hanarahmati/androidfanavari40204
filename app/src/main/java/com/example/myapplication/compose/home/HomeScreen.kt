package com.example.myapplication.compose.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.myapplication.R
import com.example.myapplication.compose.flower.PopularFlowersList
import com.example.myapplication.compose.plantList.PlantListScreen
import com.example.myapplication.data.Plant
import com.example.myapplication.data.PlantsData
import com.example.myapplication.databinding.HomeScreenBinding
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.md_theme_light_secondary
import com.example.myapplication.ui.theme.md_theme_light_surfaceVariant
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch


enum class SunflowerPage(
    @StringRes val titleResId: Int,
    @DrawableRes val drawableResId: Int
) {
    PLANT_LIST(R.string.plant_list_title, R.drawable.ic_plant_list_active),
    MY_GARDEN(R.string.my_garden_title, R.drawable.ic_my_garden_active)

}



@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onPlantClick: (Plant) -> Unit = {},
    onPageChange: (SunflowerPage) -> Unit = {},
    onAttached: (MaterialToolbar) -> Unit = {},
) {
    val activity = (LocalContext.current as AppCompatActivity)

    AndroidViewBinding(factory = HomeScreenBinding::inflate, modifier = modifier) {

        onAttached(toolbar)

        activity.setSupportActionBar(toolbar)
        composeView.setContent {
            HomePagerScreen(
                onPlantClick = onPlantClick,
                onPageChange = onPageChange,
                plants = PlantsData.list,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePagerScreen(
    onPlantClick: (Plant) -> Unit,
    onPageChange: (SunflowerPage) -> Unit,
    modifier: Modifier = Modifier,
    pages: Array<SunflowerPage> = SunflowerPage.values(),
    plants: List<Plant>,
) {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState.currentPage) {
        onPageChange(pages[pagerState.currentPage])
    }

    // Use Modifier.nestedScroll + rememberNestedScrollInteropConnection() here so that this
    // composable participates in the nested scroll hierarchy so that HomeScreen can be used in
    // use cases like a collapsing toolbar
    Column(modifier.nestedScroll(
        rememberNestedScrollInteropConnection())) {
        val coroutineScope = rememberCoroutineScope()

        // Tab Row
        TabRow(selectedTabIndex = pagerState.currentPage) {
            pages.forEachIndexed { index, page ->
                val title = stringResource(id = page.titleResId)
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(text = title) },
                    icon = {
                        Icon(
                            painter = painterResource(id = page.drawableResId),
                            contentDescription = title
                        )
                    },
                    unselectedContentColor = md_theme_light_surfaceVariant,
                    selectedContentColor = md_theme_light_secondary,
                )
            }
        }

        // Pages
        HorizontalPager(
            pageCount = pages.size,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { index ->
            when (pages[index]) {
                SunflowerPage.MY_GARDEN -> {
                    PopularFlowersList()
                }
                SunflowerPage.PLANT_LIST -> {
                    PlantListScreen(
                        plants = plants,
                        onPlantClick = onPlantClick,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, device = "id:pixel_5", showBackground = true)
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(HomeScreenPreviewParamProvider::class) param: HomePreviewParam
) {
    MaterialTheme() {
        HomePagerScreen(
            onPlantClick = {},
            onPageChange = {},
            plants = param.plants
        )
    }
}

private data class HomePreviewParam(
    val plants: List<Plant>,
)

private class HomeScreenPreviewParamProvider :
    PreviewParameterProvider<HomePreviewParam> {
    override val values: Sequence<HomePreviewParam> =
        sequenceOf(
            HomePreviewParam(
                plants = emptyList()
            ),
            HomePreviewParam(
                plants = PlantsData.list
            )
        )
}