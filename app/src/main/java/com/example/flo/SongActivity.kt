package com.example.flo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding
import com.example.model.Song

class SongActivity: AppCompatActivity() {
    lateinit var binding : ActivitySongBinding
    lateinit var song : Song
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSong()
        setPlayer(song)


        binding.btnSongDown.setOnClickListener {
            finish()
        }

        binding.ivSongMiniplayer.setOnClickListener {
            setPlayerStatus(true)
        }

        binding.ivSongPause.setOnClickListener {
            setPlayerStatus(false)
        }
    }

    private fun setPlayerStatus (isPlaying : Boolean){
        Log.d("SongTest","$isPlaying")
        song.isPlaying = isPlaying
        timer.isPlaying = isPlaying

        if(isPlaying){
            binding.ivSongMiniplayer.visibility = View.GONE
            binding.ivSongPause.visibility = View.VISIBLE
        } else {
            binding.ivSongMiniplayer.visibility = View.VISIBLE
            binding.ivSongPause.visibility = View.GONE
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
    }

    private fun initSong(){
        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            song = Song(
                intent.getStringExtra("title")!!,
                intent.getStringExtra("singer")!!,
                intent.getIntExtra("second",0),
                intent.getIntExtra("playTime",0),
                intent.getBooleanExtra("isPlaying",true)
            )
        }
        startTimer()
    }

    private fun setPlayer(song: Song){
        binding.tvSongMusicTitle.text = intent.getStringExtra("title")!!
        binding.tvSongSingerName.text = intent.getStringExtra("singer")!!
        binding.songStartTimeTv.text = String.format("%02d:%02d",song.second / 60, song.second % 60)
        binding.songEndTimeTv.text = String.format("%02d:%02d",song.playTime / 60, song.playTime % 60)
        binding.songProgressSb.progress = if(song.playTime!=0) (song.second * 1000 / song.playTime) else 0

        setPlayerStatus(song.isPlaying)
    }

    private fun startTimer(){
        timer = Timer(song.playTime,song.isPlaying)
        timer.start()
    }

    inner class Timer(private val playTime: Int,var isPlaying: Boolean = true):Thread(){

        private var second : Int = 0
        private var mills: Float = 0f

        override fun run() {
            super.run()
            try {
                while (true){
                    if (second >= playTime){ break }
                    if (isPlaying){
                        sleep(50)
                        mills += 50
                        runOnUiThread {
                            binding.songProgressSb.progress = ((mills / playTime)*100).toInt()
                        }
                        if (mills % 1000 == 0f){
                            runOnUiThread {
                                binding.songStartTimeTv.text = String.format("%02d:%02d",second / 60, second % 60)
                            }
                            second++
                        }
                    }
                }
            }catch (e: InterruptedException){
                Log.d("SongTest","Thread Dead, ${e.message}")
            }
        }
    }
}
