package boris.com.appbemyspace.upgrade_user


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import boris.com.appbemyspace.R
import boris.com.appbemyspace.data.prefs.AppReferencesHelper
import boris.com.appbemyspace.data.prefs.AppReferencesHelperImpl
import boris.com.appbemyspace.databinding.FragmentUpgradeUserBinding
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import java.util.*


class UpgradeUserFragment : Fragment() {

    private lateinit var viewmodel: UpgradeUserViewModel
    private lateinit var referencesHelper: AppReferencesHelper
    private lateinit var binding: FragmentUpgradeUserBinding


    val placesFields = Arrays.asList(
        Place.Field.ID,
        Place.Field.NAME,
        Place.Field.ADDRESS
    )

    lateinit var placesClient: PlacesClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_upgrade_user,
            container,
            false
        )

        //   viewmodel = ViewModelProviders.of(this).get(UpgradeUserViewModel::class.java)
        referencesHelper = AppReferencesHelperImpl(this.context!!)


        initPlaces()
        setupPlacesAutoComplete()

        return binding.root
    }


    private fun initPlaces() {
        Places.initialize(context!!, getString(R.string.google_api))
        placesClient = Places.createClient(context!!)
    }

    private fun setupPlacesAutoComplete() {

        val autocompleteFragment: AutocompleteSupportFragment? =
            childFragmentManager.findFragmentById(R.id.google_autocomplete_place) as AutocompleteSupportFragment?
        autocompleteFragment!!.setPlaceFields(placesFields)

        autocompleteFragment.run {
            setPlaceFields(placesFields)

            setOnPlaceSelectedListener(object : PlaceSelectionListener {
                override fun onPlaceSelected(place: Place) {
                    Toast.makeText(context, place.address.toString(), Toast.LENGTH_LONG)
                }

                override fun onError(status: Status) {
                    Toast.makeText(context, status.toString(), Toast.LENGTH_LONG)
                }

            })
        }
    }


}
