package com.maxcar.tenant.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserTenantExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserTenantExample() {
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

        public Criteria andTenantNoIsNull() {
            addCriterion("tenant_no is null");
            return (Criteria) this;
        }

        public Criteria andTenantNoIsNotNull() {
            addCriterion("tenant_no is not null");
            return (Criteria) this;
        }

        public Criteria andTenantNoEqualTo(String value) {
            addCriterion("tenant_no =", value, "tenantNo");
            return (Criteria) this;
        }

        public Criteria andTenantNoNotEqualTo(String value) {
            addCriterion("tenant_no <>", value, "tenantNo");
            return (Criteria) this;
        }

        public Criteria andTenantNoGreaterThan(String value) {
            addCriterion("tenant_no >", value, "tenantNo");
            return (Criteria) this;
        }

        public Criteria andTenantNoGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_no >=", value, "tenantNo");
            return (Criteria) this;
        }

        public Criteria andTenantNoLessThan(String value) {
            addCriterion("tenant_no <", value, "tenantNo");
            return (Criteria) this;
        }

        public Criteria andTenantNoLessThanOrEqualTo(String value) {
            addCriterion("tenant_no <=", value, "tenantNo");
            return (Criteria) this;
        }

        public Criteria andTenantNoLike(String value) {
            addCriterion("tenant_no like", value, "tenantNo");
            return (Criteria) this;
        }

        public Criteria andTenantNoNotLike(String value) {
            addCriterion("tenant_no not like", value, "tenantNo");
            return (Criteria) this;
        }

        public Criteria andTenantNoIn(List<String> values) {
            addCriterion("tenant_no in", values, "tenantNo");
            return (Criteria) this;
        }

        public Criteria andTenantNoNotIn(List<String> values) {
            addCriterion("tenant_no not in", values, "tenantNo");
            return (Criteria) this;
        }

        public Criteria andTenantNoBetween(String value1, String value2) {
            addCriterion("tenant_no between", value1, value2, "tenantNo");
            return (Criteria) this;
        }

        public Criteria andTenantNoNotBetween(String value1, String value2) {
            addCriterion("tenant_no not between", value1, value2, "tenantNo");
            return (Criteria) this;
        }

        public Criteria andTenantNameIsNull() {
            addCriterion("tenant_name is null");
            return (Criteria) this;
        }

        public Criteria andTenantNameIsNotNull() {
            addCriterion("tenant_name is not null");
            return (Criteria) this;
        }

        public Criteria andTenantNameEqualTo(String value) {
            addCriterion("tenant_name =", value, "tenantName");
            return (Criteria) this;
        }

        public Criteria andTenantNameNotEqualTo(String value) {
            addCriterion("tenant_name <>", value, "tenantName");
            return (Criteria) this;
        }

        public Criteria andTenantNameGreaterThan(String value) {
            addCriterion("tenant_name >", value, "tenantName");
            return (Criteria) this;
        }

        public Criteria andTenantNameGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_name >=", value, "tenantName");
            return (Criteria) this;
        }

        public Criteria andTenantNameLessThan(String value) {
            addCriterion("tenant_name <", value, "tenantName");
            return (Criteria) this;
        }

        public Criteria andTenantNameLessThanOrEqualTo(String value) {
            addCriterion("tenant_name <=", value, "tenantName");
            return (Criteria) this;
        }

        public Criteria andTenantNameLike(String value) {
            addCriterion("tenant_name like", value, "tenantName");
            return (Criteria) this;
        }

        public Criteria andTenantNameNotLike(String value) {
            addCriterion("tenant_name not like", value, "tenantName");
            return (Criteria) this;
        }

        public Criteria andTenantNameIn(List<String> values) {
            addCriterion("tenant_name in", values, "tenantName");
            return (Criteria) this;
        }

        public Criteria andTenantNameNotIn(List<String> values) {
            addCriterion("tenant_name not in", values, "tenantName");
            return (Criteria) this;
        }

        public Criteria andTenantNameBetween(String value1, String value2) {
            addCriterion("tenant_name between", value1, value2, "tenantName");
            return (Criteria) this;
        }

        public Criteria andTenantNameNotBetween(String value1, String value2) {
            addCriterion("tenant_name not between", value1, value2, "tenantName");
            return (Criteria) this;
        }

        public Criteria andTenantTypeIsNull() {
            addCriterion("tenant_type is null");
            return (Criteria) this;
        }

        public Criteria andTenantTypeIsNotNull() {
            addCriterion("tenant_type is not null");
            return (Criteria) this;
        }

        public Criteria andTenantTypeEqualTo(String value) {
            addCriterion("tenant_type =", value, "tenantType");
            return (Criteria) this;
        }

        public Criteria andTenantTypeNotEqualTo(String value) {
            addCriterion("tenant_type <>", value, "tenantType");
            return (Criteria) this;
        }

        public Criteria andTenantTypeGreaterThan(String value) {
            addCriterion("tenant_type >", value, "tenantType");
            return (Criteria) this;
        }

        public Criteria andTenantTypeGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_type >=", value, "tenantType");
            return (Criteria) this;
        }

        public Criteria andTenantTypeLessThan(String value) {
            addCriterion("tenant_type <", value, "tenantType");
            return (Criteria) this;
        }

        public Criteria andTenantTypeLessThanOrEqualTo(String value) {
            addCriterion("tenant_type <=", value, "tenantType");
            return (Criteria) this;
        }

        public Criteria andTenantTypeLike(String value) {
            addCriterion("tenant_type like", value, "tenantType");
            return (Criteria) this;
        }

        public Criteria andTenantTypeNotLike(String value) {
            addCriterion("tenant_type not like", value, "tenantType");
            return (Criteria) this;
        }

        public Criteria andTenantTypeIn(List<String> values) {
            addCriterion("tenant_type in", values, "tenantType");
            return (Criteria) this;
        }

        public Criteria andTenantTypeNotIn(List<String> values) {
            addCriterion("tenant_type not in", values, "tenantType");
            return (Criteria) this;
        }

        public Criteria andTenantTypeBetween(String value1, String value2) {
            addCriterion("tenant_type between", value1, value2, "tenantType");
            return (Criteria) this;
        }

        public Criteria andTenantTypeNotBetween(String value1, String value2) {
            addCriterion("tenant_type not between", value1, value2, "tenantType");
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

        public Criteria andEnterSystemTimeIsNull() {
            addCriterion("enter_system_time is null");
            return (Criteria) this;
        }

        public Criteria andEnterSystemTimeIsNotNull() {
            addCriterion("enter_system_time is not null");
            return (Criteria) this;
        }

        public Criteria andEnterSystemTimeEqualTo(Date value) {
            addCriterion("enter_system_time =", value, "enterSystemTime");
            return (Criteria) this;
        }

        public Criteria andEnterSystemTimeNotEqualTo(Date value) {
            addCriterion("enter_system_time <>", value, "enterSystemTime");
            return (Criteria) this;
        }

        public Criteria andEnterSystemTimeGreaterThan(Date value) {
            addCriterion("enter_system_time >", value, "enterSystemTime");
            return (Criteria) this;
        }

        public Criteria andEnterSystemTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("enter_system_time >=", value, "enterSystemTime");
            return (Criteria) this;
        }

        public Criteria andEnterSystemTimeLessThan(Date value) {
            addCriterion("enter_system_time <", value, "enterSystemTime");
            return (Criteria) this;
        }

        public Criteria andEnterSystemTimeLessThanOrEqualTo(Date value) {
            addCriterion("enter_system_time <=", value, "enterSystemTime");
            return (Criteria) this;
        }

        public Criteria andEnterSystemTimeIn(List<Date> values) {
            addCriterion("enter_system_time in", values, "enterSystemTime");
            return (Criteria) this;
        }

        public Criteria andEnterSystemTimeNotIn(List<Date> values) {
            addCriterion("enter_system_time not in", values, "enterSystemTime");
            return (Criteria) this;
        }

        public Criteria andEnterSystemTimeBetween(Date value1, Date value2) {
            addCriterion("enter_system_time between", value1, value2, "enterSystemTime");
            return (Criteria) this;
        }

        public Criteria andEnterSystemTimeNotBetween(Date value1, Date value2) {
            addCriterion("enter_system_time not between", value1, value2, "enterSystemTime");
            return (Criteria) this;
        }

        public Criteria andCorporateNameIsNull() {
            addCriterion("corporate_name is null");
            return (Criteria) this;
        }

        public Criteria andCorporateNameIsNotNull() {
            addCriterion("corporate_name is not null");
            return (Criteria) this;
        }

        public Criteria andCorporateNameEqualTo(String value) {
            addCriterion("corporate_name =", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameNotEqualTo(String value) {
            addCriterion("corporate_name <>", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameGreaterThan(String value) {
            addCriterion("corporate_name >", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameGreaterThanOrEqualTo(String value) {
            addCriterion("corporate_name >=", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameLessThan(String value) {
            addCriterion("corporate_name <", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameLessThanOrEqualTo(String value) {
            addCriterion("corporate_name <=", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameLike(String value) {
            addCriterion("corporate_name like", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameNotLike(String value) {
            addCriterion("corporate_name not like", value, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameIn(List<String> values) {
            addCriterion("corporate_name in", values, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameNotIn(List<String> values) {
            addCriterion("corporate_name not in", values, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameBetween(String value1, String value2) {
            addCriterion("corporate_name between", value1, value2, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateNameNotBetween(String value1, String value2) {
            addCriterion("corporate_name not between", value1, value2, "corporateName");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardIsNull() {
            addCriterion("corporate_id_card is null");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardIsNotNull() {
            addCriterion("corporate_id_card is not null");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardEqualTo(String value) {
            addCriterion("corporate_id_card =", value, "corporateIdCard");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardNotEqualTo(String value) {
            addCriterion("corporate_id_card <>", value, "corporateIdCard");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardGreaterThan(String value) {
            addCriterion("corporate_id_card >", value, "corporateIdCard");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("corporate_id_card >=", value, "corporateIdCard");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardLessThan(String value) {
            addCriterion("corporate_id_card <", value, "corporateIdCard");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardLessThanOrEqualTo(String value) {
            addCriterion("corporate_id_card <=", value, "corporateIdCard");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardLike(String value) {
            addCriterion("corporate_id_card like", value, "corporateIdCard");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardNotLike(String value) {
            addCriterion("corporate_id_card not like", value, "corporateIdCard");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardIn(List<String> values) {
            addCriterion("corporate_id_card in", values, "corporateIdCard");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardNotIn(List<String> values) {
            addCriterion("corporate_id_card not in", values, "corporateIdCard");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardBetween(String value1, String value2) {
            addCriterion("corporate_id_card between", value1, value2, "corporateIdCard");
            return (Criteria) this;
        }

        public Criteria andCorporateIdCardNotBetween(String value1, String value2) {
            addCriterion("corporate_id_card not between", value1, value2, "corporateIdCard");
            return (Criteria) this;
        }

        public Criteria andLicenceNoIsNull() {
            addCriterion("licence_no is null");
            return (Criteria) this;
        }

        public Criteria andLicenceNoIsNotNull() {
            addCriterion("licence_no is not null");
            return (Criteria) this;
        }

        public Criteria andLicenceNoEqualTo(String value) {
            addCriterion("licence_no =", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoNotEqualTo(String value) {
            addCriterion("licence_no <>", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoGreaterThan(String value) {
            addCriterion("licence_no >", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoGreaterThanOrEqualTo(String value) {
            addCriterion("licence_no >=", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoLessThan(String value) {
            addCriterion("licence_no <", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoLessThanOrEqualTo(String value) {
            addCriterion("licence_no <=", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoLike(String value) {
            addCriterion("licence_no like", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoNotLike(String value) {
            addCriterion("licence_no not like", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoIn(List<String> values) {
            addCriterion("licence_no in", values, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoNotIn(List<String> values) {
            addCriterion("licence_no not in", values, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoBetween(String value1, String value2) {
            addCriterion("licence_no between", value1, value2, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoNotBetween(String value1, String value2) {
            addCriterion("licence_no not between", value1, value2, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andTenantAddressIsNull() {
            addCriterion("tenant_address is null");
            return (Criteria) this;
        }

        public Criteria andTenantAddressIsNotNull() {
            addCriterion("tenant_address is not null");
            return (Criteria) this;
        }

        public Criteria andTenantAddressEqualTo(String value) {
            addCriterion("tenant_address =", value, "tenantAddress");
            return (Criteria) this;
        }

        public Criteria andTenantAddressNotEqualTo(String value) {
            addCriterion("tenant_address <>", value, "tenantAddress");
            return (Criteria) this;
        }

        public Criteria andTenantAddressGreaterThan(String value) {
            addCriterion("tenant_address >", value, "tenantAddress");
            return (Criteria) this;
        }

        public Criteria andTenantAddressGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_address >=", value, "tenantAddress");
            return (Criteria) this;
        }

        public Criteria andTenantAddressLessThan(String value) {
            addCriterion("tenant_address <", value, "tenantAddress");
            return (Criteria) this;
        }

        public Criteria andTenantAddressLessThanOrEqualTo(String value) {
            addCriterion("tenant_address <=", value, "tenantAddress");
            return (Criteria) this;
        }

        public Criteria andTenantAddressLike(String value) {
            addCriterion("tenant_address like", value, "tenantAddress");
            return (Criteria) this;
        }

        public Criteria andTenantAddressNotLike(String value) {
            addCriterion("tenant_address not like", value, "tenantAddress");
            return (Criteria) this;
        }

        public Criteria andTenantAddressIn(List<String> values) {
            addCriterion("tenant_address in", values, "tenantAddress");
            return (Criteria) this;
        }

        public Criteria andTenantAddressNotIn(List<String> values) {
            addCriterion("tenant_address not in", values, "tenantAddress");
            return (Criteria) this;
        }

        public Criteria andTenantAddressBetween(String value1, String value2) {
            addCriterion("tenant_address between", value1, value2, "tenantAddress");
            return (Criteria) this;
        }

        public Criteria andTenantAddressNotBetween(String value1, String value2) {
            addCriterion("tenant_address not between", value1, value2, "tenantAddress");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneIsNull() {
            addCriterion("tenant_phone is null");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneIsNotNull() {
            addCriterion("tenant_phone is not null");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneEqualTo(String value) {
            addCriterion("tenant_phone =", value, "tenantPhone");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneNotEqualTo(String value) {
            addCriterion("tenant_phone <>", value, "tenantPhone");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneGreaterThan(String value) {
            addCriterion("tenant_phone >", value, "tenantPhone");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_phone >=", value, "tenantPhone");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneLessThan(String value) {
            addCriterion("tenant_phone <", value, "tenantPhone");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneLessThanOrEqualTo(String value) {
            addCriterion("tenant_phone <=", value, "tenantPhone");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneLike(String value) {
            addCriterion("tenant_phone like", value, "tenantPhone");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneNotLike(String value) {
            addCriterion("tenant_phone not like", value, "tenantPhone");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneIn(List<String> values) {
            addCriterion("tenant_phone in", values, "tenantPhone");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneNotIn(List<String> values) {
            addCriterion("tenant_phone not in", values, "tenantPhone");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneBetween(String value1, String value2) {
            addCriterion("tenant_phone between", value1, value2, "tenantPhone");
            return (Criteria) this;
        }

        public Criteria andTenantPhoneNotBetween(String value1, String value2) {
            addCriterion("tenant_phone not between", value1, value2, "tenantPhone");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNull() {
            addCriterion("contact_name is null");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNotNull() {
            addCriterion("contact_name is not null");
            return (Criteria) this;
        }

        public Criteria andContactNameEqualTo(String value) {
            addCriterion("contact_name =", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotEqualTo(String value) {
            addCriterion("contact_name <>", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThan(String value) {
            addCriterion("contact_name >", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("contact_name >=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThan(String value) {
            addCriterion("contact_name <", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThanOrEqualTo(String value) {
            addCriterion("contact_name <=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLike(String value) {
            addCriterion("contact_name like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotLike(String value) {
            addCriterion("contact_name not like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameIn(List<String> values) {
            addCriterion("contact_name in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotIn(List<String> values) {
            addCriterion("contact_name not in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameBetween(String value1, String value2) {
            addCriterion("contact_name between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotBetween(String value1, String value2) {
            addCriterion("contact_name not between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactMobileIsNull() {
            addCriterion("contact_mobile is null");
            return (Criteria) this;
        }

        public Criteria andContactMobileIsNotNull() {
            addCriterion("contact_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andContactMobileEqualTo(String value) {
            addCriterion("contact_mobile =", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileNotEqualTo(String value) {
            addCriterion("contact_mobile <>", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileGreaterThan(String value) {
            addCriterion("contact_mobile >", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileGreaterThanOrEqualTo(String value) {
            addCriterion("contact_mobile >=", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileLessThan(String value) {
            addCriterion("contact_mobile <", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileLessThanOrEqualTo(String value) {
            addCriterion("contact_mobile <=", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileLike(String value) {
            addCriterion("contact_mobile like", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileNotLike(String value) {
            addCriterion("contact_mobile not like", value, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileIn(List<String> values) {
            addCriterion("contact_mobile in", values, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileNotIn(List<String> values) {
            addCriterion("contact_mobile not in", values, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileBetween(String value1, String value2) {
            addCriterion("contact_mobile between", value1, value2, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactMobileNotBetween(String value1, String value2) {
            addCriterion("contact_mobile not between", value1, value2, "contactMobile");
            return (Criteria) this;
        }

        public Criteria andContactDutyIsNull() {
            addCriterion("contact_duty is null");
            return (Criteria) this;
        }

        public Criteria andContactDutyIsNotNull() {
            addCriterion("contact_duty is not null");
            return (Criteria) this;
        }

        public Criteria andContactDutyEqualTo(String value) {
            addCriterion("contact_duty =", value, "contactDuty");
            return (Criteria) this;
        }

        public Criteria andContactDutyNotEqualTo(String value) {
            addCriterion("contact_duty <>", value, "contactDuty");
            return (Criteria) this;
        }

        public Criteria andContactDutyGreaterThan(String value) {
            addCriterion("contact_duty >", value, "contactDuty");
            return (Criteria) this;
        }

        public Criteria andContactDutyGreaterThanOrEqualTo(String value) {
            addCriterion("contact_duty >=", value, "contactDuty");
            return (Criteria) this;
        }

        public Criteria andContactDutyLessThan(String value) {
            addCriterion("contact_duty <", value, "contactDuty");
            return (Criteria) this;
        }

        public Criteria andContactDutyLessThanOrEqualTo(String value) {
            addCriterion("contact_duty <=", value, "contactDuty");
            return (Criteria) this;
        }

        public Criteria andContactDutyLike(String value) {
            addCriterion("contact_duty like", value, "contactDuty");
            return (Criteria) this;
        }

        public Criteria andContactDutyNotLike(String value) {
            addCriterion("contact_duty not like", value, "contactDuty");
            return (Criteria) this;
        }

        public Criteria andContactDutyIn(List<String> values) {
            addCriterion("contact_duty in", values, "contactDuty");
            return (Criteria) this;
        }

        public Criteria andContactDutyNotIn(List<String> values) {
            addCriterion("contact_duty not in", values, "contactDuty");
            return (Criteria) this;
        }

        public Criteria andContactDutyBetween(String value1, String value2) {
            addCriterion("contact_duty between", value1, value2, "contactDuty");
            return (Criteria) this;
        }

        public Criteria andContactDutyNotBetween(String value1, String value2) {
            addCriterion("contact_duty not between", value1, value2, "contactDuty");
            return (Criteria) this;
        }

        public Criteria andContactEmailIsNull() {
            addCriterion("contact_email is null");
            return (Criteria) this;
        }

        public Criteria andContactEmailIsNotNull() {
            addCriterion("contact_email is not null");
            return (Criteria) this;
        }

        public Criteria andContactEmailEqualTo(String value) {
            addCriterion("contact_email =", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotEqualTo(String value) {
            addCriterion("contact_email <>", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailGreaterThan(String value) {
            addCriterion("contact_email >", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailGreaterThanOrEqualTo(String value) {
            addCriterion("contact_email >=", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailLessThan(String value) {
            addCriterion("contact_email <", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailLessThanOrEqualTo(String value) {
            addCriterion("contact_email <=", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailLike(String value) {
            addCriterion("contact_email like", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotLike(String value) {
            addCriterion("contact_email not like", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailIn(List<String> values) {
            addCriterion("contact_email in", values, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotIn(List<String> values) {
            addCriterion("contact_email not in", values, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailBetween(String value1, String value2) {
            addCriterion("contact_email between", value1, value2, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotBetween(String value1, String value2) {
            addCriterion("contact_email not between", value1, value2, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseIsNull() {
            addCriterion("copy_business_license is null");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseIsNotNull() {
            addCriterion("copy_business_license is not null");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseEqualTo(String value) {
            addCriterion("copy_business_license =", value, "copyBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseNotEqualTo(String value) {
            addCriterion("copy_business_license <>", value, "copyBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseGreaterThan(String value) {
            addCriterion("copy_business_license >", value, "copyBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseGreaterThanOrEqualTo(String value) {
            addCriterion("copy_business_license >=", value, "copyBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseLessThan(String value) {
            addCriterion("copy_business_license <", value, "copyBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseLessThanOrEqualTo(String value) {
            addCriterion("copy_business_license <=", value, "copyBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseLike(String value) {
            addCriterion("copy_business_license like", value, "copyBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseNotLike(String value) {
            addCriterion("copy_business_license not like", value, "copyBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseIn(List<String> values) {
            addCriterion("copy_business_license in", values, "copyBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseNotIn(List<String> values) {
            addCriterion("copy_business_license not in", values, "copyBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseBetween(String value1, String value2) {
            addCriterion("copy_business_license between", value1, value2, "copyBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andCopyBusinessLicenseNotBetween(String value1, String value2) {
            addCriterion("copy_business_license not between", value1, value2, "copyBusinessLicense");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
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

        public Criteria andIntegralIsNull() {
            addCriterion("Integral is null");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNotNull() {
            addCriterion("Integral is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralEqualTo(String value) {
            addCriterion("Integral =", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotEqualTo(String value) {
            addCriterion("Integral <>", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThan(String value) {
            addCriterion("Integral >", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThanOrEqualTo(String value) {
            addCriterion("Integral >=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThan(String value) {
            addCriterion("Integral <", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThanOrEqualTo(String value) {
            addCriterion("Integral <=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLike(String value) {
            addCriterion("Integral like", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotLike(String value) {
            addCriterion("Integral not like", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralIn(List<String> values) {
            addCriterion("Integral in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotIn(List<String> values) {
            addCriterion("Integral not in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralBetween(String value1, String value2) {
            addCriterion("Integral between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotBetween(String value1, String value2) {
            addCriterion("Integral not between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdIsNull() {
            addCriterion("tenant_shop_id is null");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdIsNotNull() {
            addCriterion("tenant_shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdEqualTo(String value) {
            addCriterion("tenant_shop_id =", value, "tenantShopId");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdNotEqualTo(String value) {
            addCriterion("tenant_shop_id <>", value, "tenantShopId");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdGreaterThan(String value) {
            addCriterion("tenant_shop_id >", value, "tenantShopId");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_shop_id >=", value, "tenantShopId");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdLessThan(String value) {
            addCriterion("tenant_shop_id <", value, "tenantShopId");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdLessThanOrEqualTo(String value) {
            addCriterion("tenant_shop_id <=", value, "tenantShopId");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdLike(String value) {
            addCriterion("tenant_shop_id like", value, "tenantShopId");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdNotLike(String value) {
            addCriterion("tenant_shop_id not like", value, "tenantShopId");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdIn(List<String> values) {
            addCriterion("tenant_shop_id in", values, "tenantShopId");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdNotIn(List<String> values) {
            addCriterion("tenant_shop_id not in", values, "tenantShopId");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdBetween(String value1, String value2) {
            addCriterion("tenant_shop_id between", value1, value2, "tenantShopId");
            return (Criteria) this;
        }

        public Criteria andTenantShopIdNotBetween(String value1, String value2) {
            addCriterion("tenant_shop_id not between", value1, value2, "tenantShopId");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeIsNull() {
            addCriterion("check_in_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeIsNotNull() {
            addCriterion("check_in_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeEqualTo(Date value) {
            addCriterion("check_in_time =", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeNotEqualTo(Date value) {
            addCriterion("check_in_time <>", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeGreaterThan(Date value) {
            addCriterion("check_in_time >", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_in_time >=", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeLessThan(Date value) {
            addCriterion("check_in_time <", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_in_time <=", value, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeIn(List<Date> values) {
            addCriterion("check_in_time in", values, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeNotIn(List<Date> values) {
            addCriterion("check_in_time not in", values, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeBetween(Date value1, Date value2) {
            addCriterion("check_in_time between", value1, value2, "checkInTime");
            return (Criteria) this;
        }

        public Criteria andCheckInTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_in_time not between", value1, value2, "checkInTime");
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

        public Criteria andCorporatePhotoFaceIsNull() {
            addCriterion("corporate_photo_face is null");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoFaceIsNotNull() {
            addCriterion("corporate_photo_face is not null");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoFaceEqualTo(String value) {
            addCriterion("corporate_photo_face =", value, "corporatePhotoFace");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoFaceNotEqualTo(String value) {
            addCriterion("corporate_photo_face <>", value, "corporatePhotoFace");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoFaceGreaterThan(String value) {
            addCriterion("corporate_photo_face >", value, "corporatePhotoFace");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoFaceGreaterThanOrEqualTo(String value) {
            addCriterion("corporate_photo_face >=", value, "corporatePhotoFace");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoFaceLessThan(String value) {
            addCriterion("corporate_photo_face <", value, "corporatePhotoFace");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoFaceLessThanOrEqualTo(String value) {
            addCriterion("corporate_photo_face <=", value, "corporatePhotoFace");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoFaceLike(String value) {
            addCriterion("corporate_photo_face like", value, "corporatePhotoFace");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoFaceNotLike(String value) {
            addCriterion("corporate_photo_face not like", value, "corporatePhotoFace");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoFaceIn(List<String> values) {
            addCriterion("corporate_photo_face in", values, "corporatePhotoFace");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoFaceNotIn(List<String> values) {
            addCriterion("corporate_photo_face not in", values, "corporatePhotoFace");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoFaceBetween(String value1, String value2) {
            addCriterion("corporate_photo_face between", value1, value2, "corporatePhotoFace");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoFaceNotBetween(String value1, String value2) {
            addCriterion("corporate_photo_face not between", value1, value2, "corporatePhotoFace");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackIsNull() {
            addCriterion("corporate_photo_back is null");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackIsNotNull() {
            addCriterion("corporate_photo_back is not null");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackEqualTo(String value) {
            addCriterion("corporate_photo_back =", value, "corporatePhotoBack");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackNotEqualTo(String value) {
            addCriterion("corporate_photo_back <>", value, "corporatePhotoBack");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackGreaterThan(String value) {
            addCriterion("corporate_photo_back >", value, "corporatePhotoBack");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackGreaterThanOrEqualTo(String value) {
            addCriterion("corporate_photo_back >=", value, "corporatePhotoBack");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackLessThan(String value) {
            addCriterion("corporate_photo_back <", value, "corporatePhotoBack");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackLessThanOrEqualTo(String value) {
            addCriterion("corporate_photo_back <=", value, "corporatePhotoBack");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackLike(String value) {
            addCriterion("corporate_photo_back like", value, "corporatePhotoBack");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackNotLike(String value) {
            addCriterion("corporate_photo_back not like", value, "corporatePhotoBack");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackIn(List<String> values) {
            addCriterion("corporate_photo_back in", values, "corporatePhotoBack");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackNotIn(List<String> values) {
            addCriterion("corporate_photo_back not in", values, "corporatePhotoBack");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackBetween(String value1, String value2) {
            addCriterion("corporate_photo_back between", value1, value2, "corporatePhotoBack");
            return (Criteria) this;
        }

        public Criteria andCorporatePhotoBackNotBetween(String value1, String value2) {
            addCriterion("corporate_photo_back not between", value1, value2, "corporatePhotoBack");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneIsNull() {
            addCriterion("corporate_phone is null");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneIsNotNull() {
            addCriterion("corporate_phone is not null");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneEqualTo(String value) {
            addCriterion("corporate_phone =", value, "corporatePhone");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneNotEqualTo(String value) {
            addCriterion("corporate_phone <>", value, "corporatePhone");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneGreaterThan(String value) {
            addCriterion("corporate_phone >", value, "corporatePhone");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("corporate_phone >=", value, "corporatePhone");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneLessThan(String value) {
            addCriterion("corporate_phone <", value, "corporatePhone");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneLessThanOrEqualTo(String value) {
            addCriterion("corporate_phone <=", value, "corporatePhone");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneLike(String value) {
            addCriterion("corporate_phone like", value, "corporatePhone");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneNotLike(String value) {
            addCriterion("corporate_phone not like", value, "corporatePhone");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneIn(List<String> values) {
            addCriterion("corporate_phone in", values, "corporatePhone");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneNotIn(List<String> values) {
            addCriterion("corporate_phone not in", values, "corporatePhone");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneBetween(String value1, String value2) {
            addCriterion("corporate_phone between", value1, value2, "corporatePhone");
            return (Criteria) this;
        }

        public Criteria andCorporatePhoneNotBetween(String value1, String value2) {
            addCriterion("corporate_phone not between", value1, value2, "corporatePhone");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceIsNull() {
            addCriterion("contact_photo_face is null");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceIsNotNull() {
            addCriterion("contact_photo_face is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceEqualTo(String value) {
            addCriterion("contact_photo_face =", value, "contactPhotoFace");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceNotEqualTo(String value) {
            addCriterion("contact_photo_face <>", value, "contactPhotoFace");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceGreaterThan(String value) {
            addCriterion("contact_photo_face >", value, "contactPhotoFace");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceGreaterThanOrEqualTo(String value) {
            addCriterion("contact_photo_face >=", value, "contactPhotoFace");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceLessThan(String value) {
            addCriterion("contact_photo_face <", value, "contactPhotoFace");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceLessThanOrEqualTo(String value) {
            addCriterion("contact_photo_face <=", value, "contactPhotoFace");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceLike(String value) {
            addCriterion("contact_photo_face like", value, "contactPhotoFace");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceNotLike(String value) {
            addCriterion("contact_photo_face not like", value, "contactPhotoFace");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceIn(List<String> values) {
            addCriterion("contact_photo_face in", values, "contactPhotoFace");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceNotIn(List<String> values) {
            addCriterion("contact_photo_face not in", values, "contactPhotoFace");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceBetween(String value1, String value2) {
            addCriterion("contact_photo_face between", value1, value2, "contactPhotoFace");
            return (Criteria) this;
        }

        public Criteria andContactPhotoFaceNotBetween(String value1, String value2) {
            addCriterion("contact_photo_face not between", value1, value2, "contactPhotoFace");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackIsNull() {
            addCriterion("contact_photo_back is null");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackIsNotNull() {
            addCriterion("contact_photo_back is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackEqualTo(String value) {
            addCriterion("contact_photo_back =", value, "contactPhotoBack");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackNotEqualTo(String value) {
            addCriterion("contact_photo_back <>", value, "contactPhotoBack");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackGreaterThan(String value) {
            addCriterion("contact_photo_back >", value, "contactPhotoBack");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackGreaterThanOrEqualTo(String value) {
            addCriterion("contact_photo_back >=", value, "contactPhotoBack");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackLessThan(String value) {
            addCriterion("contact_photo_back <", value, "contactPhotoBack");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackLessThanOrEqualTo(String value) {
            addCriterion("contact_photo_back <=", value, "contactPhotoBack");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackLike(String value) {
            addCriterion("contact_photo_back like", value, "contactPhotoBack");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackNotLike(String value) {
            addCriterion("contact_photo_back not like", value, "contactPhotoBack");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackIn(List<String> values) {
            addCriterion("contact_photo_back in", values, "contactPhotoBack");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackNotIn(List<String> values) {
            addCriterion("contact_photo_back not in", values, "contactPhotoBack");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackBetween(String value1, String value2) {
            addCriterion("contact_photo_back between", value1, value2, "contactPhotoBack");
            return (Criteria) this;
        }

        public Criteria andContactPhotoBackNotBetween(String value1, String value2) {
            addCriterion("contact_photo_back not between", value1, value2, "contactPhotoBack");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameIsNull() {
            addCriterion("tenant_shop_name is null");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameIsNotNull() {
            addCriterion("tenant_shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameEqualTo(String value) {
            addCriterion("tenant_shop_name =", value, "tenantShopName");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameNotEqualTo(String value) {
            addCriterion("tenant_shop_name <>", value, "tenantShopName");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameGreaterThan(String value) {
            addCriterion("tenant_shop_name >", value, "tenantShopName");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_shop_name >=", value, "tenantShopName");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameLessThan(String value) {
            addCriterion("tenant_shop_name <", value, "tenantShopName");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameLessThanOrEqualTo(String value) {
            addCriterion("tenant_shop_name <=", value, "tenantShopName");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameLike(String value) {
            addCriterion("tenant_shop_name like", value, "tenantShopName");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameNotLike(String value) {
            addCriterion("tenant_shop_name not like", value, "tenantShopName");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameIn(List<String> values) {
            addCriterion("tenant_shop_name in", values, "tenantShopName");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameNotIn(List<String> values) {
            addCriterion("tenant_shop_name not in", values, "tenantShopName");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameBetween(String value1, String value2) {
            addCriterion("tenant_shop_name between", value1, value2, "tenantShopName");
            return (Criteria) this;
        }

        public Criteria andTenantShopNameNotBetween(String value1, String value2) {
            addCriterion("tenant_shop_name not between", value1, value2, "tenantShopName");
            return (Criteria) this;
        }

        public Criteria andTenantAreaIsNull() {
            addCriterion("tenant_area is null");
            return (Criteria) this;
        }

        public Criteria andTenantAreaIsNotNull() {
            addCriterion("tenant_area is not null");
            return (Criteria) this;
        }

        public Criteria andTenantAreaEqualTo(String value) {
            addCriterion("tenant_area =", value, "tenantArea");
            return (Criteria) this;
        }

        public Criteria andTenantAreaNotEqualTo(String value) {
            addCriterion("tenant_area <>", value, "tenantArea");
            return (Criteria) this;
        }

        public Criteria andTenantAreaGreaterThan(String value) {
            addCriterion("tenant_area >", value, "tenantArea");
            return (Criteria) this;
        }

        public Criteria andTenantAreaGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_area >=", value, "tenantArea");
            return (Criteria) this;
        }

        public Criteria andTenantAreaLessThan(String value) {
            addCriterion("tenant_area <", value, "tenantArea");
            return (Criteria) this;
        }

        public Criteria andTenantAreaLessThanOrEqualTo(String value) {
            addCriterion("tenant_area <=", value, "tenantArea");
            return (Criteria) this;
        }

        public Criteria andTenantAreaLike(String value) {
            addCriterion("tenant_area like", value, "tenantArea");
            return (Criteria) this;
        }

        public Criteria andTenantAreaNotLike(String value) {
            addCriterion("tenant_area not like", value, "tenantArea");
            return (Criteria) this;
        }

        public Criteria andTenantAreaIn(List<String> values) {
            addCriterion("tenant_area in", values, "tenantArea");
            return (Criteria) this;
        }

        public Criteria andTenantAreaNotIn(List<String> values) {
            addCriterion("tenant_area not in", values, "tenantArea");
            return (Criteria) this;
        }

        public Criteria andTenantAreaBetween(String value1, String value2) {
            addCriterion("tenant_area between", value1, value2, "tenantArea");
            return (Criteria) this;
        }

        public Criteria andTenantAreaNotBetween(String value1, String value2) {
            addCriterion("tenant_area not between", value1, value2, "tenantArea");
            return (Criteria) this;
        }

        public Criteria andContactIdCardIsNull() {
            addCriterion("contact_id_card is null");
            return (Criteria) this;
        }

        public Criteria andContactIdCardIsNotNull() {
            addCriterion("contact_id_card is not null");
            return (Criteria) this;
        }

        public Criteria andContactIdCardEqualTo(String value) {
            addCriterion("contact_id_card =", value, "contactIdCard");
            return (Criteria) this;
        }

        public Criteria andContactIdCardNotEqualTo(String value) {
            addCriterion("contact_id_card <>", value, "contactIdCard");
            return (Criteria) this;
        }

        public Criteria andContactIdCardGreaterThan(String value) {
            addCriterion("contact_id_card >", value, "contactIdCard");
            return (Criteria) this;
        }

        public Criteria andContactIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("contact_id_card >=", value, "contactIdCard");
            return (Criteria) this;
        }

        public Criteria andContactIdCardLessThan(String value) {
            addCriterion("contact_id_card <", value, "contactIdCard");
            return (Criteria) this;
        }

        public Criteria andContactIdCardLessThanOrEqualTo(String value) {
            addCriterion("contact_id_card <=", value, "contactIdCard");
            return (Criteria) this;
        }

        public Criteria andContactIdCardLike(String value) {
            addCriterion("contact_id_card like", value, "contactIdCard");
            return (Criteria) this;
        }

        public Criteria andContactIdCardNotLike(String value) {
            addCriterion("contact_id_card not like", value, "contactIdCard");
            return (Criteria) this;
        }

        public Criteria andContactIdCardIn(List<String> values) {
            addCriterion("contact_id_card in", values, "contactIdCard");
            return (Criteria) this;
        }

        public Criteria andContactIdCardNotIn(List<String> values) {
            addCriterion("contact_id_card not in", values, "contactIdCard");
            return (Criteria) this;
        }

        public Criteria andContactIdCardBetween(String value1, String value2) {
            addCriterion("contact_id_card between", value1, value2, "contactIdCard");
            return (Criteria) this;
        }

        public Criteria andContactIdCardNotBetween(String value1, String value2) {
            addCriterion("contact_id_card not between", value1, value2, "contactIdCard");
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