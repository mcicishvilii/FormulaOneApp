package com.example.formulaone.models.driversResponse

data class CurrentDriversStandingsResponse(
    val MRData: MrData
){
    data class MrData(
        val StandingsTable: StandingsTableClass,
        val limit: String,
        val offset: String,
        val series: String,
        val total: String,
        val url: String,
        val xmlns: String
    ){
        data class StandingsTableClass(
            val StandingsLists: List<StandingsListsClass>,
            val season: String
        ){
            data class StandingsListsClass(
                val DriverStandings: List<DriverStanding>,
                val round: String,
                val season: String
            ){
                data class DriverStanding(
                    val Constructors: List<Constructor>,
                    val Driver: DriverClass,
                    val points: String,
                    val position: String,
                    val positionText: String,
                    val wins: String
                ){
                    data class DriverClass(
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