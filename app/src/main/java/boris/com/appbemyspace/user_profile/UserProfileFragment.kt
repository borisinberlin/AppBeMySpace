package boris.com.appbemyspace.user_profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import boris.com.appbemyspace.R
import boris.com.appbemyspace.data.model.UserInfo
import boris.com.appbemyspace.data.model.currentUser
import boris.com.appbemyspace.data.prefs.AppReferencesHelper
import boris.com.appbemyspace.data.prefs.AppReferencesHelperImpl
import boris.com.appbemyspace.databinding.FragmentListSpacesBinding
import boris.com.appbemyspace.databinding.FragmentUserProfilBinding


class UserProfileFragment : Fragment() {

    private lateinit var viewmodel: UserProfileViewModel
    private  lateinit var referencesHelper : AppReferencesHelper
    private lateinit var binding : FragmentUserProfilBinding

   // private lateinit var token  : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_profil, container, false)

        // val application = requireNotNull(this.activity).application
        referencesHelper  = AppReferencesHelperImpl(this.requireContext())
        //token= referencesHelper.getCurrentUserToken()!!


        val viewModelFactory = UserProfileViewModelFactory(referencesHelper.getCurrentUserToken()!!,referencesHelper.getUsername()!!)

        // Get a reference to the ViewModel associated with this fragment.
        viewmodel =
            ViewModelProviders.of(
                this, viewModelFactory).get(UserProfileViewModel::class.java)


        binding.viewmodelUser = viewmodel
        binding.lifecycleOwner = this

      /* viewmodel.userInfo.observe(this, Observer<UserInfo>{ currentUser->


       })*/
        return binding.root
    }


}
