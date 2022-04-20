package com.ilosipov.crypto.pojo

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * CoinPriceInfoRawData
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 20.04.2022
 * @version $Id$
 */

data class CoinPriceInfoRawData(
    @SerializedName("RAW")
    @Expose
    val coinPriceInfoJsonObject: JsonObject
)