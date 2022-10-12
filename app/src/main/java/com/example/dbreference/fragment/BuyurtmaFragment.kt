package com.example.dbreference.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dbreference.R
import com.example.dbreference.adapter.BuyurtmaAdapter
import com.example.dbreference.databinding.FragmentBuyurtmaBinding
import com.example.dbreference.databinding.ItemDialog0rderBinding
import com.example.dbreference.db.MyDbHelper
import com.example.dbreference.models.Buyurtma

class BuyurtmaFragment : Fragment() {
    private lateinit var binding: FragmentBuyurtmaBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list: ArrayList<Buyurtma>
    private lateinit var buyurtmaAdapter: BuyurtmaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentBuyurtmaBinding.inflate(layoutInflater)

        myDbHelper= MyDbHelper(context)
        list= ArrayList()
        list.addAll(myDbHelper.getAllOrders())
        buyurtmaAdapter= BuyurtmaAdapter(list)
        binding.rvBuyurtma.adapter=buyurtmaAdapter

        addBuyurtma()


   return binding.root
    }

    private fun addBuyurtma() {
       binding.btnAddbuyurtma.setOnClickListener {
           val dialog=AlertDialog.Builder(binding.root.context).create()
           val itemDialog0rderBinding=ItemDialog0rderBinding.inflate(layoutInflater)
           dialog.setView(itemDialog0rderBinding.root)
           dialog.show()


           val listSotuvchi=myDbHelper.getAllSalesman()
           val listSotuvchiName=ArrayList<String>()
             listSotuvchi.forEach{
                 listSotuvchiName.add(it.name)
             }

             val sAdapter=ArrayAdapter<String>(binding.root.context,android.R.layout.simple_list_item_1,listSotuvchiName )
               itemDialog0rderBinding.spinner1.adapter=sAdapter

           val listXaridor=myDbHelper.getAllCustomer()
           val listXaridorName=ArrayList<String>()
           listXaridor.forEach{
               listXaridorName.add(it.name)
           }

           val xAdapter=ArrayAdapter<String>(binding.root.context,android.R.layout.simple_list_item_1,listXaridorName)
           itemDialog0rderBinding.spinner2.adapter=xAdapter

           itemDialog0rderBinding.buttunSave.setOnClickListener {

               val buyurtma=Buyurtma(

                   itemDialog0rderBinding.edtName.text.toString(),
                   itemDialog0rderBinding.edtPrice.text.toString().toInt(),
                   listSotuvchi[itemDialog0rderBinding.spinner1.selectedItemPosition],
                   listXaridor[itemDialog0rderBinding.spinner2.selectedItemPosition]

               )

               myDbHelper.addOlder(buyurtma)
               list.add(buyurtma)
               buyurtmaAdapter.notifyItemInserted(list.size-1)
               Toast.makeText(context, "save", Toast.LENGTH_SHORT).show()

           }


       }
    }

}