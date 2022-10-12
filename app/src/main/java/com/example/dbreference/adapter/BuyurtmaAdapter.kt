package com.example.dbreference.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dbreference.databinding.ItemSotuvRvBinding
import com.example.dbreference.models.Buyurtma


class BuyurtmaAdapter (var list: List<Buyurtma>) : RecyclerView.Adapter<BuyurtmaAdapter.VP_Vh>() {

    inner class VP_Vh(var itemListBinding: ItemSotuvRvBinding   ):
        RecyclerView.ViewHolder(itemListBinding.root) {
        fun onBind(buyurtma: Buyurtma) {

            itemListBinding.tvName.text=buyurtma.name
            itemListBinding.tvNumber.text=buyurtma.price.toString()
            itemListBinding.tvAddress.visibility=View.VISIBLE
            itemListBinding.tvAddress.text=buyurtma.sotuvchi?.name
            itemListBinding.tvXaridor.visibility=View.VISIBLE
            itemListBinding.tvXaridor.text=buyurtma.xaridor?.name


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VP_Vh {
        return VP_Vh(ItemSotuvRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    }

    override fun onBindViewHolder(holder: VP_Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size



}