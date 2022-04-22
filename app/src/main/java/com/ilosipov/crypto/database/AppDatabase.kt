package com.ilosipov.crypto.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ilosipov.crypto.pojo.CoinPriceInfo

/**
 * AppDatabase
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 22.04.2022
 * @version $Id$
 */

@Database(entities = [CoinPriceInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance: AppDatabase =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    ).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao
}