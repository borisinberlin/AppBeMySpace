package boris.com.appbemyspace.launch.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment

import boris.com.appbemyspace.R
import boris.com.appbemyspace.data.model.currentUser
import boris.com.appbemyspace.data.prefs.AppReferencesHelper
import boris.com.appbemyspace.data.prefs.AppReferencesHelperImpl
import boris.com.appbemyspace.databinding.FragmentLoginBinding



class LoginFragment : Fragment() {

    private lateinit var viewmodel: LoginViewModel
    private  lateinit var referencesHelper : AppReferencesHelper
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        viewmodel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        binding.viewmodelLogin = viewmodel
        binding.lifecycleOwner = this

       viewmodel.currentUser.observe(this, Observer<currentUser>{ currentUser->

             saveInfo(currentUser)
             goToMainPage()

        })

        return binding.root
    }


    fun saveInfo(currentUser:currentUser)
    {
        referencesHelper = AppReferencesHelperImpl(this.context!!)
        referencesHelper.saveUserName(currentUser.user.username)
        referencesHelper.saveCurrentUserToken(currentUser.user.token)
        println(referencesHelper.getCurrentUserToken())
    }

    fun goToMainPage()
    {
        val action =
            LoginFragmentDirections.actionLoginFragmentToCreateAccountFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }


}
