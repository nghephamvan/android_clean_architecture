package aki.pvnghe.unittestapi.users.di.component

import aki.pvnghe.unittestapi.di.component.AppComponent
import aki.pvnghe.unittestapi.users.UsersFragment
import aki.pvnghe.unittestapi.users.di.module.FragmentModule
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [FragmentModule::class])
interface UsersFragmentComponent {
    fun inject(usersFragment: UsersFragment)
}