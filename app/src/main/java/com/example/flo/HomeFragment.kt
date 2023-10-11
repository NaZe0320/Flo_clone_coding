package com.example.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.flo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

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

        val panelAdapter = BannerViewPagerAdapter(this)
        panelAdapter.addFragment(BannerFragment(R.drawable.img_first_album_default))
        panelAdapter.addFragment(BannerFragment(R.drawable.img_album_exp3))
        panelAdapter.addFragment(BannerFragment(R.drawable.img_album_exp4))

        binding.vpPanelBanner.adapter = panelAdapter
        binding.vpPanelBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.indicatorPanel.setViewPager(binding.vpPanelBanner)
        panelAdapter.registerAdapterDataObserver(binding.indicatorPanel.adapterDataObserver);

        val bannerAdapter = BannerViewPagerAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        binding.vpBanner.adapter = bannerAdapter
        binding.vpBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }
}