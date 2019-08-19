package boris.com.appbemyspace.main_screen.listSpaces


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

import boris.com.appbemyspace.R
import boris.com.appbemyspace.data.prefs.AppReferencesHelper
import boris.com.appbemyspace.data.prefs.AppReferencesHelperImpl
import boris.com.appbemyspace.databinding.FragmentListSpacesBinding
import boris.com.appbemyspace.main_screen.listSpaces.adapter.ListSpaceAdapter
import boris.com.appbemyspace.main_screen.listSpaces.adapter.SpaceInfoListener
import boris.com.appbemyspace.main_screen.listSpaces.viewModel.ListSpacesViewModel
import boris.com.appbemyspace.main_screen.listSpaces.viewModel.ListSpacesViewModelFactory

class ListSpacesFragment : Fragment() {


    private lateinit var viewmodel: ListSpacesViewModel
    private  lateinit var referencesHelper : AppReferencesHelper
    private lateinit var binding : FragmentListSpacesBinding

    private lateinit var token  : String

    private lateinit var adapter : ListSpaceAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      binding =DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_spaces
            , container, false)

        referencesHelper  = AppReferencesHelperImpl(this.requireContext())
       token= referencesHelper.getCurrentUserToken()!!


        val viewModelFactory = ListSpacesViewModelFactory(token)

          viewmodel =
              ViewModelProviders.of(
                  this, viewModelFactory).get(ListSpacesViewModel::class.java)


          binding.viewmodelListSpace = viewmodel
          binding.lifecycleOwner = this

         adapter = ListSpaceAdapter(SpaceInfoListener { spaceId ->
              Toast.makeText(context,"${spaceId}", Toast.LENGTH_LONG).show()
          })
          binding.spaceRcView.adapter = adapter

          viewmodel.placelist.observe(this, Observer {
              it?.let{
                  adapter.submitList(it)
              }

          })

        viewmodel.getSearchValue.observe(this, Observer { searchValue->
            val searchList = viewmodel.placelist.value!!.filter { searchValue in it.name }
            adapter.submitList(searchList)
        })


        onChangeLayoutListener()
        return binding.root
    }



    fun onChangeLayoutListener()
    {
        binding.spaceRcView.addOnScrollListener(object :RecyclerView.OnScrollListener()
        {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy<0)
                   binding.searchEditText.visibility=View.GONE
                else if(dy>0)
                    binding.searchEditText.visibility=View.VISIBLE
            }
        })
    }


}
