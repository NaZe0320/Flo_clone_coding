package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.HomeAdapter
import com.example.flo.databinding.FragmentHomeBinding
import com.example.model.ItemData

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val albumAdapter = HomeAdapter(1)
        val potCastAdapter = HomeAdapter(2)
        val videoAdapter = HomeAdapter(3)

        binding.rvHomeMusic.adapter = albumAdapter
        binding.rvHomePotcast.adapter = potCastAdapter
        binding.rvHomeVideo.adapter = videoAdapter

        return binding.root
    }
}