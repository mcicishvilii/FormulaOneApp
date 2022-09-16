package com.example.formulaone.data.teams

data class Teams(
    val MRData: MRdata
){
    data class MRdata(
        val ConstructorTable: ConstructorsTable,
        val limit: String,
        val offset: String,
        val series: String,
        val total: String,
        val url: String,
        val xmlns: String
    ){
        data class ConstructorsTable(
            val Constructors: List<Constructor>?
        ) {
            data class Constructor(
                val constructorId: String?,
                val name: String?,
                val nationality: String?,
                val url: String?
            )
        }
    }
}