package io.github.niwin92.brandpay.common.jpa;


import com.google.common.collect.Lists;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import lombok.Data;
import org.hibernate.validator.internal.engine.groups.Group;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

public class Restrictions {
    private List<Condition> conditions = Lists.newArrayList();
    private List<Restrictions> children = Lists.newArrayList();
    private Conn currCon;


    public Restrictions() {
        this.currCon = Conn.AND;
    }

    public Restrictions(Conn conn) {
        this.currCon = conn;
    }

    public void clear() {
        this.conditions.clear();
    }


    public void addChild(Restrictions child) {
        this.children.add(child);
    }

    public Restrictions eq(String name, Object value) {
        conditions.add(new Condition(ConditionType.EQUALS, name, value));
        return this;
    }

    public Restrictions le(String name, Object value) {
        conditions.add(new Condition(ConditionType.LESS_THAN_OR_EQUAL_TO, name, value));
        return this;
    }

    public Restrictions ge(String name, Object value) {
        conditions.add(new Condition(ConditionType.GREATER_THAN_OR_EQUAL_TO, name, value));
        return this;
    }

    public Restrictions isNull(String key) {
        conditions.add(new Condition(ConditionType.IS_NULL, key));
        return this;
    }

    public Restrictions isNotNull(String key) {
        conditions.add(new Condition(ConditionType.IS_NOT_NULL, key));
        return this;
    }

    public Restrictions ne(String key, Object object) {
        conditions.add(new Condition(ConditionType.NOT_EQUAL, key, object));
        return this;
    }


    public Restrictions like(String key, String object) {
        conditions.add(new Condition(ConditionType.LIKE, key, object));
        return this;
    }

    public Restrictions between(String key, Object start, Object end) {
        conditions.add(new Condition(ConditionType.BETWEEN, key, start, end));
        return this;
    }

    public Restrictions near(Double lat, Double lng) {
        conditions.add(new Condition(ConditionType.ORDER_NEAR, "distance", lat, lng));
        return this;
    }

    public Restrictions addOrder(String name, Direction sort) {
        conditions.add(new Condition(ConditionType.ORDER, name, sort));
        return this;
    }

    public Restrictions inNear(Double lat, Double lng, Double distance) {
        conditions.add(new Condition(ConditionType.IN_NEAR, distance, lat, lng));
        return this;
    }

    @SuppressWarnings("rawtypes")
    public void in(String name, Collection value) {
        conditions.add(new Condition(ConditionType.IN, name, value));
    }

    @SuppressWarnings("rawtypes")
    public void strIn(String name, Collection value) {
        conditions.add(new Condition(ConditionType.STR_IN, name, value));
    }

    @SuppressWarnings("rawtypes")
    public void notIn(String name, Collection value) {
        conditions.add(new Condition(ConditionType.NOT_IN, name, value));
    }

    public void isNullOrLe(String key, Object value) {
        conditions.add(new Condition(ConditionType.LESS_THAN_OR_IS_NULL, key, value));
    }

    public void isNullOrGe(String key, Object value) {
        conditions.add(new Condition(ConditionType.GREATER_THAN_OR_IS_NULL, key, value));
    }

    public void isNullOrEq(String key, Object value) {
        conditions.add(new Condition(ConditionType.EQ_OR_IS_NULL, key, value));
    }


    public void eqProperty(String key, String key2) {
        conditions.add(new Condition(ConditionType.EQUAL_PROPERTY, key, key2));
    }

    public Restrictions having(Class o, String joinField, String key, Object value) {
        conditions.add(new Condition(ConditionType.HAVING, key, value, o, joinField));
        return this;
    }

    final public <T> Specification<T> output() {
        return this.output(null);
    }

