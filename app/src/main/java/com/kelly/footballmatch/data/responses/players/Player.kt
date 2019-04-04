package com.kelly.footballmatch.data.responses.players


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(

        @SerializedName("idPlayer") val idPlayer                : String?,
        @SerializedName("idTeam") val idTeam                    : String?,
        @SerializedName("strPlayer") val strPlayer              : String?,
        @SerializedName("strNationality") val strNationality    : String?,
        @SerializedName("dateBorn") val dateBorn                : String?,
        @SerializedName("dateSigned") val dateSigned            : String?,
        @SerializedName("strSigning") val strSigning            : String?,
        @SerializedName("strWage") val strWage                  : String?,
        @SerializedName("strBirthLocation") val strBirthLocation: String?,
        @SerializedName("idSoccerXML") val idSoccerXML          : String?,
        @SerializedName("strDescriptionEN") val strDescriptionEN: String?,
        @SerializedName("strPosition") val strPosition          : String?,
        @SerializedName("strHeight") val strHeight              : String?,
        @SerializedName("strWeight") val strWeight              : String?,
        @SerializedName("strThumb") val strThumb                : String?,
        @SerializedName("strCutout") var strCutout              : String?,
        @SerializedName("strFanart1") var strFanart1            : String?,
        @SerializedName("strFanart2") var strFanart2            : String?,
        @SerializedName("strFanart3") var strFanart3            : String?,
        @SerializedName("strFanart4") var strFanart4            : String?,
        @SerializedName("intLoved") var intLoved                : String?


): Parcelable