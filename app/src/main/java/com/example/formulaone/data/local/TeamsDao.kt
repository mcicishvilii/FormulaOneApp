package com.example.formulaone.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamsDao {
    @Query("SELECT * FROM gundebi")
    fun getAll(): Flow<List<TeamsDtoLocal>>

    @Query("SELECT * FROM gundebi WHERE teamId IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<TeamsDtoLocal>>

    @Query("SELECT * FROM gundebi WHERE team_name LIKE :first AND " +
            "team_nationality LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Flow<TeamsDtoLocal>

    @Insert
    fun insertAll(vararg users: TeamsDtoLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: TeamsDtoLocal)

    @Delete
    fun delete(user: TeamsDtoLocal)

    @Query("DELETE FROM gundebi")
    fun deleteAll()

}