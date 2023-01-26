package com.example.searchstudy.network.services
import retrofit2.Call
import retrofit2.http.GET

interface SearchService {
    //나중에 지울것
    //@Field key=value 형식의 데이터를 보낼때 사용
    //@Body는 해당 데이터를 직열화하여 하번에 보내줄때 사용 Json형식일때 사용 한다고 보면 된다. 여기서
    // {
    //  "userId": "pmsdev",
    //  "password": "open1404!!"
    //}

    //이런 형식을 보내준다. 이걸 다시보면 { "userId": "pmsdev", "password": "open1404!!"} 형식의 직열화된 형식으로 보낸다.
    @GET("/blog.json")
    fun searchBlog(): Call<String>
}