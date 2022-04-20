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
    @SerializedName("Id")
    @Expose
    val id: String? = null,
    @SerializedName("Name")
    @Expose
    val name: String? = null,
    @SerializedName("FullName")
    @Expose
    val fullName: String? = null,
    @SerializedName("ImageUrl")
    @Expose
    val imageUrl: String? = null
)