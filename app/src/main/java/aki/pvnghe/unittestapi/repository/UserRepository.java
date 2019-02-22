package aki.pvnghe.unittestapi.repository;


import java.util.List;

import aki.pvnghe.unittestapi.model.User;
import rx.Observable;

public interface UserRepository {

    Observable<List<User>> searchUsers(String searchTerm);
}
