package com.example.pexelsapp.data.local.dao

import androidx.room.*
import com.example.pexelsapp.data.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    // Получить все избранные фото, отсортированные по дате добавления
    @Query("SELECT * FROM bookmarks ORDER BY added_at DESC")
    fun getAll(): Flow<List<BookmarkEntity>>

    // Получить избранное по ID
    @Query("SELECT * FROM bookmarks WHERE id = :id")
    suspend fun getById(id: Int): BookmarkEntity?


    // Добавить или обновить избранное
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookmark: BookmarkEntity)

    // Удалить избранное
    @Delete
    suspend fun delete(bookmark: BookmarkEntity)


    // Удалить избранное по ID фото
    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun deleteById(id: Int)

    // есть ли фото в избранном
    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE id = :id)")
    suspend fun exists(id: Int): Boolean

    // Получить количество избранных фото
    @Query("SELECT COUNT(*) FROM bookmarks")
    fun getCount(): Flow<Int>

    // Удалить все избранные фото
    @Query("DELETE FROM bookmarks")
    suspend fun clearAll()

    // Получить список ID всех избранных фото
    @Query("SELECT id FROM bookmarks")
    suspend fun getAllIds(): List<Int>
}