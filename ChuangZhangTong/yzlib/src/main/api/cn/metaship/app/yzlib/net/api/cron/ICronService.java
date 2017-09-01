package cn.metaship.app.yzlib.net.api.cron;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import retrofit2.http.POST;
import retrofit2.http.Body;
public interface ICronService {
    /**
    * 新增一个调度任务
    */
    @POST("/v1/admin/cron/add-task")
    void addTask(@Body TaskInfoBean arg0 );
}