<<<<<<< HEAD
package com.example.tryengapp.dal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tryengapp.dal.model.WordEntity

@Database(
    version = 12,
    exportSchema = false,
    entities = [WordEntity::class]
)
abstract class TryEngDatabase : RoomDatabase() {
    abstract val try_eng_DAL: TryEngDAL
=======
package com.example.tryengapp.dal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tryengapp.dal.model.WordEntity

@Database(
    version = 12,
    exportSchema = false,
    entities = [WordEntity::class]
)
abstract class TryEngDatabase : RoomDatabase() {
    abstract val try_eng_DAL: TryEngDAL
>>>>>>> 38b66a03b864ca564c30ccc18f51636406be220a
}