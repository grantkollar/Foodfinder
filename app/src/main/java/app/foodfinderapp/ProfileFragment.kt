package app.foodfinderapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.foodfinderapp.databinding.FragmentProfileBinding
import app.foodfinderapp.ui.viewModel.UserViewModel
import app.foodfinderapp.MeAsActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        val viewModel by lazy { ViewModelProvider(this).get(UserViewModel::class.java) }
        if (LoginData.LOGIN_STATUS == 0) {
            binding.loginOutCard.visibility = View.GONE
        }

        //Account Security
        binding.layoutMePra.setOnClickListener {
            val intent = Intent(Application.context, MeAsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        //Privacy
        binding.layoutPrivacy.setOnClickListener {
            Toast.makeText(context, "None", Toast.LENGTH_SHORT).show()
        }

        //My Address
        binding.layoutMyAddress.setOnClickListener {
            if (LoginData.LOGIN_STATUS == 1) {
                val Token =
                    activity?.getSharedPreferences("userInfo", AppCompatActivity.MODE_PRIVATE)
                val token = Token?.getString("token", "null")
                Log.d("token", token.toString())
                if (token != null) {
                    viewModel.resultLocation(token)
                }

            }
        }

        //Feedback
        binding.layoutEvaluate.setOnClickListener {
            val intent = Intent(Application.context, FeedbackActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        //Coupon
        binding.layoutMeCoupon.setOnClickListener {
            val intent = Intent(Application.context, MyCouponActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        //Personal Center
        binding.cardMeData.setOnClickListener {
            if (LoginData.LOGIN_STATUS == 0) {
                val intent = Intent(Application.context, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(context, "Already login.", Toast.LENGTH_SHORT).show()
            }
        }

        //About
        binding.layoutMeAbout.setOnClickListener {
            val intent = Intent(Application.context, MeAsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        //order
        binding.layoutMyOrders.setOnClickListener {
            val intent = Intent(Application.context, MeOrderActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        //Evaluation Center

        //Log out

    }

    override fun onResume() {
        super.onResume()
        LoginData.TURN_MAIN = 0
        if (LoginData.LOGIN_STATUS == 1) {
            val pers = activity?.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            binding.textMeWords.text = pers?.getString("userName", "Not Log in")
            binding.loginOutCard.visibility = View.VISIBLE
        } else {
            binding.textMeWords.text = "Not Log in"
            binding.loginOutCard.visibility = View.GONE

        }
    }
}