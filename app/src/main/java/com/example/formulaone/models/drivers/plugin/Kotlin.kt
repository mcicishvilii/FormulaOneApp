package com.example.formulaone.models.drivers.plugin

import com.squareup.moshi.Json


data class Kotlin (
    @Json(name = "MRData" ) var MRData : MRData? = MRData()
)

data class MRData (
    @Json(name = "xmlns"          ) var xmlns          : String?         = null,
    @Json(name = "series"         ) var series         : String?         = null,
    @Json(name = "url"            ) var url            : String?         = null,
    @Json(name = "limit"          ) var limit          : String?         = null,
    @Json(name = "offset"         ) var offset         : String?         = null,
    @Json(name = "total"          ) var total          : String?         = null,
    @Json(name = "StandingsTable" ) var StandingsTable : StandingsTable? = StandingsTable()
)

data class StandingsTable (
    @Json(name = "season"         ) var season         : String?                   = null,
    @Json(name = "StandingsLists" ) var StandingsLists : List<StandingsLists> = listOf()
)

data class StandingsLists (
    @Json(name = "season"          ) var season          : String?                    = null,
    @Json(name = "round"           ) var round           : String?                    = null,
    @Json(name = "DriverStandings" ) var DriverStandings : List<DriverStandings> = listOf()
)

data class DriverStandings (
    @Json(name = "position"     ) var position     : String?                 = null,
    @Json(name = "positionText" ) var positionText : String?                 = null,
    @Json(name = "points"       ) var points       : String?                 = null,
    @Json(name = "wins"         ) var wins         : String?                 = null,
    @Json(name = "Driver"       ) var Driver       : Driver?                 = Driver(),
    @Json(name = "Constructors" ) var Constructors : List<Constructors> = listOf()
)

data class Driver (
    @Json(name = "driverId"        ) var driverId        : String? = null,
    @Json(name = "permanentNumber" ) var permanentNumber : String? = null,
    @Json(name = "code"            ) var code            : String? = null,
    @Json(name = "url"             ) var url             : String? = null,
    @Json(name = "givenName"       ) var givenName       : String? = null,
    @Json(name = "familyName"      ) var familyName      : String? = null,
    @Json(name = "dateOfBirth"     ) var dateOfBirth     : String? = null,
    @Json(name = "nationality"     ) var nationality     : String? = null
)

data class Constructors (
    @Json(name = "constructorId" ) var constructorId : String? = null,
    @Json(name = "url"           ) var url           : String? = null,
    @Json(name = "name"          ) var name          : String? = null,
    @Json(name = "nationality"   ) var nationality   : String? = null
)
