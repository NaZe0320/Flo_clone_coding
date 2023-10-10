package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.flo.databinding.FragmentAlbumBinding

class AlbumFragment : Fragment() {

    lateinit var binding: FragmentAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumBinding.inflate(inflater,container,false)

        binding.albumBackIv.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
        }

        binding.songLalacLayout.setOnClickListener {
            makeToast( "LILAC")
        }

        binding.songFluLayout.setOnClickListener {
            makeToast("FLU")
        }

        binding.songCoinLayout.setOnClickListener {
            makeToast("Coin")
        }

        binding.songSpringLayout.setOnClickListener {
            makeToast("봄 안녕 봄")
        }

        binding.songCelebrityLayout.setOnClickListener {
            makeToast("Celebrity")
        }

        binding.songSingLayout.setOnClickListener {
            makeToast("돌림노래(Feat. DEAN")
        }

        return binding.root
    }

    private fun makeToast(title: String) {
        Toast.makeText(activity,title, Toast.LENGTH_SHORT).show()
    }
}