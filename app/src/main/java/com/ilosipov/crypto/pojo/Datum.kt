package com.ilosipov.crypto.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Datum
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 20.04.2022
 * @version $Id$
 */

data class Datum(
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinInfo? = null
)