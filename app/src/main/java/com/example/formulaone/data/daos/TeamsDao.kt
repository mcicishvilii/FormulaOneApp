package com.example.formulaoneapplicationn.data.daos

import androidx.room.*
import com.example.formulaoneapplicationn.data.model.TeamsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamsDao {
    @Query("SELECT * FROM gundebi")
    fun getAll(): Flow<List<TeamsEntity>>

    @Query("SELECT * FROM gundebi WHERE teamId IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<TeamsEntity>>

    @Query("SELECT * FROM gundebi WHERE team_name LIKE :first AND " +
            "team_nationality LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Flow<TeamsEntity>

    @Insert
    fun insertAll(vararg users: TeamsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: TeamsEntity)

    @Delete
    fun delete(user: TeamsEntity)

    @Query("DELETE FROM gundebi")
    fun deleteAll()

}