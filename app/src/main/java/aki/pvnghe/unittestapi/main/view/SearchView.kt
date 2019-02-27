package aki.pvnghe.unittestapi.main.view

import aki.pvnghe.unittestapi.model.User

interface SearchView : BaseView {
    fun showSearchResults(githubUserList: List<User>)
    fun showError(message: String?)
    fun showLoading()
    fun hideLoading()
}