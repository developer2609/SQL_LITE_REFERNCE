package com.example.dbreference.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dbreference.adapter.Sotuvchi_rv_adapter
import com.example.dbreference.databinding.FragmentXaridBinding
import com.example.dbreference.databinding.ItemDialogBinding
import com.example.dbreference.db.MyDbHelper
import com.example.dbreference.models.Xaridor

class XaridFragment : Fragment() {
    private lateinit var binding: FragmentXaridBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list: ArrayList<Xaridor>
    private lateinit var sotuvchiRvAdapter: Sotuvchi_rv_adapter<Xaridor>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentXaridBinding.inflate(layoutInflater)
        myDbHelper= MyDbHelper(context)
        list= ArrayList()
        list.addAll(myDbHelper.getAllCustomer())
        sotuvchiRvAdapter= Sotuvchi_rv_adapter(list)
         binding.rv.adapter=sotuvchiRvAdapter


        binding.buttonAdd.setOnClickListener{

            val dialog= AlertDialog.Builder(binding.root.context).create()
            val itemDialogBinding=ItemDialogBinding.inflate(layoutInflater)
            itemDialogBinding.edtAddress.visibility=View.VISIBLE
            dialog.setView(itemDialogBinding.root)
            dialog.show()

            itemDialogBinding.btnSave.setOnClickListener {
                val xaridor=Xaridor(itemDialogBinding.edtName.text.toString(),itemDialogBinding.edtNumber.text.toString(),itemDialogBinding.edtAddress.text.toString())
                myDbHelper.addCustomer(xaridor)
                list.add(xaridor)
                sotuvchiRvAdapter.notifyItemInserted(list.size-1)



                Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
        }






        return binding.root

    }


}