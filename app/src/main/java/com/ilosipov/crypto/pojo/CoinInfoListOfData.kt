package com.ilosipov.crypto.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * CoinInfoListOfData
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 20.04.2022
 * @version $Id$
 */

data class CoinInfoListOfData(
    @SerializedName("Data")
    @Expose
    val data: List<Datum>? = null
)