    public final <T, X extends T> Specification<T> output(final Class<X> type) {
        Specification<T> spec = (Specification<T>) (root, query, cb) -> {
            Root<?> _root = root;
            if (type != null) {
                _root = cb.treat(root, type);
            }

            List<Predicate> items = Lists.newArrayList();
            List<Order> orders = Lists.newArrayList();
            for (Condition condition : conditions) {
                String key = condition.getName().toString();
                Object object = condition.getValue1();
                Object object2 = condition.getValue2();
                Object object3 = condition.getValue3();

                switch (condition.type) {
                    case EQUALS: {
                        items.add(cb.equal(getPath(_root, key), object));
                        break;
                    }
                    case EQUAL_PROPERTY: {
                        items.add(cb.equal(getPath(_root, key), getPath(_root, object.toString())));
                        break;
                    }
                    case NOT_EQUAL: {
                        items.add(cb.notEqual(getPath(_root, key), object));
                        break;
                    }
                    case LIKE: {
                        items.add(cb.like(getPath(_root, key), (String) object));
                        break;
                    }
                    case IS_NULL: {
                        items.add(cb.isNull(getPath(_root, key)));
                        break;
                    }
                    case IS_NOT_NULL: {
                        items.add(cb.isNotNull(getPath(_root, key)));
                        break;
                    }
                    case STR_IN: {
                        Predicate p = null;
                        Path<String> field = getPath(_root, key);

                        Iterator itr = ((Collection) object).iterator();
                        while (itr.hasNext()) {
                            Object o = itr.next();
                            Predicate _p = cb.like(field.as(String.class), "%" + o + "%");
                            p = (p == null) ? _p : cb.or(p, _p);
                        }

                        items.add(p);
                        break;
                    }
                    case IN: {
                        final Path<Group> group = getPath(_root, key);
                        if (Collection.class.isAssignableFrom(object.getClass())) {
                            items.add(group.in((Collection) object));
                        } else {
                            items.add(group.in(object));
                        }
                        break;
                    }
                    case NOT_IN: {
                        final Path<Group> group = getPath(_root, key);
                        items.add(group.in((Collection) object).not());
                        break;
                    }
                    case LESS_THAN_OR_EQUAL_TO: {
                        if (object instanceof Date) {
                            items.add(
                                    cb.lessThanOrEqualTo(Restrictions.getPath(_root, key), (Date) object));
                        } else if (object instanceof LocalDateTime) {
                            items.add(cb.lessThanOrEqualTo(Restrictions.getPath(_root, key),
                                    (LocalDateTime) object));
                        } else if (object instanceof LocalDate) {
                            items.add(cb.lessThanOrEqualTo(Restrictions.getPath(_root, key),
                                    (LocalDate) object));
                        } else if (object instanceof LocalTime) {
                            items.add(cb.lessThanOrEqualTo(Restrictions.getPath(_root, key),
                                    (LocalTime) object));
                        } else {
                            items.add(cb.le(Restrictions.getPath(_root, key), (Number) object));
                        }
                        break;
                    }
                    case GREATER_THAN_OR_EQUAL_TO: {
                        if (object instanceof Date) {
                            items.add(
                                    cb.greaterThanOrEqualTo(Restrictions.getPath(_root, key), (Date) object));
                        } else if (object instanceof LocalDateTime) {
                            items.add(cb.greaterThanOrEqualTo(Restrictions.getPath(_root, key),
                                    (LocalDateTime) object));
                        } else if (object instanceof LocalDate) {
                            items.add(cb.greaterThanOrEqualTo(Restrictions.getPath(_root, key),
                                    (LocalDate) object));
                        } else if (object instanceof LocalTime) {
                            items.add(cb.greaterThanOrEqualTo(Restrictions.getPath(_root, key),
                                    (LocalTime) object));
                        } else {
                            items.add(cb.ge(Restrictions.getPath(_root, key), (Number) object));
                        }
                        break;
                    }
                    case LESS_THAN_OR_IS_NULL: {
                        Path path = getPath(_root, key);
                        if (object instanceof Date) {
                            items.add(cb.or(cb.isNull(path), cb.lessThanOrEqualTo(path, (Date) object)));
                        } else if (object instanceof LocalDateTime) {
                            items.add(
                                    cb.or(cb.isNull(path), cb.lessThanOrEqualTo(path, (LocalDateTime) object)));
                        } else if (object instanceof LocalDate) {
                            items.add(cb.or(cb.isNull(path), cb.lessThanOrEqualTo(path, (LocalDate) object)));
                        } else if (object instanceof LocalTime) {
                            items.add(cb.or(cb.isNull(path), cb.lessThanOrEqualTo(path, (LocalTime) object)));
                        } else {
                            items.add(cb.or(cb.isNull(path), cb.le(path, (Number) object)));
                        }
                        break;
                    }
                    case GREATER_THAN_OR_IS_NULL: {
                        Path path = getPath(_root, key);
                        if (object instanceof Date) {
                            items.add(cb.or(cb.isNull(path), cb.greaterThanOrEqualTo(path, (Date) object)));
                        } else if (object instanceof LocalDateTime) {
                            items.add(
                                    cb.or(cb.isNull(path),
                                            cb.greaterThanOrEqualTo(path, (LocalDateTime) object)));
                        } else if (object instanceof LocalDate) {
                            items
                                    .add(cb.or(cb.isNull(path),
                                            cb.greaterThanOrEqualTo(path, (LocalDate) object)));
                        } else if (object instanceof LocalTime) {
                            items
                                    .add(cb.or(cb.isNull(path),
                                            cb.greaterThanOrEqualTo(path, (LocalTime) object)));
                        } else {
                            items.add(cb.or(cb.isNull(path), cb.ge(path, (Number) object)));
                        }
                        break;
                    }
                    case EQ_OR_IS_NULL: {
                        Path path = getPath(_root, key);
                        items.add(cb.or(cb.isNull(path), cb.equal(path, object)));
                        break;
                    }
                    case BETWEEN: {
                        if (object instanceof Date) {
                            items.add(cb.between(Restrictions.<Date>getPath(_root, key), (Date) object,
                                    (Date) object2));
                        } else if (LocalDateTime.class.isAssignableFrom(object.getClass())) {
                            items.add(cb.between(Restrictions.getPath(_root, key), (LocalDateTime) object,
                                    (LocalDateTime) object2));
                        } else if (LocalDate.class.isAssignableFrom(object.getClass())) {
                            items.add(cb.between(Restrictions.getPath(_root, key), (LocalDate) object,
                                    (LocalDate) object2));
                        } else if (LocalTime.class.isAssignableFrom(object.getClass())) {
                            items.add(cb.between(Restrictions.getPath(_root, key), (LocalTime) object,
                                    (LocalTime) object2));
                        } else {
                            items.add(
                                    cb.between(Restrictions.getPath(_root, key), (Double) object,
                                            (Double) object2));
                        }
                        break;
                    }

                    case ORDER: {
                        if (object == Direction.DESC) {
                            orders.add(cb.desc(getPath(_root, key)));
                        } else {
                            orders.add(cb.asc(getPath(_root, key)));
                        }
                        break;
                    }

                    case ORDER_NEAR: {
                        orders.add(cb.asc(cb.function(key, Double.class, root.get("lat"), root.get("lng"),
                                cb.literal((Double) object), cb.literal((Double) object2))));
                        break;
                    }

                    case IN_NEAR: {
                        Expression fn = cb
                                .function("distance", Double.class, root.get("lat"), root.get("lng"),
                                        cb.literal((Double) object), cb.literal((Double) object2));
                        items.add(cb.le(fn, (Double) condition.getName()));
                        break;
                    }

                    case HAVING: {
                        Subquery subQuery = query.subquery((Class) object2);
                        Root owner = subQuery.from((Class) object2);
                        Expression<Collection<T>> ownerCats = getPath(owner, object3.toString());
                        subQuery.select(owner);
                        subQuery.where(cb.equal(getPath(owner, key), object), cb.isMember(root, ownerCats));
                        items.add(cb.exists(subQuery));
                    }

                    default:
                        break;
                }
            }

            if (orders.size() > 0) {
                query.orderBy(orders);
            }

            if (items.size() > 1) {
                Predicate[] ps = items.toArray(new Predicate[]{});
                return (currCon == Conn.AND) ? cb.and(ps) : cb.or(ps);
            } else if (items.size() == 1) {
                return items.get(0);
            }

            return null;
        };

        if (children.size() > 0) {
            for (Restrictions child : children) {
                Specification<T> output = type != null ? child.output(type) : child.output();
                spec = (currCon == Conn.AND) ? spec.and(output) : spec.or(output);
            }
        }
        return spec;
    }


    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <Y> Path<Y> getPath(Root root, String key) {
        Path<Y> x = null;
        if (key.contains(".")) {
            for (String k : key.split("\\.")) {
                x = (x == null ? root.get(k) : x.get(k));
            }
        } else {
            x = root.<Y>get(key);
        }
        return x;
    }

