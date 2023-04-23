package com.example.movieapp_mad_ld05.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieapp_mad_ld05.R
import com.example.movieapp_mad_ld05.viewmodels.AddScreenViewModel
import com.example.movieapp_mad_ld05.widgets.Text
import kotlinx.coroutines.launch

@Composable
fun AddMovieScreen(
    navController: NavController,
    addScreenViewModel: AddScreenViewModel
){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {

         TopAppBar(elevation = 3.dp) {
            Row {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )

                Spacer(modifier = Modifier.width(20.dp))

                stringResource(id = R.string.add_movie)
            }
        }},
    ) { padding ->
        MainContent(
            Modifier.padding(padding),
            viewModel = addScreenViewModel,
            navController = navController
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    viewModel: AddScreenViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        MovieBody(
            movieUiState = viewModel.movieUiState,
            onMovieValueChange = { newUiState, event -> viewModel.updateUIState(newUiState, event)},
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveMovie()
                    navController.navigate(Screen.MainScreen.route)
                }
            }
        )
    }
}

@Composable
fun MovieBody(
    movieUiState: AddMovieUiState,
    onMovieValueChange: (AddMovieUiState, AddMovieUIEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        MovieInputForm(movieUiState = movieUiState, onMovieValueChange = onMovieValueChange)

        Button(
            enabled = movieUiState.actionEnabled,
            onClick = onSaveClick) {
            Text(text = stringResource(R.string.add))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieInputForm(
    movieUiState: AddMovieUiState,
    onMovieValueChange: (AddMovieUiState, AddMovieUIEvent) -> Unit,
){
    Text(
        value = movieUiState.title,
        label = stringResource(R.string.enter_movie_title),
        isError = movieUiState.titleErr,
        errMsg = stringResource(id = R.string.title_required),
        onChange = { input ->
            onMovieValueChange(movieUiState.copy(title = input), AddMovieUIEvent.TitleChanged)
        }
    )

    Text(
        value = movieUiState.year,
        label = stringResource(id = R.string.enter_movie_year),
        errMsg = stringResource(id = R.string.year_required),
        isError = movieUiState.yearErr,
        onChange = { input ->
            onMovieValueChange(movieUiState.copy(year = input), AddMovieUIEvent.YearChanged) }
    )

    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = stringResource(R.string.select_genres),
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.h6)

    LazyHorizontalGrid(
        modifier = Modifier.height(100.dp),
        rows = GridCells.Fixed(3)){
        items(movieUiState.selectableGenreItems) { genreItem ->
            Chip(
                modifier = Modifier.padding(2.dp),
                colors = ChipDefaults.chipColors(
                    backgroundColor = if (genreItem.isSelected)
                        colorResource(id = R.color.purple_200)
                    else
                        colorResource(id = R.color.white)
                ),
                onClick = {
                    onMovieValueChange(movieUiState.copy(genre = movieUiState.selectGenre(genreItem)), AddMovieUIEvent.GenresChanged)
                }
            ) {
                Text(text = genreItem.title)
            }
        }
    }

    if(movieUiState.genreErr){
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(id = R.string.genres_required),
            fontSize = 14.sp,
            color = MaterialTheme.colors.error
        )
    }

    Text(
        value = movieUiState.director,
        label = stringResource(R.string.enter_director),
        errMsg = stringResource(id = R.string.director_required),
        isError = movieUiState.directorErr,
        onChange = { input ->  onMovieValueChange(movieUiState.copy(director = input), AddMovieUIEvent.DirectorChanged)},
    )

    Text(
        value = movieUiState.actors,
        label = stringResource(R.string.enter_actors),
        errMsg = stringResource(id = R.string.actors_required),
        isError = movieUiState.actorsErr,
        onChange = { input ->  onMovieValueChange(movieUiState.copy(actors = input), AddMovieUIEvent.ActorsChanged)},
    )

    Text(
        value = movieUiState.plot,
        label = stringResource(R.string.enter_plot),
        isError = false,
        singleLine = false,
        modifier = Modifier.height(120.dp),
        onChange = { input ->  onMovieValueChange(movieUiState.copy(plot = input), AddMovieUIEvent.PlotChanged)},
    )

    Text(
        value = movieUiState.rating,
        label = stringResource(R.string.enter_rating),
        keyboardType = KeyboardType.Decimal,
        errMsg = stringResource(id = R.string.rating_required),
        isError = movieUiState.ratingErr,
        onChange = { input ->  onMovieValueChange(movieUiState.copy(rating = input), AddMovieUIEvent.RatingChanged)},
    )
}