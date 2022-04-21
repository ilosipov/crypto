package com.ilosipov.crypto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ilosipov.crypto.api.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * MainActivity
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 21.04.2022
 * @version $Id$
 */

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val disposable = ApiFactory.apiService.getTopCoinsInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "Test of loading data: $it")
            }, {
                Log.e(TAG, "Test of loading data: ${it.message}", it)
            })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}