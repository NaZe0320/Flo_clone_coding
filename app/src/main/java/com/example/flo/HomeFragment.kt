package com.example.flo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val panelHandler = Handler(Looper.getMainLooper())
    private val panelRunnable = Runnable {
        binding.vpPanelBanner.currentItem = binding.vpPanelBanner.currentItem + 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeAlbumImgIv1.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm , AlbumFragment())
                .commitAllowingStateLoss()
        }

        setPanel()

        val bannerAdapter = BannerViewPagerAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        binding.vpBanner.adapter = bannerAdapter
        binding.vpBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }

    private fun setPanel() {
        val panelAdapter = PanelViewPagerAdapter(this)
        panelAdapter.addFragment(BannerFragment(R.drawable.img_first_album_default))
        panelAdapter.addFragment(BannerFragment(R.drawable.img_album_exp3))
        panelAdapter.addFragment(BannerFragment(R.drawable.img_album_exp4))

        binding.vpPanelBanner.apply {
            adapter = panelAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            offscreenPageLimit = 1
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    panelHandler.removeCallbacks(panelRunnable)
                    panelHandler.postDelayed(panelRunnable, 3000)
                }
            })
        }

        binding.indicatorPanel.setViewPager(binding.vpPanelBanner)
        panelAdapter.registerAdapterDataObserver(binding.indicatorPanel.adapterDataObserver);

    }

    override fun onResume() {
        super.onResume()
        panelHandler.postDelayed(panelRunnable, 3000)
    }

    override fun onPause() {
        super.onPause()
        panelHandler.removeCallbacks(panelRunnable)
    }
}