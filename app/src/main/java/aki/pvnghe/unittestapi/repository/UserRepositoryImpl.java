package aki.pvnghe.unittestapi.repository;


import java.io.IOException;
import java.util.List;

import aki.pvnghe.unittestapi.model.User;
import aki.pvnghe.unittestapi.service.GithubUserService;
import rx.Observable;

public class UserRepositoryImpl implements UserRepository {

   private GithubUserService githubUserService;

    public UserRepositoryImpl(GithubUserService githubUserService) {
        this.githubUserService = githubUserService;
    }

    @Override
    public Observable<List<User>> searchUsers(final String searchTerm) {
        return rx.Observable.defer(() -> githubUserService.searchGithubUsers(searchTerm).concatMap(
                usersList -> rx.Observable.from(usersList.getItems())
                        .concatMap(user -> githubUserService.getUser(user.getLogin())).toList()))
                .retryWhen(observable -> observable.flatMap(o -> {
                    if (o instanceof IOException) {
                        return rx.Observable.just(null);
                    }
                    return rx.Observable.error(o);
                }));
    }
}
