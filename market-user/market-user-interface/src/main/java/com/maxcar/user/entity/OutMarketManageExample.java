package com.maxcar.user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OutMarketManageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OutMarketManageExample() {
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

        public Criteria andOutMarketTypeIsNull() {
            addCriterion("out_market_type is null");
            return (Criteria) this;
        }

        public Criteria andOutMarketTypeIsNotNull() {
            addCriterion("out_market_type is not null");
            return (Criteria) this;
        }

        public Criteria andOutMarketTypeEqualTo(String value) {
            addCriterion("out_market_type =", value, "outMarketType");
            return (Criteria) this;
        }

        public Criteria andOutMarketTypeNotEqualTo(String value) {
            addCriterion("out_market_type <>", value, "outMarketType");
            return (Criteria) this;
        }

        public Criteria andOutMarketTypeGreaterThan(String value) {
            addCriterion("out_market_type >", value, "outMarketType");
            return (Criteria) this;
        }

        public Criteria andOutMarketTypeGreaterThanOrEqualTo(String value) {
            addCriterion("out_market_type >=", value, "outMarketType");
            return (Criteria) this;
        }

        public Criteria andOutMarketTypeLessThan(String value) {
            addCriterion("out_market_type <", value, "outMarketType");
            return (Criteria) this;
        }

        public Criteria andOutMarketTypeLessThanOrEqualTo(String value) {
            addCriterion("out_market_type <=", value, "outMarketType");
            return (Criteria) this;
        }

        public Criteria andOutMarketTypeLike(String value) {
            addCriterion("out_market_type like", value, "outMarketType");
            return (Criteria) this;
        }

        public Criteria andOutMarketTypeNotLike(String value) {
            addCriterion("out_market_type not like", value, "outMarketType");
            return (Criteria) this;
        }

        public Criteria andOutMarketTypeIn(List<String> values) {
            addCriterion("out_market_type in", values, "outMarketType");
            return (Criteria) this;
        }

        public Criteria andOutMarketTypeNotIn(List<String> values) {
            addCriterion("out_market_type not in", values, "outMarketType");
            return (Criteria) this;
        }

        public Criteria andOutMarketTypeBetween(String value1, String value2) {
            addCriterion("out_market_type between", value1, value2, "outMarketType");
            return (Criteria) this;
        }

        public Criteria andOutMarketTypeNotBetween(String value1, String value2) {
            addCriterion("out_market_type not between", value1, value2, "outMarketType");
            return (Criteria) this;
        }

        public Criteria andOutMarketTimeIsNull() {
            addCriterion("out_market_time is null");
            return (Criteria) this;
        }

        public Criteria andOutMarketTimeIsNotNull() {
            addCriterion("out_market_time is not null");
            return (Criteria) this;
        }

        public Criteria andOutMarketTimeEqualTo(Double value) {
            addCriterion("out_market_time =", value, "outMarketTime");
            return (Criteria) this;
        }

        public Criteria andOutMarketTimeNotEqualTo(Double value) {
            addCriterion("out_market_time <>", value, "outMarketTime");
            return (Criteria) this;
        }

        public Criteria andOutMarketTimeGreaterThan(Double value) {
            addCriterion("out_market_time >", value, "outMarketTime");
            return (Criteria) this;
        }

        public Criteria andOutMarketTimeGreaterThanOrEqualTo(Double value) {
            addCriterion("out_market_time >=", value, "outMarketTime");
            return (Criteria) this;
        }

        public Criteria andOutMarketTimeLessThan(Double value) {
            addCriterion("out_market_time <", value, "outMarketTime");
            return (Criteria) this;
        }

        public Criteria andOutMarketTimeLessThanOrEqualTo(Double value) {
            addCriterion("out_market_time <=", value, "outMarketTime");
            return (Criteria) this;
        }

        public Criteria andOutMarketTimeIn(List<Double> values) {
            addCriterion("out_market_time in", values, "outMarketTime");
            return (Criteria) this;
        }

        public Criteria andOutMarketTimeNotIn(List<Double> values) {
            addCriterion("out_market_time not in", values, "outMarketTime");
            return (Criteria) this;
        }

        public Criteria andOutMarketTimeBetween(Double value1, Double value2) {
            addCriterion("out_market_time between", value1, value2, "outMarketTime");
            return (Criteria) this;
        }

        public Criteria andOutMarketTimeNotBetween(Double value1, Double value2) {
            addCriterion("out_market_time not between", value1, value2, "outMarketTime");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalIsNull() {
            addCriterion("out_market_approval is null");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalIsNotNull() {
            addCriterion("out_market_approval is not null");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalEqualTo(String value) {
            addCriterion("out_market_approval =", value, "outMarketApproval");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalNotEqualTo(String value) {
            addCriterion("out_market_approval <>", value, "outMarketApproval");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalGreaterThan(String value) {
            addCriterion("out_market_approval >", value, "outMarketApproval");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalGreaterThanOrEqualTo(String value) {
            addCriterion("out_market_approval >=", value, "outMarketApproval");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalLessThan(String value) {
            addCriterion("out_market_approval <", value, "outMarketApproval");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalLessThanOrEqualTo(String value) {
            addCriterion("out_market_approval <=", value, "outMarketApproval");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalLike(String value) {
            addCriterion("out_market_approval like", value, "outMarketApproval");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalNotLike(String value) {
            addCriterion("out_market_approval not like", value, "outMarketApproval");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalIn(List<String> values) {
            addCriterion("out_market_approval in", values, "outMarketApproval");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalNotIn(List<String> values) {
            addCriterion("out_market_approval not in", values, "outMarketApproval");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalBetween(String value1, String value2) {
            addCriterion("out_market_approval between", value1, value2, "outMarketApproval");
            return (Criteria) this;
        }

        public Criteria andOutMarketApprovalNotBetween(String value1, String value2) {
            addCriterion("out_market_approval not between", value1, value2, "outMarketApproval");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
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