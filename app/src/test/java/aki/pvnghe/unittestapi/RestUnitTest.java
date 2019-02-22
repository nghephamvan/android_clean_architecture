package aki.pvnghe.unittestapi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aki.pvnghe.unittestapi.model.User;
import aki.pvnghe.unittestapi.model.UsersList;
import aki.pvnghe.unittestapi.repository.UserRepository;
import aki.pvnghe.unittestapi.repository.UserRepositoryImpl;
import aki.pvnghe.unittestapi.service.GithubUserService;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
import rx.Observable;
import rx.observers.TestSubscriber;

public class RestUnitTest {

    private static final String USER_LOGIN_NGHEPHAMVAN = "nghephamvan";
    private static final String USER_LOGIN_2_NGHEPHAM = "nghepham97";

    @Mock
    GithubUserService githubUserService;

    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userRepository = new UserRepositoryImpl(githubUserService);
    }

    @Test
    public void searchUsers_200OkResponse_InvokesCorrectApiCalls() {
        //Given
        when(githubUserService.searchGithubUsers(anyString())).thenReturn(Observable.just(githubUserList()));
        when(githubUserService.getUser(anyString()))
                .thenReturn(Observable.just(user1FullDetails()), Observable.just(user2FullDetails()));

        //When
        TestSubscriber<List<User>> observer = new TestSubscriber<>();
        userRepository.searchUsers(USER_LOGIN_NGHEPHAMVAN).subscribe(observer);


        //Then
        observer.awaitTerminalEvent();
        observer.assertNoErrors();
        List<List<User>> events = observer.getOnNextEvents();
        List<User> users = events.get(0);

        Assert.assertEquals(USER_LOGIN_NGHEPHAMVAN, users.get(0).getLogin());
        Assert.assertEquals(USER_LOGIN_2_NGHEPHAM, users.get(1).getLogin());
        verify(githubUserService).searchGithubUsers(USER_LOGIN_NGHEPHAMVAN);
        verify(githubUserService).getUser(USER_LOGIN_NGHEPHAMVAN);
        verify(githubUserService).getUser(USER_LOGIN_2_NGHEPHAM);
    }

    private UsersList githubUserList() {
        User user = new User(USER_LOGIN_NGHEPHAMVAN);

        User user2 = new User(USER_LOGIN_2_NGHEPHAM);

        List<User> githubUsers = new ArrayList<>();
        githubUsers.add(user);
        githubUsers.add(user2);
        UsersList usersList = new UsersList(githubUsers);
        return usersList;
    }

    private User user1FullDetails() {
        User user = new User(USER_LOGIN_NGHEPHAMVAN, "Nghe Pham", "https://avatars3.githubusercontent.com/u/9383651?v=4", "Android developer");
        return user;
    }

    private User user2FullDetails() {
        User user = new User(USER_LOGIN_2_NGHEPHAM, null, "https://avatars1.githubusercontent.com/u/32038000?v=4", null);
        return user;
    }

    @Test
    public void searchUsers_IOExceptionThenSuccess_SearchUsersRetried() {
        //Given
        when(githubUserService.searchGithubUsers(anyString()))
                .thenReturn(getIOExceptionError(), Observable.just(githubUserList()));
        when(githubUserService.getUser(anyString()))
                .thenReturn(Observable.just(user1FullDetails()), Observable.just(user2FullDetails()));

        //When
         TestSubscriber<List<User>> observer = new TestSubscriber<>();
//        TestObserver<List<User>> observer = new TestObserver<>();
        userRepository.searchUsers(USER_LOGIN_NGHEPHAMVAN).subscribe(observer);


        //Then
        observer.awaitTerminalEvent();
        observer.assertNoErrors();

        verify(githubUserService, times(2)).searchGithubUsers(USER_LOGIN_NGHEPHAMVAN);

        verify(githubUserService).getUser(USER_LOGIN_NGHEPHAMVAN);
        verify(githubUserService).getUser(USER_LOGIN_2_NGHEPHAM);
    }

    @Test
    public void searchUsers_GetUserIOExceptionThenSuccess_SearchUsersRetried() {
        //Given
        when(githubUserService.searchGithubUsers(anyString())).thenReturn(Observable.just(githubUserList()));
        when(githubUserService.getUser(anyString()))
                .thenReturn(getIOExceptionError(), Observable.just(user1FullDetails()),
                        Observable.just(user2FullDetails()));

        //When
        TestSubscriber<List<User>> observer = new TestSubscriber<>();
        userRepository.searchUsers(USER_LOGIN_NGHEPHAMVAN).subscribe(observer);

        //Then
        observer.awaitTerminalEvent();
        observer.assertNoErrors();

        verify(githubUserService, times(2)).searchGithubUsers(USER_LOGIN_NGHEPHAMVAN);

        verify(githubUserService, times(2)).getUser(USER_LOGIN_NGHEPHAMVAN);
        verify(githubUserService).getUser(USER_LOGIN_2_NGHEPHAM);
    }

    @Test
    public void searchUsers_OtherHttpError_SearchTerminatedWithError() {
        //Given
        when(githubUserService.searchGithubUsers(anyString())).thenReturn(get403ForbiddenError());

        //When
        TestSubscriber<List<User>> observer = new TestSubscriber<>();
        userRepository.searchUsers(USER_LOGIN_NGHEPHAMVAN).subscribe(observer);

        //Then
        observer.awaitTerminalEvent();
        observer.assertError(HttpException.class);


        verify(githubUserService).searchGithubUsers(USER_LOGIN_NGHEPHAMVAN);

        verify(githubUserService, never()).getUser(USER_LOGIN_NGHEPHAMVAN);
        verify(githubUserService, never()).getUser(USER_LOGIN_2_NGHEPHAM);
    }

    private Observable getIOExceptionError() {
        return Observable.error(new IOException());
    }

    private Observable<UsersList> get403ForbiddenError() {
        return Observable.error(new HttpException(
                Response.error(403, ResponseBody.create(MediaType.parse("application/json"), "Forbidden"))));

    }
}
