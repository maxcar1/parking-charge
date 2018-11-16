package com.maxcar.user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfigurationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConfigurationExample() {
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

        public Criteria andConfigurationIdIsNull() {
            addCriterion("configuration_id is null");
            return (Criteria) this;
        }

        public Criteria andConfigurationIdIsNotNull() {
            addCriterion("configuration_id is not null");
            return (Criteria) this;
        }

        public Criteria andConfigurationIdEqualTo(String value) {
            addCriterion("configuration_id =", value, "configurationId");
            return (Criteria) this;
        }

        public Criteria andConfigurationIdNotEqualTo(String value) {
            addCriterion("configuration_id <>", value, "configurationId");
            return (Criteria) this;
        }

        public Criteria andConfigurationIdGreaterThan(String value) {
            addCriterion("configuration_id >", value, "configurationId");
            return (Criteria) this;
        }

        public Criteria andConfigurationIdGreaterThanOrEqualTo(String value) {
            addCriterion("configuration_id >=", value, "configurationId");
            return (Criteria) this;
        }

        public Criteria andConfigurationIdLessThan(String value) {
            addCriterion("configuration_id <", value, "configurationId");
            return (Criteria) this;
        }

        public Criteria andConfigurationIdLessThanOrEqualTo(String value) {
            addCriterion("configuration_id <=", value, "configurationId");
            return (Criteria) this;
        }

        public Criteria andConfigurationIdLike(String value) {
            addCriterion("configuration_id like", value, "configurationId");
            return (Criteria) this;
        }

        public Criteria andConfigurationIdNotLike(String value) {
            addCriterion("configuration_id not like", value, "configurationId");
            return (Criteria) this;
        }

        public Criteria andConfigurationIdIn(List<String> values) {
            addCriterion("configuration_id in", values, "configurationId");
            return (Criteria) this;
        }

        public Criteria andConfigurationIdNotIn(List<String> values) {
            addCriterion("configuration_id not in", values, "configurationId");
            return (Criteria) this;
        }

        public Criteria andConfigurationIdBetween(String value1, String value2) {
            addCriterion("configuration_id between", value1, value2, "configurationId");
            return (Criteria) this;
        }

        public Criteria andConfigurationIdNotBetween(String value1, String value2) {
            addCriterion("configuration_id not between", value1, value2, "configurationId");
            return (Criteria) this;
        }

        public Criteria andMarketIdIsNull() {
            addCriterion("market_id is null");
            return (Criteria) this;
        }

        public Criteria andMarketIdIsNotNull() {
            addCriterion("market_id is not null");
            return (Criteria) this;
        }

        public Criteria andMarketIdEqualTo(String value) {
            addCriterion("market_id =", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotEqualTo(String value) {
            addCriterion("market_id <>", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdGreaterThan(String value) {
            addCriterion("market_id >", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdGreaterThanOrEqualTo(String value) {
            addCriterion("market_id >=", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdLessThan(String value) {
            addCriterion("market_id <", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdLessThanOrEqualTo(String value) {
            addCriterion("market_id <=", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdLike(String value) {
            addCriterion("market_id like", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotLike(String value) {
            addCriterion("market_id not like", value, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdIn(List<String> values) {
            addCriterion("market_id in", values, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotIn(List<String> values) {
            addCriterion("market_id not in", values, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdBetween(String value1, String value2) {
            addCriterion("market_id between", value1, value2, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketIdNotBetween(String value1, String value2) {
            addCriterion("market_id not between", value1, value2, "marketId");
            return (Criteria) this;
        }

        public Criteria andMarketNameIsNull() {
            addCriterion("market_name is null");
            return (Criteria) this;
        }

        public Criteria andMarketNameIsNotNull() {
            addCriterion("market_name is not null");
            return (Criteria) this;
        }

        public Criteria andMarketNameEqualTo(String value) {
            addCriterion("market_name =", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameNotEqualTo(String value) {
            addCriterion("market_name <>", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameGreaterThan(String value) {
            addCriterion("market_name >", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameGreaterThanOrEqualTo(String value) {
            addCriterion("market_name >=", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameLessThan(String value) {
            addCriterion("market_name <", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameLessThanOrEqualTo(String value) {
            addCriterion("market_name <=", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameLike(String value) {
            addCriterion("market_name like", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameNotLike(String value) {
            addCriterion("market_name not like", value, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameIn(List<String> values) {
            addCriterion("market_name in", values, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameNotIn(List<String> values) {
            addCriterion("market_name not in", values, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameBetween(String value1, String value2) {
            addCriterion("market_name between", value1, value2, "marketName");
            return (Criteria) this;
        }

        public Criteria andMarketNameNotBetween(String value1, String value2) {
            addCriterion("market_name not between", value1, value2, "marketName");
            return (Criteria) this;
        }

        public Criteria andMerantsIdIsNull() {
            addCriterion("merants_id is null");
            return (Criteria) this;
        }

        public Criteria andMerantsIdIsNotNull() {
            addCriterion("merants_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerantsIdEqualTo(String value) {
            addCriterion("merants_id =", value, "merantsId");
            return (Criteria) this;
        }

        public Criteria andMerantsIdNotEqualTo(String value) {
            addCriterion("merants_id <>", value, "merantsId");
            return (Criteria) this;
        }

        public Criteria andMerantsIdGreaterThan(String value) {
            addCriterion("merants_id >", value, "merantsId");
            return (Criteria) this;
        }

        public Criteria andMerantsIdGreaterThanOrEqualTo(String value) {
            addCriterion("merants_id >=", value, "merantsId");
            return (Criteria) this;
        }

        public Criteria andMerantsIdLessThan(String value) {
            addCriterion("merants_id <", value, "merantsId");
            return (Criteria) this;
        }

        public Criteria andMerantsIdLessThanOrEqualTo(String value) {
            addCriterion("merants_id <=", value, "merantsId");
            return (Criteria) this;
        }

        public Criteria andMerantsIdLike(String value) {
            addCriterion("merants_id like", value, "merantsId");
            return (Criteria) this;
        }

        public Criteria andMerantsIdNotLike(String value) {
            addCriterion("merants_id not like", value, "merantsId");
            return (Criteria) this;
        }

        public Criteria andMerantsIdIn(List<String> values) {
            addCriterion("merants_id in", values, "merantsId");
            return (Criteria) this;
        }

        public Criteria andMerantsIdNotIn(List<String> values) {
            addCriterion("merants_id not in", values, "merantsId");
            return (Criteria) this;
        }

        public Criteria andMerantsIdBetween(String value1, String value2) {
            addCriterion("merants_id between", value1, value2, "merantsId");
            return (Criteria) this;
        }

        public Criteria andMerantsIdNotBetween(String value1, String value2) {
            addCriterion("merants_id not between", value1, value2, "merantsId");
            return (Criteria) this;
        }

        public Criteria andMerantsNameIsNull() {
            addCriterion("merants_name is null");
            return (Criteria) this;
        }

        public Criteria andMerantsNameIsNotNull() {
            addCriterion("merants_name is not null");
            return (Criteria) this;
        }

        public Criteria andMerantsNameEqualTo(String value) {
            addCriterion("merants_name =", value, "merantsName");
            return (Criteria) this;
        }

        public Criteria andMerantsNameNotEqualTo(String value) {
            addCriterion("merants_name <>", value, "merantsName");
            return (Criteria) this;
        }

        public Criteria andMerantsNameGreaterThan(String value) {
            addCriterion("merants_name >", value, "merantsName");
            return (Criteria) this;
        }

        public Criteria andMerantsNameGreaterThanOrEqualTo(String value) {
            addCriterion("merants_name >=", value, "merantsName");
            return (Criteria) this;
        }

        public Criteria andMerantsNameLessThan(String value) {
            addCriterion("merants_name <", value, "merantsName");
            return (Criteria) this;
        }

        public Criteria andMerantsNameLessThanOrEqualTo(String value) {
            addCriterion("merants_name <=", value, "merantsName");
            return (Criteria) this;
        }

        public Criteria andMerantsNameLike(String value) {
            addCriterion("merants_name like", value, "merantsName");
            return (Criteria) this;
        }

        public Criteria andMerantsNameNotLike(String value) {
            addCriterion("merants_name not like", value, "merantsName");
            return (Criteria) this;
        }

        public Criteria andMerantsNameIn(List<String> values) {
            addCriterion("merants_name in", values, "merantsName");
            return (Criteria) this;
        }

        public Criteria andMerantsNameNotIn(List<String> values) {
            addCriterion("merants_name not in", values, "merantsName");
            return (Criteria) this;
        }

        public Criteria andMerantsNameBetween(String value1, String value2) {
            addCriterion("merants_name between", value1, value2, "merantsName");
            return (Criteria) this;
        }

        public Criteria andMerantsNameNotBetween(String value1, String value2) {
            addCriterion("merants_name not between", value1, value2, "merantsName");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameIsNull() {
            addCriterion("configuration_name is null");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameIsNotNull() {
            addCriterion("configuration_name is not null");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameEqualTo(String value) {
            addCriterion("configuration_name =", value, "configurationName");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameNotEqualTo(String value) {
            addCriterion("configuration_name <>", value, "configurationName");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameGreaterThan(String value) {
            addCriterion("configuration_name >", value, "configurationName");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameGreaterThanOrEqualTo(String value) {
            addCriterion("configuration_name >=", value, "configurationName");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameLessThan(String value) {
            addCriterion("configuration_name <", value, "configurationName");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameLessThanOrEqualTo(String value) {
            addCriterion("configuration_name <=", value, "configurationName");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameLike(String value) {
            addCriterion("configuration_name like", value, "configurationName");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameNotLike(String value) {
            addCriterion("configuration_name not like", value, "configurationName");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameIn(List<String> values) {
            addCriterion("configuration_name in", values, "configurationName");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameNotIn(List<String> values) {
            addCriterion("configuration_name not in", values, "configurationName");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameBetween(String value1, String value2) {
            addCriterion("configuration_name between", value1, value2, "configurationName");
            return (Criteria) this;
        }

        public Criteria andConfigurationNameNotBetween(String value1, String value2) {
            addCriterion("configuration_name not between", value1, value2, "configurationName");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyIsNull() {
            addCriterion("configuration_key is null");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyIsNotNull() {
            addCriterion("configuration_key is not null");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyEqualTo(String value) {
            addCriterion("configuration_key =", value, "configurationKey");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyNotEqualTo(String value) {
            addCriterion("configuration_key <>", value, "configurationKey");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyGreaterThan(String value) {
            addCriterion("configuration_key >", value, "configurationKey");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyGreaterThanOrEqualTo(String value) {
            addCriterion("configuration_key >=", value, "configurationKey");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyLessThan(String value) {
            addCriterion("configuration_key <", value, "configurationKey");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyLessThanOrEqualTo(String value) {
            addCriterion("configuration_key <=", value, "configurationKey");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyLike(String value) {
            addCriterion("configuration_key like", value, "configurationKey");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyNotLike(String value) {
            addCriterion("configuration_key not like", value, "configurationKey");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyIn(List<String> values) {
            addCriterion("configuration_key in", values, "configurationKey");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyNotIn(List<String> values) {
            addCriterion("configuration_key not in", values, "configurationKey");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyBetween(String value1, String value2) {
            addCriterion("configuration_key between", value1, value2, "configurationKey");
            return (Criteria) this;
        }

        public Criteria andConfigurationKeyNotBetween(String value1, String value2) {
            addCriterion("configuration_key not between", value1, value2, "configurationKey");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueIsNull() {
            addCriterion("configuration_value is null");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueIsNotNull() {
            addCriterion("configuration_value is not null");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueEqualTo(String value) {
            addCriterion("configuration_value =", value, "configurationValue");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueNotEqualTo(String value) {
            addCriterion("configuration_value <>", value, "configurationValue");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueGreaterThan(String value) {
            addCriterion("configuration_value >", value, "configurationValue");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueGreaterThanOrEqualTo(String value) {
            addCriterion("configuration_value >=", value, "configurationValue");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueLessThan(String value) {
            addCriterion("configuration_value <", value, "configurationValue");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueLessThanOrEqualTo(String value) {
            addCriterion("configuration_value <=", value, "configurationValue");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueLike(String value) {
            addCriterion("configuration_value like", value, "configurationValue");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueNotLike(String value) {
            addCriterion("configuration_value not like", value, "configurationValue");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueIn(List<String> values) {
            addCriterion("configuration_value in", values, "configurationValue");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueNotIn(List<String> values) {
            addCriterion("configuration_value not in", values, "configurationValue");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueBetween(String value1, String value2) {
            addCriterion("configuration_value between", value1, value2, "configurationValue");
            return (Criteria) this;
        }

        public Criteria andConfigurationValueNotBetween(String value1, String value2) {
            addCriterion("configuration_value not between", value1, value2, "configurationValue");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescIsNull() {
            addCriterion("configuration_desc is null");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescIsNotNull() {
            addCriterion("configuration_desc is not null");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescEqualTo(String value) {
            addCriterion("configuration_desc =", value, "configurationDesc");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescNotEqualTo(String value) {
            addCriterion("configuration_desc <>", value, "configurationDesc");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescGreaterThan(String value) {
            addCriterion("configuration_desc >", value, "configurationDesc");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescGreaterThanOrEqualTo(String value) {
            addCriterion("configuration_desc >=", value, "configurationDesc");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescLessThan(String value) {
            addCriterion("configuration_desc <", value, "configurationDesc");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescLessThanOrEqualTo(String value) {
            addCriterion("configuration_desc <=", value, "configurationDesc");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescLike(String value) {
            addCriterion("configuration_desc like", value, "configurationDesc");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescNotLike(String value) {
            addCriterion("configuration_desc not like", value, "configurationDesc");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescIn(List<String> values) {
            addCriterion("configuration_desc in", values, "configurationDesc");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescNotIn(List<String> values) {
            addCriterion("configuration_desc not in", values, "configurationDesc");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescBetween(String value1, String value2) {
            addCriterion("configuration_desc between", value1, value2, "configurationDesc");
            return (Criteria) this;
        }

        public Criteria andConfigurationDescNotBetween(String value1, String value2) {
            addCriterion("configuration_desc not between", value1, value2, "configurationDesc");
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

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
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