package boris.com.appbemyspace.launch.welcome


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import boris.com.appbemyspace.R
import boris.com.appbemyspace.data.prefs.AppReferencesHelper
import boris.com.appbemyspace.data.prefs.AppReferencesHelperImpl
import boris.com.appbemyspace.databinding.FragmentWelcomeBinding
import boris.com.appbemyspace.launch.login.LoginFragmentDirections
import boris.com.appbemyspace.launch_viewmodel.WelcomeViewModel


class WelcomeFragment : Fragment() {

    private lateinit var viewmodel: WelcomeViewModel
    private  lateinit var referencesHelper : AppReferencesHelper
    private lateinit var binding : FragmentWelcomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome,
            container,
            false)

        viewmodel = ViewModelProviders.of(this).get(WelcomeViewModel::class.java)
        referencesHelper = AppReferencesHelperImpl(this.context!!)

        checkUser()


        binding.createAccountBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_loginFragment)
        )

        binding.loginTextview2.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_loginFragment)
        )

       /*binding.viewmodelWelcome = viewmodel
        binding.lifecycleOwner = this

       viewmodel.eventGoToLogin.observe(this, Observer<Boolean> {isClickLogin ->

            if(isClickLogin)
                login_textview_2.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_loginFragment))
        })

       viewmodel.eventGoToSignIn.observe(this, Observer<Boolean> {isClickSignIn ->

            if(isClickSignIn)
            {
                create_account_btn.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_loginFragment))

            }

        })*/

        return binding.root
    }

    fun checkUser()
    {
        if(referencesHelper.getUsername()!=null)
        {
            println(referencesHelper.getCurrentUserToken())
            val action =
               WelcomeFragmentDirections.actionWelcomeFragmentToListSpacesFragment()
              // WelcomeFragmentDirections.actionWelcomeFragmentToUserProfileFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }


}
