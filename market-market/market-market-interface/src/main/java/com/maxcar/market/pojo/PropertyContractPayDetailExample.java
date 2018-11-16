package com.maxcar.market.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PropertyContractPayDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PropertyContractPayDetailExample() {
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

        public Criteria andMaxketIdIsNull() {
            addCriterion("maxket_id is null");
            return (Criteria) this;
        }

        public Criteria andMaxketIdIsNotNull() {
            addCriterion("maxket_id is not null");
            return (Criteria) this;
        }

        public Criteria andMaxketIdEqualTo(String value) {
            addCriterion("maxket_id =", value, "maxketId");
            return (Criteria) this;
        }

        public Criteria andMaxketIdNotEqualTo(String value) {
            addCriterion("maxket_id <>", value, "maxketId");
            return (Criteria) this;
        }

        public Criteria andMaxketIdGreaterThan(String value) {
            addCriterion("maxket_id >", value, "maxketId");
            return (Criteria) this;
        }

        public Criteria andMaxketIdGreaterThanOrEqualTo(String value) {
            addCriterion("maxket_id >=", value, "maxketId");
            return (Criteria) this;
        }

        public Criteria andMaxketIdLessThan(String value) {
            addCriterion("maxket_id <", value, "maxketId");
            return (Criteria) this;
        }

        public Criteria andMaxketIdLessThanOrEqualTo(String value) {
            addCriterion("maxket_id <=", value, "maxketId");
            return (Criteria) this;
        }

        public Criteria andMaxketIdLike(String value) {
            addCriterion("maxket_id like", value, "maxketId");
            return (Criteria) this;
        }

        public Criteria andMaxketIdNotLike(String value) {
            addCriterion("maxket_id not like", value, "maxketId");
            return (Criteria) this;
        }

        public Criteria andMaxketIdIn(List<String> values) {
            addCriterion("maxket_id in", values, "maxketId");
            return (Criteria) this;
        }

        public Criteria andMaxketIdNotIn(List<String> values) {
            addCriterion("maxket_id not in", values, "maxketId");
            return (Criteria) this;
        }

        public Criteria andMaxketIdBetween(String value1, String value2) {
            addCriterion("maxket_id between", value1, value2, "maxketId");
            return (Criteria) this;
        }

        public Criteria andMaxketIdNotBetween(String value1, String value2) {
            addCriterion("maxket_id not between", value1, value2, "maxketId");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdIsNull() {
            addCriterion("property_contract_id is null");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdIsNotNull() {
            addCriterion("property_contract_id is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdEqualTo(String value) {
            addCriterion("property_contract_id =", value, "propertyContractId");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdNotEqualTo(String value) {
            addCriterion("property_contract_id <>", value, "propertyContractId");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdGreaterThan(String value) {
            addCriterion("property_contract_id >", value, "propertyContractId");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdGreaterThanOrEqualTo(String value) {
            addCriterion("property_contract_id >=", value, "propertyContractId");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdLessThan(String value) {
            addCriterion("property_contract_id <", value, "propertyContractId");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdLessThanOrEqualTo(String value) {
            addCriterion("property_contract_id <=", value, "propertyContractId");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdLike(String value) {
            addCriterion("property_contract_id like", value, "propertyContractId");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdNotLike(String value) {
            addCriterion("property_contract_id not like", value, "propertyContractId");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdIn(List<String> values) {
            addCriterion("property_contract_id in", values, "propertyContractId");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdNotIn(List<String> values) {
            addCriterion("property_contract_id not in", values, "propertyContractId");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdBetween(String value1, String value2) {
            addCriterion("property_contract_id between", value1, value2, "propertyContractId");
            return (Criteria) this;
        }

        public Criteria andPropertyContractIdNotBetween(String value1, String value2) {
            addCriterion("property_contract_id not between", value1, value2, "propertyContractId");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailIsNull() {
            addCriterion("property_contract_detail is null");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailIsNotNull() {
            addCriterion("property_contract_detail is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailEqualTo(String value) {
            addCriterion("property_contract_detail =", value, "propertyContractDetail");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailNotEqualTo(String value) {
            addCriterion("property_contract_detail <>", value, "propertyContractDetail");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailGreaterThan(String value) {
            addCriterion("property_contract_detail >", value, "propertyContractDetail");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailGreaterThanOrEqualTo(String value) {
            addCriterion("property_contract_detail >=", value, "propertyContractDetail");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailLessThan(String value) {
            addCriterion("property_contract_detail <", value, "propertyContractDetail");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailLessThanOrEqualTo(String value) {
            addCriterion("property_contract_detail <=", value, "propertyContractDetail");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailLike(String value) {
            addCriterion("property_contract_detail like", value, "propertyContractDetail");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailNotLike(String value) {
            addCriterion("property_contract_detail not like", value, "propertyContractDetail");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailIn(List<String> values) {
            addCriterion("property_contract_detail in", values, "propertyContractDetail");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailNotIn(List<String> values) {
            addCriterion("property_contract_detail not in", values, "propertyContractDetail");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailBetween(String value1, String value2) {
            addCriterion("property_contract_detail between", value1, value2, "propertyContractDetail");
            return (Criteria) this;
        }

        public Criteria andPropertyContractDetailNotBetween(String value1, String value2) {
            addCriterion("property_contract_detail not between", value1, value2, "propertyContractDetail");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPayPriceIsNull() {
            addCriterion("pay_price is null");
            return (Criteria) this;
        }

        public Criteria andPayPriceIsNotNull() {
            addCriterion("pay_price is not null");
            return (Criteria) this;
        }

        public Criteria andPayPriceEqualTo(Integer value) {
            addCriterion("pay_price =", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceNotEqualTo(Integer value) {
            addCriterion("pay_price <>", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceGreaterThan(Integer value) {
            addCriterion("pay_price >", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_price >=", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceLessThan(Integer value) {
            addCriterion("pay_price <", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceLessThanOrEqualTo(Integer value) {
            addCriterion("pay_price <=", value, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceIn(List<Integer> values) {
            addCriterion("pay_price in", values, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceNotIn(List<Integer> values) {
            addCriterion("pay_price not in", values, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceBetween(Integer value1, Integer value2) {
            addCriterion("pay_price between", value1, value2, "payPrice");
            return (Criteria) this;
        }

        public Criteria andPayPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_price not between", value1, value2, "payPrice");
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

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIsNull() {
            addCriterion("insert_time is null");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIsNotNull() {
            addCriterion("insert_time is not null");
            return (Criteria) this;
        }

        public Criteria andInsertTimeEqualTo(Date value) {
            addCriterion("insert_time =", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotEqualTo(Date value) {
            addCriterion("insert_time <>", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThan(Date value) {
            addCriterion("insert_time >", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("insert_time >=", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThan(Date value) {
            addCriterion("insert_time <", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeLessThanOrEqualTo(Date value) {
            addCriterion("insert_time <=", value, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeIn(List<Date> values) {
            addCriterion("insert_time in", values, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotIn(List<Date> values) {
            addCriterion("insert_time not in", values, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeBetween(Date value1, Date value2) {
            addCriterion("insert_time between", value1, value2, "insertTime");
            return (Criteria) this;
        }

        public Criteria andInsertTimeNotBetween(Date value1, Date value2) {
            addCriterion("insert_time not between", value1, value2, "insertTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorIsNull() {
            addCriterion("insert_operator is null");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorIsNotNull() {
            addCriterion("insert_operator is not null");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorEqualTo(String value) {
            addCriterion("insert_operator =", value, "insertOperator");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorNotEqualTo(String value) {
            addCriterion("insert_operator <>", value, "insertOperator");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorGreaterThan(String value) {
            addCriterion("insert_operator >", value, "insertOperator");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("insert_operator >=", value, "insertOperator");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorLessThan(String value) {
            addCriterion("insert_operator <", value, "insertOperator");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorLessThanOrEqualTo(String value) {
            addCriterion("insert_operator <=", value, "insertOperator");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorLike(String value) {
            addCriterion("insert_operator like", value, "insertOperator");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorNotLike(String value) {
            addCriterion("insert_operator not like", value, "insertOperator");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorIn(List<String> values) {
            addCriterion("insert_operator in", values, "insertOperator");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorNotIn(List<String> values) {
            addCriterion("insert_operator not in", values, "insertOperator");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorBetween(String value1, String value2) {
            addCriterion("insert_operator between", value1, value2, "insertOperator");
            return (Criteria) this;
        }

        public Criteria andInsertOperatorNotBetween(String value1, String value2) {
            addCriterion("insert_operator not between", value1, value2, "insertOperator");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorIsNull() {
            addCriterion("update_operator is null");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorIsNotNull() {
            addCriterion("update_operator is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorEqualTo(String value) {
            addCriterion("update_operator =", value, "updateOperator");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorNotEqualTo(String value) {
            addCriterion("update_operator <>", value, "updateOperator");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorGreaterThan(String value) {
            addCriterion("update_operator >", value, "updateOperator");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("update_operator >=", value, "updateOperator");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorLessThan(String value) {
            addCriterion("update_operator <", value, "updateOperator");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorLessThanOrEqualTo(String value) {
            addCriterion("update_operator <=", value, "updateOperator");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorLike(String value) {
            addCriterion("update_operator like", value, "updateOperator");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorNotLike(String value) {
            addCriterion("update_operator not like", value, "updateOperator");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorIn(List<String> values) {
            addCriterion("update_operator in", values, "updateOperator");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorNotIn(List<String> values) {
            addCriterion("update_operator not in", values, "updateOperator");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorBetween(String value1, String value2) {
            addCriterion("update_operator between", value1, value2, "updateOperator");
            return (Criteria) this;
        }

        public Criteria andUpdateOperatorNotBetween(String value1, String value2) {
            addCriterion("update_operator not between", value1, value2, "updateOperator");
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