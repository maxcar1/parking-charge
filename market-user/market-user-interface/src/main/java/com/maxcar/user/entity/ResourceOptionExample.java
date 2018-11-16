package com.maxcar.user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResourceOptionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResourceOptionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andResourceOptionIdIsNull() {
            addCriterion("resource_option_id is null");
            return (Criteria) this;
        }

        public Criteria andResourceOptionIdIsNotNull() {
            addCriterion("resource_option_id is not null");
            return (Criteria) this;
        }

        public Criteria andResourceOptionIdEqualTo(String value) {
            addCriterion("resource_option_id =", value, "resourceOptionId");
            return (Criteria) this;
        }

        public Criteria andResourceOptionIdNotEqualTo(String value) {
            addCriterion("resource_option_id <>", value, "resourceOptionId");
            return (Criteria) this;
        }

        public Criteria andResourceOptionIdGreaterThan(String value) {
            addCriterion("resource_option_id >", value, "resourceOptionId");
            return (Criteria) this;
        }

        public Criteria andResourceOptionIdGreaterThanOrEqualTo(String value) {
            addCriterion("resource_option_id >=", value, "resourceOptionId");
            return (Criteria) this;
        }

        public Criteria andResourceOptionIdLessThan(String value) {
            addCriterion("resource_option_id <", value, "resourceOptionId");
            return (Criteria) this;
        }

        public Criteria andResourceOptionIdLessThanOrEqualTo(String value) {
            addCriterion("resource_option_id <=", value, "resourceOptionId");
            return (Criteria) this;
        }

        public Criteria andResourceOptionIdLike(String value) {
            addCriterion("resource_option_id like", value, "resourceOptionId");
            return (Criteria) this;
        }

        public Criteria andResourceOptionIdNotLike(String value) {
            addCriterion("resource_option_id not like", value, "resourceOptionId");
            return (Criteria) this;
        }

        public Criteria andResourceOptionIdIn(List<String> values) {
            addCriterion("resource_option_id in", values, "resourceOptionId");
            return (Criteria) this;
        }

        public Criteria andResourceOptionIdNotIn(List<String> values) {
            addCriterion("resource_option_id not in", values, "resourceOptionId");
            return (Criteria) this;
        }

        public Criteria andResourceOptionIdBetween(String value1, String value2) {
            addCriterion("resource_option_id between", value1, value2, "resourceOptionId");
            return (Criteria) this;
        }

        public Criteria andResourceOptionIdNotBetween(String value1, String value2) {
            addCriterion("resource_option_id not between", value1, value2, "resourceOptionId");
            return (Criteria) this;
        }

        public Criteria andResourceIdIsNull() {
            addCriterion("resource_id is null");
            return (Criteria) this;
        }

        public Criteria andResourceIdIsNotNull() {
            addCriterion("resource_id is not null");
            return (Criteria) this;
        }

        public Criteria andResourceIdEqualTo(String value) {
            addCriterion("resource_id =", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotEqualTo(String value) {
            addCriterion("resource_id <>", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdGreaterThan(String value) {
            addCriterion("resource_id >", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("resource_id >=", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLessThan(String value) {
            addCriterion("resource_id <", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLessThanOrEqualTo(String value) {
            addCriterion("resource_id <=", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLike(String value) {
            addCriterion("resource_id like", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotLike(String value) {
            addCriterion("resource_id not like", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdIn(List<String> values) {
            addCriterion("resource_id in", values, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotIn(List<String> values) {
            addCriterion("resource_id not in", values, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdBetween(String value1, String value2) {
            addCriterion("resource_id between", value1, value2, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotBetween(String value1, String value2) {
            addCriterion("resource_id not between", value1, value2, "resourceId");
            return (Criteria) this;
        }

        public Criteria andOptionNameIsNull() {
            addCriterion("option_name is null");
            return (Criteria) this;
        }

        public Criteria andOptionNameIsNotNull() {
            addCriterion("option_name is not null");
            return (Criteria) this;
        }

        public Criteria andOptionNameEqualTo(String value) {
            addCriterion("option_name =", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameNotEqualTo(String value) {
            addCriterion("option_name <>", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameGreaterThan(String value) {
            addCriterion("option_name >", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameGreaterThanOrEqualTo(String value) {
            addCriterion("option_name >=", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameLessThan(String value) {
            addCriterion("option_name <", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameLessThanOrEqualTo(String value) {
            addCriterion("option_name <=", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameLike(String value) {
            addCriterion("option_name like", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameNotLike(String value) {
            addCriterion("option_name not like", value, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameIn(List<String> values) {
            addCriterion("option_name in", values, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameNotIn(List<String> values) {
            addCriterion("option_name not in", values, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameBetween(String value1, String value2) {
            addCriterion("option_name between", value1, value2, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionNameNotBetween(String value1, String value2) {
            addCriterion("option_name not between", value1, value2, "optionName");
            return (Criteria) this;
        }

        public Criteria andOptionTypeIsNull() {
            addCriterion("option_type is null");
            return (Criteria) this;
        }

        public Criteria andOptionTypeIsNotNull() {
            addCriterion("option_type is not null");
            return (Criteria) this;
        }

        public Criteria andOptionTypeEqualTo(Integer value) {
            addCriterion("option_type =", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeNotEqualTo(Integer value) {
            addCriterion("option_type <>", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeGreaterThan(Integer value) {
            addCriterion("option_type >", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("option_type >=", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeLessThan(Integer value) {
            addCriterion("option_type <", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeLessThanOrEqualTo(Integer value) {
            addCriterion("option_type <=", value, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeIn(List<Integer> values) {
            addCriterion("option_type in", values, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeNotIn(List<Integer> values) {
            addCriterion("option_type not in", values, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeBetween(Integer value1, Integer value2) {
            addCriterion("option_type between", value1, value2, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("option_type not between", value1, value2, "optionType");
            return (Criteria) this;
        }

        public Criteria andOptionUrlIsNull() {
            addCriterion("option_url is null");
            return (Criteria) this;
        }

        public Criteria andOptionUrlIsNotNull() {
            addCriterion("option_url is not null");
            return (Criteria) this;
        }

        public Criteria andOptionUrlEqualTo(String value) {
            addCriterion("option_url =", value, "optionUrl");
            return (Criteria) this;
        }

        public Criteria andOptionUrlNotEqualTo(String value) {
            addCriterion("option_url <>", value, "optionUrl");
            return (Criteria) this;
        }

        public Criteria andOptionUrlGreaterThan(String value) {
            addCriterion("option_url >", value, "optionUrl");
            return (Criteria) this;
        }

        public Criteria andOptionUrlGreaterThanOrEqualTo(String value) {
            addCriterion("option_url >=", value, "optionUrl");
            return (Criteria) this;
        }

        public Criteria andOptionUrlLessThan(String value) {
            addCriterion("option_url <", value, "optionUrl");
            return (Criteria) this;
        }

        public Criteria andOptionUrlLessThanOrEqualTo(String value) {
            addCriterion("option_url <=", value, "optionUrl");
            return (Criteria) this;
        }

        public Criteria andOptionUrlLike(String value) {
            addCriterion("option_url like", value, "optionUrl");
            return (Criteria) this;
        }

        public Criteria andOptionUrlNotLike(String value) {
            addCriterion("option_url not like", value, "optionUrl");
            return (Criteria) this;
        }

        public Criteria andOptionUrlIn(List<String> values) {
            addCriterion("option_url in", values, "optionUrl");
            return (Criteria) this;
        }

        public Criteria andOptionUrlNotIn(List<String> values) {
            addCriterion("option_url not in", values, "optionUrl");
            return (Criteria) this;
        }

        public Criteria andOptionUrlBetween(String value1, String value2) {
            addCriterion("option_url between", value1, value2, "optionUrl");
            return (Criteria) this;
        }

        public Criteria andOptionUrlNotBetween(String value1, String value2) {
            addCriterion("option_url not between", value1, value2, "optionUrl");
            return (Criteria) this;
        }

        public Criteria andIndIsNull() {
            addCriterion("ind is null");
            return (Criteria) this;
        }

        public Criteria andIndIsNotNull() {
            addCriterion("ind is not null");
            return (Criteria) this;
        }

        public Criteria andIndEqualTo(Integer value) {
            addCriterion("ind =", value, "ind");
            return (Criteria) this;
        }

        public Criteria andIndNotEqualTo(Integer value) {
            addCriterion("ind <>", value, "ind");
            return (Criteria) this;
        }

        public Criteria andIndGreaterThan(Integer value) {
            addCriterion("ind >", value, "ind");
            return (Criteria) this;
        }

        public Criteria andIndGreaterThanOrEqualTo(Integer value) {
            addCriterion("ind >=", value, "ind");
            return (Criteria) this;
        }

        public Criteria andIndLessThan(Integer value) {
            addCriterion("ind <", value, "ind");
            return (Criteria) this;
        }

        public Criteria andIndLessThanOrEqualTo(Integer value) {
            addCriterion("ind <=", value, "ind");
            return (Criteria) this;
        }

        public Criteria andIndIn(List<Integer> values) {
            addCriterion("ind in", values, "ind");
            return (Criteria) this;
        }

        public Criteria andIndNotIn(List<Integer> values) {
            addCriterion("ind not in", values, "ind");
            return (Criteria) this;
        }

        public Criteria andIndBetween(Integer value1, Integer value2) {
            addCriterion("ind between", value1, value2, "ind");
            return (Criteria) this;
        }

        public Criteria andIndNotBetween(Integer value1, Integer value2) {
            addCriterion("ind not between", value1, value2, "ind");
            return (Criteria) this;
        }

        public Criteria andIsvalidIsNull() {
            addCriterion("isvalid is null");
            return (Criteria) this;
        }

        public Criteria andIsvalidIsNotNull() {
            addCriterion("isvalid is not null");
            return (Criteria) this;
        }

        public Criteria andIsvalidEqualTo(Integer value) {
            addCriterion("isvalid =", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotEqualTo(Integer value) {
            addCriterion("isvalid <>", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThan(Integer value) {
            addCriterion("isvalid >", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThanOrEqualTo(Integer value) {
            addCriterion("isvalid >=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThan(Integer value) {
            addCriterion("isvalid <", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThanOrEqualTo(Integer value) {
            addCriterion("isvalid <=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidIn(List<Integer> values) {
            addCriterion("isvalid in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotIn(List<Integer> values) {
            addCriterion("isvalid not in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidBetween(Integer value1, Integer value2) {
            addCriterion("isvalid between", value1, value2, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotBetween(Integer value1, Integer value2) {
            addCriterion("isvalid not between", value1, value2, "isvalid");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeIsNull() {
            addCriterion("register_time is null");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeIsNotNull() {
            addCriterion("register_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeEqualTo(Date value) {
            addCriterion("register_time =", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeNotEqualTo(Date value) {
            addCriterion("register_time <>", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeGreaterThan(Date value) {
            addCriterion("register_time >", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("register_time >=", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeLessThan(Date value) {
            addCriterion("register_time <", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeLessThanOrEqualTo(Date value) {
            addCriterion("register_time <=", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeIn(List<Date> values) {
            addCriterion("register_time in", values, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeNotIn(List<Date> values) {
            addCriterion("register_time not in", values, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeBetween(Date value1, Date value2) {
            addCriterion("register_time between", value1, value2, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeNotBetween(Date value1, Date value2) {
            addCriterion("register_time not between", value1, value2, "registerTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}