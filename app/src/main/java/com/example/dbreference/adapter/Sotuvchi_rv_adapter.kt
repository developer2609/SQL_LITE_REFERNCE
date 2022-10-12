package com.example.dbreference.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dbreference.databinding.ItemSotuvRvBinding
import com.example.dbreference.models.Sotuvchi
import com.example.dbreference.models.Xaridor


class Sotuvchi_rv_adapter<T> (var list: List<T>) : RecyclerView.Adapter<Sotuvchi_rv_adapter<T>.VP_Vh>() {

    inner class VP_Vh(var itemSotuvRvBinding: ItemSotuvRvBinding):
        RecyclerView.ViewHolder(itemSotuvRvBinding.root) {
        fun onBindSotuvchi(sotuvchi: Sotuvchi, position: Int) {

            itemSotuvRvBinding.tvName.text=sotuvchi.name
            itemSotuvRvBinding.tvNumber.text=sotuvchi.number


        }
        fun onBindXaridor(xaridor: Xaridor, position: Int) {

            itemSotuvRvBinding.tvName.text=xaridor.name
            itemSotuvRvBinding.tvNumber.text=xaridor.number
            itemSotuvRvBinding.tvAddress.visibility=View.VISIBLE
            itemSotuvRvBinding.tvAddress.text=xaridor.address


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VP_Vh {
        return VP_Vh(ItemSotuvRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    }

    override fun onBindViewHolder(holder: VP_Vh, position: Int) {
        try {

            val sotuvchi:Sotuvchi=list[position] as Sotuvchi
             holder.onBindSotuvchi(sotuvchi,position)

        }catch (e:Exception){


        }
        try {
           val xaridor:Xaridor=list[position] as Xaridor
            holder.onBindXaridor(xaridor,position)
        }catch (e:Exception){}

    }



    override fun getItemCount(): Int = list.size



}