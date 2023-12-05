package com.pablo.proyectopablomartinez.model.server

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.pablo.proyectopablomartinez.model.Pokemon
import kotlinx.parcelize.Parcelize
import java.net.URL
@Parcelize
data class PokeApiResponse(
    @Expose @SerializedName("count") val count: Int,
    @Expose @SerializedName("next") val next: String,
    @Expose @SerializedName("previous") val previous: String,
    @Expose @SerializedName("results") val url: URL
): Parcelable