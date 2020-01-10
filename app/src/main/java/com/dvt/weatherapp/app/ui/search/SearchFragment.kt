package com.dvt.weatherapp.app.ui.search

import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dvt.weatherapp.app.R
import com.dvt.weatherapp.app.base.BaseFragment
import com.dvt.weatherapp.app.databinding.FragmentSearchBinding
import com.dvt.weatherapp.app.domain.repo.entity.CitiesForSearchEntity
import com.dvt.weatherapp.app.di.Injectable
import com.dvt.weatherapp.app.domain.usecase.SearchCitiesUseCase
import com.dvt.weatherapp.app.ui.main.MainActivity
import com.dvt.weatherapp.app.ui.search.result.SearchResultAdapter
import com.dvt.weatherapp.app.utils.extensions.hideKeyboard
import com.dvt.weatherapp.app.utils.extensions.tryCatch

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(SearchViewModel::class.java), Injectable {

    override fun getLayoutRes(): Int = R.layout.fragment_search

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun init() {
        super.init()
        initSearchResultsAdapter()
        initSearchView()

        viewModel.getSearchViewState().observe(
            viewLifecycleOwner,
            Observer {
                mBinding.viewState = it
                it.data?.let { results -> initSearchResultsRecyclerView(results) }
            }
        )
    }

    private fun initSearchView() {
        val searchEditText: EditText = mBinding.searchView.findViewById(R.id.search_src_text)
        activity?.applicationContext?.let { ContextCompat.getColor(it, R.color.mainTextColor) }
            ?.let { searchEditText.setTextColor(it) }
        activity?.applicationContext?.let { ContextCompat.getColor(it, android.R.color.darker_gray) }
            ?.let { searchEditText.setHintTextColor(it) }
        mBinding.searchView.isActivated = true
        mBinding.searchView.setIconifiedByDefault(false)
        mBinding.searchView.isIconified = false

        val searchViewSearchIcon = mBinding.searchView.findViewById<ImageView>(R.id.search_mag_icon)
        searchViewSearchIcon.setImageResource(R.drawable.ic_search)

        mBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String): Boolean {
                if (newText.isNotEmpty() && newText.count() > 2) {
                    viewModel.useCaseParams.postValue(SearchCitiesUseCase.SearchCitiesParams(newText))
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isNotEmpty() == true && newText.count() > 2) {
                    viewModel.useCaseParams.postValue(SearchCitiesUseCase.SearchCitiesParams(newText))
                }
                return true
            }
        })
    }

    private fun initSearchResultsAdapter() {
        val adapter = SearchResultAdapter { item ->
            item.coord?.let {
                viewModel.saveCoordsToSharedPref(it)?.subscribe { t1, t2 ->

                    tryCatch(
                        tryBlock = {
                            mBinding.searchView.hideKeyboard((activity as MainActivity))
                        }
                    )

                    findNavController().navigate(R.id.action_searchFragment_to_dashboardFragment)
                }
            }
        }

        mBinding.recyclerViewSearchResults.adapter = adapter
        mBinding.recyclerViewSearchResults.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun initSearchResultsRecyclerView(list: List<CitiesForSearchEntity>) {
        (mBinding.recyclerViewSearchResults.adapter as SearchResultAdapter).submitList(list.distinctBy { it.getFullName() }.sortedBy { it.importance })
    }
}
