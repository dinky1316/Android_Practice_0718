package com.example.test13_16_17_18.test17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain3Binding
import com.example.test13_16_17_18.databinding.ActivityMainBinding

// 1) 기본 SQLite 살펴보기 -> pdf에서 제공한 소스 코드 이용
// 2) 연습문제 17 살펴보기 -> 조금 더 활용한 부분. 쓰기, 읽기 만
//   ㄴ>  경로 ch17_database/src/main/java/com/example/ch17_database/MainActivity.kt
//        참고 파일)
//        1. AddActivity(액티비티 클래스)
//        2. DBHelper 일반 클래스
//        3. MainActivity(액티비티 클래스) || 변경사항 :  R.menu.menu_main 추가 , 세팅 관련 부분 제거 (SettingActivity)
//        4. MyAdapter 일반 클래스
//          액션바 활성화
// 3) crud 블로그 샘플소스 살펴보기
// 4) 제트팩 라이브러리에서 구글에서 공식적으로 SQLite 보다 room 사용을 권장
//
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMain3Binding

    // AddActivity에서 입력된 한줄의 텍스트들을 요소로 리스트에 보관
    var datas: MutableList<String>? = null

    // 입력된 문자열 내용을, 리사이클러 뷰로 출력
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 입력 창으로 텍스트 입력 후 저장 버튼을 누르면 여기로 돌아옴
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        )
        {
            // 돌아온 결과값은 it 라는 Map 형태로 저장
            // 키 : result라는 부분의 값을 가지고 와서
            // datas 라는 리스트에 add 담기
            // 어댑터 객체의 함수중에 변경 사항을 알리는 함수를 수행해서
            // 리사이클러 뷰에 적용함
            it.data!!.getStringExtra("result")?.let {
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }
        }
        // 플로팅 액션바 버튼, 클릭 이벤트 처리 -> 입력 액티비티로 이동
        // 후처리를 하는 함수
        // 입력 창에서 todo를 입력 후 입력된 값을 가지고 되돌아 옴
        // val requestLauncher = registerForActivity 이 부분으로 돌아옴
        // AddActivity에서는 데이터를 처리하는 세터 부분이 있음
        binding.mainFab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            requestLauncher.launch(intent)
        }

        datas = mutableListOf<String>()

        //add......................

        // 조회
        // readbleDatabase -> 읽기
        val db = DBHelper(this).readableDatabase
        // 커서 cursor 쉽게, 조회된 결과를 테이블 형식으로 저장된 객체
        val cursor = db.rawQuery("select * from TODO_TB", null)
        // 테이블 형식으로 저장되어 있음
        cursor.run {
            // 반복문으로 커서 테이블에 데이터를 한행씩 불러와서 해당 컬럼을 가져오기
            // 원래 리스트 인덱스를 0부터
            // 하지만 커서는 1행부터 시작.
            while (moveToNext()) {
                datas?.add(cursor.getString(1))
            }
        }
        // 디비 서버에서 조회된 내용을 현재 메모리 datas라는 리스트에 다 담기
        // 디비 사용을 반납
        db.close()

        // 리사이클러뷰 적용하는 부분
        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.layoutManager = layoutManager
        // 디비 서버에서 받아온 데이터를 메모리 상의 임시 객체 datas에 담아서
        // 어댑터 클래스에 연결 하는 부분
        adapter = MyAdapter(datas)
        // 어댑터 클래스에 적용된 데이터 <-> 뷰, 결과를 뷰에 적용하는 부분
        binding.mainRecyclerView.adapter = adapter
        // 리사이클러 뷰의 옵션 선 정도 생성
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 세팅 부분
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === R.id.menu_main_setting) {
//            val intent = Intent(this, SettingActivity::class.java)
//            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}