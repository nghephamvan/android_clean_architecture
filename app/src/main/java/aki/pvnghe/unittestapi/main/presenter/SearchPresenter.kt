package aki.pvnghe.unittestapi.main.presenter

import aki.pvnghe.unittestapi.main.view.SearchView

interface SearchPresenter : Presenter<SearchView> {
    fun onSearch(searchTerm: String)
}