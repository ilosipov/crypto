package com.ilosipov.crypto.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * CoinInfo
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 20.04.2022
 * @version $Id4
 */

data class CoinInfo (
    @SerializedName("Name")
    @Expose
    val name: String? = null
)