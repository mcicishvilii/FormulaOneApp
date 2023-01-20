package com.example.formulaone.domain.use_case.news


//class NewsUseCase @Inject constructor(
//    private val repository: NewsRepository
//) {
//    operator fun invoke(): Flow<Resource<List<ArticleDomain>>> = channelFlow {
//        repository.getNews().collectLatest {
//            when (it){
//                is Resource.Success -> {
//                    send(Resource.Success(it.data))
//                }
//                is Resource.Error -> {
//                    send(Resource.Error(it.error))
//                }
//                is Resource.Loading -> {
//                    send(Resource.Loading(it.loading))
//                }
//            }
//        }
//    }
//}