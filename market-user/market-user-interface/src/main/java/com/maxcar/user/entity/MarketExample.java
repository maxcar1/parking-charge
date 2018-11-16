package com.maxcar.user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MarketExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MarketExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMarketNoIsNull() {
            addCriterion("market_no is null");
            return (Criteria) this;
        }

        public Criteria andMarketNoIsNotNull() {
            addCriterion("market_no is not null");
            return (Criteria) this;
        }

        public Criteria andMarketNoEqualTo(String value) {
            addCriterion("market_no =", value, "marketNo");
            return (Criteria) this;
        }

        public Criteria andMarketNoNotEqualTo(String value) {
            addCriterion("market_no <>", value, "marketNo");
            return (Criteria) this;
        }

        public Criteria andMarketNoGreaterThan(String value) {
            addCriterion("market_no >", value, "marketNo");
            return (Criteria) this;
        }

        public Criteria andMarketNoGreaterThanOrEqualTo(String value) {
            addCriterion("market_no >=", value, "marketNo");
            return (Criteria) this;
        }

        public Criteria andMarketNoLessThan(String value) {
            addCriterion("market_no <", value, "marketNo");
            return (Criteria) this;
        }

        public Criteria andMarketNoLessThanOrEqualTo(String value) {
            addCriterion("market_no <=", value, "marketNo");
            return (Criteria) this;
        }

        public Criteria andMarketNoLike(String value) {
            addCriterion("market_no like", value, "marketNo");
            return (Criteria) this;
        }

        public Criteria andMarketNoNotLike(String value) {
            addCriterion("market_no not like", value, "marketNo");
            return (Criteria) this;
        }

        public Criteria andMarketNoIn(List<String> values) {
            addCriterion("market_no in", values, "marketNo");
            return (Criteria) this;
        }

        public Criteria andMarketNoNotIn(List<String> values) {
            addCriterion("market_no not in", values, "marketNo");
            return (Criteria) this;
        }

        public Criteria andMarketNoBetween(String value1, String value2) {
            addCriterion("market_no between", value1, value2, "marketNo");
            return (Criteria) this;
        }

        public Criteria andMarketNoNotBetween(String value1, String value2) {
            addCriterion("market_no not between", value1, value2, "marketNo");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSimpleNameIsNull() {
            addCriterion("simple_name is null");
            return (Criteria) this;
        }

        public Criteria andSimpleNameIsNotNull() {
            addCriterion("simple_name is not null");
            return (Criteria) this;
        }

        public Criteria andSimpleNameEqualTo(String value) {
            addCriterion("simple_name =", value, "simpleName");
            return (Criteria) this;
        }

        public Criteria andSimpleNameNotEqualTo(String value) {
            addCriterion("simple_name <>", value, "simpleName");
            return (Criteria) this;
        }

        public Criteria andSimpleNameGreaterThan(String value) {
            addCriterion("simple_name >", value, "simpleName");
            return (Criteria) this;
        }

        public Criteria andSimpleNameGreaterThanOrEqualTo(String value) {
            addCriterion("simple_name >=", value, "simpleName");
            return (Criteria) this;
        }

        public Criteria andSimpleNameLessThan(String value) {
            addCriterion("simple_name <", value, "simpleName");
            return (Criteria) this;
        }

        public Criteria andSimpleNameLessThanOrEqualTo(String value) {
            addCriterion("simple_name <=", value, "simpleName");
            return (Criteria) this;
        }

        public Criteria andSimpleNameLike(String value) {
            addCriterion("simple_name like", value, "simpleName");
            return (Criteria) this;
        }

        public Criteria andSimpleNameNotLike(String value) {
            addCriterion("simple_name not like", value, "simpleName");
            return (Criteria) this;
        }

        public Criteria andSimpleNameIn(List<String> values) {
            addCriterion("simple_name in", values, "simpleName");
            return (Criteria) this;
        }

        public Criteria andSimpleNameNotIn(List<String> values) {
            addCriterion("simple_name not in", values, "simpleName");
            return (Criteria) this;
        }

        public Criteria andSimpleNameBetween(String value1, String value2) {
            addCriterion("simple_name between", value1, value2, "simpleName");
            return (Criteria) this;
        }

        public Criteria andSimpleNameNotBetween(String value1, String value2) {
            addCriterion("simple_name not between", value1, value2, "simpleName");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andCorporationNameIsNull() {
            addCriterion("corporation_name is null");
            return (Criteria) this;
        }

        public Criteria andCorporationNameIsNotNull() {
            addCriterion("corporation_name is not null");
            return (Criteria) this;
        }

        public Criteria andCorporationNameEqualTo(String value) {
            addCriterion("corporation_name =", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameNotEqualTo(String value) {
            addCriterion("corporation_name <>", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameGreaterThan(String value) {
            addCriterion("corporation_name >", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameGreaterThanOrEqualTo(String value) {
            addCriterion("corporation_name >=", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameLessThan(String value) {
            addCriterion("corporation_name <", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameLessThanOrEqualTo(String value) {
            addCriterion("corporation_name <=", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameLike(String value) {
            addCriterion("corporation_name like", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameNotLike(String value) {
            addCriterion("corporation_name not like", value, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameIn(List<String> values) {
            addCriterion("corporation_name in", values, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameNotIn(List<String> values) {
            addCriterion("corporation_name not in", values, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameBetween(String value1, String value2) {
            addCriterion("corporation_name between", value1, value2, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationNameNotBetween(String value1, String value2) {
            addCriterion("corporation_name not between", value1, value2, "corporationName");
            return (Criteria) this;
        }

        public Criteria andCorporationIdIsNull() {
            addCriterion("corporation_id is null");
            return (Criteria) this;
        }

        public Criteria andCorporationIdIsNotNull() {
            addCriterion("corporation_id is not null");
            return (Criteria) this;
        }

        public Criteria andCorporationIdEqualTo(String value) {
            addCriterion("corporation_id =", value, "corporationId");
            return (Criteria) this;
        }

        public Criteria andCorporationIdNotEqualTo(String value) {
            addCriterion("corporation_id <>", value, "corporationId");
            return (Criteria) this;
        }

        public Criteria andCorporationIdGreaterThan(String value) {
            addCriterion("corporation_id >", value, "corporationId");
            return (Criteria) this;
        }

        public Criteria andCorporationIdGreaterThanOrEqualTo(String value) {
            addCriterion("corporation_id >=", value, "corporationId");
            return (Criteria) this;
        }

        public Criteria andCorporationIdLessThan(String value) {
            addCriterion("corporation_id <", value, "corporationId");
            return (Criteria) this;
        }

        public Criteria andCorporationIdLessThanOrEqualTo(String value) {
            addCriterion("corporation_id <=", value, "corporationId");
            return (Criteria) this;
        }

        public Criteria andCorporationIdLike(String value) {
            addCriterion("corporation_id like", value, "corporationId");
            return (Criteria) this;
        }

        public Criteria andCorporationIdNotLike(String value) {
            addCriterion("corporation_id not like", value, "corporationId");
            return (Criteria) this;
        }

        public Criteria andCorporationIdIn(List<String> values) {
            addCriterion("corporation_id in", values, "corporationId");
            return (Criteria) this;
        }

        public Criteria andCorporationIdNotIn(List<String> values) {
            addCriterion("corporation_id not in", values, "corporationId");
            return (Criteria) this;
        }

        public Criteria andCorporationIdBetween(String value1, String value2) {
            addCriterion("corporation_id between", value1, value2, "corporationId");
            return (Criteria) this;
        }

        public Criteria andCorporationIdNotBetween(String value1, String value2) {
            addCriterion("corporation_id not between", value1, value2, "corporationId");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(Integer value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(Integer value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(Integer value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(Integer value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(Integer value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(Integer value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<Integer> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<Integer> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(Integer value1, Integer value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(Integer value1, Integer value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andBankIsNull() {
            addCriterion("bank is null");
            return (Criteria) this;
        }

        public Criteria andBankIsNotNull() {
            addCriterion("bank is not null");
            return (Criteria) this;
        }

        public Criteria andBankEqualTo(String value) {
            addCriterion("bank =", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotEqualTo(String value) {
            addCriterion("bank <>", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThan(String value) {
            addCriterion("bank >", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThanOrEqualTo(String value) {
            addCriterion("bank >=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThan(String value) {
            addCriterion("bank <", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThanOrEqualTo(String value) {
            addCriterion("bank <=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLike(String value) {
            addCriterion("bank like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotLike(String value) {
            addCriterion("bank not like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankIn(List<String> values) {
            addCriterion("bank in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotIn(List<String> values) {
            addCriterion("bank not in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankBetween(String value1, String value2) {
            addCriterion("bank between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotBetween(String value1, String value2) {
            addCriterion("bank not between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andTaxnoIsNull() {
            addCriterion("taxno is null");
            return (Criteria) this;
        }

        public Criteria andTaxnoIsNotNull() {
            addCriterion("taxno is not null");
            return (Criteria) this;
        }

        public Criteria andTaxnoEqualTo(String value) {
            addCriterion("taxno =", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoNotEqualTo(String value) {
            addCriterion("taxno <>", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoGreaterThan(String value) {
            addCriterion("taxno >", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoGreaterThanOrEqualTo(String value) {
            addCriterion("taxno >=", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoLessThan(String value) {
            addCriterion("taxno <", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoLessThanOrEqualTo(String value) {
            addCriterion("taxno <=", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoLike(String value) {
            addCriterion("taxno like", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoNotLike(String value) {
            addCriterion("taxno not like", value, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoIn(List<String> values) {
            addCriterion("taxno in", values, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoNotIn(List<String> values) {
            addCriterion("taxno not in", values, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoBetween(String value1, String value2) {
            addCriterion("taxno between", value1, value2, "taxno");
            return (Criteria) this;
        }

        public Criteria andTaxnoNotBetween(String value1, String value2) {
            addCriterion("taxno not between", value1, value2, "taxno");
            return (Criteria) this;
        }

        public Criteria andLtLonIsNull() {
            addCriterion("lt_lon is null");
            return (Criteria) this;
        }

        public Criteria andLtLonIsNotNull() {
            addCriterion("lt_lon is not null");
            return (Criteria) this;
        }

        public Criteria andLtLonEqualTo(Double value) {
            addCriterion("lt_lon =", value, "ltLon");
            return (Criteria) this;
        }

        public Criteria andLtLonNotEqualTo(Double value) {
            addCriterion("lt_lon <>", value, "ltLon");
            return (Criteria) this;
        }

        public Criteria andLtLonGreaterThan(Double value) {
            addCriterion("lt_lon >", value, "ltLon");
            return (Criteria) this;
        }

        public Criteria andLtLonGreaterThanOrEqualTo(Double value) {
            addCriterion("lt_lon >=", value, "ltLon");
            return (Criteria) this;
        }

        public Criteria andLtLonLessThan(Double value) {
            addCriterion("lt_lon <", value, "ltLon");
            return (Criteria) this;
        }

        public Criteria andLtLonLessThanOrEqualTo(Double value) {
            addCriterion("lt_lon <=", value, "ltLon");
            return (Criteria) this;
        }

        public Criteria andLtLonIn(List<Double> values) {
            addCriterion("lt_lon in", values, "ltLon");
            return (Criteria) this;
        }

        public Criteria andLtLonNotIn(List<Double> values) {
            addCriterion("lt_lon not in", values, "ltLon");
            return (Criteria) this;
        }

        public Criteria andLtLonBetween(Double value1, Double value2) {
            addCriterion("lt_lon between", value1, value2, "ltLon");
            return (Criteria) this;
        }

        public Criteria andLtLonNotBetween(Double value1, Double value2) {
            addCriterion("lt_lon not between", value1, value2, "ltLon");
            return (Criteria) this;
        }

        public Criteria andLtLatIsNull() {
            addCriterion("lt_lat is null");
            return (Criteria) this;
        }

        public Criteria andLtLatIsNotNull() {
            addCriterion("lt_lat is not null");
            return (Criteria) this;
        }

        public Criteria andLtLatEqualTo(Double value) {
            addCriterion("lt_lat =", value, "ltLat");
            return (Criteria) this;
        }

        public Criteria andLtLatNotEqualTo(Double value) {
            addCriterion("lt_lat <>", value, "ltLat");
            return (Criteria) this;
        }

        public Criteria andLtLatGreaterThan(Double value) {
            addCriterion("lt_lat >", value, "ltLat");
            return (Criteria) this;
        }

        public Criteria andLtLatGreaterThanOrEqualTo(Double value) {
            addCriterion("lt_lat >=", value, "ltLat");
            return (Criteria) this;
        }

        public Criteria andLtLatLessThan(Double value) {
            addCriterion("lt_lat <", value, "ltLat");
            return (Criteria) this;
        }

        public Criteria andLtLatLessThanOrEqualTo(Double value) {
            addCriterion("lt_lat <=", value, "ltLat");
            return (Criteria) this;
        }

        public Criteria andLtLatIn(List<Double> values) {
            addCriterion("lt_lat in", values, "ltLat");
            return (Criteria) this;
        }

        public Criteria andLtLatNotIn(List<Double> values) {
            addCriterion("lt_lat not in", values, "ltLat");
            return (Criteria) this;
        }

        public Criteria andLtLatBetween(Double value1, Double value2) {
            addCriterion("lt_lat between", value1, value2, "ltLat");
            return (Criteria) this;
        }

        public Criteria andLtLatNotBetween(Double value1, Double value2) {
            addCriterion("lt_lat not between", value1, value2, "ltLat");
            return (Criteria) this;
        }

        public Criteria andRbLonIsNull() {
            addCriterion("rb_lon is null");
            return (Criteria) this;
        }

        public Criteria andRbLonIsNotNull() {
            addCriterion("rb_lon is not null");
            return (Criteria) this;
        }

        public Criteria andRbLonEqualTo(Double value) {
            addCriterion("rb_lon =", value, "rbLon");
            return (Criteria) this;
        }

        public Criteria andRbLonNotEqualTo(Double value) {
            addCriterion("rb_lon <>", value, "rbLon");
            return (Criteria) this;
        }

        public Criteria andRbLonGreaterThan(Double value) {
            addCriterion("rb_lon >", value, "rbLon");
            return (Criteria) this;
        }

        public Criteria andRbLonGreaterThanOrEqualTo(Double value) {
            addCriterion("rb_lon >=", value, "rbLon");
            return (Criteria) this;
        }

        public Criteria andRbLonLessThan(Double value) {
            addCriterion("rb_lon <", value, "rbLon");
            return (Criteria) this;
        }

        public Criteria andRbLonLessThanOrEqualTo(Double value) {
            addCriterion("rb_lon <=", value, "rbLon");
            return (Criteria) this;
        }

        public Criteria andRbLonIn(List<Double> values) {
            addCriterion("rb_lon in", values, "rbLon");
            return (Criteria) this;
        }

        public Criteria andRbLonNotIn(List<Double> values) {
            addCriterion("rb_lon not in", values, "rbLon");
            return (Criteria) this;
        }

        public Criteria andRbLonBetween(Double value1, Double value2) {
            addCriterion("rb_lon between", value1, value2, "rbLon");
            return (Criteria) this;
        }

        public Criteria andRbLonNotBetween(Double value1, Double value2) {
            addCriterion("rb_lon not between", value1, value2, "rbLon");
            return (Criteria) this;
        }

        public Criteria andRbLatIsNull() {
            addCriterion("rb_lat is null");
            return (Criteria) this;
        }

        public Criteria andRbLatIsNotNull() {
            addCriterion("rb_lat is not null");
            return (Criteria) this;
        }

        public Criteria andRbLatEqualTo(Double value) {
            addCriterion("rb_lat =", value, "rbLat");
            return (Criteria) this;
        }

        public Criteria andRbLatNotEqualTo(Double value) {
            addCriterion("rb_lat <>", value, "rbLat");
            return (Criteria) this;
        }

        public Criteria andRbLatGreaterThan(Double value) {
            addCriterion("rb_lat >", value, "rbLat");
            return (Criteria) this;
        }

        public Criteria andRbLatGreaterThanOrEqualTo(Double value) {
            addCriterion("rb_lat >=", value, "rbLat");
            return (Criteria) this;
        }

        public Criteria andRbLatLessThan(Double value) {
            addCriterion("rb_lat <", value, "rbLat");
            return (Criteria) this;
        }

        public Criteria andRbLatLessThanOrEqualTo(Double value) {
            addCriterion("rb_lat <=", value, "rbLat");
            return (Criteria) this;
        }

        public Criteria andRbLatIn(List<Double> values) {
            addCriterion("rb_lat in", values, "rbLat");
            return (Criteria) this;
        }

        public Criteria andRbLatNotIn(List<Double> values) {
            addCriterion("rb_lat not in", values, "rbLat");
            return (Criteria) this;
        }

        public Criteria andRbLatBetween(Double value1, Double value2) {
            addCriterion("rb_lat between", value1, value2, "rbLat");
            return (Criteria) this;
        }

        public Criteria andRbLatNotBetween(Double value1, Double value2) {
            addCriterion("rb_lat not between", value1, value2, "rbLat");
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

        public Criteria andContacts1NameIsNull() {
            addCriterion("contacts1_name is null");
            return (Criteria) this;
        }

        public Criteria andContacts1NameIsNotNull() {
            addCriterion("contacts1_name is not null");
            return (Criteria) this;
        }

        public Criteria andContacts1NameEqualTo(String value) {
            addCriterion("contacts1_name =", value, "contacts1Name");
            return (Criteria) this;
        }

        public Criteria andContacts1NameNotEqualTo(String value) {
            addCriterion("contacts1_name <>", value, "contacts1Name");
            return (Criteria) this;
        }

        public Criteria andContacts1NameGreaterThan(String value) {
            addCriterion("contacts1_name >", value, "contacts1Name");
            return (Criteria) this;
        }

        public Criteria andContacts1NameGreaterThanOrEqualTo(String value) {
            addCriterion("contacts1_name >=", value, "contacts1Name");
            return (Criteria) this;
        }

        public Criteria andContacts1NameLessThan(String value) {
            addCriterion("contacts1_name <", value, "contacts1Name");
            return (Criteria) this;
        }

        public Criteria andContacts1NameLessThanOrEqualTo(String value) {
            addCriterion("contacts1_name <=", value, "contacts1Name");
            return (Criteria) this;
        }

        public Criteria andContacts1NameLike(String value) {
            addCriterion("contacts1_name like", value, "contacts1Name");
            return (Criteria) this;
        }

        public Criteria andContacts1NameNotLike(String value) {
            addCriterion("contacts1_name not like", value, "contacts1Name");
            return (Criteria) this;
        }

        public Criteria andContacts1NameIn(List<String> values) {
            addCriterion("contacts1_name in", values, "contacts1Name");
            return (Criteria) this;
        }

        public Criteria andContacts1NameNotIn(List<String> values) {
            addCriterion("contacts1_name not in", values, "contacts1Name");
            return (Criteria) this;
        }

        public Criteria andContacts1NameBetween(String value1, String value2) {
            addCriterion("contacts1_name between", value1, value2, "contacts1Name");
            return (Criteria) this;
        }

        public Criteria andContacts1NameNotBetween(String value1, String value2) {
            addCriterion("contacts1_name not between", value1, value2, "contacts1Name");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileIsNull() {
            addCriterion("contacts1_mobile is null");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileIsNotNull() {
            addCriterion("contacts1_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileEqualTo(String value) {
            addCriterion("contacts1_mobile =", value, "contacts1Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileNotEqualTo(String value) {
            addCriterion("contacts1_mobile <>", value, "contacts1Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileGreaterThan(String value) {
            addCriterion("contacts1_mobile >", value, "contacts1Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileGreaterThanOrEqualTo(String value) {
            addCriterion("contacts1_mobile >=", value, "contacts1Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileLessThan(String value) {
            addCriterion("contacts1_mobile <", value, "contacts1Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileLessThanOrEqualTo(String value) {
            addCriterion("contacts1_mobile <=", value, "contacts1Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileLike(String value) {
            addCriterion("contacts1_mobile like", value, "contacts1Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileNotLike(String value) {
            addCriterion("contacts1_mobile not like", value, "contacts1Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileIn(List<String> values) {
            addCriterion("contacts1_mobile in", values, "contacts1Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileNotIn(List<String> values) {
            addCriterion("contacts1_mobile not in", values, "contacts1Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileBetween(String value1, String value2) {
            addCriterion("contacts1_mobile between", value1, value2, "contacts1Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts1MobileNotBetween(String value1, String value2) {
            addCriterion("contacts1_mobile not between", value1, value2, "contacts1Mobile");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andContacts2NameIsNull() {
            addCriterion("contacts2_name is null");
            return (Criteria) this;
        }

        public Criteria andContacts2NameIsNotNull() {
            addCriterion("contacts2_name is not null");
            return (Criteria) this;
        }

        public Criteria andContacts2NameEqualTo(String value) {
            addCriterion("contacts2_name =", value, "contacts2Name");
            return (Criteria) this;
        }

        public Criteria andContacts2NameNotEqualTo(String value) {
            addCriterion("contacts2_name <>", value, "contacts2Name");
            return (Criteria) this;
        }

        public Criteria andContacts2NameGreaterThan(String value) {
            addCriterion("contacts2_name >", value, "contacts2Name");
            return (Criteria) this;
        }

        public Criteria andContacts2NameGreaterThanOrEqualTo(String value) {
            addCriterion("contacts2_name >=", value, "contacts2Name");
            return (Criteria) this;
        }

        public Criteria andContacts2NameLessThan(String value) {
            addCriterion("contacts2_name <", value, "contacts2Name");
            return (Criteria) this;
        }

        public Criteria andContacts2NameLessThanOrEqualTo(String value) {
            addCriterion("contacts2_name <=", value, "contacts2Name");
            return (Criteria) this;
        }

        public Criteria andContacts2NameLike(String value) {
            addCriterion("contacts2_name like", value, "contacts2Name");
            return (Criteria) this;
        }

        public Criteria andContacts2NameNotLike(String value) {
            addCriterion("contacts2_name not like", value, "contacts2Name");
            return (Criteria) this;
        }

        public Criteria andContacts2NameIn(List<String> values) {
            addCriterion("contacts2_name in", values, "contacts2Name");
            return (Criteria) this;
        }

        public Criteria andContacts2NameNotIn(List<String> values) {
            addCriterion("contacts2_name not in", values, "contacts2Name");
            return (Criteria) this;
        }

        public Criteria andContacts2NameBetween(String value1, String value2) {
            addCriterion("contacts2_name between", value1, value2, "contacts2Name");
            return (Criteria) this;
        }

        public Criteria andContacts2NameNotBetween(String value1, String value2) {
            addCriterion("contacts2_name not between", value1, value2, "contacts2Name");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileIsNull() {
            addCriterion("contacts2_mobile is null");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileIsNotNull() {
            addCriterion("contacts2_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileEqualTo(String value) {
            addCriterion("contacts2_mobile =", value, "contacts2Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileNotEqualTo(String value) {
            addCriterion("contacts2_mobile <>", value, "contacts2Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileGreaterThan(String value) {
            addCriterion("contacts2_mobile >", value, "contacts2Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileGreaterThanOrEqualTo(String value) {
            addCriterion("contacts2_mobile >=", value, "contacts2Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileLessThan(String value) {
            addCriterion("contacts2_mobile <", value, "contacts2Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileLessThanOrEqualTo(String value) {
            addCriterion("contacts2_mobile <=", value, "contacts2Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileLike(String value) {
            addCriterion("contacts2_mobile like", value, "contacts2Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileNotLike(String value) {
            addCriterion("contacts2_mobile not like", value, "contacts2Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileIn(List<String> values) {
            addCriterion("contacts2_mobile in", values, "contacts2Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileNotIn(List<String> values) {
            addCriterion("contacts2_mobile not in", values, "contacts2Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileBetween(String value1, String value2) {
            addCriterion("contacts2_mobile between", value1, value2, "contacts2Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts2MobileNotBetween(String value1, String value2) {
            addCriterion("contacts2_mobile not between", value1, value2, "contacts2Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts3NameIsNull() {
            addCriterion("contacts3_name is null");
            return (Criteria) this;
        }

        public Criteria andContacts3NameIsNotNull() {
            addCriterion("contacts3_name is not null");
            return (Criteria) this;
        }

        public Criteria andContacts3NameEqualTo(String value) {
            addCriterion("contacts3_name =", value, "contacts3Name");
            return (Criteria) this;
        }

        public Criteria andContacts3NameNotEqualTo(String value) {
            addCriterion("contacts3_name <>", value, "contacts3Name");
            return (Criteria) this;
        }

        public Criteria andContacts3NameGreaterThan(String value) {
            addCriterion("contacts3_name >", value, "contacts3Name");
            return (Criteria) this;
        }

        public Criteria andContacts3NameGreaterThanOrEqualTo(String value) {
            addCriterion("contacts3_name >=", value, "contacts3Name");
            return (Criteria) this;
        }

        public Criteria andContacts3NameLessThan(String value) {
            addCriterion("contacts3_name <", value, "contacts3Name");
            return (Criteria) this;
        }

        public Criteria andContacts3NameLessThanOrEqualTo(String value) {
            addCriterion("contacts3_name <=", value, "contacts3Name");
            return (Criteria) this;
        }

        public Criteria andContacts3NameLike(String value) {
            addCriterion("contacts3_name like", value, "contacts3Name");
            return (Criteria) this;
        }

        public Criteria andContacts3NameNotLike(String value) {
            addCriterion("contacts3_name not like", value, "contacts3Name");
            return (Criteria) this;
        }

        public Criteria andContacts3NameIn(List<String> values) {
            addCriterion("contacts3_name in", values, "contacts3Name");
            return (Criteria) this;
        }

        public Criteria andContacts3NameNotIn(List<String> values) {
            addCriterion("contacts3_name not in", values, "contacts3Name");
            return (Criteria) this;
        }

        public Criteria andContacts3NameBetween(String value1, String value2) {
            addCriterion("contacts3_name between", value1, value2, "contacts3Name");
            return (Criteria) this;
        }

        public Criteria andContacts3NameNotBetween(String value1, String value2) {
            addCriterion("contacts3_name not between", value1, value2, "contacts3Name");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileIsNull() {
            addCriterion("contacts3_mobile is null");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileIsNotNull() {
            addCriterion("contacts3_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileEqualTo(String value) {
            addCriterion("contacts3_mobile =", value, "contacts3Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileNotEqualTo(String value) {
            addCriterion("contacts3_mobile <>", value, "contacts3Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileGreaterThan(String value) {
            addCriterion("contacts3_mobile >", value, "contacts3Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileGreaterThanOrEqualTo(String value) {
            addCriterion("contacts3_mobile >=", value, "contacts3Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileLessThan(String value) {
            addCriterion("contacts3_mobile <", value, "contacts3Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileLessThanOrEqualTo(String value) {
            addCriterion("contacts3_mobile <=", value, "contacts3Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileLike(String value) {
            addCriterion("contacts3_mobile like", value, "contacts3Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileNotLike(String value) {
            addCriterion("contacts3_mobile not like", value, "contacts3Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileIn(List<String> values) {
            addCriterion("contacts3_mobile in", values, "contacts3Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileNotIn(List<String> values) {
            addCriterion("contacts3_mobile not in", values, "contacts3Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileBetween(String value1, String value2) {
            addCriterion("contacts3_mobile between", value1, value2, "contacts3Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts3MobileNotBetween(String value1, String value2) {
            addCriterion("contacts3_mobile not between", value1, value2, "contacts3Mobile");
            return (Criteria) this;
        }

        public Criteria andContacts1PostIsNull() {
            addCriterion("contacts1_post is null");
            return (Criteria) this;
        }

        public Criteria andContacts1PostIsNotNull() {
            addCriterion("contacts1_post is not null");
            return (Criteria) this;
        }

        public Criteria andContacts1PostEqualTo(String value) {
            addCriterion("contacts1_post =", value, "contacts1Post");
            return (Criteria) this;
        }

        public Criteria andContacts1PostNotEqualTo(String value) {
            addCriterion("contacts1_post <>", value, "contacts1Post");
            return (Criteria) this;
        }

        public Criteria andContacts1PostGreaterThan(String value) {
            addCriterion("contacts1_post >", value, "contacts1Post");
            return (Criteria) this;
        }

        public Criteria andContacts1PostGreaterThanOrEqualTo(String value) {
            addCriterion("contacts1_post >=", value, "contacts1Post");
            return (Criteria) this;
        }

        public Criteria andContacts1PostLessThan(String value) {
            addCriterion("contacts1_post <", value, "contacts1Post");
            return (Criteria) this;
        }

        public Criteria andContacts1PostLessThanOrEqualTo(String value) {
            addCriterion("contacts1_post <=", value, "contacts1Post");
            return (Criteria) this;
        }

        public Criteria andContacts1PostLike(String value) {
            addCriterion("contacts1_post like", value, "contacts1Post");
            return (Criteria) this;
        }

        public Criteria andContacts1PostNotLike(String value) {
            addCriterion("contacts1_post not like", value, "contacts1Post");
            return (Criteria) this;
        }

        public Criteria andContacts1PostIn(List<String> values) {
            addCriterion("contacts1_post in", values, "contacts1Post");
            return (Criteria) this;
        }

        public Criteria andContacts1PostNotIn(List<String> values) {
            addCriterion("contacts1_post not in", values, "contacts1Post");
            return (Criteria) this;
        }

        public Criteria andContacts1PostBetween(String value1, String value2) {
            addCriterion("contacts1_post between", value1, value2, "contacts1Post");
            return (Criteria) this;
        }

        public Criteria andContacts1PostNotBetween(String value1, String value2) {
            addCriterion("contacts1_post not between", value1, value2, "contacts1Post");
            return (Criteria) this;
        }

        public Criteria andContacts2PostIsNull() {
            addCriterion("contacts2_post is null");
            return (Criteria) this;
        }

        public Criteria andContacts2PostIsNotNull() {
            addCriterion("contacts2_post is not null");
            return (Criteria) this;
        }

        public Criteria andContacts2PostEqualTo(String value) {
            addCriterion("contacts2_post =", value, "contacts2Post");
            return (Criteria) this;
        }

        public Criteria andContacts2PostNotEqualTo(String value) {
            addCriterion("contacts2_post <>", value, "contacts2Post");
            return (Criteria) this;
        }

        public Criteria andContacts2PostGreaterThan(String value) {
            addCriterion("contacts2_post >", value, "contacts2Post");
            return (Criteria) this;
        }

        public Criteria andContacts2PostGreaterThanOrEqualTo(String value) {
            addCriterion("contacts2_post >=", value, "contacts2Post");
            return (Criteria) this;
        }

        public Criteria andContacts2PostLessThan(String value) {
            addCriterion("contacts2_post <", value, "contacts2Post");
            return (Criteria) this;
        }

        public Criteria andContacts2PostLessThanOrEqualTo(String value) {
            addCriterion("contacts2_post <=", value, "contacts2Post");
            return (Criteria) this;
        }

        public Criteria andContacts2PostLike(String value) {
            addCriterion("contacts2_post like", value, "contacts2Post");
            return (Criteria) this;
        }

        public Criteria andContacts2PostNotLike(String value) {
            addCriterion("contacts2_post not like", value, "contacts2Post");
            return (Criteria) this;
        }

        public Criteria andContacts2PostIn(List<String> values) {
            addCriterion("contacts2_post in", values, "contacts2Post");
            return (Criteria) this;
        }

        public Criteria andContacts2PostNotIn(List<String> values) {
            addCriterion("contacts2_post not in", values, "contacts2Post");
            return (Criteria) this;
        }

        public Criteria andContacts2PostBetween(String value1, String value2) {
            addCriterion("contacts2_post between", value1, value2, "contacts2Post");
            return (Criteria) this;
        }

        public Criteria andContacts2PostNotBetween(String value1, String value2) {
            addCriterion("contacts2_post not between", value1, value2, "contacts2Post");
            return (Criteria) this;
        }

        public Criteria andContacts3PostIsNull() {
            addCriterion("contacts3_post is null");
            return (Criteria) this;
        }

        public Criteria andContacts3PostIsNotNull() {
            addCriterion("contacts3_post is not null");
            return (Criteria) this;
        }

        public Criteria andContacts3PostEqualTo(String value) {
            addCriterion("contacts3_post =", value, "contacts3Post");
            return (Criteria) this;
        }

        public Criteria andContacts3PostNotEqualTo(String value) {
            addCriterion("contacts3_post <>", value, "contacts3Post");
            return (Criteria) this;
        }

        public Criteria andContacts3PostGreaterThan(String value) {
            addCriterion("contacts3_post >", value, "contacts3Post");
            return (Criteria) this;
        }

        public Criteria andContacts3PostGreaterThanOrEqualTo(String value) {
            addCriterion("contacts3_post >=", value, "contacts3Post");
            return (Criteria) this;
        }

        public Criteria andContacts3PostLessThan(String value) {
            addCriterion("contacts3_post <", value, "contacts3Post");
            return (Criteria) this;
        }

        public Criteria andContacts3PostLessThanOrEqualTo(String value) {
            addCriterion("contacts3_post <=", value, "contacts3Post");
            return (Criteria) this;
        }

        public Criteria andContacts3PostLike(String value) {
            addCriterion("contacts3_post like", value, "contacts3Post");
            return (Criteria) this;
        }

        public Criteria andContacts3PostNotLike(String value) {
            addCriterion("contacts3_post not like", value, "contacts3Post");
            return (Criteria) this;
        }

        public Criteria andContacts3PostIn(List<String> values) {
            addCriterion("contacts3_post in", values, "contacts3Post");
            return (Criteria) this;
        }

        public Criteria andContacts3PostNotIn(List<String> values) {
            addCriterion("contacts3_post not in", values, "contacts3Post");
            return (Criteria) this;
        }

        public Criteria andContacts3PostBetween(String value1, String value2) {
            addCriterion("contacts3_post between", value1, value2, "contacts3Post");
            return (Criteria) this;
        }

        public Criteria andContacts3PostNotBetween(String value1, String value2) {
            addCriterion("contacts3_post not between", value1, value2, "contacts3Post");
            return (Criteria) this;
        }

        public Criteria andContacts1MailIsNull() {
            addCriterion("contacts1_mail is null");
            return (Criteria) this;
        }

        public Criteria andContacts1MailIsNotNull() {
            addCriterion("contacts1_mail is not null");
            return (Criteria) this;
        }

        public Criteria andContacts1MailEqualTo(String value) {
            addCriterion("contacts1_mail =", value, "contacts1Mail");
            return (Criteria) this;
        }

        public Criteria andContacts1MailNotEqualTo(String value) {
            addCriterion("contacts1_mail <>", value, "contacts1Mail");
            return (Criteria) this;
        }

        public Criteria andContacts1MailGreaterThan(String value) {
            addCriterion("contacts1_mail >", value, "contacts1Mail");
            return (Criteria) this;
        }

        public Criteria andContacts1MailGreaterThanOrEqualTo(String value) {
            addCriterion("contacts1_mail >=", value, "contacts1Mail");
            return (Criteria) this;
        }

        public Criteria andContacts1MailLessThan(String value) {
            addCriterion("contacts1_mail <", value, "contacts1Mail");
            return (Criteria) this;
        }

        public Criteria andContacts1MailLessThanOrEqualTo(String value) {
            addCriterion("contacts1_mail <=", value, "contacts1Mail");
            return (Criteria) this;
        }

        public Criteria andContacts1MailLike(String value) {
            addCriterion("contacts1_mail like", value, "contacts1Mail");
            return (Criteria) this;
        }

        public Criteria andContacts1MailNotLike(String value) {
            addCriterion("contacts1_mail not like", value, "contacts1Mail");
            return (Criteria) this;
        }

        public Criteria andContacts1MailIn(List<String> values) {
            addCriterion("contacts1_mail in", values, "contacts1Mail");
            return (Criteria) this;
        }

        public Criteria andContacts1MailNotIn(List<String> values) {
            addCriterion("contacts1_mail not in", values, "contacts1Mail");
            return (Criteria) this;
        }

        public Criteria andContacts1MailBetween(String value1, String value2) {
            addCriterion("contacts1_mail between", value1, value2, "contacts1Mail");
            return (Criteria) this;
        }

        public Criteria andContacts1MailNotBetween(String value1, String value2) {
            addCriterion("contacts1_mail not between", value1, value2, "contacts1Mail");
            return (Criteria) this;
        }

        public Criteria andContacts2MailIsNull() {
            addCriterion("contacts2_mail is null");
            return (Criteria) this;
        }

        public Criteria andContacts2MailIsNotNull() {
            addCriterion("contacts2_mail is not null");
            return (Criteria) this;
        }

        public Criteria andContacts2MailEqualTo(String value) {
            addCriterion("contacts2_mail =", value, "contacts2Mail");
            return (Criteria) this;
        }

        public Criteria andContacts2MailNotEqualTo(String value) {
            addCriterion("contacts2_mail <>", value, "contacts2Mail");
            return (Criteria) this;
        }

        public Criteria andContacts2MailGreaterThan(String value) {
            addCriterion("contacts2_mail >", value, "contacts2Mail");
            return (Criteria) this;
        }

        public Criteria andContacts2MailGreaterThanOrEqualTo(String value) {
            addCriterion("contacts2_mail >=", value, "contacts2Mail");
            return (Criteria) this;
        }

        public Criteria andContacts2MailLessThan(String value) {
            addCriterion("contacts2_mail <", value, "contacts2Mail");
            return (Criteria) this;
        }

        public Criteria andContacts2MailLessThanOrEqualTo(String value) {
            addCriterion("contacts2_mail <=", value, "contacts2Mail");
            return (Criteria) this;
        }

        public Criteria andContacts2MailLike(String value) {
            addCriterion("contacts2_mail like", value, "contacts2Mail");
            return (Criteria) this;
        }

        public Criteria andContacts2MailNotLike(String value) {
            addCriterion("contacts2_mail not like", value, "contacts2Mail");
            return (Criteria) this;
        }

        public Criteria andContacts2MailIn(List<String> values) {
            addCriterion("contacts2_mail in", values, "contacts2Mail");
            return (Criteria) this;
        }

        public Criteria andContacts2MailNotIn(List<String> values) {
            addCriterion("contacts2_mail not in", values, "contacts2Mail");
            return (Criteria) this;
        }

        public Criteria andContacts2MailBetween(String value1, String value2) {
            addCriterion("contacts2_mail between", value1, value2, "contacts2Mail");
            return (Criteria) this;
        }

        public Criteria andContacts2MailNotBetween(String value1, String value2) {
            addCriterion("contacts2_mail not between", value1, value2, "contacts2Mail");
            return (Criteria) this;
        }

        public Criteria andContacts3MailIsNull() {
            addCriterion("contacts3_mail is null");
            return (Criteria) this;
        }

        public Criteria andContacts3MailIsNotNull() {
            addCriterion("contacts3_mail is not null");
            return (Criteria) this;
        }

        public Criteria andContacts3MailEqualTo(String value) {
            addCriterion("contacts3_mail =", value, "contacts3Mail");
            return (Criteria) this;
        }

        public Criteria andContacts3MailNotEqualTo(String value) {
            addCriterion("contacts3_mail <>", value, "contacts3Mail");
            return (Criteria) this;
        }

        public Criteria andContacts3MailGreaterThan(String value) {
            addCriterion("contacts3_mail >", value, "contacts3Mail");
            return (Criteria) this;
        }

        public Criteria andContacts3MailGreaterThanOrEqualTo(String value) {
            addCriterion("contacts3_mail >=", value, "contacts3Mail");
            return (Criteria) this;
        }

        public Criteria andContacts3MailLessThan(String value) {
            addCriterion("contacts3_mail <", value, "contacts3Mail");
            return (Criteria) this;
        }

        public Criteria andContacts3MailLessThanOrEqualTo(String value) {
            addCriterion("contacts3_mail <=", value, "contacts3Mail");
            return (Criteria) this;
        }

        public Criteria andContacts3MailLike(String value) {
            addCriterion("contacts3_mail like", value, "contacts3Mail");
            return (Criteria) this;
        }

        public Criteria andContacts3MailNotLike(String value) {
            addCriterion("contacts3_mail not like", value, "contacts3Mail");
            return (Criteria) this;
        }

        public Criteria andContacts3MailIn(List<String> values) {
            addCriterion("contacts3_mail in", values, "contacts3Mail");
            return (Criteria) this;
        }

        public Criteria andContacts3MailNotIn(List<String> values) {
            addCriterion("contacts3_mail not in", values, "contacts3Mail");
            return (Criteria) this;
        }

        public Criteria andContacts3MailBetween(String value1, String value2) {
            addCriterion("contacts3_mail between", value1, value2, "contacts3Mail");
            return (Criteria) this;
        }

        public Criteria andContacts3MailNotBetween(String value1, String value2) {
            addCriterion("contacts3_mail not between", value1, value2, "contacts3Mail");
            return (Criteria) this;
        }

        public Criteria andTaxNameIsNull() {
            addCriterion("tax_name is null");
            return (Criteria) this;
        }

        public Criteria andTaxNameIsNotNull() {
            addCriterion("tax_name is not null");
            return (Criteria) this;
        }

        public Criteria andTaxNameEqualTo(String value) {
            addCriterion("tax_name =", value, "taxName");
            return (Criteria) this;
        }

        public Criteria andTaxNameNotEqualTo(String value) {
            addCriterion("tax_name <>", value, "taxName");
            return (Criteria) this;
        }

        public Criteria andTaxNameGreaterThan(String value) {
            addCriterion("tax_name >", value, "taxName");
            return (Criteria) this;
        }

        public Criteria andTaxNameGreaterThanOrEqualTo(String value) {
            addCriterion("tax_name >=", value, "taxName");
            return (Criteria) this;
        }

        public Criteria andTaxNameLessThan(String value) {
            addCriterion("tax_name <", value, "taxName");
            return (Criteria) this;
        }

        public Criteria andTaxNameLessThanOrEqualTo(String value) {
            addCriterion("tax_name <=", value, "taxName");
            return (Criteria) this;
        }

        public Criteria andTaxNameLike(String value) {
            addCriterion("tax_name like", value, "taxName");
            return (Criteria) this;
        }

        public Criteria andTaxNameNotLike(String value) {
            addCriterion("tax_name not like", value, "taxName");
            return (Criteria) this;
        }

        public Criteria andTaxNameIn(List<String> values) {
            addCriterion("tax_name in", values, "taxName");
            return (Criteria) this;
        }

        public Criteria andTaxNameNotIn(List<String> values) {
            addCriterion("tax_name not in", values, "taxName");
            return (Criteria) this;
        }

        public Criteria andTaxNameBetween(String value1, String value2) {
            addCriterion("tax_name between", value1, value2, "taxName");
            return (Criteria) this;
        }

        public Criteria andTaxNameNotBetween(String value1, String value2) {
            addCriterion("tax_name not between", value1, value2, "taxName");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneIsNull() {
            addCriterion("tax_phone is null");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneIsNotNull() {
            addCriterion("tax_phone is not null");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneEqualTo(String value) {
            addCriterion("tax_phone =", value, "taxPhone");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneNotEqualTo(String value) {
            addCriterion("tax_phone <>", value, "taxPhone");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneGreaterThan(String value) {
            addCriterion("tax_phone >", value, "taxPhone");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("tax_phone >=", value, "taxPhone");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneLessThan(String value) {
            addCriterion("tax_phone <", value, "taxPhone");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneLessThanOrEqualTo(String value) {
            addCriterion("tax_phone <=", value, "taxPhone");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneLike(String value) {
            addCriterion("tax_phone like", value, "taxPhone");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneNotLike(String value) {
            addCriterion("tax_phone not like", value, "taxPhone");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneIn(List<String> values) {
            addCriterion("tax_phone in", values, "taxPhone");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneNotIn(List<String> values) {
            addCriterion("tax_phone not in", values, "taxPhone");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneBetween(String value1, String value2) {
            addCriterion("tax_phone between", value1, value2, "taxPhone");
            return (Criteria) this;
        }

        public Criteria andTaxPhoneNotBetween(String value1, String value2) {
            addCriterion("tax_phone not between", value1, value2, "taxPhone");
            return (Criteria) this;
        }

        public Criteria andCopyFileIsNull() {
            addCriterion("copy_file is null");
            return (Criteria) this;
        }

        public Criteria andCopyFileIsNotNull() {
            addCriterion("copy_file is not null");
            return (Criteria) this;
        }

        public Criteria andCopyFileEqualTo(String value) {
            addCriterion("copy_file =", value, "copyFile");
            return (Criteria) this;
        }

        public Criteria andCopyFileNotEqualTo(String value) {
            addCriterion("copy_file <>", value, "copyFile");
            return (Criteria) this;
        }

        public Criteria andCopyFileGreaterThan(String value) {
            addCriterion("copy_file >", value, "copyFile");
            return (Criteria) this;
        }

        public Criteria andCopyFileGreaterThanOrEqualTo(String value) {
            addCriterion("copy_file >=", value, "copyFile");
            return (Criteria) this;
        }

        public Criteria andCopyFileLessThan(String value) {
            addCriterion("copy_file <", value, "copyFile");
            return (Criteria) this;
        }

        public Criteria andCopyFileLessThanOrEqualTo(String value) {
            addCriterion("copy_file <=", value, "copyFile");
            return (Criteria) this;
        }

        public Criteria andCopyFileLike(String value) {
            addCriterion("copy_file like", value, "copyFile");
            return (Criteria) this;
        }

        public Criteria andCopyFileNotLike(String value) {
            addCriterion("copy_file not like", value, "copyFile");
            return (Criteria) this;
        }

        public Criteria andCopyFileIn(List<String> values) {
            addCriterion("copy_file in", values, "copyFile");
            return (Criteria) this;
        }

        public Criteria andCopyFileNotIn(List<String> values) {
            addCriterion("copy_file not in", values, "copyFile");
            return (Criteria) this;
        }

        public Criteria andCopyFileBetween(String value1, String value2) {
            addCriterion("copy_file between", value1, value2, "copyFile");
            return (Criteria) this;
        }

        public Criteria andCopyFileNotBetween(String value1, String value2) {
            addCriterion("copy_file not between", value1, value2, "copyFile");
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

        public Criteria andPictureIsNull() {
            addCriterion("picture is null");
            return (Criteria) this;
        }

        public Criteria andPictureIsNotNull() {
            addCriterion("picture is not null");
            return (Criteria) this;
        }

        public Criteria andPictureEqualTo(String value) {
            addCriterion("picture =", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotEqualTo(String value) {
            addCriterion("picture <>", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThan(String value) {
            addCriterion("picture >", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThanOrEqualTo(String value) {
            addCriterion("picture >=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThan(String value) {
            addCriterion("picture <", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThanOrEqualTo(String value) {
            addCriterion("picture <=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLike(String value) {
            addCriterion("picture like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotLike(String value) {
            addCriterion("picture not like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureIn(List<String> values) {
            addCriterion("picture in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotIn(List<String> values) {
            addCriterion("picture not in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureBetween(String value1, String value2) {
            addCriterion("picture between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotBetween(String value1, String value2) {
            addCriterion("picture not between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andExportIsNull() {
            addCriterion("export is null");
            return (Criteria) this;
        }

        public Criteria andExportIsNotNull() {
            addCriterion("export is not null");
            return (Criteria) this;
        }

        public Criteria andExportEqualTo(Integer value) {
            addCriterion("export =", value, "export");
            return (Criteria) this;
        }

        public Criteria andExportNotEqualTo(Integer value) {
            addCriterion("export <>", value, "export");
            return (Criteria) this;
        }

        public Criteria andExportGreaterThan(Integer value) {
            addCriterion("export >", value, "export");
            return (Criteria) this;
        }

        public Criteria andExportGreaterThanOrEqualTo(Integer value) {
            addCriterion("export >=", value, "export");
            return (Criteria) this;
        }

        public Criteria andExportLessThan(Integer value) {
            addCriterion("export <", value, "export");
            return (Criteria) this;
        }

        public Criteria andExportLessThanOrEqualTo(Integer value) {
            addCriterion("export <=", value, "export");
            return (Criteria) this;
        }

        public Criteria andExportIn(List<Integer> values) {
            addCriterion("export in", values, "export");
            return (Criteria) this;
        }

        public Criteria andExportNotIn(List<Integer> values) {
            addCriterion("export not in", values, "export");
            return (Criteria) this;
        }

        public Criteria andExportBetween(Integer value1, Integer value2) {
            addCriterion("export between", value1, value2, "export");
            return (Criteria) this;
        }

        public Criteria andExportNotBetween(Integer value1, Integer value2) {
            addCriterion("export not between", value1, value2, "export");
            return (Criteria) this;
        }

        public Criteria andAppidIsNull() {
            addCriterion("appId is null");
            return (Criteria) this;
        }

        public Criteria andAppidIsNotNull() {
            addCriterion("appId is not null");
            return (Criteria) this;
        }

        public Criteria andAppidEqualTo(String value) {
            addCriterion("appId =", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotEqualTo(String value) {
            addCriterion("appId <>", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThan(String value) {
            addCriterion("appId >", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThanOrEqualTo(String value) {
            addCriterion("appId >=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThan(String value) {
            addCriterion("appId <", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThanOrEqualTo(String value) {
            addCriterion("appId <=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLike(String value) {
            addCriterion("appId like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotLike(String value) {
            addCriterion("appId not like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidIn(List<String> values) {
            addCriterion("appId in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotIn(List<String> values) {
            addCriterion("appId not in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidBetween(String value1, String value2) {
            addCriterion("appId between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotBetween(String value1, String value2) {
            addCriterion("appId not between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppsecretIsNull() {
            addCriterion("appSecret is null");
            return (Criteria) this;
        }

        public Criteria andAppsecretIsNotNull() {
            addCriterion("appSecret is not null");
            return (Criteria) this;
        }

        public Criteria andAppsecretEqualTo(String value) {
            addCriterion("appSecret =", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotEqualTo(String value) {
            addCriterion("appSecret <>", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretGreaterThan(String value) {
            addCriterion("appSecret >", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretGreaterThanOrEqualTo(String value) {
            addCriterion("appSecret >=", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretLessThan(String value) {
            addCriterion("appSecret <", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretLessThanOrEqualTo(String value) {
            addCriterion("appSecret <=", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretLike(String value) {
            addCriterion("appSecret like", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotLike(String value) {
            addCriterion("appSecret not like", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretIn(List<String> values) {
            addCriterion("appSecret in", values, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotIn(List<String> values) {
            addCriterion("appSecret not in", values, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretBetween(String value1, String value2) {
            addCriterion("appSecret between", value1, value2, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotBetween(String value1, String value2) {
            addCriterion("appSecret not between", value1, value2, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppid2IsNull() {
            addCriterion("appId2 is null");
            return (Criteria) this;
        }

        public Criteria andAppid2IsNotNull() {
            addCriterion("appId2 is not null");
            return (Criteria) this;
        }

        public Criteria andAppid2EqualTo(String value) {
            addCriterion("appId2 =", value, "appid2");
            return (Criteria) this;
        }

        public Criteria andAppid2NotEqualTo(String value) {
            addCriterion("appId2 <>", value, "appid2");
            return (Criteria) this;
        }

        public Criteria andAppid2GreaterThan(String value) {
            addCriterion("appId2 >", value, "appid2");
            return (Criteria) this;
        }

        public Criteria andAppid2GreaterThanOrEqualTo(String value) {
            addCriterion("appId2 >=", value, "appid2");
            return (Criteria) this;
        }

        public Criteria andAppid2LessThan(String value) {
            addCriterion("appId2 <", value, "appid2");
            return (Criteria) this;
        }

        public Criteria andAppid2LessThanOrEqualTo(String value) {
            addCriterion("appId2 <=", value, "appid2");
            return (Criteria) this;
        }

        public Criteria andAppid2Like(String value) {
            addCriterion("appId2 like", value, "appid2");
            return (Criteria) this;
        }

        public Criteria andAppid2NotLike(String value) {
            addCriterion("appId2 not like", value, "appid2");
            return (Criteria) this;
        }

        public Criteria andAppid2In(List<String> values) {
            addCriterion("appId2 in", values, "appid2");
            return (Criteria) this;
        }

        public Criteria andAppid2NotIn(List<String> values) {
            addCriterion("appId2 not in", values, "appid2");
            return (Criteria) this;
        }

        public Criteria andAppid2Between(String value1, String value2) {
            addCriterion("appId2 between", value1, value2, "appid2");
            return (Criteria) this;
        }

        public Criteria andAppid2NotBetween(String value1, String value2) {
            addCriterion("appId2 not between", value1, value2, "appid2");
            return (Criteria) this;
        }

        public Criteria andAppsecret2IsNull() {
            addCriterion("appSecret2 is null");
            return (Criteria) this;
        }

        public Criteria andAppsecret2IsNotNull() {
            addCriterion("appSecret2 is not null");
            return (Criteria) this;
        }

        public Criteria andAppsecret2EqualTo(String value) {
            addCriterion("appSecret2 =", value, "appsecret2");
            return (Criteria) this;
        }

        public Criteria andAppsecret2NotEqualTo(String value) {
            addCriterion("appSecret2 <>", value, "appsecret2");
            return (Criteria) this;
        }

        public Criteria andAppsecret2GreaterThan(String value) {
            addCriterion("appSecret2 >", value, "appsecret2");
            return (Criteria) this;
        }

        public Criteria andAppsecret2GreaterThanOrEqualTo(String value) {
            addCriterion("appSecret2 >=", value, "appsecret2");
            return (Criteria) this;
        }

        public Criteria andAppsecret2LessThan(String value) {
            addCriterion("appSecret2 <", value, "appsecret2");
            return (Criteria) this;
        }

        public Criteria andAppsecret2LessThanOrEqualTo(String value) {
            addCriterion("appSecret2 <=", value, "appsecret2");
            return (Criteria) this;
        }

        public Criteria andAppsecret2Like(String value) {
            addCriterion("appSecret2 like", value, "appsecret2");
            return (Criteria) this;
        }

        public Criteria andAppsecret2NotLike(String value) {
            addCriterion("appSecret2 not like", value, "appsecret2");
            return (Criteria) this;
        }

        public Criteria andAppsecret2In(List<String> values) {
            addCriterion("appSecret2 in", values, "appsecret2");
            return (Criteria) this;
        }

        public Criteria andAppsecret2NotIn(List<String> values) {
            addCriterion("appSecret2 not in", values, "appsecret2");
            return (Criteria) this;
        }

        public Criteria andAppsecret2Between(String value1, String value2) {
            addCriterion("appSecret2 between", value1, value2, "appsecret2");
            return (Criteria) this;
        }

        public Criteria andAppsecret2NotBetween(String value1, String value2) {
            addCriterion("appSecret2 not between", value1, value2, "appsecret2");
            return (Criteria) this;
        }

        public Criteria andFmapidIsNull() {
            addCriterion("fmapID is null");
            return (Criteria) this;
        }

        public Criteria andFmapidIsNotNull() {
            addCriterion("fmapID is not null");
            return (Criteria) this;
        }

        public Criteria andFmapidEqualTo(String value) {
            addCriterion("fmapID =", value, "fmapid");
            return (Criteria) this;
        }

        public Criteria andFmapidNotEqualTo(String value) {
            addCriterion("fmapID <>", value, "fmapid");
            return (Criteria) this;
        }

        public Criteria andFmapidGreaterThan(String value) {
            addCriterion("fmapID >", value, "fmapid");
            return (Criteria) this;
        }

        public Criteria andFmapidGreaterThanOrEqualTo(String value) {
            addCriterion("fmapID >=", value, "fmapid");
            return (Criteria) this;
        }

        public Criteria andFmapidLessThan(String value) {
            addCriterion("fmapID <", value, "fmapid");
            return (Criteria) this;
        }

        public Criteria andFmapidLessThanOrEqualTo(String value) {
            addCriterion("fmapID <=", value, "fmapid");
            return (Criteria) this;
        }

        public Criteria andFmapidLike(String value) {
            addCriterion("fmapID like", value, "fmapid");
            return (Criteria) this;
        }

        public Criteria andFmapidNotLike(String value) {
            addCriterion("fmapID not like", value, "fmapid");
            return (Criteria) this;
        }

        public Criteria andFmapidIn(List<String> values) {
            addCriterion("fmapID in", values, "fmapid");
            return (Criteria) this;
        }

        public Criteria andFmapidNotIn(List<String> values) {
            addCriterion("fmapID not in", values, "fmapid");
            return (Criteria) this;
        }

        public Criteria andFmapidBetween(String value1, String value2) {
            addCriterion("fmapID between", value1, value2, "fmapid");
            return (Criteria) this;
        }

        public Criteria andFmapidNotBetween(String value1, String value2) {
            addCriterion("fmapID not between", value1, value2, "fmapid");
            return (Criteria) this;
        }

        public Criteria andELonIsNull() {
            addCriterion("e_lon is null");
            return (Criteria) this;
        }

        public Criteria andELonIsNotNull() {
            addCriterion("e_lon is not null");
            return (Criteria) this;
        }

        public Criteria andELonEqualTo(Double value) {
            addCriterion("e_lon =", value, "eLon");
            return (Criteria) this;
        }

        public Criteria andELonNotEqualTo(Double value) {
            addCriterion("e_lon <>", value, "eLon");
            return (Criteria) this;
        }

        public Criteria andELonGreaterThan(Double value) {
            addCriterion("e_lon >", value, "eLon");
            return (Criteria) this;
        }

        public Criteria andELonGreaterThanOrEqualTo(Double value) {
            addCriterion("e_lon >=", value, "eLon");
            return (Criteria) this;
        }

        public Criteria andELonLessThan(Double value) {
            addCriterion("e_lon <", value, "eLon");
            return (Criteria) this;
        }

        public Criteria andELonLessThanOrEqualTo(Double value) {
            addCriterion("e_lon <=", value, "eLon");
            return (Criteria) this;
        }

        public Criteria andELonIn(List<Double> values) {
            addCriterion("e_lon in", values, "eLon");
            return (Criteria) this;
        }

        public Criteria andELonNotIn(List<Double> values) {
            addCriterion("e_lon not in", values, "eLon");
            return (Criteria) this;
        }

        public Criteria andELonBetween(Double value1, Double value2) {
            addCriterion("e_lon between", value1, value2, "eLon");
            return (Criteria) this;
        }

        public Criteria andELonNotBetween(Double value1, Double value2) {
            addCriterion("e_lon not between", value1, value2, "eLon");
            return (Criteria) this;
        }

        public Criteria andELatIsNull() {
            addCriterion("e_lat is null");
            return (Criteria) this;
        }

        public Criteria andELatIsNotNull() {
            addCriterion("e_lat is not null");
            return (Criteria) this;
        }

        public Criteria andELatEqualTo(Double value) {
            addCriterion("e_lat =", value, "eLat");
            return (Criteria) this;
        }

        public Criteria andELatNotEqualTo(Double value) {
            addCriterion("e_lat <>", value, "eLat");
            return (Criteria) this;
        }

        public Criteria andELatGreaterThan(Double value) {
            addCriterion("e_lat >", value, "eLat");
            return (Criteria) this;
        }

        public Criteria andELatGreaterThanOrEqualTo(Double value) {
            addCriterion("e_lat >=", value, "eLat");
            return (Criteria) this;
        }

        public Criteria andELatLessThan(Double value) {
            addCriterion("e_lat <", value, "eLat");
            return (Criteria) this;
        }

        public Criteria andELatLessThanOrEqualTo(Double value) {
            addCriterion("e_lat <=", value, "eLat");
            return (Criteria) this;
        }

        public Criteria andELatIn(List<Double> values) {
            addCriterion("e_lat in", values, "eLat");
            return (Criteria) this;
        }

        public Criteria andELatNotIn(List<Double> values) {
            addCriterion("e_lat not in", values, "eLat");
            return (Criteria) this;
        }

        public Criteria andELatBetween(Double value1, Double value2) {
            addCriterion("e_lat between", value1, value2, "eLat");
            return (Criteria) this;
        }

        public Criteria andELatNotBetween(Double value1, Double value2) {
            addCriterion("e_lat not between", value1, value2, "eLat");
            return (Criteria) this;
        }

        public Criteria andMarketTestIsNull() {
            addCriterion("market_test is null");
            return (Criteria) this;
        }

        public Criteria andMarketTestIsNotNull() {
            addCriterion("market_test is not null");
            return (Criteria) this;
        }

        public Criteria andMarketTestEqualTo(Integer value) {
            addCriterion("market_test =", value, "marketTest");
            return (Criteria) this;
        }

        public Criteria andMarketTestNotEqualTo(Integer value) {
            addCriterion("market_test <>", value, "marketTest");
            return (Criteria) this;
        }

        public Criteria andMarketTestGreaterThan(Integer value) {
            addCriterion("market_test >", value, "marketTest");
            return (Criteria) this;
        }

        public Criteria andMarketTestGreaterThanOrEqualTo(Integer value) {
            addCriterion("market_test >=", value, "marketTest");
            return (Criteria) this;
        }

        public Criteria andMarketTestLessThan(Integer value) {
            addCriterion("market_test <", value, "marketTest");
            return (Criteria) this;
        }

        public Criteria andMarketTestLessThanOrEqualTo(Integer value) {
            addCriterion("market_test <=", value, "marketTest");
            return (Criteria) this;
        }

        public Criteria andMarketTestIn(List<Integer> values) {
            addCriterion("market_test in", values, "marketTest");
            return (Criteria) this;
        }

        public Criteria andMarketTestNotIn(List<Integer> values) {
            addCriterion("market_test not in", values, "marketTest");
            return (Criteria) this;
        }

        public Criteria andMarketTestBetween(Integer value1, Integer value2) {
            addCriterion("market_test between", value1, value2, "marketTest");
            return (Criteria) this;
        }

        public Criteria andMarketTestNotBetween(Integer value1, Integer value2) {
            addCriterion("market_test not between", value1, value2, "marketTest");
            return (Criteria) this;
        }

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andCarnumberIsNull() {
            addCriterion("carNumber is null");
            return (Criteria) this;
        }

        public Criteria andCarnumberIsNotNull() {
            addCriterion("carNumber is not null");
            return (Criteria) this;
        }

        public Criteria andCarnumberEqualTo(String value) {
            addCriterion("carNumber =", value, "carnumber");
            return (Criteria) this;
        }

        public Criteria andCarnumberNotEqualTo(String value) {
            addCriterion("carNumber <>", value, "carnumber");
            return (Criteria) this;
        }

        public Criteria andCarnumberGreaterThan(String value) {
            addCriterion("carNumber >", value, "carnumber");
            return (Criteria) this;
        }

        public Criteria andCarnumberGreaterThanOrEqualTo(String value) {
            addCriterion("carNumber >=", value, "carnumber");
            return (Criteria) this;
        }

        public Criteria andCarnumberLessThan(String value) {
            addCriterion("carNumber <", value, "carnumber");
            return (Criteria) this;
        }

        public Criteria andCarnumberLessThanOrEqualTo(String value) {
            addCriterion("carNumber <=", value, "carnumber");
            return (Criteria) this;
        }

        public Criteria andCarnumberLike(String value) {
            addCriterion("carNumber like", value, "carnumber");
            return (Criteria) this;
        }

        public Criteria andCarnumberNotLike(String value) {
            addCriterion("carNumber not like", value, "carnumber");
            return (Criteria) this;
        }

        public Criteria andCarnumberIn(List<String> values) {
            addCriterion("carNumber in", values, "carnumber");
            return (Criteria) this;
        }

        public Criteria andCarnumberNotIn(List<String> values) {
            addCriterion("carNumber not in", values, "carnumber");
            return (Criteria) this;
        }

        public Criteria andCarnumberBetween(String value1, String value2) {
            addCriterion("carNumber between", value1, value2, "carnumber");
            return (Criteria) this;
        }

        public Criteria andCarnumberNotBetween(String value1, String value2) {
            addCriterion("carNumber not between", value1, value2, "carnumber");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameIsNull() {
            addCriterion("carManageName is null");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameIsNotNull() {
            addCriterion("carManageName is not null");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameEqualTo(String value) {
            addCriterion("carManageName =", value, "carmanagename");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameNotEqualTo(String value) {
            addCriterion("carManageName <>", value, "carmanagename");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameGreaterThan(String value) {
            addCriterion("carManageName >", value, "carmanagename");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameGreaterThanOrEqualTo(String value) {
            addCriterion("carManageName >=", value, "carmanagename");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameLessThan(String value) {
            addCriterion("carManageName <", value, "carmanagename");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameLessThanOrEqualTo(String value) {
            addCriterion("carManageName <=", value, "carmanagename");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameLike(String value) {
            addCriterion("carManageName like", value, "carmanagename");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameNotLike(String value) {
            addCriterion("carManageName not like", value, "carmanagename");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameIn(List<String> values) {
            addCriterion("carManageName in", values, "carmanagename");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameNotIn(List<String> values) {
            addCriterion("carManageName not in", values, "carmanagename");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameBetween(String value1, String value2) {
            addCriterion("carManageName between", value1, value2, "carmanagename");
            return (Criteria) this;
        }

        public Criteria andCarmanagenameNotBetween(String value1, String value2) {
            addCriterion("carManageName not between", value1, value2, "carmanagename");
            return (Criteria) this;
        }

        public Criteria andStockcontrolsIsNull() {
            addCriterion("stockControls is null");
            return (Criteria) this;
        }

        public Criteria andStockcontrolsIsNotNull() {
            addCriterion("stockControls is not null");
            return (Criteria) this;
        }

        public Criteria andStockcontrolsEqualTo(Integer value) {
            addCriterion("stockControls =", value, "stockcontrols");
            return (Criteria) this;
        }

        public Criteria andStockcontrolsNotEqualTo(Integer value) {
            addCriterion("stockControls <>", value, "stockcontrols");
            return (Criteria) this;
        }

        public Criteria andStockcontrolsGreaterThan(Integer value) {
            addCriterion("stockControls >", value, "stockcontrols");
            return (Criteria) this;
        }

        public Criteria andStockcontrolsGreaterThanOrEqualTo(Integer value) {
            addCriterion("stockControls >=", value, "stockcontrols");
            return (Criteria) this;
        }

        public Criteria andStockcontrolsLessThan(Integer value) {
            addCriterion("stockControls <", value, "stockcontrols");
            return (Criteria) this;
        }

        public Criteria andStockcontrolsLessThanOrEqualTo(Integer value) {
            addCriterion("stockControls <=", value, "stockcontrols");
            return (Criteria) this;
        }

        public Criteria andStockcontrolsIn(List<Integer> values) {
            addCriterion("stockControls in", values, "stockcontrols");
            return (Criteria) this;
        }

        public Criteria andStockcontrolsNotIn(List<Integer> values) {
            addCriterion("stockControls not in", values, "stockcontrols");
            return (Criteria) this;
        }

        public Criteria andStockcontrolsBetween(Integer value1, Integer value2) {
            addCriterion("stockControls between", value1, value2, "stockcontrols");
            return (Criteria) this;
        }

        public Criteria andStockcontrolsNotBetween(Integer value1, Integer value2) {
            addCriterion("stockControls not between", value1, value2, "stockcontrols");
            return (Criteria) this;
        }

        public Criteria andTenantappidIsNull() {
            addCriterion("tenantAppId is null");
            return (Criteria) this;
        }

        public Criteria andTenantappidIsNotNull() {
            addCriterion("tenantAppId is not null");
            return (Criteria) this;
        }

        public Criteria andTenantappidEqualTo(String value) {
            addCriterion("tenantAppId =", value, "tenantappid");
            return (Criteria) this;
        }

        public Criteria andTenantappidNotEqualTo(String value) {
            addCriterion("tenantAppId <>", value, "tenantappid");
            return (Criteria) this;
        }

        public Criteria andTenantappidGreaterThan(String value) {
            addCriterion("tenantAppId >", value, "tenantappid");
            return (Criteria) this;
        }

        public Criteria andTenantappidGreaterThanOrEqualTo(String value) {
            addCriterion("tenantAppId >=", value, "tenantappid");
            return (Criteria) this;
        }

        public Criteria andTenantappidLessThan(String value) {
            addCriterion("tenantAppId <", value, "tenantappid");
            return (Criteria) this;
        }

        public Criteria andTenantappidLessThanOrEqualTo(String value) {
            addCriterion("tenantAppId <=", value, "tenantappid");
            return (Criteria) this;
        }

        public Criteria andTenantappidLike(String value) {
            addCriterion("tenantAppId like", value, "tenantappid");
            return (Criteria) this;
        }

        public Criteria andTenantappidNotLike(String value) {
            addCriterion("tenantAppId not like", value, "tenantappid");
            return (Criteria) this;
        }

        public Criteria andTenantappidIn(List<String> values) {
            addCriterion("tenantAppId in", values, "tenantappid");
            return (Criteria) this;
        }

        public Criteria andTenantappidNotIn(List<String> values) {
            addCriterion("tenantAppId not in", values, "tenantappid");
            return (Criteria) this;
        }

        public Criteria andTenantappidBetween(String value1, String value2) {
            addCriterion("tenantAppId between", value1, value2, "tenantappid");
            return (Criteria) this;
        }

        public Criteria andTenantappidNotBetween(String value1, String value2) {
            addCriterion("tenantAppId not between", value1, value2, "tenantappid");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretIsNull() {
            addCriterion("tenantAppSecret is null");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretIsNotNull() {
            addCriterion("tenantAppSecret is not null");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretEqualTo(String value) {
            addCriterion("tenantAppSecret =", value, "tenantappsecret");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretNotEqualTo(String value) {
            addCriterion("tenantAppSecret <>", value, "tenantappsecret");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretGreaterThan(String value) {
            addCriterion("tenantAppSecret >", value, "tenantappsecret");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretGreaterThanOrEqualTo(String value) {
            addCriterion("tenantAppSecret >=", value, "tenantappsecret");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretLessThan(String value) {
            addCriterion("tenantAppSecret <", value, "tenantappsecret");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretLessThanOrEqualTo(String value) {
            addCriterion("tenantAppSecret <=", value, "tenantappsecret");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretLike(String value) {
            addCriterion("tenantAppSecret like", value, "tenantappsecret");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretNotLike(String value) {
            addCriterion("tenantAppSecret not like", value, "tenantappsecret");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretIn(List<String> values) {
            addCriterion("tenantAppSecret in", values, "tenantappsecret");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretNotIn(List<String> values) {
            addCriterion("tenantAppSecret not in", values, "tenantappsecret");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretBetween(String value1, String value2) {
            addCriterion("tenantAppSecret between", value1, value2, "tenantappsecret");
            return (Criteria) this;
        }

        public Criteria andTenantappsecretNotBetween(String value1, String value2) {
            addCriterion("tenantAppSecret not between", value1, value2, "tenantappsecret");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidIsNull() {
            addCriterion("pay_wechat_mchid is null");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidIsNotNull() {
            addCriterion("pay_wechat_mchid is not null");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidEqualTo(String value) {
            addCriterion("pay_wechat_mchid =", value, "payWechatMchid");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidNotEqualTo(String value) {
            addCriterion("pay_wechat_mchid <>", value, "payWechatMchid");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidGreaterThan(String value) {
            addCriterion("pay_wechat_mchid >", value, "payWechatMchid");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidGreaterThanOrEqualTo(String value) {
            addCriterion("pay_wechat_mchid >=", value, "payWechatMchid");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidLessThan(String value) {
            addCriterion("pay_wechat_mchid <", value, "payWechatMchid");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidLessThanOrEqualTo(String value) {
            addCriterion("pay_wechat_mchid <=", value, "payWechatMchid");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidLike(String value) {
            addCriterion("pay_wechat_mchid like", value, "payWechatMchid");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidNotLike(String value) {
            addCriterion("pay_wechat_mchid not like", value, "payWechatMchid");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidIn(List<String> values) {
            addCriterion("pay_wechat_mchid in", values, "payWechatMchid");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidNotIn(List<String> values) {
            addCriterion("pay_wechat_mchid not in", values, "payWechatMchid");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidBetween(String value1, String value2) {
            addCriterion("pay_wechat_mchid between", value1, value2, "payWechatMchid");
            return (Criteria) this;
        }

        public Criteria andPayWechatMchidNotBetween(String value1, String value2) {
            addCriterion("pay_wechat_mchid not between", value1, value2, "payWechatMchid");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyIsNull() {
            addCriterion("pay_wechat_key is null");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyIsNotNull() {
            addCriterion("pay_wechat_key is not null");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyEqualTo(String value) {
            addCriterion("pay_wechat_key =", value, "payWechatKey");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyNotEqualTo(String value) {
            addCriterion("pay_wechat_key <>", value, "payWechatKey");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyGreaterThan(String value) {
            addCriterion("pay_wechat_key >", value, "payWechatKey");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyGreaterThanOrEqualTo(String value) {
            addCriterion("pay_wechat_key >=", value, "payWechatKey");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyLessThan(String value) {
            addCriterion("pay_wechat_key <", value, "payWechatKey");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyLessThanOrEqualTo(String value) {
            addCriterion("pay_wechat_key <=", value, "payWechatKey");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyLike(String value) {
            addCriterion("pay_wechat_key like", value, "payWechatKey");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyNotLike(String value) {
            addCriterion("pay_wechat_key not like", value, "payWechatKey");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyIn(List<String> values) {
            addCriterion("pay_wechat_key in", values, "payWechatKey");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyNotIn(List<String> values) {
            addCriterion("pay_wechat_key not in", values, "payWechatKey");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyBetween(String value1, String value2) {
            addCriterion("pay_wechat_key between", value1, value2, "payWechatKey");
            return (Criteria) this;
        }

        public Criteria andPayWechatKeyNotBetween(String value1, String value2) {
            addCriterion("pay_wechat_key not between", value1, value2, "payWechatKey");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidIsNull() {
            addCriterion("pay_wechat_appid is null");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidIsNotNull() {
            addCriterion("pay_wechat_appid is not null");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidEqualTo(String value) {
            addCriterion("pay_wechat_appid =", value, "payWechatAppid");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidNotEqualTo(String value) {
            addCriterion("pay_wechat_appid <>", value, "payWechatAppid");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidGreaterThan(String value) {
            addCriterion("pay_wechat_appid >", value, "payWechatAppid");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidGreaterThanOrEqualTo(String value) {
            addCriterion("pay_wechat_appid >=", value, "payWechatAppid");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidLessThan(String value) {
            addCriterion("pay_wechat_appid <", value, "payWechatAppid");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidLessThanOrEqualTo(String value) {
            addCriterion("pay_wechat_appid <=", value, "payWechatAppid");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidLike(String value) {
            addCriterion("pay_wechat_appid like", value, "payWechatAppid");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidNotLike(String value) {
            addCriterion("pay_wechat_appid not like", value, "payWechatAppid");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidIn(List<String> values) {
            addCriterion("pay_wechat_appid in", values, "payWechatAppid");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidNotIn(List<String> values) {
            addCriterion("pay_wechat_appid not in", values, "payWechatAppid");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidBetween(String value1, String value2) {
            addCriterion("pay_wechat_appid between", value1, value2, "payWechatAppid");
            return (Criteria) this;
        }

        public Criteria andPayWechatAppidNotBetween(String value1, String value2) {
            addCriterion("pay_wechat_appid not between", value1, value2, "payWechatAppid");
            return (Criteria) this;
        }

        public Criteria andMarketappidIsNull() {
            addCriterion("marketAppId is null");
            return (Criteria) this;
        }

        public Criteria andMarketappidIsNotNull() {
            addCriterion("marketAppId is not null");
            return (Criteria) this;
        }

        public Criteria andMarketappidEqualTo(String value) {
            addCriterion("marketAppId =", value, "marketappid");
            return (Criteria) this;
        }

        public Criteria andMarketappidNotEqualTo(String value) {
            addCriterion("marketAppId <>", value, "marketappid");
            return (Criteria) this;
        }

        public Criteria andMarketappidGreaterThan(String value) {
            addCriterion("marketAppId >", value, "marketappid");
            return (Criteria) this;
        }

        public Criteria andMarketappidGreaterThanOrEqualTo(String value) {
            addCriterion("marketAppId >=", value, "marketappid");
            return (Criteria) this;
        }

        public Criteria andMarketappidLessThan(String value) {
            addCriterion("marketAppId <", value, "marketappid");
            return (Criteria) this;
        }

        public Criteria andMarketappidLessThanOrEqualTo(String value) {
            addCriterion("marketAppId <=", value, "marketappid");
            return (Criteria) this;
        }

        public Criteria andMarketappidLike(String value) {
            addCriterion("marketAppId like", value, "marketappid");
            return (Criteria) this;
        }

        public Criteria andMarketappidNotLike(String value) {
            addCriterion("marketAppId not like", value, "marketappid");
            return (Criteria) this;
        }

        public Criteria andMarketappidIn(List<String> values) {
            addCriterion("marketAppId in", values, "marketappid");
            return (Criteria) this;
        }

        public Criteria andMarketappidNotIn(List<String> values) {
            addCriterion("marketAppId not in", values, "marketappid");
            return (Criteria) this;
        }

        public Criteria andMarketappidBetween(String value1, String value2) {
            addCriterion("marketAppId between", value1, value2, "marketappid");
            return (Criteria) this;
        }

        public Criteria andMarketappidNotBetween(String value1, String value2) {
            addCriterion("marketAppId not between", value1, value2, "marketappid");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretIsNull() {
            addCriterion("marketAppSecret is null");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretIsNotNull() {
            addCriterion("marketAppSecret is not null");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretEqualTo(String value) {
            addCriterion("marketAppSecret =", value, "marketappsecret");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretNotEqualTo(String value) {
            addCriterion("marketAppSecret <>", value, "marketappsecret");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretGreaterThan(String value) {
            addCriterion("marketAppSecret >", value, "marketappsecret");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretGreaterThanOrEqualTo(String value) {
            addCriterion("marketAppSecret >=", value, "marketappsecret");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretLessThan(String value) {
            addCriterion("marketAppSecret <", value, "marketappsecret");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretLessThanOrEqualTo(String value) {
            addCriterion("marketAppSecret <=", value, "marketappsecret");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretLike(String value) {
            addCriterion("marketAppSecret like", value, "marketappsecret");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretNotLike(String value) {
            addCriterion("marketAppSecret not like", value, "marketappsecret");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretIn(List<String> values) {
            addCriterion("marketAppSecret in", values, "marketappsecret");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretNotIn(List<String> values) {
            addCriterion("marketAppSecret not in", values, "marketappsecret");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretBetween(String value1, String value2) {
            addCriterion("marketAppSecret between", value1, value2, "marketappsecret");
            return (Criteria) this;
        }

        public Criteria andMarketappsecretNotBetween(String value1, String value2) {
            addCriterion("marketAppSecret not between", value1, value2, "marketappsecret");
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