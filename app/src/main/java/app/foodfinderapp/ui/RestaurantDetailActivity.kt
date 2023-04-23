package app.foodfinderapp.ui

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import app.foodfinderapp.BuildConfig
import app.foodfinderapp.R
import app.foodfinderapp.dao.FirebaseAuthDAO
import app.foodfinderapp.dao.RestaurantDao
import app.foodfinderapp.databinding.ActivityRestaurantDetailBinding
import app.foodfinderapp.dto.Restaurant
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class RestaurantDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestaurantDetailBinding
    private lateinit var restaurantDao: RestaurantDao

    companion object {
        private const val PERMISSIONS_REQUEST_CODE = 100
        private const val REQUEST_CODE_TAKE_PHOTO = 200// 权限请求码
    }

    private var photoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_detail)
        restaurantDao = RestaurantDao()

        val restaurant = intent.getParcelableExtra<Restaurant>("restaurant")
        binding.restaurant = restaurant

        val dao = RestaurantDao()
        dao.getImageUrl(restaurant!!.restaurantID) { imageUrl ->
            Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .into(binding.imageView)
        }

        binding.imageView.setOnClickListener {
            if (restaurant!!.ownerId == FirebaseAuthDAO.getCurrentUserId()) { // ownerId 和当前用户 ID 相同
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                ) { // 权限未被授予
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        PERMISSIONS_REQUEST_CODE
                    )
                } else { // 权限已经被授予
                    takePhoto()
                }
            }
        }
    }

    private fun takePhoto() {
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoFile = createImageFile()
        photoUri = FileProvider.getUriForFile(this, "${BuildConfig.APPLICATION_ID}.fileprovider", photoFile)
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        startActivityForResult(captureIntent, REQUEST_CODE_TAKE_PHOTO)
    }

    private fun createImageFile(): File {
        // 创建一个唯一的文件名
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmm ss", Locale.getDefault()).format(Date())
        val fileName = "JPEG_${timeStamp}_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDir)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {
            // 上传照片
            uploadPhoto()
        }
    }

    private fun uploadPhoto() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("正在上传照片")
        progressDialog.show()

        val storageRef = FirebaseStorage.getInstance().reference
        val photoRef = storageRef.child("images/${photoUri!!.lastPathSegment}")

        val uploadTask = photoRef.putFile(photoUri!!)

        uploadTask.addOnSuccessListener {
            progressDialog.dismiss()
            Toast.makeText(this, "照片上传成功", Toast.LENGTH_SHORT).show()

            // 获取照片的 URL 并加载到 ImageView 中
            photoRef.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(this).load(uri).into(binding.imageView)
                val imageUrl = uri.toString()

                // 将照片的 URL 更新到餐厅对象中
                val restaurant = binding.restaurant
                restaurant!!.imageURL = imageUrl

                // 更新餐厅信息
                restaurantDao.updateRestaurant(restaurant)
            }
        }.addOnFailureListener {
            progressDialog.dismiss()
            Toast.makeText(this, "照片上传失败", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onStop() {
        super.onStop()
        // 用户退出登录后，取消权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        ) {
            val uri = Uri.fromFile(File(""))
            revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        }
    }

}
