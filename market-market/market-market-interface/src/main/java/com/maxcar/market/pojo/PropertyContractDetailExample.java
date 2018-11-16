package com.maxcar.market.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PropertyContractDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PropertyContractDetailExample() {
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

        public Criteria andTenantIdIsNull() {
            addCriterion("tenant_id is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("tenant_id is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(String value) {
            addCriterion("tenant_id =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(String value) {
            addCriterion("tenant_id <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(String value) {
            addCriterion("tenant_id >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_id >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(String value) {
            addCriterion("tenant_id <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(String value) {
            addCriterion("tenant_id <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLike(String value) {
            addCriterion("tenant_id like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotLike(String value) {
            addCriterion("tenant_id not like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<String> values) {
            addCriterion("tenant_id in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<String> values) {
            addCriterion("tenant_id not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(String value1, String value2) {
            addCriterion("tenant_id between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(String value1, String value2) {
            addCriterion("tenant_id not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("area_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("area_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(String value) {
            addCriterion("area_id =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(String value) {
            addCriterion("area_id <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(String value) {
            addCriterion("area_id >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("area_id >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(String value) {
            addCriterion("area_id <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(String value) {
            addCriterion("area_id <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLike(String value) {
            addCriterion("area_id like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotLike(String value) {
            addCriterion("area_id not like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<String> values) {
            addCriterion("area_id in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<String> values) {
            addCriterion("area_id not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(String value1, String value2) {
            addCriterion("area_id between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(String value1, String value2) {
            addCriterion("area_id not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIsNull() {
            addCriterion("contract_category is null");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIsNotNull() {
            addCriterion("contract_category is not null");
            return (Criteria) this;
        }

        public Criteria andContractCategoryEqualTo(Integer value) {
            addCriterion("contract_category =", value, "contractCategory");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNotEqualTo(Integer value) {
            addCriterion("contract_category <>", value, "contractCategory");
            return (Criteria) this;
        }

        public Criteria andContractCategoryGreaterThan(Integer value) {
            addCriterion("contract_category >", value, "contractCategory");
            return (Criteria) this;
        }

        public Criteria andContractCategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("contract_category >=", value, "contractCategory");
            return (Criteria) this;
        }

        public Criteria andContractCategoryLessThan(Integer value) {
            addCriterion("contract_category <", value, "contractCategory");
            return (Criteria) this;
        }

        public Criteria andContractCategoryLessThanOrEqualTo(Integer value) {
            addCriterion("contract_category <=", value, "contractCategory");
            return (Criteria) this;
        }

        public Criteria andContractCategoryIn(List<Integer> values) {
            addCriterion("contract_category in", values, "contractCategory");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNotIn(List<Integer> values) {
            addCriterion("contract_category not in", values, "contractCategory");
            return (Criteria) this;
        }

        public Criteria andContractCategoryBetween(Integer value1, Integer value2) {
            addCriterion("contract_category between", value1, value2, "contractCategory");
            return (Criteria) this;
        }

        public Criteria andContractCategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("contract_category not between", value1, value2, "contractCategory");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Integer value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Integer value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Integer value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Integer value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Integer value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Integer> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Integer> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Integer value1, Integer value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceIsNull() {
            addCriterion("preferential_price is null");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceIsNotNull() {
            addCriterion("preferential_price is not null");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceEqualTo(Integer value) {
            addCriterion("preferential_price =", value, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceNotEqualTo(Integer value) {
            addCriterion("preferential_price <>", value, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceGreaterThan(Integer value) {
            addCriterion("preferential_price >", value, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("preferential_price >=", value, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceLessThan(Integer value) {
            addCriterion("preferential_price <", value, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceLessThanOrEqualTo(Integer value) {
            addCriterion("preferential_price <=", value, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceIn(List<Integer> values) {
            addCriterion("preferential_price in", values, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceNotIn(List<Integer> values) {
            addCriterion("preferential_price not in", values, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceBetween(Integer value1, Integer value2) {
            addCriterion("preferential_price between", value1, value2, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andPreferentialPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("preferential_price not between", value1, value2, "preferentialPrice");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStatusTmeIsNull() {
            addCriterion("status_tme is null");
            return (Criteria) this;
        }

        public Criteria andStatusTmeIsNotNull() {
            addCriterion("status_tme is not null");
            return (Criteria) this;
        }

        public Criteria andStatusTmeEqualTo(Date value) {
            addCriterion("status_tme =", value, "statusTme");
            return (Criteria) this;
        }

        public Criteria andStatusTmeNotEqualTo(Date value) {
            addCriterion("status_tme <>", value, "statusTme");
            return (Criteria) this;
        }

        public Criteria andStatusTmeGreaterThan(Date value) {
            addCriterion("status_tme >", value, "statusTme");
            return (Criteria) this;
        }

        public Criteria andStatusTmeGreaterThanOrEqualTo(Date value) {
            addCriterion("status_tme >=", value, "statusTme");
            return (Criteria) this;
        }

        public Criteria andStatusTmeLessThan(Date value) {
            addCriterion("status_tme <", value, "statusTme");
            return (Criteria) this;
        }

        public Criteria andStatusTmeLessThanOrEqualTo(Date value) {
            addCriterion("status_tme <=", value, "statusTme");
            return (Criteria) this;
        }

        public Criteria andStatusTmeIn(List<Date> values) {
            addCriterion("status_tme in", values, "statusTme");
            return (Criteria) this;
        }

        public Criteria andStatusTmeNotIn(List<Date> values) {
            addCriterion("status_tme not in", values, "statusTme");
            return (Criteria) this;
        }

        public Criteria andStatusTmeBetween(Date value1, Date value2) {
            addCriterion("status_tme between", value1, value2, "statusTme");
            return (Criteria) this;
        }

        public Criteria andStatusTmeNotBetween(Date value1, Date value2) {
            addCriterion("status_tme not between", value1, value2, "statusTme");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkIsNull() {
            addCriterion("termination_remark is null");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkIsNotNull() {
            addCriterion("termination_remark is not null");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkEqualTo(String value) {
            addCriterion("termination_remark =", value, "terminationRemark");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkNotEqualTo(String value) {
            addCriterion("termination_remark <>", value, "terminationRemark");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkGreaterThan(String value) {
            addCriterion("termination_remark >", value, "terminationRemark");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("termination_remark >=", value, "terminationRemark");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkLessThan(String value) {
            addCriterion("termination_remark <", value, "terminationRemark");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkLessThanOrEqualTo(String value) {
            addCriterion("termination_remark <=", value, "terminationRemark");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkLike(String value) {
            addCriterion("termination_remark like", value, "terminationRemark");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkNotLike(String value) {
            addCriterion("termination_remark not like", value, "terminationRemark");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkIn(List<String> values) {
            addCriterion("termination_remark in", values, "terminationRemark");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkNotIn(List<String> values) {
            addCriterion("termination_remark not in", values, "terminationRemark");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkBetween(String value1, String value2) {
            addCriterion("termination_remark between", value1, value2, "terminationRemark");
            return (Criteria) this;
        }

        public Criteria andTerminationRemarkNotBetween(String value1, String value2) {
            addCriterion("termination_remark not between", value1, value2, "terminationRemark");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageIsNull() {
            addCriterion("termination_message is null");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageIsNotNull() {
            addCriterion("termination_message is not null");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageEqualTo(String value) {
            addCriterion("termination_message =", value, "terminationMessage");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageNotEqualTo(String value) {
            addCriterion("termination_message <>", value, "terminationMessage");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageGreaterThan(String value) {
            addCriterion("termination_message >", value, "terminationMessage");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageGreaterThanOrEqualTo(String value) {
            addCriterion("termination_message >=", value, "terminationMessage");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageLessThan(String value) {
            addCriterion("termination_message <", value, "terminationMessage");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageLessThanOrEqualTo(String value) {
            addCriterion("termination_message <=", value, "terminationMessage");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageLike(String value) {
            addCriterion("termination_message like", value, "terminationMessage");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageNotLike(String value) {
            addCriterion("termination_message not like", value, "terminationMessage");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageIn(List<String> values) {
            addCriterion("termination_message in", values, "terminationMessage");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageNotIn(List<String> values) {
            addCriterion("termination_message not in", values, "terminationMessage");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageBetween(String value1, String value2) {
            addCriterion("termination_message between", value1, value2, "terminationMessage");
            return (Criteria) this;
        }

        public Criteria andTerminationMessageNotBetween(String value1, String value2) {
            addCriterion("termination_message not between", value1, value2, "terminationMessage");
            return (Criteria) this;
        }

        public Criteria andTerminationTimeIsNull() {
            addCriterion("termination_time is null");
            return (Criteria) this;
        }

        public Criteria andTerminationTimeIsNotNull() {
            addCriterion("termination_time is not null");
            return (Criteria) this;
        }

        public Criteria andTerminationTimeEqualTo(Date value) {
            addCriterion("termination_time =", value, "terminationTime");
            return (Criteria) this;
        }

        public Criteria andTerminationTimeNotEqualTo(Date value) {
            addCriterion("termination_time <>", value, "terminationTime");
            return (Criteria) this;
        }

        public Criteria andTerminationTimeGreaterThan(Date value) {
            addCriterion("termination_time >", value, "terminationTime");
            return (Criteria) this;
        }

        public Criteria andTerminationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("termination_time >=", value, "terminationTime");
            return (Criteria) this;
        }

        public Criteria andTerminationTimeLessThan(Date value) {
            addCriterion("termination_time <", value, "terminationTime");
            return (Criteria) this;
        }

        public Criteria andTerminationTimeLessThanOrEqualTo(Date value) {
            addCriterion("termination_time <=", value, "terminationTime");
            return (Criteria) this;
        }

        public Criteria andTerminationTimeIn(List<Date> values) {
            addCriterion("termination_time in", values, "terminationTime");
            return (Criteria) this;
        }

        public Criteria andTerminationTimeNotIn(List<Date> values) {
            addCriterion("termination_time not in", values, "terminationTime");
            return (Criteria) this;
        }

        public Criteria andTerminationTimeBetween(Date value1, Date value2) {
            addCriterion("termination_time between", value1, value2, "terminationTime");
            return (Criteria) this;
        }

        public Criteria andTerminationTimeNotBetween(Date value1, Date value2) {
            addCriterion("termination_time not between", value1, value2, "terminationTime");
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

        public Criteria andPropertyContractDetailStatusEqualTo(int value) {
            addCriterion("property_contract_detail_status =", value, "propertyContractDetailStatus");
            return (Criteria) this;
        }

        public Criteria andContractCategoryEqualTo(int value) {
            addCriterion("contract_category =", value, "contractCategory");
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