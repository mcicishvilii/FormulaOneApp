package com.example.formulaone.models.drivers.plugin

data class PluginStandings(
    val MRData: MRDataX)
{
    data class MRDataX(
        val StandingsTable: StandingsTableX,
        val limit: String,
        val offset: String,
        val series: String,
        val total: String,
        val url: String,
        val xmlns: String
    ){
        data class StandingsTableX(
            val StandingsLists: List<StandingsListsX>,
            val season: String
        ){
            data class StandingsListsX(
                val DriverStandings: List<DriverStanding>,
                val round: String,
                val season: String
            ){
                data class DriverStanding(
                    val Driver: DriverX,
                    val points: String,
                    val position: String,
                    val positionText: String,
                    val wins: String
                ){
                    data class DriverX(
                        val code: String,
                        val dateOfBirth: String,
                        val driverId: String,
                        val familyName: String,
                        val givenName: String,
                        val nationality: String,
                        val permanentNumber: String,
                        val url: String
                    )
                }
            }
        }
    }
}