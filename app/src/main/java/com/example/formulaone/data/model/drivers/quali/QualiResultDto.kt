package com.example.formulaone.data.model.drivers.quali


import com.google.gson.annotations.SerializedName

data class QualiResultDto(
    @SerializedName("MRData")
    val mRData: MRData
)