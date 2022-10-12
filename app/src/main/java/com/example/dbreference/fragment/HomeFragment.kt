package com.example.dbreference.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dbreference.R
import com.example.dbreference.databinding.FragmentHomeBinding
import com.example.dbreference.databinding.FragmentSotuvBinding
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater)

        binding.buttonSotish.setOnClickListener {
            findNavController().navigate(R.id.sotuvFragment)
        }
        binding.buttonXaridor.setOnClickListener{
            findNavController().navigate(R.id.xaridFragment)
        }

        binding.buttonBuyurtma.setOnClickListener{

            findNavController().navigate(R.id.buyurtmaFragment)
        }
        return binding.root

    }

}