package cn.metaship.app.yzlib.net.api.user;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IFriendSystemService {
    /**
    * 添加好友分组
    */
    @POST("/v1/friendsystem/add-group")
    Observable<Response<BuddyGroupBean>> insertBuddyGroup(@Body AddBuddyGroupBean arg0 );
}