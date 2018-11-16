package com.maxcar.market.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class InvoiceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InvoiceExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andBillTimeIsNull() {
            addCriterion("bill_time is null");
            return (Criteria) this;
        }

        public Criteria andBillTimeIsNotNull() {
            addCriterion("bill_time is not null");
            return (Criteria) this;
        }

        public Criteria andBillTimeEqualTo(Date value) {
            addCriterionForJDBCDate("bill_time =", value, "billTime");
            return (Criteria) this;
        }

        public Criteria andBillTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("bill_time <>", value, "billTime");
            return (Criteria) this;
        }

        public Criteria andBillTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("bill_time >", value, "billTime");
            return (Criteria) this;
        }

        public Criteria andBillTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bill_time >=", value, "billTime");
            return (Criteria) this;
        }

        public Criteria andBillTimeLessThan(Date value) {
            addCriterionForJDBCDate("bill_time <", value, "billTime");
            return (Criteria) this;
        }

        public Criteria andBillTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("bill_time <=", value, "billTime");
            return (Criteria) this;
        }

        public Criteria andBillTimeIn(List<Date> values) {
            addCriterionForJDBCDate("bill_time in", values, "billTime");
            return (Criteria) this;
        }

        public Criteria andBillTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("bill_time not in", values, "billTime");
            return (Criteria) this;
        }

        public Criteria andBillTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bill_time between", value1, value2, "billTime");
            return (Criteria) this;
        }

        public Criteria andBillTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("bill_time not between", value1, value2, "billTime");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIsNull() {
            addCriterion("invoice_no is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIsNotNull() {
            addCriterion("invoice_no is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoEqualTo(String value) {
            addCriterion("invoice_no =", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotEqualTo(String value) {
            addCriterion("invoice_no <>", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThan(String value) {
            addCriterion("invoice_no >", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_no >=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThan(String value) {
            addCriterion("invoice_no <", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThanOrEqualTo(String value) {
            addCriterion("invoice_no <=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLike(String value) {
            addCriterion("invoice_no like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotLike(String value) {
            addCriterion("invoice_no not like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIn(List<String> values) {
            addCriterion("invoice_no in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotIn(List<String> values) {
            addCriterion("invoice_no not in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoBetween(String value1, String value2) {
            addCriterion("invoice_no between", value1, value2, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotBetween(String value1, String value2) {
            addCriterion("invoice_no not between", value1, value2, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andCurrentNoIsNull() {
            addCriterion("current_no is null");
            return (Criteria) this;
        }

        public Criteria andCurrentNoIsNotNull() {
            addCriterion("current_no is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentNoEqualTo(String value) {
            addCriterion("current_no =", value, "currentNo");
            return (Criteria) this;
        }

        public Criteria andCurrentNoNotEqualTo(String value) {
            addCriterion("current_no <>", value, "currentNo");
            return (Criteria) this;
        }

        public Criteria andCurrentNoGreaterThan(String value) {
            addCriterion("current_no >", value, "currentNo");
            return (Criteria) this;
        }

        public Criteria andCurrentNoGreaterThanOrEqualTo(String value) {
            addCriterion("current_no >=", value, "currentNo");
            return (Criteria) this;
        }

        public Criteria andCurrentNoLessThan(String value) {
            addCriterion("current_no <", value, "currentNo");
            return (Criteria) this;
        }

        public Criteria andCurrentNoLessThanOrEqualTo(String value) {
            addCriterion("current_no <=", value, "currentNo");
            return (Criteria) this;
        }

        public Criteria andCurrentNoLike(String value) {
            addCriterion("current_no like", value, "currentNo");
            return (Criteria) this;
        }

        public Criteria andCurrentNoNotLike(String value) {
            addCriterion("current_no not like", value, "currentNo");
            return (Criteria) this;
        }

        public Criteria andCurrentNoIn(List<String> values) {
            addCriterion("current_no in", values, "currentNo");
            return (Criteria) this;
        }

        public Criteria andCurrentNoNotIn(List<String> values) {
            addCriterion("current_no not in", values, "currentNo");
            return (Criteria) this;
        }

        public Criteria andCurrentNoBetween(String value1, String value2) {
            addCriterion("current_no between", value1, value2, "currentNo");
            return (Criteria) this;
        }

        public Criteria andCurrentNoNotBetween(String value1, String value2) {
            addCriterion("current_no not between", value1, value2, "currentNo");
            return (Criteria) this;
        }

        public Criteria andContractIsNull() {
            addCriterion("contract is null");
            return (Criteria) this;
        }

        public Criteria andContractIsNotNull() {
            addCriterion("contract is not null");
            return (Criteria) this;
        }

        public Criteria andContractEqualTo(String value) {
            addCriterion("contract =", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractNotEqualTo(String value) {
            addCriterion("contract <>", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractGreaterThan(String value) {
            addCriterion("contract >", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractGreaterThanOrEqualTo(String value) {
            addCriterion("contract >=", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractLessThan(String value) {
            addCriterion("contract <", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractLessThanOrEqualTo(String value) {
            addCriterion("contract <=", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractLike(String value) {
            addCriterion("contract like", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractNotLike(String value) {
            addCriterion("contract not like", value, "contract");
            return (Criteria) this;
        }

        public Criteria andContractIn(List<String> values) {
            addCriterion("contract in", values, "contract");
            return (Criteria) this;
        }

        public Criteria andContractNotIn(List<String> values) {
            addCriterion("contract not in", values, "contract");
            return (Criteria) this;
        }

        public Criteria andContractBetween(String value1, String value2) {
            addCriterion("contract between", value1, value2, "contract");
            return (Criteria) this;
        }

        public Criteria andContractNotBetween(String value1, String value2) {
            addCriterion("contract not between", value1, value2, "contract");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoIsNull() {
            addCriterion("purchacer_tax_no is null");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoIsNotNull() {
            addCriterion("purchacer_tax_no is not null");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoEqualTo(String value) {
            addCriterion("purchacer_tax_no =", value, "purchacerTaxNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoNotEqualTo(String value) {
            addCriterion("purchacer_tax_no <>", value, "purchacerTaxNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoGreaterThan(String value) {
            addCriterion("purchacer_tax_no >", value, "purchacerTaxNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoGreaterThanOrEqualTo(String value) {
            addCriterion("purchacer_tax_no >=", value, "purchacerTaxNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoLessThan(String value) {
            addCriterion("purchacer_tax_no <", value, "purchacerTaxNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoLessThanOrEqualTo(String value) {
            addCriterion("purchacer_tax_no <=", value, "purchacerTaxNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoLike(String value) {
            addCriterion("purchacer_tax_no like", value, "purchacerTaxNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoNotLike(String value) {
            addCriterion("purchacer_tax_no not like", value, "purchacerTaxNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoIn(List<String> values) {
            addCriterion("purchacer_tax_no in", values, "purchacerTaxNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoNotIn(List<String> values) {
            addCriterion("purchacer_tax_no not in", values, "purchacerTaxNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoBetween(String value1, String value2) {
            addCriterion("purchacer_tax_no between", value1, value2, "purchacerTaxNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerTaxNoNotBetween(String value1, String value2) {
            addCriterion("purchacer_tax_no not between", value1, value2, "purchacerTaxNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityIsNull() {
            addCriterion("purchacer_nationality is null");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityIsNotNull() {
            addCriterion("purchacer_nationality is not null");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityEqualTo(String value) {
            addCriterion("purchacer_nationality =", value, "purchacerNationality");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityNotEqualTo(String value) {
            addCriterion("purchacer_nationality <>", value, "purchacerNationality");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityGreaterThan(String value) {
            addCriterion("purchacer_nationality >", value, "purchacerNationality");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityGreaterThanOrEqualTo(String value) {
            addCriterion("purchacer_nationality >=", value, "purchacerNationality");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityLessThan(String value) {
            addCriterion("purchacer_nationality <", value, "purchacerNationality");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityLessThanOrEqualTo(String value) {
            addCriterion("purchacer_nationality <=", value, "purchacerNationality");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityLike(String value) {
            addCriterion("purchacer_nationality like", value, "purchacerNationality");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityNotLike(String value) {
            addCriterion("purchacer_nationality not like", value, "purchacerNationality");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityIn(List<String> values) {
            addCriterion("purchacer_nationality in", values, "purchacerNationality");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityNotIn(List<String> values) {
            addCriterion("purchacer_nationality not in", values, "purchacerNationality");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityBetween(String value1, String value2) {
            addCriterion("purchacer_nationality between", value1, value2, "purchacerNationality");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationalityNotBetween(String value1, String value2) {
            addCriterion("purchacer_nationality not between", value1, value2, "purchacerNationality");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameIsNull() {
            addCriterion("purchacer_name is null");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameIsNotNull() {
            addCriterion("purchacer_name is not null");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameEqualTo(String value) {
            addCriterion("purchacer_name =", value, "purchacerName");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameNotEqualTo(String value) {
            addCriterion("purchacer_name <>", value, "purchacerName");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameGreaterThan(String value) {
            addCriterion("purchacer_name >", value, "purchacerName");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameGreaterThanOrEqualTo(String value) {
            addCriterion("purchacer_name >=", value, "purchacerName");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameLessThan(String value) {
            addCriterion("purchacer_name <", value, "purchacerName");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameLessThanOrEqualTo(String value) {
            addCriterion("purchacer_name <=", value, "purchacerName");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameLike(String value) {
            addCriterion("purchacer_name like", value, "purchacerName");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameNotLike(String value) {
            addCriterion("purchacer_name not like", value, "purchacerName");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameIn(List<String> values) {
            addCriterion("purchacer_name in", values, "purchacerName");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameNotIn(List<String> values) {
            addCriterion("purchacer_name not in", values, "purchacerName");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameBetween(String value1, String value2) {
            addCriterion("purchacer_name between", value1, value2, "purchacerName");
            return (Criteria) this;
        }

        public Criteria andPurchacerNameNotBetween(String value1, String value2) {
            addCriterion("purchacer_name not between", value1, value2, "purchacerName");
            return (Criteria) this;
        }

        public Criteria andPurchacerTypeIsNull() {
            addCriterion("purchacer_type is null");
            return (Criteria) this;
        }

        public Criteria andPurchacerTypeIsNotNull() {
            addCriterion("purchacer_type is not null");
            return (Criteria) this;
        }

        public Criteria andPurchacerTypeEqualTo(Integer value) {
            addCriterion("purchacer_type =", value, "purchacerType");
            return (Criteria) this;
        }

        public Criteria andPurchacerTypeNotEqualTo(Integer value) {
            addCriterion("purchacer_type <>", value, "purchacerType");
            return (Criteria) this;
        }

        public Criteria andPurchacerTypeGreaterThan(Integer value) {
            addCriterion("purchacer_type >", value, "purchacerType");
            return (Criteria) this;
        }

        public Criteria andPurchacerTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchacer_type >=", value, "purchacerType");
            return (Criteria) this;
        }

        public Criteria andPurchacerTypeLessThan(Integer value) {
            addCriterion("purchacer_type <", value, "purchacerType");
            return (Criteria) this;
        }

        public Criteria andPurchacerTypeLessThanOrEqualTo(Integer value) {
            addCriterion("purchacer_type <=", value, "purchacerType");
            return (Criteria) this;
        }

        public Criteria andPurchacerTypeIn(List<Integer> values) {
            addCriterion("purchacer_type in", values, "purchacerType");
            return (Criteria) this;
        }

        public Criteria andPurchacerTypeNotIn(List<Integer> values) {
            addCriterion("purchacer_type not in", values, "purchacerType");
            return (Criteria) this;
        }

        public Criteria andPurchacerTypeBetween(Integer value1, Integer value2) {
            addCriterion("purchacer_type between", value1, value2, "purchacerType");
            return (Criteria) this;
        }

        public Criteria andPurchacerTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("purchacer_type not between", value1, value2, "purchacerType");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportIsNull() {
            addCriterion("purchacer_passport is null");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportIsNotNull() {
            addCriterion("purchacer_passport is not null");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportEqualTo(String value) {
            addCriterion("purchacer_passport =", value, "purchacerPassport");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportNotEqualTo(String value) {
            addCriterion("purchacer_passport <>", value, "purchacerPassport");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportGreaterThan(String value) {
            addCriterion("purchacer_passport >", value, "purchacerPassport");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportGreaterThanOrEqualTo(String value) {
            addCriterion("purchacer_passport >=", value, "purchacerPassport");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportLessThan(String value) {
            addCriterion("purchacer_passport <", value, "purchacerPassport");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportLessThanOrEqualTo(String value) {
            addCriterion("purchacer_passport <=", value, "purchacerPassport");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportLike(String value) {
            addCriterion("purchacer_passport like", value, "purchacerPassport");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportNotLike(String value) {
            addCriterion("purchacer_passport not like", value, "purchacerPassport");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportIn(List<String> values) {
            addCriterion("purchacer_passport in", values, "purchacerPassport");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportNotIn(List<String> values) {
            addCriterion("purchacer_passport not in", values, "purchacerPassport");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportBetween(String value1, String value2) {
            addCriterion("purchacer_passport between", value1, value2, "purchacerPassport");
            return (Criteria) this;
        }

        public Criteria andPurchacerPassportNotBetween(String value1, String value2) {
            addCriterion("purchacer_passport not between", value1, value2, "purchacerPassport");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardIsNull() {
            addCriterion("purchacer_id_card is null");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardIsNotNull() {
            addCriterion("purchacer_id_card is not null");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardEqualTo(String value) {
            addCriterion("purchacer_id_card =", value, "purchacerIdCard");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardNotEqualTo(String value) {
            addCriterion("purchacer_id_card <>", value, "purchacerIdCard");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardGreaterThan(String value) {
            addCriterion("purchacer_id_card >", value, "purchacerIdCard");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("purchacer_id_card >=", value, "purchacerIdCard");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardLessThan(String value) {
            addCriterion("purchacer_id_card <", value, "purchacerIdCard");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardLessThanOrEqualTo(String value) {
            addCriterion("purchacer_id_card <=", value, "purchacerIdCard");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardLike(String value) {
            addCriterion("purchacer_id_card like", value, "purchacerIdCard");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardNotLike(String value) {
            addCriterion("purchacer_id_card not like", value, "purchacerIdCard");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardIn(List<String> values) {
            addCriterion("purchacer_id_card in", values, "purchacerIdCard");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardNotIn(List<String> values) {
            addCriterion("purchacer_id_card not in", values, "purchacerIdCard");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardBetween(String value1, String value2) {
            addCriterion("purchacer_id_card between", value1, value2, "purchacerIdCard");
            return (Criteria) this;
        }

        public Criteria andPurchacerIdCardNotBetween(String value1, String value2) {
            addCriterion("purchacer_id_card not between", value1, value2, "purchacerIdCard");
            return (Criteria) this;
        }

        public Criteria andIsPurchacerCodeIsNull() {
            addCriterion("is_purchacer_code is null");
            return (Criteria) this;
        }

        public Criteria andIsPurchacerCodeIsNotNull() {
            addCriterion("is_purchacer_code is not null");
            return (Criteria) this;
        }

        public Criteria andIsPurchacerCodeEqualTo(Integer value) {
            addCriterion("is_purchacer_code =", value, "isPurchacerCode");
            return (Criteria) this;
        }

        public Criteria andIsPurchacerCodeNotEqualTo(Integer value) {
            addCriterion("is_purchacer_code <>", value, "isPurchacerCode");
            return (Criteria) this;
        }

        public Criteria andIsPurchacerCodeGreaterThan(Integer value) {
            addCriterion("is_purchacer_code >", value, "isPurchacerCode");
            return (Criteria) this;
        }

        public Criteria andIsPurchacerCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_purchacer_code >=", value, "isPurchacerCode");
            return (Criteria) this;
        }

        public Criteria andIsPurchacerCodeLessThan(Integer value) {
            addCriterion("is_purchacer_code <", value, "isPurchacerCode");
            return (Criteria) this;
        }

        public Criteria andIsPurchacerCodeLessThanOrEqualTo(Integer value) {
            addCriterion("is_purchacer_code <=", value, "isPurchacerCode");
            return (Criteria) this;
        }

        public Criteria andIsPurchacerCodeIn(List<Integer> values) {
            addCriterion("is_purchacer_code in", values, "isPurchacerCode");
            return (Criteria) this;
        }

        public Criteria andIsPurchacerCodeNotIn(List<Integer> values) {
            addCriterion("is_purchacer_code not in", values, "isPurchacerCode");
            return (Criteria) this;
        }

        public Criteria andIsPurchacerCodeBetween(Integer value1, Integer value2) {
            addCriterion("is_purchacer_code between", value1, value2, "isPurchacerCode");
            return (Criteria) this;
        }

        public Criteria andIsPurchacerCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("is_purchacer_code not between", value1, value2, "isPurchacerCode");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoIsNull() {
            addCriterion("purchacer_no is null");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoIsNotNull() {
            addCriterion("purchacer_no is not null");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoEqualTo(String value) {
            addCriterion("purchacer_no =", value, "purchacerNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoNotEqualTo(String value) {
            addCriterion("purchacer_no <>", value, "purchacerNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoGreaterThan(String value) {
            addCriterion("purchacer_no >", value, "purchacerNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoGreaterThanOrEqualTo(String value) {
            addCriterion("purchacer_no >=", value, "purchacerNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoLessThan(String value) {
            addCriterion("purchacer_no <", value, "purchacerNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoLessThanOrEqualTo(String value) {
            addCriterion("purchacer_no <=", value, "purchacerNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoLike(String value) {
            addCriterion("purchacer_no like", value, "purchacerNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoNotLike(String value) {
            addCriterion("purchacer_no not like", value, "purchacerNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoIn(List<String> values) {
            addCriterion("purchacer_no in", values, "purchacerNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoNotIn(List<String> values) {
            addCriterion("purchacer_no not in", values, "purchacerNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoBetween(String value1, String value2) {
            addCriterion("purchacer_no between", value1, value2, "purchacerNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerNoNotBetween(String value1, String value2) {
            addCriterion("purchacer_no not between", value1, value2, "purchacerNo");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressIsNull() {
            addCriterion("purchacer_address is null");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressIsNotNull() {
            addCriterion("purchacer_address is not null");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressEqualTo(String value) {
            addCriterion("purchacer_address =", value, "purchacerAddress");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressNotEqualTo(String value) {
            addCriterion("purchacer_address <>", value, "purchacerAddress");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressGreaterThan(String value) {
            addCriterion("purchacer_address >", value, "purchacerAddress");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressGreaterThanOrEqualTo(String value) {
            addCriterion("purchacer_address >=", value, "purchacerAddress");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressLessThan(String value) {
            addCriterion("purchacer_address <", value, "purchacerAddress");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressLessThanOrEqualTo(String value) {
            addCriterion("purchacer_address <=", value, "purchacerAddress");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressLike(String value) {
            addCriterion("purchacer_address like", value, "purchacerAddress");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressNotLike(String value) {
            addCriterion("purchacer_address not like", value, "purchacerAddress");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressIn(List<String> values) {
            addCriterion("purchacer_address in", values, "purchacerAddress");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressNotIn(List<String> values) {
            addCriterion("purchacer_address not in", values, "purchacerAddress");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressBetween(String value1, String value2) {
            addCriterion("purchacer_address between", value1, value2, "purchacerAddress");
            return (Criteria) this;
        }

        public Criteria andPurchacerAddressNotBetween(String value1, String value2) {
            addCriterion("purchacer_address not between", value1, value2, "purchacerAddress");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationIsNull() {
            addCriterion("purchacer_nation is null");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationIsNotNull() {
            addCriterion("purchacer_nation is not null");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationEqualTo(String value) {
            addCriterion("purchacer_nation =", value, "purchacerNation");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationNotEqualTo(String value) {
            addCriterion("purchacer_nation <>", value, "purchacerNation");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationGreaterThan(String value) {
            addCriterion("purchacer_nation >", value, "purchacerNation");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationGreaterThanOrEqualTo(String value) {
            addCriterion("purchacer_nation >=", value, "purchacerNation");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationLessThan(String value) {
            addCriterion("purchacer_nation <", value, "purchacerNation");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationLessThanOrEqualTo(String value) {
            addCriterion("purchacer_nation <=", value, "purchacerNation");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationLike(String value) {
            addCriterion("purchacer_nation like", value, "purchacerNation");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationNotLike(String value) {
            addCriterion("purchacer_nation not like", value, "purchacerNation");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationIn(List<String> values) {
            addCriterion("purchacer_nation in", values, "purchacerNation");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationNotIn(List<String> values) {
            addCriterion("purchacer_nation not in", values, "purchacerNation");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationBetween(String value1, String value2) {
            addCriterion("purchacer_nation between", value1, value2, "purchacerNation");
            return (Criteria) this;
        }

        public Criteria andPurchacerNationNotBetween(String value1, String value2) {
            addCriterion("purchacer_nation not between", value1, value2, "purchacerNation");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileIsNull() {
            addCriterion("purchacer_mobile is null");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileIsNotNull() {
            addCriterion("purchacer_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileEqualTo(String value) {
            addCriterion("purchacer_mobile =", value, "purchacerMobile");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileNotEqualTo(String value) {
            addCriterion("purchacer_mobile <>", value, "purchacerMobile");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileGreaterThan(String value) {
            addCriterion("purchacer_mobile >", value, "purchacerMobile");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileGreaterThanOrEqualTo(String value) {
            addCriterion("purchacer_mobile >=", value, "purchacerMobile");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileLessThan(String value) {
            addCriterion("purchacer_mobile <", value, "purchacerMobile");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileLessThanOrEqualTo(String value) {
            addCriterion("purchacer_mobile <=", value, "purchacerMobile");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileLike(String value) {
            addCriterion("purchacer_mobile like", value, "purchacerMobile");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileNotLike(String value) {
            addCriterion("purchacer_mobile not like", value, "purchacerMobile");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileIn(List<String> values) {
            addCriterion("purchacer_mobile in", values, "purchacerMobile");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileNotIn(List<String> values) {
            addCriterion("purchacer_mobile not in", values, "purchacerMobile");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileBetween(String value1, String value2) {
            addCriterion("purchacer_mobile between", value1, value2, "purchacerMobile");
            return (Criteria) this;
        }

        public Criteria andPurchacerMobileNotBetween(String value1, String value2) {
            addCriterion("purchacer_mobile not between", value1, value2, "purchacerMobile");
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

        public Criteria andIsSellerCodeIsNull() {
            addCriterion("is_seller_code is null");
            return (Criteria) this;
        }

        public Criteria andIsSellerCodeIsNotNull() {
            addCriterion("is_seller_code is not null");
            return (Criteria) this;
        }

        public Criteria andIsSellerCodeEqualTo(Integer value) {
            addCriterion("is_seller_code =", value, "isSellerCode");
            return (Criteria) this;
        }

        public Criteria andIsSellerCodeNotEqualTo(Integer value) {
            addCriterion("is_seller_code <>", value, "isSellerCode");
            return (Criteria) this;
        }

        public Criteria andIsSellerCodeGreaterThan(Integer value) {
            addCriterion("is_seller_code >", value, "isSellerCode");
            return (Criteria) this;
        }

        public Criteria andIsSellerCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_seller_code >=", value, "isSellerCode");
            return (Criteria) this;
        }

        public Criteria andIsSellerCodeLessThan(Integer value) {
            addCriterion("is_seller_code <", value, "isSellerCode");
            return (Criteria) this;
        }

        public Criteria andIsSellerCodeLessThanOrEqualTo(Integer value) {
            addCriterion("is_seller_code <=", value, "isSellerCode");
            return (Criteria) this;
        }

        public Criteria andIsSellerCodeIn(List<Integer> values) {
            addCriterion("is_seller_code in", values, "isSellerCode");
            return (Criteria) this;
        }

        public Criteria andIsSellerCodeNotIn(List<Integer> values) {
            addCriterion("is_seller_code not in", values, "isSellerCode");
            return (Criteria) this;
        }

        public Criteria andIsSellerCodeBetween(Integer value1, Integer value2) {
            addCriterion("is_seller_code between", value1, value2, "isSellerCode");
            return (Criteria) this;
        }

        public Criteria andIsSellerCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("is_seller_code not between", value1, value2, "isSellerCode");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityIsNull() {
            addCriterion("seller_nationality is null");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityIsNotNull() {
            addCriterion("seller_nationality is not null");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityEqualTo(String value) {
            addCriterion("seller_nationality =", value, "sellerNationality");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityNotEqualTo(String value) {
            addCriterion("seller_nationality <>", value, "sellerNationality");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityGreaterThan(String value) {
            addCriterion("seller_nationality >", value, "sellerNationality");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityGreaterThanOrEqualTo(String value) {
            addCriterion("seller_nationality >=", value, "sellerNationality");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityLessThan(String value) {
            addCriterion("seller_nationality <", value, "sellerNationality");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityLessThanOrEqualTo(String value) {
            addCriterion("seller_nationality <=", value, "sellerNationality");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityLike(String value) {
            addCriterion("seller_nationality like", value, "sellerNationality");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityNotLike(String value) {
            addCriterion("seller_nationality not like", value, "sellerNationality");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityIn(List<String> values) {
            addCriterion("seller_nationality in", values, "sellerNationality");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityNotIn(List<String> values) {
            addCriterion("seller_nationality not in", values, "sellerNationality");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityBetween(String value1, String value2) {
            addCriterion("seller_nationality between", value1, value2, "sellerNationality");
            return (Criteria) this;
        }

        public Criteria andSellerNationalityNotBetween(String value1, String value2) {
            addCriterion("seller_nationality not between", value1, value2, "sellerNationality");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoIsNull() {
            addCriterion("seller_tax_no is null");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoIsNotNull() {
            addCriterion("seller_tax_no is not null");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoEqualTo(String value) {
            addCriterion("seller_tax_no =", value, "sellerTaxNo");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoNotEqualTo(String value) {
            addCriterion("seller_tax_no <>", value, "sellerTaxNo");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoGreaterThan(String value) {
            addCriterion("seller_tax_no >", value, "sellerTaxNo");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoGreaterThanOrEqualTo(String value) {
            addCriterion("seller_tax_no >=", value, "sellerTaxNo");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoLessThan(String value) {
            addCriterion("seller_tax_no <", value, "sellerTaxNo");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoLessThanOrEqualTo(String value) {
            addCriterion("seller_tax_no <=", value, "sellerTaxNo");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoLike(String value) {
            addCriterion("seller_tax_no like", value, "sellerTaxNo");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoNotLike(String value) {
            addCriterion("seller_tax_no not like", value, "sellerTaxNo");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoIn(List<String> values) {
            addCriterion("seller_tax_no in", values, "sellerTaxNo");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoNotIn(List<String> values) {
            addCriterion("seller_tax_no not in", values, "sellerTaxNo");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoBetween(String value1, String value2) {
            addCriterion("seller_tax_no between", value1, value2, "sellerTaxNo");
            return (Criteria) this;
        }

        public Criteria andSellerTaxNoNotBetween(String value1, String value2) {
            addCriterion("seller_tax_no not between", value1, value2, "sellerTaxNo");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardIsNull() {
            addCriterion("seller_id_card is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardIsNotNull() {
            addCriterion("seller_id_card is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardEqualTo(String value) {
            addCriterion("seller_id_card =", value, "sellerIdCard");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardNotEqualTo(String value) {
            addCriterion("seller_id_card <>", value, "sellerIdCard");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardGreaterThan(String value) {
            addCriterion("seller_id_card >", value, "sellerIdCard");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("seller_id_card >=", value, "sellerIdCard");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardLessThan(String value) {
            addCriterion("seller_id_card <", value, "sellerIdCard");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardLessThanOrEqualTo(String value) {
            addCriterion("seller_id_card <=", value, "sellerIdCard");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardLike(String value) {
            addCriterion("seller_id_card like", value, "sellerIdCard");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardNotLike(String value) {
            addCriterion("seller_id_card not like", value, "sellerIdCard");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardIn(List<String> values) {
            addCriterion("seller_id_card in", values, "sellerIdCard");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardNotIn(List<String> values) {
            addCriterion("seller_id_card not in", values, "sellerIdCard");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardBetween(String value1, String value2) {
            addCriterion("seller_id_card between", value1, value2, "sellerIdCard");
            return (Criteria) this;
        }

        public Criteria andSellerIdCardNotBetween(String value1, String value2) {
            addCriterion("seller_id_card not between", value1, value2, "sellerIdCard");
            return (Criteria) this;
        }

        public Criteria andSellerPassportIsNull() {
            addCriterion("seller_passport is null");
            return (Criteria) this;
        }

        public Criteria andSellerPassportIsNotNull() {
            addCriterion("seller_passport is not null");
            return (Criteria) this;
        }

        public Criteria andSellerPassportEqualTo(String value) {
            addCriterion("seller_passport =", value, "sellerPassport");
            return (Criteria) this;
        }

        public Criteria andSellerPassportNotEqualTo(String value) {
            addCriterion("seller_passport <>", value, "sellerPassport");
            return (Criteria) this;
        }

        public Criteria andSellerPassportGreaterThan(String value) {
            addCriterion("seller_passport >", value, "sellerPassport");
            return (Criteria) this;
        }

        public Criteria andSellerPassportGreaterThanOrEqualTo(String value) {
            addCriterion("seller_passport >=", value, "sellerPassport");
            return (Criteria) this;
        }

        public Criteria andSellerPassportLessThan(String value) {
            addCriterion("seller_passport <", value, "sellerPassport");
            return (Criteria) this;
        }

        public Criteria andSellerPassportLessThanOrEqualTo(String value) {
            addCriterion("seller_passport <=", value, "sellerPassport");
            return (Criteria) this;
        }

        public Criteria andSellerPassportLike(String value) {
            addCriterion("seller_passport like", value, "sellerPassport");
            return (Criteria) this;
        }

        public Criteria andSellerPassportNotLike(String value) {
            addCriterion("seller_passport not like", value, "sellerPassport");
            return (Criteria) this;
        }

        public Criteria andSellerPassportIn(List<String> values) {
            addCriterion("seller_passport in", values, "sellerPassport");
            return (Criteria) this;
        }

        public Criteria andSellerPassportNotIn(List<String> values) {
            addCriterion("seller_passport not in", values, "sellerPassport");
            return (Criteria) this;
        }

        public Criteria andSellerPassportBetween(String value1, String value2) {
            addCriterion("seller_passport between", value1, value2, "sellerPassport");
            return (Criteria) this;
        }

        public Criteria andSellerPassportNotBetween(String value1, String value2) {
            addCriterion("seller_passport not between", value1, value2, "sellerPassport");
            return (Criteria) this;
        }

        public Criteria andSellerNationIsNull() {
            addCriterion("seller_nation is null");
            return (Criteria) this;
        }

        public Criteria andSellerNationIsNotNull() {
            addCriterion("seller_nation is not null");
            return (Criteria) this;
        }

        public Criteria andSellerNationEqualTo(String value) {
            addCriterion("seller_nation =", value, "sellerNation");
            return (Criteria) this;
        }

        public Criteria andSellerNationNotEqualTo(String value) {
            addCriterion("seller_nation <>", value, "sellerNation");
            return (Criteria) this;
        }

        public Criteria andSellerNationGreaterThan(String value) {
            addCriterion("seller_nation >", value, "sellerNation");
            return (Criteria) this;
        }

        public Criteria andSellerNationGreaterThanOrEqualTo(String value) {
            addCriterion("seller_nation >=", value, "sellerNation");
            return (Criteria) this;
        }

        public Criteria andSellerNationLessThan(String value) {
            addCriterion("seller_nation <", value, "sellerNation");
            return (Criteria) this;
        }

        public Criteria andSellerNationLessThanOrEqualTo(String value) {
            addCriterion("seller_nation <=", value, "sellerNation");
            return (Criteria) this;
        }

        public Criteria andSellerNationLike(String value) {
            addCriterion("seller_nation like", value, "sellerNation");
            return (Criteria) this;
        }

        public Criteria andSellerNationNotLike(String value) {
            addCriterion("seller_nation not like", value, "sellerNation");
            return (Criteria) this;
        }

        public Criteria andSellerNationIn(List<String> values) {
            addCriterion("seller_nation in", values, "sellerNation");
            return (Criteria) this;
        }

        public Criteria andSellerNationNotIn(List<String> values) {
            addCriterion("seller_nation not in", values, "sellerNation");
            return (Criteria) this;
        }

        public Criteria andSellerNationBetween(String value1, String value2) {
            addCriterion("seller_nation between", value1, value2, "sellerNation");
            return (Criteria) this;
        }

        public Criteria andSellerNationNotBetween(String value1, String value2) {
            addCriterion("seller_nation not between", value1, value2, "sellerNation");
            return (Criteria) this;
        }

        public Criteria andSellerNameIsNull() {
            addCriterion("seller_name is null");
            return (Criteria) this;
        }

        public Criteria andSellerNameIsNotNull() {
            addCriterion("seller_name is not null");
            return (Criteria) this;
        }

        public Criteria andSellerNameEqualTo(String value) {
            addCriterion("seller_name =", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotEqualTo(String value) {
            addCriterion("seller_name <>", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameGreaterThan(String value) {
            addCriterion("seller_name >", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameGreaterThanOrEqualTo(String value) {
            addCriterion("seller_name >=", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLessThan(String value) {
            addCriterion("seller_name <", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLessThanOrEqualTo(String value) {
            addCriterion("seller_name <=", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLike(String value) {
            addCriterion("seller_name like", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotLike(String value) {
            addCriterion("seller_name not like", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameIn(List<String> values) {
            addCriterion("seller_name in", values, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotIn(List<String> values) {
            addCriterion("seller_name not in", values, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameBetween(String value1, String value2) {
            addCriterion("seller_name between", value1, value2, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotBetween(String value1, String value2) {
            addCriterion("seller_name not between", value1, value2, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerTypeIsNull() {
            addCriterion("seller_type is null");
            return (Criteria) this;
        }

        public Criteria andSellerTypeIsNotNull() {
            addCriterion("seller_type is not null");
            return (Criteria) this;
        }

        public Criteria andSellerTypeEqualTo(Integer value) {
            addCriterion("seller_type =", value, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeNotEqualTo(Integer value) {
            addCriterion("seller_type <>", value, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeGreaterThan(Integer value) {
            addCriterion("seller_type >", value, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("seller_type >=", value, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeLessThan(Integer value) {
            addCriterion("seller_type <", value, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeLessThanOrEqualTo(Integer value) {
            addCriterion("seller_type <=", value, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeIn(List<Integer> values) {
            addCriterion("seller_type in", values, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeNotIn(List<Integer> values) {
            addCriterion("seller_type not in", values, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeBetween(Integer value1, Integer value2) {
            addCriterion("seller_type between", value1, value2, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("seller_type not between", value1, value2, "sellerType");
            return (Criteria) this;
        }

        public Criteria andSellerNoIsNull() {
            addCriterion("seller_no is null");
            return (Criteria) this;
        }

        public Criteria andSellerNoIsNotNull() {
            addCriterion("seller_no is not null");
            return (Criteria) this;
        }

        public Criteria andSellerNoEqualTo(String value) {
            addCriterion("seller_no =", value, "sellerNo");
            return (Criteria) this;
        }

        public Criteria andSellerNoNotEqualTo(String value) {
            addCriterion("seller_no <>", value, "sellerNo");
            return (Criteria) this;
        }

        public Criteria andSellerNoGreaterThan(String value) {
            addCriterion("seller_no >", value, "sellerNo");
            return (Criteria) this;
        }

        public Criteria andSellerNoGreaterThanOrEqualTo(String value) {
            addCriterion("seller_no >=", value, "sellerNo");
            return (Criteria) this;
        }

        public Criteria andSellerNoLessThan(String value) {
            addCriterion("seller_no <", value, "sellerNo");
            return (Criteria) this;
        }

        public Criteria andSellerNoLessThanOrEqualTo(String value) {
            addCriterion("seller_no <=", value, "sellerNo");
            return (Criteria) this;
        }

        public Criteria andSellerNoLike(String value) {
            addCriterion("seller_no like", value, "sellerNo");
            return (Criteria) this;
        }

        public Criteria andSellerNoNotLike(String value) {
            addCriterion("seller_no not like", value, "sellerNo");
            return (Criteria) this;
        }

        public Criteria andSellerNoIn(List<String> values) {
            addCriterion("seller_no in", values, "sellerNo");
            return (Criteria) this;
        }

        public Criteria andSellerNoNotIn(List<String> values) {
            addCriterion("seller_no not in", values, "sellerNo");
            return (Criteria) this;
        }

        public Criteria andSellerNoBetween(String value1, String value2) {
            addCriterion("seller_no between", value1, value2, "sellerNo");
            return (Criteria) this;
        }

        public Criteria andSellerNoNotBetween(String value1, String value2) {
            addCriterion("seller_no not between", value1, value2, "sellerNo");
            return (Criteria) this;
        }

        public Criteria andSellerAddressIsNull() {
            addCriterion("seller_address is null");
            return (Criteria) this;
        }

        public Criteria andSellerAddressIsNotNull() {
            addCriterion("seller_address is not null");
            return (Criteria) this;
        }

        public Criteria andSellerAddressEqualTo(String value) {
            addCriterion("seller_address =", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressNotEqualTo(String value) {
            addCriterion("seller_address <>", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressGreaterThan(String value) {
            addCriterion("seller_address >", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressGreaterThanOrEqualTo(String value) {
            addCriterion("seller_address >=", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressLessThan(String value) {
            addCriterion("seller_address <", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressLessThanOrEqualTo(String value) {
            addCriterion("seller_address <=", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressLike(String value) {
            addCriterion("seller_address like", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressNotLike(String value) {
            addCriterion("seller_address not like", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressIn(List<String> values) {
            addCriterion("seller_address in", values, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressNotIn(List<String> values) {
            addCriterion("seller_address not in", values, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressBetween(String value1, String value2) {
            addCriterion("seller_address between", value1, value2, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressNotBetween(String value1, String value2) {
            addCriterion("seller_address not between", value1, value2, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerMobileIsNull() {
            addCriterion("seller_mobile is null");
            return (Criteria) this;
        }

        public Criteria andSellerMobileIsNotNull() {
            addCriterion("seller_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andSellerMobileEqualTo(String value) {
            addCriterion("seller_mobile =", value, "sellerMobile");
            return (Criteria) this;
        }

        public Criteria andSellerMobileNotEqualTo(String value) {
            addCriterion("seller_mobile <>", value, "sellerMobile");
            return (Criteria) this;
        }

        public Criteria andSellerMobileGreaterThan(String value) {
            addCriterion("seller_mobile >", value, "sellerMobile");
            return (Criteria) this;
        }

        public Criteria andSellerMobileGreaterThanOrEqualTo(String value) {
            addCriterion("seller_mobile >=", value, "sellerMobile");
            return (Criteria) this;
        }

        public Criteria andSellerMobileLessThan(String value) {
            addCriterion("seller_mobile <", value, "sellerMobile");
            return (Criteria) this;
        }

        public Criteria andSellerMobileLessThanOrEqualTo(String value) {
            addCriterion("seller_mobile <=", value, "sellerMobile");
            return (Criteria) this;
        }

        public Criteria andSellerMobileLike(String value) {
            addCriterion("seller_mobile like", value, "sellerMobile");
            return (Criteria) this;
        }

        public Criteria andSellerMobileNotLike(String value) {
            addCriterion("seller_mobile not like", value, "sellerMobile");
            return (Criteria) this;
        }

        public Criteria andSellerMobileIn(List<String> values) {
            addCriterion("seller_mobile in", values, "sellerMobile");
            return (Criteria) this;
        }

        public Criteria andSellerMobileNotIn(List<String> values) {
            addCriterion("seller_mobile not in", values, "sellerMobile");
            return (Criteria) this;
        }

        public Criteria andSellerMobileBetween(String value1, String value2) {
            addCriterion("seller_mobile between", value1, value2, "sellerMobile");
            return (Criteria) this;
        }

        public Criteria andSellerMobileNotBetween(String value1, String value2) {
            addCriterion("seller_mobile not between", value1, value2, "sellerMobile");
            return (Criteria) this;
        }

        public Criteria andTransferTypeIsNull() {
            addCriterion("transfer_type is null");
            return (Criteria) this;
        }

        public Criteria andTransferTypeIsNotNull() {
            addCriterion("transfer_type is not null");
            return (Criteria) this;
        }

        public Criteria andTransferTypeEqualTo(Integer value) {
            addCriterion("transfer_type =", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeNotEqualTo(Integer value) {
            addCriterion("transfer_type <>", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeGreaterThan(Integer value) {
            addCriterion("transfer_type >", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("transfer_type >=", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeLessThan(Integer value) {
            addCriterion("transfer_type <", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeLessThanOrEqualTo(Integer value) {
            addCriterion("transfer_type <=", value, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeIn(List<Integer> values) {
            addCriterion("transfer_type in", values, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeNotIn(List<Integer> values) {
            addCriterion("transfer_type not in", values, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeBetween(Integer value1, Integer value2) {
            addCriterion("transfer_type between", value1, value2, "transferType");
            return (Criteria) this;
        }

        public Criteria andTransferTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("transfer_type not between", value1, value2, "transferType");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateIsNull() {
            addCriterion("factory_plate is null");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateIsNotNull() {
            addCriterion("factory_plate is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateEqualTo(String value) {
            addCriterion("factory_plate =", value, "factoryPlate");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateNotEqualTo(String value) {
            addCriterion("factory_plate <>", value, "factoryPlate");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateGreaterThan(String value) {
            addCriterion("factory_plate >", value, "factoryPlate");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateGreaterThanOrEqualTo(String value) {
            addCriterion("factory_plate >=", value, "factoryPlate");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateLessThan(String value) {
            addCriterion("factory_plate <", value, "factoryPlate");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateLessThanOrEqualTo(String value) {
            addCriterion("factory_plate <=", value, "factoryPlate");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateLike(String value) {
            addCriterion("factory_plate like", value, "factoryPlate");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateNotLike(String value) {
            addCriterion("factory_plate not like", value, "factoryPlate");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateIn(List<String> values) {
            addCriterion("factory_plate in", values, "factoryPlate");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateNotIn(List<String> values) {
            addCriterion("factory_plate not in", values, "factoryPlate");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateBetween(String value1, String value2) {
            addCriterion("factory_plate between", value1, value2, "factoryPlate");
            return (Criteria) this;
        }

        public Criteria andFactoryPlateNotBetween(String value1, String value2) {
            addCriterion("factory_plate not between", value1, value2, "factoryPlate");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberIsNull() {
            addCriterion("certificate_number is null");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberIsNotNull() {
            addCriterion("certificate_number is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberEqualTo(String value) {
            addCriterion("certificate_number =", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberNotEqualTo(String value) {
            addCriterion("certificate_number <>", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberGreaterThan(String value) {
            addCriterion("certificate_number >", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberGreaterThanOrEqualTo(String value) {
            addCriterion("certificate_number >=", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberLessThan(String value) {
            addCriterion("certificate_number <", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberLessThanOrEqualTo(String value) {
            addCriterion("certificate_number <=", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberLike(String value) {
            addCriterion("certificate_number like", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberNotLike(String value) {
            addCriterion("certificate_number not like", value, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberIn(List<String> values) {
            addCriterion("certificate_number in", values, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberNotIn(List<String> values) {
            addCriterion("certificate_number not in", values, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberBetween(String value1, String value2) {
            addCriterion("certificate_number between", value1, value2, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andCertificateNumberNotBetween(String value1, String value2) {
            addCriterion("certificate_number not between", value1, value2, "certificateNumber");
            return (Criteria) this;
        }

        public Criteria andInvoicePortofIsNull() {
            addCriterion("invoice_portof is null");
            return (Criteria) this;
        }

        public Criteria andInvoicePortofIsNotNull() {
            addCriterion("invoice_portof is not null");
            return (Criteria) this;
        }

        public Criteria andInvoicePortofEqualTo(Integer value) {
            addCriterion("invoice_portof =", value, "invoicePortof");
            return (Criteria) this;
        }

        public Criteria andInvoicePortofNotEqualTo(Integer value) {
            addCriterion("invoice_portof <>", value, "invoicePortof");
            return (Criteria) this;
        }

        public Criteria andInvoicePortofGreaterThan(Integer value) {
            addCriterion("invoice_portof >", value, "invoicePortof");
            return (Criteria) this;
        }

        public Criteria andInvoicePortofGreaterThanOrEqualTo(Integer value) {
            addCriterion("invoice_portof >=", value, "invoicePortof");
            return (Criteria) this;
        }

        public Criteria andInvoicePortofLessThan(Integer value) {
            addCriterion("invoice_portof <", value, "invoicePortof");
            return (Criteria) this;
        }

        public Criteria andInvoicePortofLessThanOrEqualTo(Integer value) {
            addCriterion("invoice_portof <=", value, "invoicePortof");
            return (Criteria) this;
        }

        public Criteria andInvoicePortofIn(List<Integer> values) {
            addCriterion("invoice_portof in", values, "invoicePortof");
            return (Criteria) this;
        }

        public Criteria andInvoicePortofNotIn(List<Integer> values) {
            addCriterion("invoice_portof not in", values, "invoicePortof");
            return (Criteria) this;
        }

        public Criteria andInvoicePortofBetween(Integer value1, Integer value2) {
            addCriterion("invoice_portof between", value1, value2, "invoicePortof");
            return (Criteria) this;
        }

        public Criteria andInvoicePortofNotBetween(Integer value1, Integer value2) {
            addCriterion("invoice_portof not between", value1, value2, "invoicePortof");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusIsNull() {
            addCriterion("invoice_status is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusIsNotNull() {
            addCriterion("invoice_status is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusEqualTo(Integer value) {
            addCriterion("invoice_status =", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusNotEqualTo(Integer value) {
            addCriterion("invoice_status <>", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusGreaterThan(Integer value) {
            addCriterion("invoice_status >", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("invoice_status >=", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusLessThan(Integer value) {
            addCriterion("invoice_status <", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusLessThanOrEqualTo(Integer value) {
            addCriterion("invoice_status <=", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusIn(List<Integer> values) {
            addCriterion("invoice_status in", values, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusNotIn(List<Integer> values) {
            addCriterion("invoice_status not in", values, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusBetween(Integer value1, Integer value2) {
            addCriterion("invoice_status between", value1, value2, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("invoice_status not between", value1, value2, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIsNull() {
            addCriterion("invoice_code is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIsNotNull() {
            addCriterion("invoice_code is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeEqualTo(String value) {
            addCriterion("invoice_code =", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotEqualTo(String value) {
            addCriterion("invoice_code <>", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeGreaterThan(String value) {
            addCriterion("invoice_code >", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_code >=", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLessThan(String value) {
            addCriterion("invoice_code <", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLessThanOrEqualTo(String value) {
            addCriterion("invoice_code <=", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeLike(String value) {
            addCriterion("invoice_code like", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotLike(String value) {
            addCriterion("invoice_code not like", value, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeIn(List<String> values) {
            addCriterion("invoice_code in", values, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotIn(List<String> values) {
            addCriterion("invoice_code not in", values, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeBetween(String value1, String value2) {
            addCriterion("invoice_code between", value1, value2, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andInvoiceCodeNotBetween(String value1, String value2) {
            addCriterion("invoice_code not between", value1, value2, "invoiceCode");
            return (Criteria) this;
        }

        public Criteria andCarSourcesIsNull() {
            addCriterion("car_sources is null");
            return (Criteria) this;
        }

        public Criteria andCarSourcesIsNotNull() {
            addCriterion("car_sources is not null");
            return (Criteria) this;
        }

        public Criteria andCarSourcesEqualTo(Integer value) {
            addCriterion("car_sources =", value, "carSources");
            return (Criteria) this;
        }

        public Criteria andCarSourcesNotEqualTo(Integer value) {
            addCriterion("car_sources <>", value, "carSources");
            return (Criteria) this;
        }

        public Criteria andCarSourcesGreaterThan(Integer value) {
            addCriterion("car_sources >", value, "carSources");
            return (Criteria) this;
        }

        public Criteria andCarSourcesGreaterThanOrEqualTo(Integer value) {
            addCriterion("car_sources >=", value, "carSources");
            return (Criteria) this;
        }

        public Criteria andCarSourcesLessThan(Integer value) {
            addCriterion("car_sources <", value, "carSources");
            return (Criteria) this;
        }

        public Criteria andCarSourcesLessThanOrEqualTo(Integer value) {
            addCriterion("car_sources <=", value, "carSources");
            return (Criteria) this;
        }

        public Criteria andCarSourcesIn(List<Integer> values) {
            addCriterion("car_sources in", values, "carSources");
            return (Criteria) this;
        }

        public Criteria andCarSourcesNotIn(List<Integer> values) {
            addCriterion("car_sources not in", values, "carSources");
            return (Criteria) this;
        }

        public Criteria andCarSourcesBetween(Integer value1, Integer value2) {
            addCriterion("car_sources between", value1, value2, "carSources");
            return (Criteria) this;
        }

        public Criteria andCarSourcesNotBetween(Integer value1, Integer value2) {
            addCriterion("car_sources not between", value1, value2, "carSources");
            return (Criteria) this;
        }

        public Criteria andCarStockStatusIsNull() {
            addCriterion("car_stock_status is null");
            return (Criteria) this;
        }

        public Criteria andCarStockStatusIsNotNull() {
            addCriterion("car_stock_status is not null");
            return (Criteria) this;
        }

        public Criteria andCarStockStatusEqualTo(Integer value) {
            addCriterion("car_stock_status =", value, "carStockStatus");
            return (Criteria) this;
        }

        public Criteria andCarStockStatusNotEqualTo(Integer value) {
            addCriterion("car_stock_status <>", value, "carStockStatus");
            return (Criteria) this;
        }

        public Criteria andCarStockStatusGreaterThan(Integer value) {
            addCriterion("car_stock_status >", value, "carStockStatus");
            return (Criteria) this;
        }

        public Criteria andCarStockStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("car_stock_status >=", value, "carStockStatus");
            return (Criteria) this;
        }

        public Criteria andCarStockStatusLessThan(Integer value) {
            addCriterion("car_stock_status <", value, "carStockStatus");
            return (Criteria) this;
        }

        public Criteria andCarStockStatusLessThanOrEqualTo(Integer value) {
            addCriterion("car_stock_status <=", value, "carStockStatus");
            return (Criteria) this;
        }

        public Criteria andCarStockStatusIn(List<Integer> values) {
            addCriterion("car_stock_status in", values, "carStockStatus");
            return (Criteria) this;
        }

        public Criteria andCarStockStatusNotIn(List<Integer> values) {
            addCriterion("car_stock_status not in", values, "carStockStatus");
            return (Criteria) this;
        }

        public Criteria andCarStockStatusBetween(Integer value1, Integer value2) {
            addCriterion("car_stock_status between", value1, value2, "carStockStatus");
            return (Criteria) this;
        }

        public Criteria andCarStockStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("car_stock_status not between", value1, value2, "carStockStatus");
            return (Criteria) this;
        }

        public Criteria andCarIdIsNull() {
            addCriterion("car_id is null");
            return (Criteria) this;
        }

        public Criteria andCarIdIsNotNull() {
            addCriterion("car_id is not null");
            return (Criteria) this;
        }

        public Criteria andCarIdEqualTo(String value) {
            addCriterion("car_id =", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotEqualTo(String value) {
            addCriterion("car_id <>", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdGreaterThan(String value) {
            addCriterion("car_id >", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdGreaterThanOrEqualTo(String value) {
            addCriterion("car_id >=", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdLessThan(String value) {
            addCriterion("car_id <", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdLessThanOrEqualTo(String value) {
            addCriterion("car_id <=", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdLike(String value) {
            addCriterion("car_id like", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotLike(String value) {
            addCriterion("car_id not like", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdIn(List<String> values) {
            addCriterion("car_id in", values, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotIn(List<String> values) {
            addCriterion("car_id not in", values, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdBetween(String value1, String value2) {
            addCriterion("car_id between", value1, value2, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotBetween(String value1, String value2) {
            addCriterion("car_id not between", value1, value2, "carId");
            return (Criteria) this;
        }

        public Criteria andCarNoIsNull() {
            addCriterion("car_no is null");
            return (Criteria) this;
        }

        public Criteria andCarNoIsNotNull() {
            addCriterion("car_no is not null");
            return (Criteria) this;
        }

        public Criteria andCarNoEqualTo(String value) {
            addCriterion("car_no =", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoNotEqualTo(String value) {
            addCriterion("car_no <>", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoGreaterThan(String value) {
            addCriterion("car_no >", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoGreaterThanOrEqualTo(String value) {
            addCriterion("car_no >=", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoLessThan(String value) {
            addCriterion("car_no <", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoLessThanOrEqualTo(String value) {
            addCriterion("car_no <=", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoLike(String value) {
            addCriterion("car_no like", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoNotLike(String value) {
            addCriterion("car_no not like", value, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoIn(List<String> values) {
            addCriterion("car_no in", values, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoNotIn(List<String> values) {
            addCriterion("car_no not in", values, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoBetween(String value1, String value2) {
            addCriterion("car_no between", value1, value2, "carNo");
            return (Criteria) this;
        }

        public Criteria andCarNoNotBetween(String value1, String value2) {
            addCriterion("car_no not between", value1, value2, "carNo");
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

        public Criteria andRegistrationNoIsNull() {
            addCriterion("registration_no is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationNoIsNotNull() {
            addCriterion("registration_no is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationNoEqualTo(String value) {
            addCriterion("registration_no =", value, "registrationNo");
            return (Criteria) this;
        }

        public Criteria andRegistrationNoNotEqualTo(String value) {
            addCriterion("registration_no <>", value, "registrationNo");
            return (Criteria) this;
        }

        public Criteria andRegistrationNoGreaterThan(String value) {
            addCriterion("registration_no >", value, "registrationNo");
            return (Criteria) this;
        }

        public Criteria andRegistrationNoGreaterThanOrEqualTo(String value) {
            addCriterion("registration_no >=", value, "registrationNo");
            return (Criteria) this;
        }

        public Criteria andRegistrationNoLessThan(String value) {
            addCriterion("registration_no <", value, "registrationNo");
            return (Criteria) this;
        }

        public Criteria andRegistrationNoLessThanOrEqualTo(String value) {
            addCriterion("registration_no <=", value, "registrationNo");
            return (Criteria) this;
        }

        public Criteria andRegistrationNoLike(String value) {
            addCriterion("registration_no like", value, "registrationNo");
            return (Criteria) this;
        }

        public Criteria andRegistrationNoNotLike(String value) {
            addCriterion("registration_no not like", value, "registrationNo");
            return (Criteria) this;
        }

        public Criteria andRegistrationNoIn(List<String> values) {
            addCriterion("registration_no in", values, "registrationNo");
            return (Criteria) this;
        }

        public Criteria andRegistrationNoNotIn(List<String> values) {
            addCriterion("registration_no not in", values, "registrationNo");
            return (Criteria) this;
        }

        public Criteria andRegistrationNoBetween(String value1, String value2) {
            addCriterion("registration_no between", value1, value2, "registrationNo");
            return (Criteria) this;
        }

        public Criteria andRegistrationNoNotBetween(String value1, String value2) {
            addCriterion("registration_no not between", value1, value2, "registrationNo");
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

        public Criteria andVinIsNull() {
            addCriterion("vin is null");
            return (Criteria) this;
        }

        public Criteria andVinIsNotNull() {
            addCriterion("vin is not null");
            return (Criteria) this;
        }

        public Criteria andVinEqualTo(String value) {
            addCriterion("vin =", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotEqualTo(String value) {
            addCriterion("vin <>", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinGreaterThan(String value) {
            addCriterion("vin >", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinGreaterThanOrEqualTo(String value) {
            addCriterion("vin >=", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinLessThan(String value) {
            addCriterion("vin <", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinLessThanOrEqualTo(String value) {
            addCriterion("vin <=", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinLike(String value) {
            addCriterion("vin like", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotLike(String value) {
            addCriterion("vin not like", value, "vin");
            return (Criteria) this;
        }

        public Criteria andVinIn(List<String> values) {
            addCriterion("vin in", values, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotIn(List<String> values) {
            addCriterion("vin not in", values, "vin");
            return (Criteria) this;
        }

        public Criteria andVinBetween(String value1, String value2) {
            addCriterion("vin between", value1, value2, "vin");
            return (Criteria) this;
        }

        public Criteria andVinNotBetween(String value1, String value2) {
            addCriterion("vin not between", value1, value2, "vin");
            return (Criteria) this;
        }

        public Criteria andSeriesIsNull() {
            addCriterion("series is null");
            return (Criteria) this;
        }

        public Criteria andSeriesIsNotNull() {
            addCriterion("series is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesEqualTo(String value) {
            addCriterion("series =", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesNotEqualTo(String value) {
            addCriterion("series <>", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesGreaterThan(String value) {
            addCriterion("series >", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesGreaterThanOrEqualTo(String value) {
            addCriterion("series >=", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesLessThan(String value) {
            addCriterion("series <", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesLessThanOrEqualTo(String value) {
            addCriterion("series <=", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesLike(String value) {
            addCriterion("series like", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesNotLike(String value) {
            addCriterion("series not like", value, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesIn(List<String> values) {
            addCriterion("series in", values, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesNotIn(List<String> values) {
            addCriterion("series not in", values, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesBetween(String value1, String value2) {
            addCriterion("series between", value1, value2, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesNotBetween(String value1, String value2) {
            addCriterion("series not between", value1, value2, "series");
            return (Criteria) this;
        }

        public Criteria andSeriesNameIsNull() {
            addCriterion("series_name is null");
            return (Criteria) this;
        }

        public Criteria andSeriesNameIsNotNull() {
            addCriterion("series_name is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesNameEqualTo(String value) {
            addCriterion("series_name =", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameNotEqualTo(String value) {
            addCriterion("series_name <>", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameGreaterThan(String value) {
            addCriterion("series_name >", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameGreaterThanOrEqualTo(String value) {
            addCriterion("series_name >=", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameLessThan(String value) {
            addCriterion("series_name <", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameLessThanOrEqualTo(String value) {
            addCriterion("series_name <=", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameLike(String value) {
            addCriterion("series_name like", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameNotLike(String value) {
            addCriterion("series_name not like", value, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameIn(List<String> values) {
            addCriterion("series_name in", values, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameNotIn(List<String> values) {
            addCriterion("series_name not in", values, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameBetween(String value1, String value2) {
            addCriterion("series_name between", value1, value2, "seriesName");
            return (Criteria) this;
        }

        public Criteria andSeriesNameNotBetween(String value1, String value2) {
            addCriterion("series_name not between", value1, value2, "seriesName");
            return (Criteria) this;
        }

        public Criteria andOperaterIsNull() {
            addCriterion("operater is null");
            return (Criteria) this;
        }

        public Criteria andOperaterIsNotNull() {
            addCriterion("operater is not null");
            return (Criteria) this;
        }

        public Criteria andOperaterEqualTo(String value) {
            addCriterion("operater =", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterNotEqualTo(String value) {
            addCriterion("operater <>", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterGreaterThan(String value) {
            addCriterion("operater >", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterGreaterThanOrEqualTo(String value) {
            addCriterion("operater >=", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterLessThan(String value) {
            addCriterion("operater <", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterLessThanOrEqualTo(String value) {
            addCriterion("operater <=", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterLike(String value) {
            addCriterion("operater like", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterNotLike(String value) {
            addCriterion("operater not like", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterIn(List<String> values) {
            addCriterion("operater in", values, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterNotIn(List<String> values) {
            addCriterion("operater not in", values, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterBetween(String value1, String value2) {
            addCriterion("operater between", value1, value2, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterNotBetween(String value1, String value2) {
            addCriterion("operater not between", value1, value2, "operater");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andOperatorNameIsNull() {
            addCriterion("operator_name is null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIsNotNull() {
            addCriterion("operator_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameEqualTo(String value) {
            addCriterion("operator_name =", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotEqualTo(String value) {
            addCriterion("operator_name <>", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThan(String value) {
            addCriterion("operator_name >", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("operator_name >=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThan(String value) {
            addCriterion("operator_name <", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThanOrEqualTo(String value) {
            addCriterion("operator_name <=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLike(String value) {
            addCriterion("operator_name like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotLike(String value) {
            addCriterion("operator_name not like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIn(List<String> values) {
            addCriterion("operator_name in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotIn(List<String> values) {
            addCriterion("operator_name not in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameBetween(String value1, String value2) {
            addCriterion("operator_name between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotBetween(String value1, String value2) {
            addCriterion("operator_name not between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andAuctionIsNull() {
            addCriterion("auction is null");
            return (Criteria) this;
        }

        public Criteria andAuctionIsNotNull() {
            addCriterion("auction is not null");
            return (Criteria) this;
        }

        public Criteria andAuctionEqualTo(String value) {
            addCriterion("auction =", value, "auction");
            return (Criteria) this;
        }

        public Criteria andAuctionNotEqualTo(String value) {
            addCriterion("auction <>", value, "auction");
            return (Criteria) this;
        }

        public Criteria andAuctionGreaterThan(String value) {
            addCriterion("auction >", value, "auction");
            return (Criteria) this;
        }

        public Criteria andAuctionGreaterThanOrEqualTo(String value) {
            addCriterion("auction >=", value, "auction");
            return (Criteria) this;
        }

        public Criteria andAuctionLessThan(String value) {
            addCriterion("auction <", value, "auction");
            return (Criteria) this;
        }

        public Criteria andAuctionLessThanOrEqualTo(String value) {
            addCriterion("auction <=", value, "auction");
            return (Criteria) this;
        }

        public Criteria andAuctionLike(String value) {
            addCriterion("auction like", value, "auction");
            return (Criteria) this;
        }

        public Criteria andAuctionNotLike(String value) {
            addCriterion("auction not like", value, "auction");
            return (Criteria) this;
        }

        public Criteria andAuctionIn(List<String> values) {
            addCriterion("auction in", values, "auction");
            return (Criteria) this;
        }

        public Criteria andAuctionNotIn(List<String> values) {
            addCriterion("auction not in", values, "auction");
            return (Criteria) this;
        }

        public Criteria andAuctionBetween(String value1, String value2) {
            addCriterion("auction between", value1, value2, "auction");
            return (Criteria) this;
        }

        public Criteria andAuctionNotBetween(String value1, String value2) {
            addCriterion("auction not between", value1, value2, "auction");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressIsNull() {
            addCriterion("auction_address is null");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressIsNotNull() {
            addCriterion("auction_address is not null");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressEqualTo(String value) {
            addCriterion("auction_address =", value, "auctionAddress");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressNotEqualTo(String value) {
            addCriterion("auction_address <>", value, "auctionAddress");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressGreaterThan(String value) {
            addCriterion("auction_address >", value, "auctionAddress");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressGreaterThanOrEqualTo(String value) {
            addCriterion("auction_address >=", value, "auctionAddress");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressLessThan(String value) {
            addCriterion("auction_address <", value, "auctionAddress");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressLessThanOrEqualTo(String value) {
            addCriterion("auction_address <=", value, "auctionAddress");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressLike(String value) {
            addCriterion("auction_address like", value, "auctionAddress");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressNotLike(String value) {
            addCriterion("auction_address not like", value, "auctionAddress");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressIn(List<String> values) {
            addCriterion("auction_address in", values, "auctionAddress");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressNotIn(List<String> values) {
            addCriterion("auction_address not in", values, "auctionAddress");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressBetween(String value1, String value2) {
            addCriterion("auction_address between", value1, value2, "auctionAddress");
            return (Criteria) this;
        }

        public Criteria andAuctionAddressNotBetween(String value1, String value2) {
            addCriterion("auction_address not between", value1, value2, "auctionAddress");
            return (Criteria) this;
        }

        public Criteria andAuctionNoIsNull() {
            addCriterion("auction_no is null");
            return (Criteria) this;
        }

        public Criteria andAuctionNoIsNotNull() {
            addCriterion("auction_no is not null");
            return (Criteria) this;
        }

        public Criteria andAuctionNoEqualTo(String value) {
            addCriterion("auction_no =", value, "auctionNo");
            return (Criteria) this;
        }

        public Criteria andAuctionNoNotEqualTo(String value) {
            addCriterion("auction_no <>", value, "auctionNo");
            return (Criteria) this;
        }

        public Criteria andAuctionNoGreaterThan(String value) {
            addCriterion("auction_no >", value, "auctionNo");
            return (Criteria) this;
        }

        public Criteria andAuctionNoGreaterThanOrEqualTo(String value) {
            addCriterion("auction_no >=", value, "auctionNo");
            return (Criteria) this;
        }

        public Criteria andAuctionNoLessThan(String value) {
            addCriterion("auction_no <", value, "auctionNo");
            return (Criteria) this;
        }

        public Criteria andAuctionNoLessThanOrEqualTo(String value) {
            addCriterion("auction_no <=", value, "auctionNo");
            return (Criteria) this;
        }

        public Criteria andAuctionNoLike(String value) {
            addCriterion("auction_no like", value, "auctionNo");
            return (Criteria) this;
        }

        public Criteria andAuctionNoNotLike(String value) {
            addCriterion("auction_no not like", value, "auctionNo");
            return (Criteria) this;
        }

        public Criteria andAuctionNoIn(List<String> values) {
            addCriterion("auction_no in", values, "auctionNo");
            return (Criteria) this;
        }

        public Criteria andAuctionNoNotIn(List<String> values) {
            addCriterion("auction_no not in", values, "auctionNo");
            return (Criteria) this;
        }

        public Criteria andAuctionNoBetween(String value1, String value2) {
            addCriterion("auction_no between", value1, value2, "auctionNo");
            return (Criteria) this;
        }

        public Criteria andAuctionNoNotBetween(String value1, String value2) {
            addCriterion("auction_no not between", value1, value2, "auctionNo");
            return (Criteria) this;
        }

        public Criteria andAuctionBankIsNull() {
            addCriterion("auction_bank is null");
            return (Criteria) this;
        }

        public Criteria andAuctionBankIsNotNull() {
            addCriterion("auction_bank is not null");
            return (Criteria) this;
        }

        public Criteria andAuctionBankEqualTo(String value) {
            addCriterion("auction_bank =", value, "auctionBank");
            return (Criteria) this;
        }

        public Criteria andAuctionBankNotEqualTo(String value) {
            addCriterion("auction_bank <>", value, "auctionBank");
            return (Criteria) this;
        }

        public Criteria andAuctionBankGreaterThan(String value) {
            addCriterion("auction_bank >", value, "auctionBank");
            return (Criteria) this;
        }

        public Criteria andAuctionBankGreaterThanOrEqualTo(String value) {
            addCriterion("auction_bank >=", value, "auctionBank");
            return (Criteria) this;
        }

        public Criteria andAuctionBankLessThan(String value) {
            addCriterion("auction_bank <", value, "auctionBank");
            return (Criteria) this;
        }

        public Criteria andAuctionBankLessThanOrEqualTo(String value) {
            addCriterion("auction_bank <=", value, "auctionBank");
            return (Criteria) this;
        }

        public Criteria andAuctionBankLike(String value) {
            addCriterion("auction_bank like", value, "auctionBank");
            return (Criteria) this;
        }

        public Criteria andAuctionBankNotLike(String value) {
            addCriterion("auction_bank not like", value, "auctionBank");
            return (Criteria) this;
        }

        public Criteria andAuctionBankIn(List<String> values) {
            addCriterion("auction_bank in", values, "auctionBank");
            return (Criteria) this;
        }

        public Criteria andAuctionBankNotIn(List<String> values) {
            addCriterion("auction_bank not in", values, "auctionBank");
            return (Criteria) this;
        }

        public Criteria andAuctionBankBetween(String value1, String value2) {
            addCriterion("auction_bank between", value1, value2, "auctionBank");
            return (Criteria) this;
        }

        public Criteria andAuctionBankNotBetween(String value1, String value2) {
            addCriterion("auction_bank not between", value1, value2, "auctionBank");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileIsNull() {
            addCriterion("auction_mobile is null");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileIsNotNull() {
            addCriterion("auction_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileEqualTo(String value) {
            addCriterion("auction_mobile =", value, "auctionMobile");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileNotEqualTo(String value) {
            addCriterion("auction_mobile <>", value, "auctionMobile");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileGreaterThan(String value) {
            addCriterion("auction_mobile >", value, "auctionMobile");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileGreaterThanOrEqualTo(String value) {
            addCriterion("auction_mobile >=", value, "auctionMobile");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileLessThan(String value) {
            addCriterion("auction_mobile <", value, "auctionMobile");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileLessThanOrEqualTo(String value) {
            addCriterion("auction_mobile <=", value, "auctionMobile");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileLike(String value) {
            addCriterion("auction_mobile like", value, "auctionMobile");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileNotLike(String value) {
            addCriterion("auction_mobile not like", value, "auctionMobile");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileIn(List<String> values) {
            addCriterion("auction_mobile in", values, "auctionMobile");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileNotIn(List<String> values) {
            addCriterion("auction_mobile not in", values, "auctionMobile");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileBetween(String value1, String value2) {
            addCriterion("auction_mobile between", value1, value2, "auctionMobile");
            return (Criteria) this;
        }

        public Criteria andAuctionMobileNotBetween(String value1, String value2) {
            addCriterion("auction_mobile not between", value1, value2, "auctionMobile");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoIsNull() {
            addCriterion("market_taxNo is null");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoIsNotNull() {
            addCriterion("market_taxNo is not null");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoEqualTo(String value) {
            addCriterion("market_taxNo =", value, "marketTaxno");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoNotEqualTo(String value) {
            addCriterion("market_taxNo <>", value, "marketTaxno");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoGreaterThan(String value) {
            addCriterion("market_taxNo >", value, "marketTaxno");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoGreaterThanOrEqualTo(String value) {
            addCriterion("market_taxNo >=", value, "marketTaxno");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoLessThan(String value) {
            addCriterion("market_taxNo <", value, "marketTaxno");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoLessThanOrEqualTo(String value) {
            addCriterion("market_taxNo <=", value, "marketTaxno");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoLike(String value) {
            addCriterion("market_taxNo like", value, "marketTaxno");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoNotLike(String value) {
            addCriterion("market_taxNo not like", value, "marketTaxno");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoIn(List<String> values) {
            addCriterion("market_taxNo in", values, "marketTaxno");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoNotIn(List<String> values) {
            addCriterion("market_taxNo not in", values, "marketTaxno");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoBetween(String value1, String value2) {
            addCriterion("market_taxNo between", value1, value2, "marketTaxno");
            return (Criteria) this;
        }

        public Criteria andMarketTaxnoNotBetween(String value1, String value2) {
            addCriterion("market_taxNo not between", value1, value2, "marketTaxno");
            return (Criteria) this;
        }

        public Criteria andMarketAddressIsNull() {
            addCriterion("market_address is null");
            return (Criteria) this;
        }

        public Criteria andMarketAddressIsNotNull() {
            addCriterion("market_address is not null");
            return (Criteria) this;
        }

        public Criteria andMarketAddressEqualTo(String value) {
            addCriterion("market_address =", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressNotEqualTo(String value) {
            addCriterion("market_address <>", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressGreaterThan(String value) {
            addCriterion("market_address >", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressGreaterThanOrEqualTo(String value) {
            addCriterion("market_address >=", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressLessThan(String value) {
            addCriterion("market_address <", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressLessThanOrEqualTo(String value) {
            addCriterion("market_address <=", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressLike(String value) {
            addCriterion("market_address like", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressNotLike(String value) {
            addCriterion("market_address not like", value, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressIn(List<String> values) {
            addCriterion("market_address in", values, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressNotIn(List<String> values) {
            addCriterion("market_address not in", values, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressBetween(String value1, String value2) {
            addCriterion("market_address between", value1, value2, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketAddressNotBetween(String value1, String value2) {
            addCriterion("market_address not between", value1, value2, "marketAddress");
            return (Criteria) this;
        }

        public Criteria andMarketBankIsNull() {
            addCriterion("market_bank is null");
            return (Criteria) this;
        }

        public Criteria andMarketBankIsNotNull() {
            addCriterion("market_bank is not null");
            return (Criteria) this;
        }

        public Criteria andMarketBankEqualTo(String value) {
            addCriterion("market_bank =", value, "marketBank");
            return (Criteria) this;
        }

        public Criteria andMarketBankNotEqualTo(String value) {
            addCriterion("market_bank <>", value, "marketBank");
            return (Criteria) this;
        }

        public Criteria andMarketBankGreaterThan(String value) {
            addCriterion("market_bank >", value, "marketBank");
            return (Criteria) this;
        }

        public Criteria andMarketBankGreaterThanOrEqualTo(String value) {
            addCriterion("market_bank >=", value, "marketBank");
            return (Criteria) this;
        }

        public Criteria andMarketBankLessThan(String value) {
            addCriterion("market_bank <", value, "marketBank");
            return (Criteria) this;
        }

        public Criteria andMarketBankLessThanOrEqualTo(String value) {
            addCriterion("market_bank <=", value, "marketBank");
            return (Criteria) this;
        }

        public Criteria andMarketBankLike(String value) {
            addCriterion("market_bank like", value, "marketBank");
            return (Criteria) this;
        }

        public Criteria andMarketBankNotLike(String value) {
            addCriterion("market_bank not like", value, "marketBank");
            return (Criteria) this;
        }

        public Criteria andMarketBankIn(List<String> values) {
            addCriterion("market_bank in", values, "marketBank");
            return (Criteria) this;
        }

        public Criteria andMarketBankNotIn(List<String> values) {
            addCriterion("market_bank not in", values, "marketBank");
            return (Criteria) this;
        }

        public Criteria andMarketBankBetween(String value1, String value2) {
            addCriterion("market_bank between", value1, value2, "marketBank");
            return (Criteria) this;
        }

        public Criteria andMarketBankNotBetween(String value1, String value2) {
            addCriterion("market_bank not between", value1, value2, "marketBank");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneIsNull() {
            addCriterion("market_phone is null");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneIsNotNull() {
            addCriterion("market_phone is not null");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneEqualTo(String value) {
            addCriterion("market_phone =", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneNotEqualTo(String value) {
            addCriterion("market_phone <>", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneGreaterThan(String value) {
            addCriterion("market_phone >", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("market_phone >=", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneLessThan(String value) {
            addCriterion("market_phone <", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneLessThanOrEqualTo(String value) {
            addCriterion("market_phone <=", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneLike(String value) {
            addCriterion("market_phone like", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneNotLike(String value) {
            addCriterion("market_phone not like", value, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneIn(List<String> values) {
            addCriterion("market_phone in", values, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneNotIn(List<String> values) {
            addCriterion("market_phone not in", values, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneBetween(String value1, String value2) {
            addCriterion("market_phone between", value1, value2, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andMarketPhoneNotBetween(String value1, String value2) {
            addCriterion("market_phone not between", value1, value2, "marketPhone");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorIsNull() {
            addCriterion("ticket_operator is null");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorIsNotNull() {
            addCriterion("ticket_operator is not null");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorEqualTo(String value) {
            addCriterion("ticket_operator =", value, "ticketOperator");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorNotEqualTo(String value) {
            addCriterion("ticket_operator <>", value, "ticketOperator");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorGreaterThan(String value) {
            addCriterion("ticket_operator >", value, "ticketOperator");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("ticket_operator >=", value, "ticketOperator");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorLessThan(String value) {
            addCriterion("ticket_operator <", value, "ticketOperator");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorLessThanOrEqualTo(String value) {
            addCriterion("ticket_operator <=", value, "ticketOperator");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorLike(String value) {
            addCriterion("ticket_operator like", value, "ticketOperator");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorNotLike(String value) {
            addCriterion("ticket_operator not like", value, "ticketOperator");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorIn(List<String> values) {
            addCriterion("ticket_operator in", values, "ticketOperator");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorNotIn(List<String> values) {
            addCriterion("ticket_operator not in", values, "ticketOperator");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorBetween(String value1, String value2) {
            addCriterion("ticket_operator between", value1, value2, "ticketOperator");
            return (Criteria) this;
        }

        public Criteria andTicketOperatorNotBetween(String value1, String value2) {
            addCriterion("ticket_operator not between", value1, value2, "ticketOperator");
            return (Criteria) this;
        }

        public Criteria andTicketMarketIsNull() {
            addCriterion("ticket_market is null");
            return (Criteria) this;
        }

        public Criteria andTicketMarketIsNotNull() {
            addCriterion("ticket_market is not null");
            return (Criteria) this;
        }

        public Criteria andTicketMarketEqualTo(String value) {
            addCriterion("ticket_market =", value, "ticketMarket");
            return (Criteria) this;
        }

        public Criteria andTicketMarketNotEqualTo(String value) {
            addCriterion("ticket_market <>", value, "ticketMarket");
            return (Criteria) this;
        }

        public Criteria andTicketMarketGreaterThan(String value) {
            addCriterion("ticket_market >", value, "ticketMarket");
            return (Criteria) this;
        }

        public Criteria andTicketMarketGreaterThanOrEqualTo(String value) {
            addCriterion("ticket_market >=", value, "ticketMarket");
            return (Criteria) this;
        }

        public Criteria andTicketMarketLessThan(String value) {
            addCriterion("ticket_market <", value, "ticketMarket");
            return (Criteria) this;
        }

        public Criteria andTicketMarketLessThanOrEqualTo(String value) {
            addCriterion("ticket_market <=", value, "ticketMarket");
            return (Criteria) this;
        }

        public Criteria andTicketMarketLike(String value) {
            addCriterion("ticket_market like", value, "ticketMarket");
            return (Criteria) this;
        }

        public Criteria andTicketMarketNotLike(String value) {
            addCriterion("ticket_market not like", value, "ticketMarket");
            return (Criteria) this;
        }

        public Criteria andTicketMarketIn(List<String> values) {
            addCriterion("ticket_market in", values, "ticketMarket");
            return (Criteria) this;
        }

        public Criteria andTicketMarketNotIn(List<String> values) {
            addCriterion("ticket_market not in", values, "ticketMarket");
            return (Criteria) this;
        }

        public Criteria andTicketMarketBetween(String value1, String value2) {
            addCriterion("ticket_market between", value1, value2, "ticketMarket");
            return (Criteria) this;
        }

        public Criteria andTicketMarketNotBetween(String value1, String value2) {
            addCriterion("ticket_market not between", value1, value2, "ticketMarket");
            return (Criteria) this;
        }

        public Criteria andOfficeIsNull() {
            addCriterion("office is null");
            return (Criteria) this;
        }

        public Criteria andOfficeIsNotNull() {
            addCriterion("office is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeEqualTo(String value) {
            addCriterion("office =", value, "office");
            return (Criteria) this;
        }

        public Criteria andOfficeNotEqualTo(String value) {
            addCriterion("office <>", value, "office");
            return (Criteria) this;
        }

        public Criteria andOfficeGreaterThan(String value) {
            addCriterion("office >", value, "office");
            return (Criteria) this;
        }

        public Criteria andOfficeGreaterThanOrEqualTo(String value) {
            addCriterion("office >=", value, "office");
            return (Criteria) this;
        }

        public Criteria andOfficeLessThan(String value) {
            addCriterion("office <", value, "office");
            return (Criteria) this;
        }

        public Criteria andOfficeLessThanOrEqualTo(String value) {
            addCriterion("office <=", value, "office");
            return (Criteria) this;
        }

        public Criteria andOfficeLike(String value) {
            addCriterion("office like", value, "office");
            return (Criteria) this;
        }

        public Criteria andOfficeNotLike(String value) {
            addCriterion("office not like", value, "office");
            return (Criteria) this;
        }

        public Criteria andOfficeIn(List<String> values) {
            addCriterion("office in", values, "office");
            return (Criteria) this;
        }

        public Criteria andOfficeNotIn(List<String> values) {
            addCriterion("office not in", values, "office");
            return (Criteria) this;
        }

        public Criteria andOfficeBetween(String value1, String value2) {
            addCriterion("office between", value1, value2, "office");
            return (Criteria) this;
        }

        public Criteria andOfficeNotBetween(String value1, String value2) {
            addCriterion("office not between", value1, value2, "office");
            return (Criteria) this;
        }

        public Criteria andSyncTimeIsNull() {
            addCriterion("sync_time is null");
            return (Criteria) this;
        }

        public Criteria andSyncTimeIsNotNull() {
            addCriterion("sync_time is not null");
            return (Criteria) this;
        }

        public Criteria andSyncTimeEqualTo(Date value) {
            addCriterion("sync_time =", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeNotEqualTo(Date value) {
            addCriterion("sync_time <>", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeGreaterThan(Date value) {
            addCriterion("sync_time >", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sync_time >=", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeLessThan(Date value) {
            addCriterion("sync_time <", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeLessThanOrEqualTo(Date value) {
            addCriterion("sync_time <=", value, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeIn(List<Date> values) {
            addCriterion("sync_time in", values, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeNotIn(List<Date> values) {
            addCriterion("sync_time not in", values, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeBetween(Date value1, Date value2) {
            addCriterion("sync_time between", value1, value2, "syncTime");
            return (Criteria) this;
        }

        public Criteria andSyncTimeNotBetween(Date value1, Date value2) {
            addCriterion("sync_time not between", value1, value2, "syncTime");
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

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("pay_type like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("pay_type not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andIsNewEnergyIsNull() {
            addCriterion("is_new_energy is null");
            return (Criteria) this;
        }

        public Criteria andIsNewEnergyIsNotNull() {
            addCriterion("is_new_energy is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewEnergyEqualTo(Integer value) {
            addCriterion("is_new_energy =", value, "isNewEnergy");
            return (Criteria) this;
        }

        public Criteria andIsNewEnergyNotEqualTo(Integer value) {
            addCriterion("is_new_energy <>", value, "isNewEnergy");
            return (Criteria) this;
        }

        public Criteria andIsNewEnergyGreaterThan(Integer value) {
            addCriterion("is_new_energy >", value, "isNewEnergy");
            return (Criteria) this;
        }

        public Criteria andIsNewEnergyGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_new_energy >=", value, "isNewEnergy");
            return (Criteria) this;
        }

        public Criteria andIsNewEnergyLessThan(Integer value) {
            addCriterion("is_new_energy <", value, "isNewEnergy");
            return (Criteria) this;
        }

        public Criteria andIsNewEnergyLessThanOrEqualTo(Integer value) {
            addCriterion("is_new_energy <=", value, "isNewEnergy");
            return (Criteria) this;
        }

        public Criteria andIsNewEnergyIn(List<Integer> values) {
            addCriterion("is_new_energy in", values, "isNewEnergy");
            return (Criteria) this;
        }

        public Criteria andIsNewEnergyNotIn(List<Integer> values) {
            addCriterion("is_new_energy not in", values, "isNewEnergy");
            return (Criteria) this;
        }

        public Criteria andIsNewEnergyBetween(Integer value1, Integer value2) {
            addCriterion("is_new_energy between", value1, value2, "isNewEnergy");
            return (Criteria) this;
        }

        public Criteria andIsNewEnergyNotBetween(Integer value1, Integer value2) {
            addCriterion("is_new_energy not between", value1, value2, "isNewEnergy");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardIsNull() {
            addCriterion("agent_id_card is null");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardIsNotNull() {
            addCriterion("agent_id_card is not null");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardEqualTo(String value) {
            addCriterion("agent_id_card =", value, "agentIdCard");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardNotEqualTo(String value) {
            addCriterion("agent_id_card <>", value, "agentIdCard");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardGreaterThan(String value) {
            addCriterion("agent_id_card >", value, "agentIdCard");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("agent_id_card >=", value, "agentIdCard");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardLessThan(String value) {
            addCriterion("agent_id_card <", value, "agentIdCard");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardLessThanOrEqualTo(String value) {
            addCriterion("agent_id_card <=", value, "agentIdCard");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardLike(String value) {
            addCriterion("agent_id_card like", value, "agentIdCard");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardNotLike(String value) {
            addCriterion("agent_id_card not like", value, "agentIdCard");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardIn(List<String> values) {
            addCriterion("agent_id_card in", values, "agentIdCard");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardNotIn(List<String> values) {
            addCriterion("agent_id_card not in", values, "agentIdCard");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardBetween(String value1, String value2) {
            addCriterion("agent_id_card between", value1, value2, "agentIdCard");
            return (Criteria) this;
        }

        public Criteria andAgentIdCardNotBetween(String value1, String value2) {
            addCriterion("agent_id_card not between", value1, value2, "agentIdCard");
            return (Criteria) this;
        }

        public Criteria andAgentNameIsNull() {
            addCriterion("agent_name is null");
            return (Criteria) this;
        }

        public Criteria andAgentNameIsNotNull() {
            addCriterion("agent_name is not null");
            return (Criteria) this;
        }

        public Criteria andAgentNameEqualTo(String value) {
            addCriterion("agent_name =", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotEqualTo(String value) {
            addCriterion("agent_name <>", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameGreaterThan(String value) {
            addCriterion("agent_name >", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameGreaterThanOrEqualTo(String value) {
            addCriterion("agent_name >=", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLessThan(String value) {
            addCriterion("agent_name <", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLessThanOrEqualTo(String value) {
            addCriterion("agent_name <=", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameLike(String value) {
            addCriterion("agent_name like", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotLike(String value) {
            addCriterion("agent_name not like", value, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameIn(List<String> values) {
            addCriterion("agent_name in", values, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotIn(List<String> values) {
            addCriterion("agent_name not in", values, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameBetween(String value1, String value2) {
            addCriterion("agent_name between", value1, value2, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentNameNotBetween(String value1, String value2) {
            addCriterion("agent_name not between", value1, value2, "agentName");
            return (Criteria) this;
        }

        public Criteria andAgentTypeIsNull() {
            addCriterion("agent_type is null");
            return (Criteria) this;
        }

        public Criteria andAgentTypeIsNotNull() {
            addCriterion("agent_type is not null");
            return (Criteria) this;
        }

        public Criteria andAgentTypeEqualTo(Integer value) {
            addCriterion("agent_type =", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeNotEqualTo(Integer value) {
            addCriterion("agent_type <>", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeGreaterThan(Integer value) {
            addCriterion("agent_type >", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("agent_type >=", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeLessThan(Integer value) {
            addCriterion("agent_type <", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("agent_type <=", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeIn(List<Integer> values) {
            addCriterion("agent_type in", values, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeNotIn(List<Integer> values) {
            addCriterion("agent_type not in", values, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeBetween(Integer value1, Integer value2) {
            addCriterion("agent_type between", value1, value2, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("agent_type not between", value1, value2, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentAddressIsNull() {
            addCriterion("agent_address is null");
            return (Criteria) this;
        }

        public Criteria andAgentAddressIsNotNull() {
            addCriterion("agent_address is not null");
            return (Criteria) this;
        }

        public Criteria andAgentAddressEqualTo(String value) {
            addCriterion("agent_address =", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressNotEqualTo(String value) {
            addCriterion("agent_address <>", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressGreaterThan(String value) {
            addCriterion("agent_address >", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressGreaterThanOrEqualTo(String value) {
            addCriterion("agent_address >=", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressLessThan(String value) {
            addCriterion("agent_address <", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressLessThanOrEqualTo(String value) {
            addCriterion("agent_address <=", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressLike(String value) {
            addCriterion("agent_address like", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressNotLike(String value) {
            addCriterion("agent_address not like", value, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressIn(List<String> values) {
            addCriterion("agent_address in", values, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressNotIn(List<String> values) {
            addCriterion("agent_address not in", values, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressBetween(String value1, String value2) {
            addCriterion("agent_address between", value1, value2, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentAddressNotBetween(String value1, String value2) {
            addCriterion("agent_address not between", value1, value2, "agentAddress");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoIsNull() {
            addCriterion("agent_tax_no is null");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoIsNotNull() {
            addCriterion("agent_tax_no is not null");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoEqualTo(String value) {
            addCriterion("agent_tax_no =", value, "agentTaxNo");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoNotEqualTo(String value) {
            addCriterion("agent_tax_no <>", value, "agentTaxNo");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoGreaterThan(String value) {
            addCriterion("agent_tax_no >", value, "agentTaxNo");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoGreaterThanOrEqualTo(String value) {
            addCriterion("agent_tax_no >=", value, "agentTaxNo");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoLessThan(String value) {
            addCriterion("agent_tax_no <", value, "agentTaxNo");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoLessThanOrEqualTo(String value) {
            addCriterion("agent_tax_no <=", value, "agentTaxNo");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoLike(String value) {
            addCriterion("agent_tax_no like", value, "agentTaxNo");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoNotLike(String value) {
            addCriterion("agent_tax_no not like", value, "agentTaxNo");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoIn(List<String> values) {
            addCriterion("agent_tax_no in", values, "agentTaxNo");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoNotIn(List<String> values) {
            addCriterion("agent_tax_no not in", values, "agentTaxNo");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoBetween(String value1, String value2) {
            addCriterion("agent_tax_no between", value1, value2, "agentTaxNo");
            return (Criteria) this;
        }

        public Criteria andAgentTaxNoNotBetween(String value1, String value2) {
            addCriterion("agent_tax_no not between", value1, value2, "agentTaxNo");
            return (Criteria) this;
        }

        public Criteria andAgentMobileIsNull() {
            addCriterion("agent_mobile is null");
            return (Criteria) this;
        }

        public Criteria andAgentMobileIsNotNull() {
            addCriterion("agent_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andAgentMobileEqualTo(String value) {
            addCriterion("agent_mobile =", value, "agentMobile");
            return (Criteria) this;
        }

        public Criteria andAgentMobileNotEqualTo(String value) {
            addCriterion("agent_mobile <>", value, "agentMobile");
            return (Criteria) this;
        }

        public Criteria andAgentMobileGreaterThan(String value) {
            addCriterion("agent_mobile >", value, "agentMobile");
            return (Criteria) this;
        }

        public Criteria andAgentMobileGreaterThanOrEqualTo(String value) {
            addCriterion("agent_mobile >=", value, "agentMobile");
            return (Criteria) this;
        }

        public Criteria andAgentMobileLessThan(String value) {
            addCriterion("agent_mobile <", value, "agentMobile");
            return (Criteria) this;
        }

        public Criteria andAgentMobileLessThanOrEqualTo(String value) {
            addCriterion("agent_mobile <=", value, "agentMobile");
            return (Criteria) this;
        }

        public Criteria andAgentMobileLike(String value) {
            addCriterion("agent_mobile like", value, "agentMobile");
            return (Criteria) this;
        }

        public Criteria andAgentMobileNotLike(String value) {
            addCriterion("agent_mobile not like", value, "agentMobile");
            return (Criteria) this;
        }

        public Criteria andAgentMobileIn(List<String> values) {
            addCriterion("agent_mobile in", values, "agentMobile");
            return (Criteria) this;
        }

        public Criteria andAgentMobileNotIn(List<String> values) {
            addCriterion("agent_mobile not in", values, "agentMobile");
            return (Criteria) this;
        }

        public Criteria andAgentMobileBetween(String value1, String value2) {
            addCriterion("agent_mobile between", value1, value2, "agentMobile");
            return (Criteria) this;
        }

        public Criteria andAgentMobileNotBetween(String value1, String value2) {
            addCriterion("agent_mobile not between", value1, value2, "agentMobile");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityIsNull() {
            addCriterion("agent_nationality is null");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityIsNotNull() {
            addCriterion("agent_nationality is not null");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityEqualTo(String value) {
            addCriterion("agent_nationality =", value, "agentNationality");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityNotEqualTo(String value) {
            addCriterion("agent_nationality <>", value, "agentNationality");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityGreaterThan(String value) {
            addCriterion("agent_nationality >", value, "agentNationality");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityGreaterThanOrEqualTo(String value) {
            addCriterion("agent_nationality >=", value, "agentNationality");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityLessThan(String value) {
            addCriterion("agent_nationality <", value, "agentNationality");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityLessThanOrEqualTo(String value) {
            addCriterion("agent_nationality <=", value, "agentNationality");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityLike(String value) {
            addCriterion("agent_nationality like", value, "agentNationality");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityNotLike(String value) {
            addCriterion("agent_nationality not like", value, "agentNationality");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityIn(List<String> values) {
            addCriterion("agent_nationality in", values, "agentNationality");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityNotIn(List<String> values) {
            addCriterion("agent_nationality not in", values, "agentNationality");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityBetween(String value1, String value2) {
            addCriterion("agent_nationality between", value1, value2, "agentNationality");
            return (Criteria) this;
        }

        public Criteria andAgentNationalityNotBetween(String value1, String value2) {
            addCriterion("agent_nationality not between", value1, value2, "agentNationality");
            return (Criteria) this;
        }

        public Criteria andAgentNoIsNull() {
            addCriterion("agent_no is null");
            return (Criteria) this;
        }

        public Criteria andAgentNoIsNotNull() {
            addCriterion("agent_no is not null");
            return (Criteria) this;
        }

        public Criteria andAgentNoEqualTo(String value) {
            addCriterion("agent_no =", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoNotEqualTo(String value) {
            addCriterion("agent_no <>", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoGreaterThan(String value) {
            addCriterion("agent_no >", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoGreaterThanOrEqualTo(String value) {
            addCriterion("agent_no >=", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoLessThan(String value) {
            addCriterion("agent_no <", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoLessThanOrEqualTo(String value) {
            addCriterion("agent_no <=", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoLike(String value) {
            addCriterion("agent_no like", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoNotLike(String value) {
            addCriterion("agent_no not like", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoIn(List<String> values) {
            addCriterion("agent_no in", values, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoNotIn(List<String> values) {
            addCriterion("agent_no not in", values, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoBetween(String value1, String value2) {
            addCriterion("agent_no between", value1, value2, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoNotBetween(String value1, String value2) {
            addCriterion("agent_no not between", value1, value2, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentPassportIsNull() {
            addCriterion("agent_passport is null");
            return (Criteria) this;
        }

        public Criteria andAgentPassportIsNotNull() {
            addCriterion("agent_passport is not null");
            return (Criteria) this;
        }

        public Criteria andAgentPassportEqualTo(String value) {
            addCriterion("agent_passport =", value, "agentPassport");
            return (Criteria) this;
        }

        public Criteria andAgentPassportNotEqualTo(String value) {
            addCriterion("agent_passport <>", value, "agentPassport");
            return (Criteria) this;
        }

        public Criteria andAgentPassportGreaterThan(String value) {
            addCriterion("agent_passport >", value, "agentPassport");
            return (Criteria) this;
        }

        public Criteria andAgentPassportGreaterThanOrEqualTo(String value) {
            addCriterion("agent_passport >=", value, "agentPassport");
            return (Criteria) this;
        }

        public Criteria andAgentPassportLessThan(String value) {
            addCriterion("agent_passport <", value, "agentPassport");
            return (Criteria) this;
        }

        public Criteria andAgentPassportLessThanOrEqualTo(String value) {
            addCriterion("agent_passport <=", value, "agentPassport");
            return (Criteria) this;
        }

        public Criteria andAgentPassportLike(String value) {
            addCriterion("agent_passport like", value, "agentPassport");
            return (Criteria) this;
        }

        public Criteria andAgentPassportNotLike(String value) {
            addCriterion("agent_passport not like", value, "agentPassport");
            return (Criteria) this;
        }

        public Criteria andAgentPassportIn(List<String> values) {
            addCriterion("agent_passport in", values, "agentPassport");
            return (Criteria) this;
        }

        public Criteria andAgentPassportNotIn(List<String> values) {
            addCriterion("agent_passport not in", values, "agentPassport");
            return (Criteria) this;
        }

        public Criteria andAgentPassportBetween(String value1, String value2) {
            addCriterion("agent_passport between", value1, value2, "agentPassport");
            return (Criteria) this;
        }

        public Criteria andAgentPassportNotBetween(String value1, String value2) {
            addCriterion("agent_passport not between", value1, value2, "agentPassport");
            return (Criteria) this;
        }

        public Criteria andAgentNationIsNull() {
            addCriterion("agent_nation is null");
            return (Criteria) this;
        }

        public Criteria andAgentNationIsNotNull() {
            addCriterion("agent_nation is not null");
            return (Criteria) this;
        }

        public Criteria andAgentNationEqualTo(String value) {
            addCriterion("agent_nation =", value, "agentNation");
            return (Criteria) this;
        }

        public Criteria andAgentNationNotEqualTo(String value) {
            addCriterion("agent_nation <>", value, "agentNation");
            return (Criteria) this;
        }

        public Criteria andAgentNationGreaterThan(String value) {
            addCriterion("agent_nation >", value, "agentNation");
            return (Criteria) this;
        }

        public Criteria andAgentNationGreaterThanOrEqualTo(String value) {
            addCriterion("agent_nation >=", value, "agentNation");
            return (Criteria) this;
        }

        public Criteria andAgentNationLessThan(String value) {
            addCriterion("agent_nation <", value, "agentNation");
            return (Criteria) this;
        }

        public Criteria andAgentNationLessThanOrEqualTo(String value) {
            addCriterion("agent_nation <=", value, "agentNation");
            return (Criteria) this;
        }

        public Criteria andAgentNationLike(String value) {
            addCriterion("agent_nation like", value, "agentNation");
            return (Criteria) this;
        }

        public Criteria andAgentNationNotLike(String value) {
            addCriterion("agent_nation not like", value, "agentNation");
            return (Criteria) this;
        }

        public Criteria andAgentNationIn(List<String> values) {
            addCriterion("agent_nation in", values, "agentNation");
            return (Criteria) this;
        }

        public Criteria andAgentNationNotIn(List<String> values) {
            addCriterion("agent_nation not in", values, "agentNation");
            return (Criteria) this;
        }

        public Criteria andAgentNationBetween(String value1, String value2) {
            addCriterion("agent_nation between", value1, value2, "agentNation");
            return (Criteria) this;
        }

        public Criteria andAgentNationNotBetween(String value1, String value2) {
            addCriterion("agent_nation not between", value1, value2, "agentNation");
            return (Criteria) this;
        }

        public Criteria andIsAgentCodeIsNull() {
            addCriterion("is_agent_code is null");
            return (Criteria) this;
        }

        public Criteria andIsAgentCodeIsNotNull() {
            addCriterion("is_agent_code is not null");
            return (Criteria) this;
        }

        public Criteria andIsAgentCodeEqualTo(Integer value) {
            addCriterion("is_agent_code =", value, "isAgentCode");
            return (Criteria) this;
        }

        public Criteria andIsAgentCodeNotEqualTo(Integer value) {
            addCriterion("is_agent_code <>", value, "isAgentCode");
            return (Criteria) this;
        }

        public Criteria andIsAgentCodeGreaterThan(Integer value) {
            addCriterion("is_agent_code >", value, "isAgentCode");
            return (Criteria) this;
        }

        public Criteria andIsAgentCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_agent_code >=", value, "isAgentCode");
            return (Criteria) this;
        }

        public Criteria andIsAgentCodeLessThan(Integer value) {
            addCriterion("is_agent_code <", value, "isAgentCode");
            return (Criteria) this;
        }

        public Criteria andIsAgentCodeLessThanOrEqualTo(Integer value) {
            addCriterion("is_agent_code <=", value, "isAgentCode");
            return (Criteria) this;
        }

        public Criteria andIsAgentCodeIn(List<Integer> values) {
            addCriterion("is_agent_code in", values, "isAgentCode");
            return (Criteria) this;
        }

        public Criteria andIsAgentCodeNotIn(List<Integer> values) {
            addCriterion("is_agent_code not in", values, "isAgentCode");
            return (Criteria) this;
        }

        public Criteria andIsAgentCodeBetween(Integer value1, Integer value2) {
            addCriterion("is_agent_code between", value1, value2, "isAgentCode");
            return (Criteria) this;
        }

        public Criteria andIsAgentCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("is_agent_code not between", value1, value2, "isAgentCode");
            return (Criteria) this;
        }

        public Criteria andIsAgentIsNull() {
            addCriterion("is_agent is null");
            return (Criteria) this;
        }

        public Criteria andIsAgentIsNotNull() {
            addCriterion("is_agent is not null");
            return (Criteria) this;
        }

        public Criteria andIsAgentEqualTo(Integer value) {
            addCriterion("is_agent =", value, "isAgent");
            return (Criteria) this;
        }

        public Criteria andIsAgentNotEqualTo(Integer value) {
            addCriterion("is_agent <>", value, "isAgent");
            return (Criteria) this;
        }

        public Criteria andIsAgentGreaterThan(Integer value) {
            addCriterion("is_agent >", value, "isAgent");
            return (Criteria) this;
        }

        public Criteria andIsAgentGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_agent >=", value, "isAgent");
            return (Criteria) this;
        }

        public Criteria andIsAgentLessThan(Integer value) {
            addCriterion("is_agent <", value, "isAgent");
            return (Criteria) this;
        }

        public Criteria andIsAgentLessThanOrEqualTo(Integer value) {
            addCriterion("is_agent <=", value, "isAgent");
            return (Criteria) this;
        }

        public Criteria andIsAgentIn(List<Integer> values) {
            addCriterion("is_agent in", values, "isAgent");
            return (Criteria) this;
        }

        public Criteria andIsAgentNotIn(List<Integer> values) {
            addCriterion("is_agent not in", values, "isAgent");
            return (Criteria) this;
        }

        public Criteria andIsAgentBetween(Integer value1, Integer value2) {
            addCriterion("is_agent between", value1, value2, "isAgent");
            return (Criteria) this;
        }

        public Criteria andIsAgentNotBetween(Integer value1, Integer value2) {
            addCriterion("is_agent not between", value1, value2, "isAgent");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeIsNull() {
            addCriterion("car_invoice_type is null");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeIsNotNull() {
            addCriterion("car_invoice_type is not null");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeEqualTo(String value) {
            addCriterion("car_invoice_type =", value, "carInvoiceType");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeNotEqualTo(String value) {
            addCriterion("car_invoice_type <>", value, "carInvoiceType");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeGreaterThan(String value) {
            addCriterion("car_invoice_type >", value, "carInvoiceType");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("car_invoice_type >=", value, "carInvoiceType");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeLessThan(String value) {
            addCriterion("car_invoice_type <", value, "carInvoiceType");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeLessThanOrEqualTo(String value) {
            addCriterion("car_invoice_type <=", value, "carInvoiceType");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeLike(String value) {
            addCriterion("car_invoice_type like", value, "carInvoiceType");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeNotLike(String value) {
            addCriterion("car_invoice_type not like", value, "carInvoiceType");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeIn(List<String> values) {
            addCriterion("car_invoice_type in", values, "carInvoiceType");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeNotIn(List<String> values) {
            addCriterion("car_invoice_type not in", values, "carInvoiceType");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeBetween(String value1, String value2) {
            addCriterion("car_invoice_type between", value1, value2, "carInvoiceType");
            return (Criteria) this;
        }

        public Criteria andCarInvoiceTypeNotBetween(String value1, String value2) {
            addCriterion("car_invoice_type not between", value1, value2, "carInvoiceType");
            return (Criteria) this;
        }

        public Criteria andInitialRegistrationDateIsNull() {
            addCriterion("initial_registration_date is null");
            return (Criteria) this;
        }

        public Criteria andInitialRegistrationDateIsNotNull() {
            addCriterion("initial_registration_date is not null");
            return (Criteria) this;
        }

        public Criteria andInitialRegistrationDateEqualTo(Date value) {
            addCriterionForJDBCDate("initial_registration_date =", value, "initialRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andInitialRegistrationDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("initial_registration_date <>", value, "initialRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andInitialRegistrationDateGreaterThan(Date value) {
            addCriterionForJDBCDate("initial_registration_date >", value, "initialRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andInitialRegistrationDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("initial_registration_date >=", value, "initialRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andInitialRegistrationDateLessThan(Date value) {
            addCriterionForJDBCDate("initial_registration_date <", value, "initialRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andInitialRegistrationDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("initial_registration_date <=", value, "initialRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andInitialRegistrationDateIn(List<Date> values) {
            addCriterionForJDBCDate("initial_registration_date in", values, "initialRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andInitialRegistrationDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("initial_registration_date not in", values, "initialRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andInitialRegistrationDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("initial_registration_date between", value1, value2, "initialRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andInitialRegistrationDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("initial_registration_date not between", value1, value2, "initialRegistrationDate");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdIsNull() {
            addCriterion("invoice_purchase_id is null");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdIsNotNull() {
            addCriterion("invoice_purchase_id is not null");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdEqualTo(String value) {
            addCriterion("invoice_purchase_id =", value, "invoicePurchaseId");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdNotEqualTo(String value) {
            addCriterion("invoice_purchase_id <>", value, "invoicePurchaseId");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdGreaterThan(String value) {
            addCriterion("invoice_purchase_id >", value, "invoicePurchaseId");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_purchase_id >=", value, "invoicePurchaseId");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdLessThan(String value) {
            addCriterion("invoice_purchase_id <", value, "invoicePurchaseId");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdLessThanOrEqualTo(String value) {
            addCriterion("invoice_purchase_id <=", value, "invoicePurchaseId");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdLike(String value) {
            addCriterion("invoice_purchase_id like", value, "invoicePurchaseId");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdNotLike(String value) {
            addCriterion("invoice_purchase_id not like", value, "invoicePurchaseId");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdIn(List<String> values) {
            addCriterion("invoice_purchase_id in", values, "invoicePurchaseId");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdNotIn(List<String> values) {
            addCriterion("invoice_purchase_id not in", values, "invoicePurchaseId");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdBetween(String value1, String value2) {
            addCriterion("invoice_purchase_id between", value1, value2, "invoicePurchaseId");
            return (Criteria) this;
        }

        public Criteria andInvoicePurchaseIdNotBetween(String value1, String value2) {
            addCriterion("invoice_purchase_id not between", value1, value2, "invoicePurchaseId");
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