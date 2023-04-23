package com.example.movieapp_mad_ld05.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.movieapp_mad_ld05.repositories.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    // CRUD
    @Insert
    suspend fun add(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * FROM Movie")
    fun getAll(): Flow<List<Movie>>

    @Query("SELECT * FROM Movie WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<Movie>>

    @Query("SELECT * FROM Movie WHERE id=:id")
    fun getMovie(id: String): Flow<Movie?>

    /*@Query("DELETE from task")
    fun deleteAll()*/
}