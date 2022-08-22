package com.waka.dana.na.presentation.screen.search.filter

import com.waka.dana.na.domain.request.SearchInPath
import com.waka.dana.na.domain.usecase.SearchStorageInPath
import com.waka.dana.na.presentation.screen.search.BaseSearchViewModel

/**
 * Created by hvngoc on 8/22/22
 */
class FilterViewModel(override val useCase: SearchStorageInPath) :
    BaseSearchViewModel<SearchInPath>()