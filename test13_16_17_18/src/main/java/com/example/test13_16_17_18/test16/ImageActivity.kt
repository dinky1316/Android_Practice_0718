package com.example.test13_16_17_18.test16

import android.content.Intent
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMainBinding
import com.example.test13_16_17_18.databinding.ActivityImageBinding
import java.io.File
import java.util.Date

// 경로 ch16_provider/src/main/java/com/example/ch16_provider/MainActivity.kt
// 갤러리와 카메라를 연동해서 , 인텐트의 후처리 작업을 이용해서
// 비트맵 또는 drawable 타입으로 이미지 처리하는 부분 봅니다
// 주의사항, 미디어 서버에 접근하는 허가 부분이 조금 변경 되어서 소개 후 사용하고
// 콘텐츠 프로바이더 부분의  authorities 부분 주의해서 작업 따라 하시면 됩니다
// 내용은 그대로 재사용, 코드 리뷰할 때 설명 잘 보시면 됩니다.

// 변경 사항
// 바인딩 변경
// 매니페스트 권한 설정 ->
// 사이즈 변경 부분에서 res에서 가져와서 사용하는 부분
// 경로 : 리소스
// SimpleDateFormat 임포트
// Date 임포트

// 임의의 프로필 사진 한장 정해서 교체
// val photoURI: Uri = FileProvider.getUriForFile
// 이 부분의 authority 변경

class ImageActivity : AppCompatActivity() {

    lateinit var binding: ActivityImageBinding
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //gallery request launcher..................
        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        )
        {
            try {
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.imgSize),
                    resources.getDimensionPixelSize(R.dimen.imgSize)
                )
                val option = BitmapFactory.Options()
                option.inSampleSize = calRatio
                // 파일 입력 출력. 아래 코드
                // 사진을 바이트 단위로 읽었음 imputStream : 이미지의 바이트 단위의 결과값
                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                // decodeStream : 바이트로 읽어서 실제 이미지의 타입으로 변환. 단위 bitmap으로 변환
                // bitmap : 안드로이드에서 사용하는 이미지 단위. 보통 네트워크 파일 io 할 때 자주 이용
                val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
                inputStream!!.close()
                inputStream = null
                // 사진 -> 바이트 읽어서 -> inputStream -> decodeStream -> bitmap -> 뷰에 출력
                // 이미지, 영상 관련 인코딩 관심 있으면
                // 강사님이 작업 한 것 주소 : https://github.com/lsy3709/travel_sample_app_spring_firebase
                bitmap?.let {
                    Log.d("kkang", "결과 뷰에 적용 전")
                    // 결과 뷰에 갤러리에서 가져온 사진을 할당하는 부분
                    binding.userImageView.setImageBitmap(bitmap)
                    Log.d("kkang", "결과 뷰에 적용 후")
                } ?: let {
                    Log.d("kkang", "bitmap null")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.galleryButton.setOnClickListener {
            //gallery app........................
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        //camera request launcher................. 후처리를 하는 함수
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            // calRatio 임의로 만든 변수, 이미지 처리 시 크기 문제는 메모리 부족 현상이 생겨서
            // 크기를 조정 하는 함수
            // calculateInSampleSize 함수 : 저자가 임의로 만든 원본의 크기를 줄이는 비율을 구하는 식
            // 로직은 원본의 크기를 반으로 줄여서 비율 조사를 해 나가는 방식
            // 결론, 크기를 줄여 나가는 함수
            // 정수 값으로, 예 ) 3, 4 반환함
            val calRatio = calculateInSampleSize(
                // 원본 데이터
                Uri.fromFile(File(filePath)),
                // 출력할 이미지의 크기를 임의 지정
                // 현재 리소스 폴더에 150dp 로 지정
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            // BitmapFactory 비트맵 타입으로 이미지를 그대로 처리 시 문제가 됨. 
            // OOM 메모리 누수 옵션을 정해서 이미지를 처리해야함
            val option = BitmapFactory.Options()
            // calRatio 원본의 사진을 특정 비율에 맞게 줄인 결과 값
            // ex ) option.inSampleSize = 4
            // 12MB -> 3MB 사이즈로 줄여나감(크기가 조정됨)
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }


        binding.cameraButton.setOnClickListener {
            //camera app......................
            //파일 준비...............
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            // 안드로이드 시스템에서 정하는 DIRECTORY_PICTURES 정해져 있음
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            Log.d("dum", "storage의 위치 : $storageDir")
            // JPEG
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath
            Log.d("dum", "filePath의 경로 : $filePath")
            // 카메라에서 찍은 사진에 접근 하기 위해서 콘텐츠 프로바이더에 요청
            // 요청시 매내페스트에서 정한 같은 문자열을 사용
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "om.example.test13_16_17_18.test16.fileprovider",
                file
            )
            // 현재 앱 -> 외부 앱으로 가기 위해서 시스템에게 인텐트로 전달
            // 인텐트의 메세지 내용은 액션의 문자열 카메라 앱
            // 데이터의 내용은 사진의 출력(카메라로 찍은 사진), photoURI 에 담기
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            // 후처리 함수 호출하면, 위에 정된 후처리 작업하는 함수로 이동
            // 카메라 촬영 후 체크 한다음 되돌아올때 작업은 위에 함수에서 처리
            requestCameraFileLauncher.launch(intent)

        }
    }

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

}