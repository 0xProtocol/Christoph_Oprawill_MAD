package com.example.movieapp_mad_ld05.screens

sealed class AddMovieUIEvent{
    object TitleChanged : AddMovieUIEvent()
    object YearChanged: AddMovieUIEvent()
    object GenresChanged: AddMovieUIEvent()
    object DirectorChanged: AddMovieUIEvent()
    object ActorsChanged: AddMovieUIEvent()
    object PlotChanged: AddMovieUIEvent()
    object RatingChanged: AddMovieUIEvent()
}
