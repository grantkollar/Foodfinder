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
        restaurant?.let {
            dao.getImageUrl(it.restaurantID) { imageUrl ->
                Glide.with(this)
                    .load(imageUrl)
                    .centerCrop()
                    .into(binding.imageView)
            }
        }

        binding.imageView.setOnClickListener {
            if (restaurant?.ownerId == FirebaseAuthDAO.getCurrentUserId()) { // ownerId 和当前用户 ID 相同
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                ) { // permission not granted
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        PERMISSIONS_REQUEST_CODE
                    )
                } else { // permission has been granted
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
        // Create a unique filename
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmm ss", Locale.getDefault()).format(Date())
        val fileName = "JPEG_${timeStamp}_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDir)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {
            // upload photos
            uploadPhoto()
        }
    }

    private fun uploadPhoto() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("uploading photo")
        progressDialog.show()

        val storageRef = FirebaseStorage.getInstance().reference
        val photoRef = storageRef.child("images/${photoUri?.lastPathSegment}")

        val uploadTask = photoUri?.let { photoRef.putFile(it) }

        uploadTask?.addOnSuccessListener {
            progressDialog.dismiss()
            Toast.makeText(this, "Photos uploaded successfully", Toast.LENGTH_SHORT).show()

            // Get the URL of the photo and load it into ImageView
            photoRef.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(this).load(uri).into(binding.imageView)
                val imageUrl = uri.toString()

                // Update the URL of the photo into the restaurant object
                val restaurant = binding.restaurant
                restaurant?.imageURL = imageUrl
                restaurant?.let { restaurantDao.updateRestaurant(it) }

            }
        }?.addOnFailureListener {
            progressDialog.dismiss()
            Toast.makeText(this, "Photo upload failed", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onStop() {
        super.onStop()
        // After the user logs out, cancel the permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        ) {
            val uri = Uri.fromFile(File(""))
            revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        }
    }

}
