package aki.pvnghe.unittestapi.service;


import aki.pvnghe.unittestapi.model.User;
import aki.pvnghe.unittestapi.model.UsersList;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface GithubUserService {

    @GET("/search/users?per_page=2")
    Observable<UsersList> searchGithubUsers(@Query("q") String searchTerm);

    @GET("/users/{username}")
    Observable<User> getUser(@Path("username") String username);
}
