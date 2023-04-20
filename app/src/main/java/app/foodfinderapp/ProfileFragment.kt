package app.foodfinderapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.foodfinderapp.activity.LocationActivity
import app.foodfinderapp.dao.FirebaseAuthDAO
import app.foodfinderapp.ui.*
import app.foodfinderapp.databinding.FragmentProfileBinding
import app.foodfinderapp.ui.MeAsActivity
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Account Security
        binding.layoutMePra.setOnClickListener {
            val intent = Intent(Application.context, MeAsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        //My Address
        binding.layoutMyAddress.setOnClickListener{
            val intent = Intent(Application.context, LocationActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        //Log out
        binding.layoutLogout.setOnClickListener{
            FirebaseAuthDAO.signOut()
            val pers = activity?.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            pers?.edit()?.clear()?.apply()
            LoginData.LOGIN_STATUS = 0
            val intent = Intent(Application.context, LoginActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onResume() {
        super.onResume()
        if (LoginData.LOGIN_STATUS == 1) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                binding.textMeWords.text = currentUser.email
            }
            binding.loginOutCard.visibility = View.VISIBLE
        } else {
            binding.textMeWords.text = "Not logged in."
            binding.textMeWords.setOnClickListener {
                val intent = Intent(Application.context, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}