package com.example.test13_16_17_18.test16.test16multiimage

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain5Binding
import com.example.test13_16_17_18.databinding.ActivityMainBinding
import com.example.test13_16_17_18.databinding.MultiImageItemBinding

// 경로 test16-2multiimg/src/main/java/com/example/test16_2multiimg/MainActivity.kt
// glide : 이미지를 출력해주는 도구(코루틴 적용, 비동기식 처리)
// 많이 사용하는 도구이다. 설정 하려면 build.gradle 모듈 파일에서 디펜던시 추가
// implementation 'com.github.bumptech.glide:glide:4.12.0'
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMain5Binding

    // 갤러리에서 선택된 이미지의 주소를 하나의 객체로 해서 리스트에 담기
    var list = ArrayList<Uri>()

    // 리사이클러 뷰에서 데이터와 뷰를 연결시키기 위한 하나의 변환기
    var adapter = MultiImageAdapter(list, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        )
        {
            list.clear()
            if (it.data?.clipData != null) {
                val count = it.data!!.clipData?.itemCount
                if (count != null) {
                    if (count > 10) {
                        Toast.makeText(this@MainActivity, "사진 선택은 10장까지만 가능", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                for (i in 0 until count!!) {
                    val imageUri = it.data!!.clipData?.getItemAt(i)?.uri
                    if (imageUri != null) {
                        list.add(imageUri)
                    }
                }
            } else {
                it.data.let { uri ->
                    val imageUri: Uri? = it.data?.data
                    if (imageUri != null) {
                        list.add(imageUri)
                    }
                }
            }
            adapter.notifyDataSetChanged()
        }

        binding.getImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.setType("image/*")
            intent.action = Intent.ACTION_GET_CONTENT

            requestGalleryLauncher.launch(intent)
        }
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter


    }

    // 원래는 어댑터라는 폴더를 만들어서 분리
    // 테스트용으로 같이 쓰고 있음
    // 리사이클러 뷰에 출력하기 위한 목록 요소의 아이템 뷰
    class ViewHolder(val binnding: MultiImageItemBinding) : RecyclerView.ViewHolder(binnding.root)

    class MultiImageAdapter(val items: ArrayList<Uri>, val context: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder = ViewHolder(
            MultiImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binnding = (holder as ViewHolder).binnding
            // Glide 이미지 전문으로 처리 해주는 API
            // bitmapFactory options 설정, 이상한 calc 함수통해서 리사이즈.
            // context -> 현재 액티비티 -> this@MainActivity.
            // load , 사진을 불러오기. items => list , 각사진의 Uri 주소가 담아져 있음.
            // override -> 직접 원하는 사이즈 적어주면,
            // into , 해당 뷰에 , 사진을 넣는 작업.
            // items 에 선택된 이미지가 요소로 담겨있음
            Glide.with(context).load(items[position])
                // 출력 이미지의 가로 세로 정할 수 있음
                .override(500, 500)
                // 출력 이미지 뷰에 선택한 이미지를 불러오는 역할
                .into(binnding.image)
        }

        override fun getItemCount(): Int {
            return items.size
        }


    }
}