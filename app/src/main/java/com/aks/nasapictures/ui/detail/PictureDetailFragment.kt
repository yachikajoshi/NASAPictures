package com.aks.nasapictures.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.aks.nasapictures.R
import com.aks.nasapictures.databinding.FragmentPictureDetailBinding
import com.aks.nasapictures.ui.NASAPicturesActivity
import com.aks.nasapictures.ui.PictureListViewModel
import com.aks.nasapictures.ui.datasource.model.PictureData
import com.aks.nasapictures.ui.utils.StatusHandler
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PictureDetailFragment : Fragment() {

    private var _detailBinding: FragmentPictureDetailBinding? = null
    private val detailBinding get() = _detailBinding!!
    private var itemPosition: Int = -1
    private lateinit var listOfPictures: List<PictureData>
    private val args: PictureDetailFragmentArgs by navArgs()
    private val listViewModel: PictureListViewModel by hiltNavGraphViewModels(R.id.nasaNavGraph)
    private lateinit var detailPagerAdapter: DetailPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _detailBinding = FragmentPictureDetailBinding.inflate(layoutInflater)
        itemPosition = args.position
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeData()
        scrollListener()
    }

    private fun initViews() {
        detailPagerAdapter = DetailPagerAdapter()
        detailBinding.detailRv.adapter = detailPagerAdapter
        PagerSnapHelper().attachToRecyclerView(detailBinding.detailRv)
    }

    private fun observeData() {
        listViewModel.getListOfPictures.observe(viewLifecycleOwner) {
            when (it) {
                is StatusHandler.Error -> {
                    Snackbar.make(
                        detailBinding.root,
                        it.errorResponse.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is StatusHandler.Success -> {
                    listOfPictures = it.data
                    (activity as NASAPicturesActivity?)!!.supportActionBar!!.title =
                        listOfPictures[itemPosition].title
                    detailPagerAdapter.submitList(it.data)
                }
            }

        }
    }

    private fun scrollListener() {
        detailBinding.detailRv.scrollToPosition(itemPosition)

        detailBinding.detailRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = detailBinding.detailRv.layoutManager as LinearLayoutManager
                val lastItemPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
                if (lastItemPosition != -1) {
                    (activity as NASAPicturesActivity?)!!.supportActionBar!!.title =
                        listOfPictures[lastItemPosition].title

                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _detailBinding = null
    }

}