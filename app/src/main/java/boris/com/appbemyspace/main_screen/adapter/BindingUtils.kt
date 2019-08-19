package boris.com.appbemyspace.main_screen.listSpaces.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import boris.com.appbemyspace.convertToIntToString
import boris.com.appbemyspace.data.model.SpaceInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("setPlaceTitle")
fun TextView.setPlaceTitle(item:SpaceInfo){
    item.let {
        text = item.name
    }
}


@BindingAdapter("setPlaceAddress")
fun TextView.setPlaceAddress(item:SpaceInfo){
    item.let{
        text = item.address
    }
}

@BindingAdapter("setPlacePrice")
fun TextView.setPlacePrice(item:SpaceInfo){
    item.let {
        text = convertToIntToString(item.feeHour)
    }
}

@BindingAdapter("setPlaceImage")
@Suppress("SENSELESS_COMPARISON")
fun ImageView.setImagePics(item:SpaceInfo)
{
    item.let {
        if(item.images!=null)
         Glide.with(context).load(item.images.get(0))
             .apply(RequestOptions().override(165,120)).into(this)
    }
}