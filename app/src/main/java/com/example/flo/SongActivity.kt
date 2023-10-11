package com.example.flo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding

class SongActivity: AppCompatActivity() {
    lateinit var binding : ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            binding.tvSongMusicTitle.text = intent.getStringExtra("title")
            binding.tvSongSingerName.text = intent.getStringExtra("singer")
        }

        binding.btnSongDown.setOnClickListener {
            finish()
        }

        binding.ivSongMiniplayer.setOnClickListener {
            setPlayerStatus(false)
        }

        binding.ivSongPause.setOnClickListener {
            setPlayerStatus(true)
        }


    }

    private fun setPlayerStatus (isPlaying : Boolean){
        if(isPlaying){
            binding.ivSongMiniplayer.visibility = View.VISIBLE
            binding.ivSongPause.visibility = View.GONE
        } else {
            binding.ivSongMiniplayer.visibility = View.GONE
            binding.ivSongPause.visibility = View.VISIBLE
        }
    }

}
