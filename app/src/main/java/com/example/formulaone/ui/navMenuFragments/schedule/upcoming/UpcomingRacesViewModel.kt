package com.example.formulaone.ui.navMenuFragments.schedule.upcoming

//@RequiresApi(Build.VERSION_CODES.O)
//@HiltViewModel
//class UpcomingRacesViewModel @Inject constructor(
//    private val getRaceScheduleUseCase: RaceScheduleUseCase
//) : ViewModel() {
//
//    private val _state1 =
//        MutableStateFlow<Resource<List<RaceScheduleDomain>>>(Resource.Loading(false))
//    val state = _state1.asStateFlow()
//
//    init {
//        getSchedule()
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun getSchedule() {
//        getRaceScheduleUseCase().onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state1.value = Resource.Success(result.data)
//                }
//                is Resource.Error -> {
//                    _state1.value = Resource.Error("woops!")
//                }
//                is Resource.Loading -> {
//                    _state1.value = Resource.Loading(true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
//}
