package learn.leecode.arr;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 链式校验
 *
 * @author zgq
 * @version v4.2.0
 * @since 2022/1/4
 **/
public class LinkVad<T> {

    /**
     * 校验方式集
     */
    private List<LinkVad<T>> predicates = new ArrayList<>();

    /**
     * 校验对象
     */
    private T t;

    /**
     * 校验方式
     */
    private Predicate<T> predicate;

    /**
     * 校验结果
     */
    private String msg;

    /**
     * 是否允许为null,允许时，不校验
     */
    private Boolean isAllowNull = false;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Predicate<T> getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private List<LinkVad<T>> getPredicates() {
        return predicates;
    }

    private void setPredicates(List<LinkVad<T>> predicates) {
        this.predicates = predicates;
    }

    public Boolean getAllowNull() {
        return isAllowNull;
    }

    public void setAllowNull(Boolean allowNull) {
        isAllowNull = allowNull;
    }

    public LinkVad() {
    }

    public LinkVad(T t, Predicate<T> predicate, String msg, Boolean allowNull) {
        this.t = t;
        this.predicate = predicate;
        this.msg = msg;
        this.isAllowNull = allowNull;
    }

    public LinkVad(T t, Predicate<T> predicate, String msg) {
        this.t = t;
        this.predicate = predicate;
        this.msg = msg;
    }

    public LinkVad<T> next(T vo, Predicate<T> predicate, String msg, Boolean allowNull) {
        this.t = vo;
        this.predicate = predicate;
        this.msg = msg;
        this.isAllowNull = allowNull;
        predicates.add(new LinkVad<>(vo, predicate, msg, allowNull));
        return this;
    }

    public LinkVad<T> next(T vo, Predicate<T> predicate, String msg) {
        return this.next(vo, predicate, msg, this.isAllowNull);
    }

    public LinkVad<T> next(Predicate<T> predicate, String msg) {
        this.predicate = predicate;
        this.msg = msg;
        return this.next(t, predicate, msg);
    }

    public String validate() {
        for (LinkVad<T> linkVad : predicates) {
            if (linkVad.isAllowNull && linkVad.getT() == null) {
                continue;
            }

            if (!linkVad.getPredicate().test(linkVad.t)) {
                return linkVad.getMsg();
            }
        }
        return StrUtil.EMPTY;
    }
}
