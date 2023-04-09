package com.example.movieappmad23.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad23.R
import com.example.movieappmad23.models.Genre
import com.example.movieappmad23.models.ViewModel
import com.example.movieappmad23.widgets.SimpleTopAppBar

@Composable
fun AddMovieScreen(navController: NavController, viewModel: ViewModel){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = stringResource(id = R.string.add_movie))
            }
        },
    ) { padding ->
        MainContent(Modifier.padding(padding), viewModel = viewModel)
    }
    viewModel.init()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(modifier: Modifier = Modifier, viewModel: ViewModel) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            TextInputField(
                text = viewModel.title,
                errorState = viewModel.errorTitle,
                label = R.string.enter_movie_title,
                validateMethod = {viewModel.validate("title")}
            )

            TextInputField(
                text = viewModel.year,
                errorState = viewModel.errorYear,
                label = R.string.enter_movie_year,
                validateMethod = {viewModel.validate("year")}
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6)

            ErrorMessage(value = viewModel.genreError.value)

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)){
                items(viewModel.genreItems.value) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            viewModel.genreItems.value = viewModel.genreItems.value.map {
                                if (it.title == genreItem.title) {
                                    genreItem.copy(isSelected = !genreItem.isSelected)
                                } else {
                                    it
                                }
                            }
                            viewModel.validate("genres")
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }

            TextInputField(
                text = viewModel.director,
                errorState = viewModel.errorDirector,
                label = R.string.enter_director,
                validateMethod = {viewModel.validate("director")}
            )

            TextInputField(
                text = viewModel.actors,
                errorState = viewModel.errorActors,
                label = R.string.enter_actors,
                validateMethod = {viewModel.validate("actors")}
            )

            TextInputField(
                text = viewModel.plot,
                errorState = viewModel.errorPlot,
                label = R.string.enter_plot,
                validateMethod = {viewModel.validate("plot")}
            )

            TextInputField(
                viewModel.rating,
                viewModel.errorRating,
                R.string.enter_rating,
                validateMethod = {viewModel.validate("rating")}
            )

            /*
            var title by remember {
                mutableStateOf("")
            }

            var year by remember {
                mutableStateOf("")
            }

            val genres = Genre.values().toList()

            var genreItems by remember {
                mutableStateOf(
                    genres.map { genre ->
                        ListItemSelectable(
                            title = genre.toString(),
                            isSelected = false
                        )
                    }
                )
            }

            var director by remember {
                mutableStateOf("")
            }

            var actors by remember {
                mutableStateOf("")
            }

            var plot by remember {
                mutableStateOf("")
            }

            var rating by remember {
                mutableStateOf("")
            }

            var isEnabledSaveButton by remember {
                mutableStateOf(true)
            }
             */

            Button(
                enabled = viewModel.isDisabled.value,
                onClick = {
                    val genreList: MutableList<Genre> = mutableListOf()
                    viewModel.genreItems.value.filter { it.isSelected }.forEach { genreList.add(Genre.valueOf(it.title)) }

                    viewModel.addMovie(
                        viewModel.title.value,
                        viewModel.year.value,
                        viewModel.director.value,
                        genreList,
                        viewModel.actors.value,
                        viewModel.plot.value,
                        viewModel.rating.value
                    )
                }) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}

@Composable
private fun TextInputField(
    text: MutableState<String>,
    errorState: MutableState<Boolean>,
    label: Int,
    validateMethod: () -> Unit
) {
    OutlinedTextField(
        value = text.value,
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {
            text.value = it
            validateMethod()
        },
        label = { Text(stringResource(id = label)) },
        isError = errorState.value
    )
    ErrorMessage(value = errorState.value)
}

@Composable
private fun ErrorMessage(value: Boolean) {
    /*if (value.equals("error")) {
        Text(
             text = "Error",
            color = Color.Red
        )
    }*/
    if (value) {
        Text(
            text = "Error",
            color = Color.Red
        )
    }
}