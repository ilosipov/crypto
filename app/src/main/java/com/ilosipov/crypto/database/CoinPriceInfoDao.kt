package com.ilosipov.crypto.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilosipov.crypto.pojo.CoinPriceInfo

/**
 * CoinPriceInfoDao
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 22.04.2022
 * @version $Id$
 */

@Dao
interface CoinPriceInfoDao {

    @Query("SELECT * FROM full_price_list ORDER BY lastUpdate")
    fun getPriceList(): LiveData<List<CoinPriceInfo>>

    @Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym LIMIT 1")
    fun getPriceInfoAboutCoin(fSym: String): LiveData<CoinPriceInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinPriceInfo>)
}