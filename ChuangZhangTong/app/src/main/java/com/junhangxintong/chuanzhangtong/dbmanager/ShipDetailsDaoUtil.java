package com.junhangxintong.chuanzhangtong.dbmanager;

import android.content.Context;
import android.util.Log;

import com.junhangxintong.chuanzhangtong.gen.ShipDetailsBeanDao;
import com.junhangxintong.chuanzhangtong.shipposition.bean.ShipDetailsBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by anwanfei on 2017/8/4.
 */

public class ShipDetailsDaoUtil {
    private static final String TAG = ShipDetailsDaoUtil.class.getSimpleName();
    private DaoManager mManager;

    public ShipDetailsDaoUtil(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成ShipDetailsBean记录的插入，如果表未创建，先创建ShipDetailsBean表
     *
     * @param ShipDetailsBean
     * @return
     */
    public boolean insertShipDetailsBean(ShipDetailsBean ShipDetailsBean) {
        boolean flag = false;
        flag = mManager.getDaoSession().getShipDetailsBeanDao().insert(ShipDetailsBean) == -1 ? false : true;
        Log.i(TAG, "insert ShipDetailsBean :" + flag + "-->" + ShipDetailsBean.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     *
     * @param ShipDetailsBeanList
     * @return
     */
    public boolean insertMultShipDetailsBean(final List<ShipDetailsBean> ShipDetailsBeanList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (ShipDetailsBean ShipDetailsBean : ShipDetailsBeanList) {
                        mManager.getDaoSession().insertOrReplace(ShipDetailsBean);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     *
     * @param ShipDetailsBean
     * @return
     */
    public boolean updateShipDetailsBean(ShipDetailsBean ShipDetailsBean) {
        boolean flag = false;
        try {
            mManager.getDaoSession().update(ShipDetailsBean);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     *
     * @param ShipDetailsBean
     * @return
     */
    public boolean deleteShipDetailsBean(ShipDetailsBean ShipDetailsBean) {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(ShipDetailsBean);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     *
     * @return
     */
    public boolean deleteAll() {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(ShipDetailsBean.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<ShipDetailsBean> queryAllShipDetailsBean() {
        return mManager.getDaoSession().loadAll(ShipDetailsBean.class);
    }

    /**
     * 根据主键id查询记录
     *
     * @param key
     * @return
     */
    public ShipDetailsBean queryShipDetailsBeanById(long key) {
        return mManager.getDaoSession().load(ShipDetailsBean.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<ShipDetailsBean> queryShipDetailsBeanByNativeSql(String sql, String[] conditions) {
        return mManager.getDaoSession().queryRaw(ShipDetailsBean.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     *
     * @return
     */
    public List<ShipDetailsBean> queryShipDetailsBeanByQueryBuilder(String id) {
        QueryBuilder<ShipDetailsBean> queryBuilder = mManager.getDaoSession().queryBuilder(ShipDetailsBean.class);
        return queryBuilder.where(ShipDetailsBeanDao.Properties.ShipName.eq(id)).list();
    }

    /**
     * 多条件模糊查询
     *
     * @param value
     * @return
     */
    public List<ShipDetailsBean> getShipsByLike(String value) {
        return mManager.getDaoSession().queryBuilder(ShipDetailsBean.class)
                .whereOr(ShipDetailsBeanDao.Properties.ShipName.like("%" + value + "%"), ShipDetailsBeanDao.Properties.Mmsi.like("%" + value + "%")).list();
    }

}
