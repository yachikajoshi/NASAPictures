package com.aks.nasapictures.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.aks.nasapictures.R
import com.aks.nasapictures.databinding.FragmentPictureListBinding
import com.aks.nasapictures.ui.PictureListViewModel
import com.aks.nasapictures.ui.utils.StatusHandler
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PicturesList : Fragment(){

    private var _pictureListBinding: FragmentPictureListBinding? = null
    private val binding get() = _pictureListBinding!!
    private val listViewModel: PictureListViewModel by hiltNavGraphViewModels(R.id.nasaNavGraph)
    private lateinit var pictureAdapter: PictureAdapter


    private fun onItemClick(position: Int) {
        findNavController().navigate(
            PicturesListDirections.actionNasaPicturesDashboardToPictureDetail(
                position
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _pictureListBinding = FragmentPictureListBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeData()
    }

    private fun initViews() {
        pictureAdapter = PictureAdapter(::onItemClick)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.picturesRv.layoutManager = layoutManager
        binding.picturesRv.itemAnimator = DefaultItemAnimator()
        binding.picturesRv.adapter = pictureAdapter
        binding.picturesRv.setHasFixedSize(true)
    }

    private fun observeData() {
        listViewModel.sendRequestToGetPictureList()

        listViewModel.getListOfPictures.observe(viewLifecycleOwner) {
            when (it) {
                is StatusHandler.Error -> {
                    Snackbar.make(
                        binding.root,
                        it.errorResponse.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is StatusHandler.Success -> {
                    pictureAdapter.submitList(it.data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _pictureListBinding = null
    }
}