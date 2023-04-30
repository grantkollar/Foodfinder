package app.foodfinderapp

import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapFragment : Fragment() {

    private var address: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync { googleMap ->
            address = arguments?.getString("address")
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            val addresses = address?.let { geocoder.getFromLocationName(it, 1) }
            if (addresses != null) {
                if (addresses.isNotEmpty()) {
                    val latLng = LatLng(addresses[0].latitude, addresses[0].longitude)
                    googleMap.addMarker(MarkerOptions().position(latLng).title(address))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                }
            }
        }
    }

    fun updateLocation(address: String) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync { googleMap ->
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            val addresses = geocoder.getFromLocationName(address, 1)
            if (addresses != null) {
                if (addresses.isNotEmpty()) {
                    val latLng = LatLng(addresses[0].latitude, addresses[0].longitude)
                    googleMap.clear()
                    googleMap.addMarker(MarkerOptions().position(latLng).title(address))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                }
            }
        }
    }



    companion object {
        fun newInstance(address: String): MapFragment {
            val args = Bundle()
            args.putString("address", address)
            val fragment = MapFragment()
            fragment.arguments = args
            return fragment
        }
    }
}