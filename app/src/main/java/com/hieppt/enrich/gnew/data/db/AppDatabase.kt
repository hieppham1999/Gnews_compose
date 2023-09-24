package com.hieppt.enrich.gnew.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hieppt.enrich.gnew.data.dao.ArticleDao
import com.hieppt.enrich.gnew.data.entity.ArticleEntity
import com.hieppt.enrich.gnew.data.entity.EntityConverter

@Database(entities = [ArticleEntity::class], version = 1)
@TypeConverters(EntityConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun articleDao(): ArticleDao
}