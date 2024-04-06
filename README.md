네이버 검색 어플
===========
- 네이버 검색 api를 활용한 앱
- 카페, 블로그, 사전, 이미지 검색 내용 확인 기능
</br>

## 💻주요기능
</br>

### 네이버 검색 기능
---
- 성인 단어 필터링
- 오타 필터링
- VIEW(블로그, 카페)의 클릭시 해당 웹 페이지로 이동 가능
- 스크롤을 통한 검색어의 추가 검색
- 검색 결과 중 최신 검색결과를 한글 내림차순으로 보여줌
</br>


### 최근 검색 단어 기능
---
- 최근 검색 단어 저장 및 삭제
- 최근 검색 단어를 통한 검색 기능

</br>


## 사용 기술 스텍
- **Android**
- **Kotlin**
- **MVVM**
- **ViewModel**
- **Retrofit**
- **LiveData**
- **open Api(naver)**
- **Glid**

</br>

## 어렵웠던 점
- 통합 탭의 다른 모양의 Item을 가지는 RecyclerVieW 구현
- Adapter의 중복 사용으로 데이터의 내용이 중복으로 나타남

[해결책 및 느낌 점]
서로 다른 item을 가지는 recyclerView의 경우 Multiple View Types를 이용하여 구현 했으며 특정 flag에 대당하는 데이터가 들어왔을 때 하위 recyclerView를 ViewHolder에서 생성 하므로써 해결했습니다.
RecyclerView의 다른 형식의 Item View를 핸들링 할 수 있고 ViewHolder에서 RecyclerView 내부에 다른 Adatper를 생성하므로서 2 중 리사이클러뷰의 사용 방법을 배울 수 있었습니다.
RecyclerView를 사용하는 곳이 많아 최대한 재활용하려고 adapter의 데이터를 초기화하는 방법을 사용려고 했지만 해당 방법은 잘못된 방법이라는 것을 알게 되었음 이유는 뷰가 그러질때 해당 데이터를 추가로 다시 부르기 때문에 초기화에 문제가 생겼음 그리고 유지보시 각각의 adapter객체를 따로 관리하는 것이 훨씬 캡슐화에 유리 하다는 것을 느낄 수 있었습니다.
```
class AllItemsAdapter(private val context: Context, private val listener: (link: String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var arrayResultNaver = mutableListOf<ResultNaver>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            Constants.SEARCH -> {
                val allRecyclerviewItemViewBinding = AllRecyclerviewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AllItemViewHolder(context, allRecyclerviewItemViewBinding)
            }
            else -> {
                val translationViewBinding = AllTranslationRecyclerviewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AllTranslationViewHolder(translationViewBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (arrayResultNaver[position].classType) {
            Constants.SEARCH -> {
                (holder as AllItemViewHolder).bind(arrayResultNaver[position] as ResultSearchAll, listener)
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as AllTranslationViewHolder).bind(arrayResultNaver[position] as ResultPapago)
                holder.setIsRecyclable(false)
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayResultNaver.size
    }

    override fun getItemViewType(position: Int): Int {
        return arrayResultNaver[position].classType
    }

    fun setData(arrayResultNaver: ArrayList<ResultNaver>) {
        arrayResultNaver?.let {
            this.arrayResultNaver = it
            it.forEach { item ->
                Log.e("cyc","this.arrayResultNaver-item---${item}")
            }

        }
        notifyDataSetChanged()
    }
```

## 이미지
![1234](https://github.com/yoonchanchoi/SearchStudy/assets/74814647/840b6916-e3f9-479e-afe5-2c94b04f4d74)
<img src="https://github.com/yoonchanchoi/SearchStudy/assets/74814647/20c60e4c-282e-47a2-9893-6f684ec7e21b" width="200" height="400">
<img src="https://github.com/yoonchanchoi/SearchStudy/assets/74814647/3f85f767-c443-45a1-a41f-37454deae6c3" width="200" height="400">
