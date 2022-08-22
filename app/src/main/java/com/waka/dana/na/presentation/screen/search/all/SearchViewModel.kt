package com.waka.dana.na.presentation.screen.search.all

import com.waka.dana.na.domain.usecase.SearchStorageByName
import com.waka.dana.na.presentation.screen.search.BaseSearchViewModel

/**
 * Created by hvngoc on 8/22/22
 */
class SearchViewModel(override val useCase: SearchStorageByName) : BaseSearchViewModel<String>()