    @Data
    private class Condition {

        private ConditionType type;
        private Object name;
        private Object value1;
        private Object value2;
        private Object value3;

        /**
         * Instantiates a new Condition.
         *
         * @param type the type
         * @param name the name
         */
        public Condition(ConditionType type, Object name) {
            this(type, name, null);
        }

        /**
         * Instantiates a new Condition.
         *
         * @param type   the type
         * @param name   the name
         * @param value1 the value 1
         */
        public Condition(ConditionType type, Object name, Object value1) {
            this.type = type;
            this.name = name;
            this.value1 = value1;
        }

        /**
         * Instantiates a new Condition.
         *
         * @param type   the type
         * @param name   the name
         * @param value1 the value 1
         * @param value2 the value 2
         */
        public Condition(ConditionType type, Object name, Object value1, Object value2) {
            this.type = type;
            this.name = name;
            this.value1 = value1;
            this.value2 = value2;
        }

        public Condition(ConditionType type, Object name, Object value1, Object value2, Object value3) {
            this.type = type;
            this.name = name;
            this.value1 = value1;
            this.value2 = value2;
            this.value3 = value3;
        }

    }

    private enum ConditionType {
        /**
         * Equals condition type.
         */
        EQUALS,
        /**
         * In condition type.
         */
        IN,
        /**
         * Not in condition type.
         */
        NOT_IN,
        /**
         * Less than or equal to condition type.
         */
        LESS_THAN_OR_EQUAL_TO,
        /**
         * Greater than or equal to condition type.
         */
        GREATER_THAN_OR_EQUAL_TO,
        /**
         * Is null condition type.
         */
        IS_NULL,
        /**
         * Is not null condition type.
         */
        IS_NOT_NULL,
        /**
         * Not equal condition type.
         */
        NOT_EQUAL,
        /**
         * Like condition type.
         */
        LIKE,
        /**
         * Between condition type.
         */
        BETWEEN,
        /**
         * Less than or is null condition type.
         */
        LESS_THAN_OR_IS_NULL,
        /**
         * Greater than or is null condition type.
         */
        GREATER_THAN_OR_IS_NULL,
        /**
         * Equal or is null condition type.
         */
        EQ_OR_IS_NULL,
        /**
         * Equal property condition type.
         */
        EQUAL_PROPERTY,
        /**
         * Order condition type.
         */
        ORDER,
        /**
         * Order near condition type.
         */
        ORDER_NEAR,
        /**
         * In near condition type.
         */
        IN_NEAR,
        /**
         * Str in condition type.
         */
        STR_IN,

        HAVING,
    }

    public enum Conn {
        AND,
        OR
    }
}

