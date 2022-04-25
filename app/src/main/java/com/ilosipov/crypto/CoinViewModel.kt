package com.ilosipov.crypto

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.ilosipov.crypto.api.ApiFactory
import com.ilosipov.crypto.database.AppDatabase
import com.ilosipov.crypto.pojo.CoinPriceInfo
import com.ilosipov.crypto.pojo.CoinPriceInfoRawData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * CoinViewModel
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 25.04.2022
 * @version $Id$
 */

class CoinViewModel(app: Application) : AndroidViewModel(app) {

//    private val db = AppDatabase.getInstance(app.applicationContext)
    private val compositeDisposable = CompositeDisposable()

//    val priceList = db.coinPriceInfoDao().getPriceList()

    fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo()
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            .map { getPriceListFromRawData(it) }
            .subscribeOn(Schedulers.io())
            .subscribe({
//                db.coinPriceInfoDao().insertPriceList(it!!)
                Log.i("CoinViewModel", "loadData: TEST_OF_LOADING_DATA: $it")
            }, {
                Log.e("CoinViewModel", "loadData: TEST_OF_LOADING_DATA: ${it.message}", it)
            })
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
    ) : List<CoinPriceInfo>? {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject
        val coinKeySet = jsonObject.keySet()

        if (jsonObject == null)
            return result

        coinKeySet.forEach { coinKey ->
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            currencyKeySet.forEach { currencyKey ->
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}