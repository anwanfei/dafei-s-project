package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by live106 on 2017/6/3 15:14
 */

public interface IBookService {
    //@ApiOperation(value = "根据用户ID查询书架上的所有书", notes = "根据用户ID查询书架上的所有书", produces = "application/json")
    @GET(value = "/v1/app/bookshelf-books/{id}")
    Observable<Response<List<BooksBean>>> queryAllBooks(@Path("id") String userId);
}
