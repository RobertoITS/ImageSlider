package com.raqueveque.imageslider

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.raqueveque.imageslider.SliderAdapter.*

class SliderAdapter (items: MutableList<Image>, viewPager2: ViewPager2): RecyclerView.Adapter<SliderViewHolder>() {

    private val sliderItems: List<Image>
    //
    private val viewPager2: ViewPager2
    init {
        this.sliderItems = items
        //
        this.viewPager2 = viewPager2
    }



    class SliderViewHolder(item: View): RecyclerView.ViewHolder(item){
        private val image: ImageView = item.findViewById(R.id.imageSlide)

        fun image(sliderItem: Image){
            image.setImageResource(sliderItem.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_container, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.image(sliderItems[position])
        //Hasta aca la animacion:
        if (position == sliderItems.size - 2){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    //Antes de la animacion:
    @SuppressLint("NotifyDataSetChanged")
    private val runnable = Runnable {
        items.addAll(sliderItems)
        notifyDataSetChanged()
    }

}