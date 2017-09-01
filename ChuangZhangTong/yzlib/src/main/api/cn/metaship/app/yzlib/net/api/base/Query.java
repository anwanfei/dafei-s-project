package cn.metaship.app.yzlib.net.api.base;

import java.io.Serializable;
import java.util.List;

public class Query<M> implements Serializable {
    private M model;
    private List<Order> orders;
}
