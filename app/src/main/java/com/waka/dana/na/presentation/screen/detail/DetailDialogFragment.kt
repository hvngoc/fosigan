package com.waka.dana.na.presentation.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.waka.dana.na.R
import com.waka.dana.na.databinding.FragmentDetailBinding
import com.waka.dana.na.presentation.base.FullWindowDialogFragment

/**
 * Created by hvngoc on 8/22/22
 */
class DetailDialogFragment : FullWindowDialogFragment() {

    companion object {
        const val TAG = "FRAGMENT_DETAIL"
        fun newInstance(): DetailDialogFragment {
            return DetailDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }
}