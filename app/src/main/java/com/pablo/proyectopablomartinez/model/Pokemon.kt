package com.pablo.proyectopablomartinez.model


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.net.URL

@Parcelize
data class Pokemon(
   @Expose @SerializedName("id") val id : Int,
   @Expose @SerializedName("name") var name: String,
    @Expose @SerializedName("Foto") val photo: String,
) : Parcelable

