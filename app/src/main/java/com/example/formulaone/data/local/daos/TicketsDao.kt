package com.example.formulaone.data.local.daos

import androidx.room.*
import com.example.formulaone.data.local.models.TeamsDtoLocal
import com.example.formulaone.data.local.models.TicketsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketsDao {
    @Query("SELECT * FROM biletebi")
    fun getAll(): Flow<TicketsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ticket: TicketsEntity)

    @Delete
    fun delete(ticket: TicketsEntity)

    @Query("DELETE FROM biletebi")
    fun deleteAll()

}