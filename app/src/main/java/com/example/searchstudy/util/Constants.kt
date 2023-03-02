package com.example.searchstudy.util

object Constants {

    //URL
    const val BASE_URL = "https://openapi.naver.com/"

    //Sharedpreference
    const val PREF_KEY_SEARCH = "key_search" //각각의 어떤 쪽의 대한 constant인지 명시 예를 들어 해당 값음 preference에 대한 것이기 때문에 PREF_KEY_SEARCH라고쓰는것이 좋다

    //Intent
//  const val RECENT_SEARCH_DATA = "recentSearchData"

    //FINALNAME
    const val MISSWORD = 1  //이런 경우는 해당 값이 어떤것에 대한 값인지 더 명확하게 표시해주고 해당(오타체크 이므로 CHECK_MINSSWORD), 값의 따른 값들로 주석으로 처리 해주면 좋다
                            //예를 들어 1: 정상,  2: 오타 처럼
                            // 또한 constant는 다양한 곳에서 쓰는것만 표시 할거 나머지는 각각의 컴포넌트에 선언해주는 것이 좋음
                            // 대게 constant에는 URL, PREF, request 코드 등등
    //통합 탭의 해당 데이터 분기 flag
    const val VIEW = 1
    const val DICTIONARY = 2

    //번역 및 일반 데이터 분기 flag
    const val TRANSLATION = 0
    const val SEARCH = 1

    //view 더보기 시 api 분기 호출 flag
    const val VIEW_MORE_LOAD_BLOG_CAFE = 1
    const val VIEW_MORE_LOAD_BLOG = 2

    //intent
    const val DITAIL_WEB_LOAD_URL = "ditailWebLoadUrl"
    const val DITAIL_IMG_LOAD_URL = "ditailImgLoadUrl"
}