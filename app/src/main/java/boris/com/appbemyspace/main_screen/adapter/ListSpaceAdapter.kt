package boris.com.appbemyspace.main_screen.listSpaces.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import boris.com.appbemyspace.data.model.SpaceInfo
import boris.com.appbemyspace.databinding.SpaceItemLayoutBinding
import java.io.FileNotFoundException

class ListSpaceAdapter (val clikListener : SpaceInfoListener ): ListAdapter<SpaceInfo, ListSpaceAdapter.SpaceViewHolder>(
    ListSpacesDiffCallBack()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpaceViewHolder {
        return SpaceViewHolder.from(
            parent
        )
    }


    override fun onBindViewHolder(holder: SpaceViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clikListener,item)
    }

       class SpaceViewHolder private constructor(val binding: SpaceItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(clickListener : SpaceInfoListener, item: SpaceInfo) {
                binding.spaceInfo = item
                binding.clickListener =clickListener
                binding.executePendingBindings()

            /*binding.placeTitle.text = item.name
            binding.placeAddress.text = item.address
            binding.priceText.text = item.feeHour.toString()*/

        }

        companion object {
            fun from(parent: ViewGroup): SpaceViewHolder {
                val layoutInflate = LayoutInflater.from(parent.context)
                val binding = SpaceItemLayoutBinding.inflate(layoutInflate,parent,false)
                return SpaceViewHolder(binding)
            }
        }
    }

}

class ListSpacesDiffCallBack : DiffUtil.ItemCallback<SpaceInfo>()
{
    override fun areItemsTheSame(oldItem: SpaceInfo, newItem: SpaceInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SpaceInfo, newItem: SpaceInfo): Boolean {
        return oldItem == newItem
    }
}

class SpaceInfoListener(val clickListener : (spaceId: Int) -> Unit){

    fun onClick(space:SpaceInfo) = clickListener(space.id)
}