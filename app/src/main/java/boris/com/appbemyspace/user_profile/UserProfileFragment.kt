package boris.com.appbemyspace.user_profile


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import boris.com.appbemyspace.R
import boris.com.appbemyspace.data.prefs.AppReferencesHelper
import boris.com.appbemyspace.data.prefs.AppReferencesHelperImpl
import boris.com.appbemyspace.databinding.FragmentGuestUserProfileBinding
import boris.com.appbemyspace.databinding.FragmentHostUserProfileBinding

//import boris.com.appbemyspace.databinding.FragmentUserProfilBinding


class UserProfileFragment : Fragment() {

    private lateinit var viewmodel: UserProfileViewModel
    private  lateinit var referencesHelper : AppReferencesHelper
    private lateinit var bindingHost: FragmentHostUserProfileBinding
    private lateinit var bindingGuest: FragmentGuestUserProfileBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        referencesHelper  = AppReferencesHelperImpl(this.requireContext())


        val viewModelFactory = UserProfileViewModelFactory(referencesHelper.getCurrentUserToken()!!,referencesHelper.getUsername()!!)

        viewmodel =
            ViewModelProviders.of(
                this, viewModelFactory).get(UserProfileViewModel::class.java)


        if (referencesHelper.getUserState()) {
            bindingHost = DataBindingUtil.inflate(
                inflater, R.layout.fragment_host_user_profile, container, false
            )
            bindingHost.viewmodelUser = viewmodel
            bindingHost.lifecycleOwner = this
            return bindingHost.root
        } else {

            bindingGuest = DataBindingUtil.inflate(
                inflater, R.layout.fragment_guest_user_profile, container, false
            )
            bindingGuest.viewmodelUser = viewmodel
            bindingGuest.lifecycleOwner = this

            bindingGuest.upgradeUserTextview.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_userProfileFragment_to_upgradeUserFragment)
            )

            return bindingGuest.root
        }


    }


}
