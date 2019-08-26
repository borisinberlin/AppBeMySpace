package boris.com.appbemyspace

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import boris.com.appbemyspace.databinding.ActivityMainBinding


@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {


    /* val placesFields = Arrays.asList(
         Place.Field.ID,
         Place.Field.NAME,
         Place.Field.ADDRESS)

     lateinit var placesClient : PlacesClient*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )


        /* initPlaces()
         setupPlacesAutoComplete()*/
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val view = findViewById(R.id.myNavHostFragment) as View


        if (view.findNavController().currentDestination!!.label != "UserProfileFragment") {
            return NavigationUI.onNavDestinationSelected(
                item!!,
                view.findNavController()
            ) || super.onOptionsItemSelected(item)
        } else
            return false


    }


}
