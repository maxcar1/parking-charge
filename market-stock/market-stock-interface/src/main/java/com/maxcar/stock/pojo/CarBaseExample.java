package com.maxcar.stock.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarBaseExample {

    private String brandCode;

    private String seriesCode;

    private String modelCode;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CarBaseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
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

        public Criteria andBrandNameIsNull() {
            addCriterion("brand_name is null");
            return (Criteria) this;
        }

        public Criteria andBrandNameIsNotNull() {
            addCriterion("brand_name is not null");
            return (Criteria) this;
        }

        public Criteria andBrandNameEqualTo(String value) {
            addCriterion("brand_name =", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotEqualTo(String value) {
            addCriterion("brand_name <>", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThan(String value) {
            addCriterion("brand_name >", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThanOrEqualTo(String value) {
            addCriterion("brand_name >=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThan(String value) {
            addCriterion("brand_name <", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThanOrEqualTo(String value) {
            addCriterion("brand_name <=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLike(String value) {
            addCriterion("brand_name like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotLike(String value) {
            addCriterion("brand_name not like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameIn(List<String> values) {
            addCriterion("brand_name in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotIn(List<String> values) {
            addCriterion("brand_name not in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameBetween(String value1, String value2) {
            addCriterion("brand_name between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotBetween(String value1, String value2) {
            addCriterion("brand_name not between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandCodeIsNull() {
            addCriterion("brand_code is null");
            return (Criteria) this;
        }

        public Criteria andBrandCodeIsNotNull() {
            addCriterion("brand_code is not null");
            return (Criteria) this;
        }

        public Criteria andBrandCodeEqualTo(String value) {
            addCriterion("brand_code =", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotEqualTo(String value) {
            addCriterion("brand_code <>", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeGreaterThan(String value) {
            addCriterion("brand_code >", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeGreaterThanOrEqualTo(String value) {
            addCriterion("brand_code >=", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeLessThan(String value) {
            addCriterion("brand_code <", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeLessThanOrEqualTo(String value) {
            addCriterion("brand_code <=", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeLike(String value) {
            addCriterion("brand_code like", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotLike(String value) {
            addCriterion("brand_code not like", value, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeIn(List<String> values) {
            addCriterion("brand_code in", values, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotIn(List<String> values) {
            addCriterion("brand_code not in", values, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeBetween(String value1, String value2) {
            addCriterion("brand_code between", value1, value2, "brandCode");
            return (Criteria) this;
        }

        public Criteria andBrandCodeNotBetween(String value1, String value2) {
            addCriterion("brand_code not between", value1, value2, "brandCode");
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

        public Criteria andSeriesCodeIsNull() {
            addCriterion("series_code is null");
            return (Criteria) this;
        }

        public Criteria andSeriesCodeIsNotNull() {
            addCriterion("series_code is not null");
            return (Criteria) this;
        }

        public Criteria andSeriesCodeEqualTo(String value) {
            addCriterion("series_code =", value, "seriesCode");
            return (Criteria) this;
        }

        public Criteria andSeriesCodeNotEqualTo(String value) {
            addCriterion("series_code <>", value, "seriesCode");
            return (Criteria) this;
        }

        public Criteria andSeriesCodeGreaterThan(String value) {
            addCriterion("series_code >", value, "seriesCode");
            return (Criteria) this;
        }

        public Criteria andSeriesCodeGreaterThanOrEqualTo(String value) {
            addCriterion("series_code >=", value, "seriesCode");
            return (Criteria) this;
        }

        public Criteria andSeriesCodeLessThan(String value) {
            addCriterion("series_code <", value, "seriesCode");
            return (Criteria) this;
        }

        public Criteria andSeriesCodeLessThanOrEqualTo(String value) {
            addCriterion("series_code <=", value, "seriesCode");
            return (Criteria) this;
        }

        public Criteria andSeriesCodeLike(String value) {
            addCriterion("series_code like", value, "seriesCode");
            return (Criteria) this;
        }

        public Criteria andSeriesCodeNotLike(String value) {
            addCriterion("series_code not like", value, "seriesCode");
            return (Criteria) this;
        }

        public Criteria andSeriesCodeIn(List<String> values) {
            addCriterion("series_code in", values, "seriesCode");
            return (Criteria) this;
        }

        public Criteria andSeriesCodeNotIn(List<String> values) {
            addCriterion("series_code not in", values, "seriesCode");
            return (Criteria) this;
        }

        public Criteria andSeriesCodeBetween(String value1, String value2) {
            addCriterion("series_code between", value1, value2, "seriesCode");
            return (Criteria) this;
        }

        public Criteria andSeriesCodeNotBetween(String value1, String value2) {
            addCriterion("series_code not between", value1, value2, "seriesCode");
            return (Criteria) this;
        }

        public Criteria andModelYearIsNull() {
            addCriterion("model_year is null");
            return (Criteria) this;
        }

        public Criteria andModelYearIsNotNull() {
            addCriterion("model_year is not null");
            return (Criteria) this;
        }

        public Criteria andModelYearEqualTo(String value) {
            addCriterion("model_year =", value, "modelYear");
            return (Criteria) this;
        }

        public Criteria andModelYearNotEqualTo(String value) {
            addCriterion("model_year <>", value, "modelYear");
            return (Criteria) this;
        }

        public Criteria andModelYearGreaterThan(String value) {
            addCriterion("model_year >", value, "modelYear");
            return (Criteria) this;
        }

        public Criteria andModelYearGreaterThanOrEqualTo(String value) {
            addCriterion("model_year >=", value, "modelYear");
            return (Criteria) this;
        }

        public Criteria andModelYearLessThan(String value) {
            addCriterion("model_year <", value, "modelYear");
            return (Criteria) this;
        }

        public Criteria andModelYearLessThanOrEqualTo(String value) {
            addCriterion("model_year <=", value, "modelYear");
            return (Criteria) this;
        }

        public Criteria andModelYearLike(String value) {
            addCriterion("model_year like", value, "modelYear");
            return (Criteria) this;
        }

        public Criteria andModelYearNotLike(String value) {
            addCriterion("model_year not like", value, "modelYear");
            return (Criteria) this;
        }

        public Criteria andModelYearIn(List<String> values) {
            addCriterion("model_year in", values, "modelYear");
            return (Criteria) this;
        }

        public Criteria andModelYearNotIn(List<String> values) {
            addCriterion("model_year not in", values, "modelYear");
            return (Criteria) this;
        }

        public Criteria andModelYearBetween(String value1, String value2) {
            addCriterion("model_year between", value1, value2, "modelYear");
            return (Criteria) this;
        }

        public Criteria andModelYearNotBetween(String value1, String value2) {
            addCriterion("model_year not between", value1, value2, "modelYear");
            return (Criteria) this;
        }

        public Criteria andModelNameIsNull() {
            addCriterion("model_name is null");
            return (Criteria) this;
        }

        public Criteria andModelNameIsNotNull() {
            addCriterion("model_name is not null");
            return (Criteria) this;
        }

        public Criteria andModelNameEqualTo(String value) {
            addCriterion("model_name =", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotEqualTo(String value) {
            addCriterion("model_name <>", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameGreaterThan(String value) {
            addCriterion("model_name >", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameGreaterThanOrEqualTo(String value) {
            addCriterion("model_name >=", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLessThan(String value) {
            addCriterion("model_name <", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLessThanOrEqualTo(String value) {
            addCriterion("model_name <=", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLike(String value) {
            addCriterion("model_name like", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotLike(String value) {
            addCriterion("model_name not like", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameIn(List<String> values) {
            addCriterion("model_name in", values, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotIn(List<String> values) {
            addCriterion("model_name not in", values, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameBetween(String value1, String value2) {
            addCriterion("model_name between", value1, value2, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotBetween(String value1, String value2) {
            addCriterion("model_name not between", value1, value2, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelCodeIsNull() {
            addCriterion("model_code is null");
            return (Criteria) this;
        }

        public Criteria andModelCodeIsNotNull() {
            addCriterion("model_code is not null");
            return (Criteria) this;
        }

        public Criteria andModelCodeEqualTo(String value) {
            addCriterion("model_code =", value, "modelCode");
            return (Criteria) this;
        }

        public Criteria andModelCodeNotEqualTo(String value) {
            addCriterion("model_code <>", value, "modelCode");
            return (Criteria) this;
        }

        public Criteria andModelCodeGreaterThan(String value) {
            addCriterion("model_code >", value, "modelCode");
            return (Criteria) this;
        }

        public Criteria andModelCodeGreaterThanOrEqualTo(String value) {
            addCriterion("model_code >=", value, "modelCode");
            return (Criteria) this;
        }

        public Criteria andModelCodeLessThan(String value) {
            addCriterion("model_code <", value, "modelCode");
            return (Criteria) this;
        }

        public Criteria andModelCodeLessThanOrEqualTo(String value) {
            addCriterion("model_code <=", value, "modelCode");
            return (Criteria) this;
        }

        public Criteria andModelCodeLike(String value) {
            addCriterion("model_code like", value, "modelCode");
            return (Criteria) this;
        }

        public Criteria andModelCodeNotLike(String value) {
            addCriterion("model_code not like", value, "modelCode");
            return (Criteria) this;
        }

        public Criteria andModelCodeIn(List<String> values) {
            addCriterion("model_code in", values, "modelCode");
            return (Criteria) this;
        }

        public Criteria andModelCodeNotIn(List<String> values) {
            addCriterion("model_code not in", values, "modelCode");
            return (Criteria) this;
        }

        public Criteria andModelCodeBetween(String value1, String value2) {
            addCriterion("model_code between", value1, value2, "modelCode");
            return (Criteria) this;
        }

        public Criteria andModelCodeNotBetween(String value1, String value2) {
            addCriterion("model_code not between", value1, value2, "modelCode");
            return (Criteria) this;
        }

        public Criteria andMileageIsNull() {
            addCriterion("mileage is null");
            return (Criteria) this;
        }

        public Criteria andMileageIsNotNull() {
            addCriterion("mileage is not null");
            return (Criteria) this;
        }

        public Criteria andMileageEqualTo(Integer value) {
            addCriterion("mileage =", value, "mileage");
            return (Criteria) this;
        }

        public Criteria andMileageNotEqualTo(Integer value) {
            addCriterion("mileage <>", value, "mileage");
            return (Criteria) this;
        }

        public Criteria andMileageGreaterThan(Integer value) {
            addCriterion("mileage >", value, "mileage");
            return (Criteria) this;
        }

        public Criteria andMileageGreaterThanOrEqualTo(Integer value) {
            addCriterion("mileage >=", value, "mileage");
            return (Criteria) this;
        }

        public Criteria andMileageLessThan(Integer value) {
            addCriterion("mileage <", value, "mileage");
            return (Criteria) this;
        }

        public Criteria andMileageLessThanOrEqualTo(Integer value) {
            addCriterion("mileage <=", value, "mileage");
            return (Criteria) this;
        }

        public Criteria andMileageIn(List<Integer> values) {
            addCriterion("mileage in", values, "mileage");
            return (Criteria) this;
        }

        public Criteria andMileageNotIn(List<Integer> values) {
            addCriterion("mileage not in", values, "mileage");
            return (Criteria) this;
        }

        public Criteria andMileageBetween(Integer value1, Integer value2) {
            addCriterion("mileage between", value1, value2, "mileage");
            return (Criteria) this;
        }

        public Criteria andMileageNotBetween(Integer value1, Integer value2) {
            addCriterion("mileage not between", value1, value2, "mileage");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceIsNull() {
            addCriterion("evaluate_price is null");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceIsNotNull() {
            addCriterion("evaluate_price is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceEqualTo(Double value) {
            addCriterion("evaluate_price =", value, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceNotEqualTo(Double value) {
            addCriterion("evaluate_price <>", value, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceGreaterThan(Double value) {
            addCriterion("evaluate_price >", value, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceGreaterThanOrEqualTo(Double value) {
            addCriterion("evaluate_price >=", value, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceLessThan(Double value) {
            addCriterion("evaluate_price <", value, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceLessThanOrEqualTo(Double value) {
            addCriterion("evaluate_price <=", value, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceIn(List<Double> values) {
            addCriterion("evaluate_price in", values, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceNotIn(List<Double> values) {
            addCriterion("evaluate_price not in", values, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceBetween(Double value1, Double value2) {
            addCriterion("evaluate_price between", value1, value2, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andEvaluatePriceNotBetween(Double value1, Double value2) {
            addCriterion("evaluate_price not between", value1, value2, "evaluatePrice");
            return (Criteria) this;
        }

        public Criteria andNewPriceIsNull() {
            addCriterion("new_price is null");
            return (Criteria) this;
        }

        public Criteria andNewPriceIsNotNull() {
            addCriterion("new_price is not null");
            return (Criteria) this;
        }

        public Criteria andNewPriceEqualTo(Double value) {
            addCriterion("new_price =", value, "newPrice");
            return (Criteria) this;
        }

        public Criteria andNewPriceNotEqualTo(Double value) {
            addCriterion("new_price <>", value, "newPrice");
            return (Criteria) this;
        }

        public Criteria andNewPriceGreaterThan(Double value) {
            addCriterion("new_price >", value, "newPrice");
            return (Criteria) this;
        }

        public Criteria andNewPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("new_price >=", value, "newPrice");
            return (Criteria) this;
        }

        public Criteria andNewPriceLessThan(Double value) {
            addCriterion("new_price <", value, "newPrice");
            return (Criteria) this;
        }

        public Criteria andNewPriceLessThanOrEqualTo(Double value) {
            addCriterion("new_price <=", value, "newPrice");
            return (Criteria) this;
        }

        public Criteria andNewPriceIn(List<Double> values) {
            addCriterion("new_price in", values, "newPrice");
            return (Criteria) this;
        }

        public Criteria andNewPriceNotIn(List<Double> values) {
            addCriterion("new_price not in", values, "newPrice");
            return (Criteria) this;
        }

        public Criteria andNewPriceBetween(Double value1, Double value2) {
            addCriterion("new_price between", value1, value2, "newPrice");
            return (Criteria) this;
        }

        public Criteria andNewPriceNotBetween(Double value1, Double value2) {
            addCriterion("new_price not between", value1, value2, "newPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNull() {
            addCriterion("market_price is null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNotNull() {
            addCriterion("market_price is not null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceEqualTo(Double value) {
            addCriterion("market_price =", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotEqualTo(Double value) {
            addCriterion("market_price <>", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThan(Double value) {
            addCriterion("market_price >", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("market_price >=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThan(Double value) {
            addCriterion("market_price <", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThanOrEqualTo(Double value) {
            addCriterion("market_price <=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIn(List<Double> values) {
            addCriterion("market_price in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotIn(List<Double> values) {
            addCriterion("market_price not in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceBetween(Double value1, Double value2) {
            addCriterion("market_price between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotBetween(Double value1, Double value2) {
            addCriterion("market_price not between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("color is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("color is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addCriterion("color =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addCriterion("color <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addCriterion("color >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addCriterion("color >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addCriterion("color <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addCriterion("color <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLike(String value) {
            addCriterion("color like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotLike(String value) {
            addCriterion("color not like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addCriterion("color in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addCriterion("color not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addCriterion("color between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addCriterion("color not between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andEngineVolumeUnitlIsNull() {
            addCriterion("engine_volume_unitl is null");
            return (Criteria) this;
        }

        public Criteria andEngineVolumeUnitlIsNotNull() {
            addCriterion("engine_volume_unitl is not null");
            return (Criteria) this;
        }

        public Criteria andEngineVolumeUnitlEqualTo(Double value) {
            addCriterion("engine_volume_unitl =", value, "engineVolumeUnitl");
            return (Criteria) this;
        }

        public Criteria andEngineVolumeUnitlNotEqualTo(Double value) {
            addCriterion("engine_volume_unitl <>", value, "engineVolumeUnitl");
            return (Criteria) this;
        }

        public Criteria andEngineVolumeUnitlGreaterThan(Double value) {
            addCriterion("engine_volume_unitl >", value, "engineVolumeUnitl");
            return (Criteria) this;
        }

        public Criteria andEngineVolumeUnitlGreaterThanOrEqualTo(Double value) {
            addCriterion("engine_volume_unitl >=", value, "engineVolumeUnitl");
            return (Criteria) this;
        }

        public Criteria andEngineVolumeUnitlLessThan(Double value) {
            addCriterion("engine_volume_unitl <", value, "engineVolumeUnitl");
            return (Criteria) this;
        }

        public Criteria andEngineVolumeUnitlLessThanOrEqualTo(Double value) {
            addCriterion("engine_volume_unitl <=", value, "engineVolumeUnitl");
            return (Criteria) this;
        }

        public Criteria andEngineVolumeUnitlIn(List<Double> values) {
            addCriterion("engine_volume_unitl in", values, "engineVolumeUnitl");
            return (Criteria) this;
        }

        public Criteria andEngineVolumeUnitlNotIn(List<Double> values) {
            addCriterion("engine_volume_unitl not in", values, "engineVolumeUnitl");
            return (Criteria) this;
        }

        public Criteria andEngineVolumeUnitlBetween(Double value1, Double value2) {
            addCriterion("engine_volume_unitl between", value1, value2, "engineVolumeUnitl");
            return (Criteria) this;
        }

        public Criteria andEngineVolumeUnitlNotBetween(Double value1, Double value2) {
            addCriterion("engine_volume_unitl not between", value1, value2, "engineVolumeUnitl");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsIsNull() {
            addCriterion("environmental_standards is null");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsIsNotNull() {
            addCriterion("environmental_standards is not null");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsEqualTo(String value) {
            addCriterion("environmental_standards =", value, "environmentalStandards");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsNotEqualTo(String value) {
            addCriterion("environmental_standards <>", value, "environmentalStandards");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsGreaterThan(String value) {
            addCriterion("environmental_standards >", value, "environmentalStandards");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsGreaterThanOrEqualTo(String value) {
            addCriterion("environmental_standards >=", value, "environmentalStandards");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsLessThan(String value) {
            addCriterion("environmental_standards <", value, "environmentalStandards");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsLessThanOrEqualTo(String value) {
            addCriterion("environmental_standards <=", value, "environmentalStandards");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsLike(String value) {
            addCriterion("environmental_standards like", value, "environmentalStandards");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsNotLike(String value) {
            addCriterion("environmental_standards not like", value, "environmentalStandards");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsIn(List<String> values) {
            addCriterion("environmental_standards in", values, "environmentalStandards");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsNotIn(List<String> values) {
            addCriterion("environmental_standards not in", values, "environmentalStandards");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsBetween(String value1, String value2) {
            addCriterion("environmental_standards between", value1, value2, "environmentalStandards");
            return (Criteria) this;
        }

        public Criteria andEnvironmentalStandardsNotBetween(String value1, String value2) {
            addCriterion("environmental_standards not between", value1, value2, "environmentalStandards");
            return (Criteria) this;
        }

        public Criteria andGearBoxIsNull() {
            addCriterion("gear_box is null");
            return (Criteria) this;
        }

        public Criteria andGearBoxIsNotNull() {
            addCriterion("gear_box is not null");
            return (Criteria) this;
        }

        public Criteria andGearBoxEqualTo(String value) {
            addCriterion("gear_box =", value, "gearBox");
            return (Criteria) this;
        }

        public Criteria andGearBoxNotEqualTo(String value) {
            addCriterion("gear_box <>", value, "gearBox");
            return (Criteria) this;
        }

        public Criteria andGearBoxGreaterThan(String value) {
            addCriterion("gear_box >", value, "gearBox");
            return (Criteria) this;
        }

        public Criteria andGearBoxGreaterThanOrEqualTo(String value) {
            addCriterion("gear_box >=", value, "gearBox");
            return (Criteria) this;
        }

        public Criteria andGearBoxLessThan(String value) {
            addCriterion("gear_box <", value, "gearBox");
            return (Criteria) this;
        }

        public Criteria andGearBoxLessThanOrEqualTo(String value) {
            addCriterion("gear_box <=", value, "gearBox");
            return (Criteria) this;
        }

        public Criteria andGearBoxLike(String value) {
            addCriterion("gear_box like", value, "gearBox");
            return (Criteria) this;
        }

        public Criteria andGearBoxNotLike(String value) {
            addCriterion("gear_box not like", value, "gearBox");
            return (Criteria) this;
        }

        public Criteria andGearBoxIn(List<String> values) {
            addCriterion("gear_box in", values, "gearBox");
            return (Criteria) this;
        }

        public Criteria andGearBoxNotIn(List<String> values) {
            addCriterion("gear_box not in", values, "gearBox");
            return (Criteria) this;
        }

        public Criteria andGearBoxBetween(String value1, String value2) {
            addCriterion("gear_box between", value1, value2, "gearBox");
            return (Criteria) this;
        }

        public Criteria andGearBoxNotBetween(String value1, String value2) {
            addCriterion("gear_box not between", value1, value2, "gearBox");
            return (Criteria) this;
        }

        public Criteria andSeatNumberIsNull() {
            addCriterion("seat_number is null");
            return (Criteria) this;
        }

        public Criteria andSeatNumberIsNotNull() {
            addCriterion("seat_number is not null");
            return (Criteria) this;
        }

        public Criteria andSeatNumberEqualTo(Integer value) {
            addCriterion("seat_number =", value, "seatNumber");
            return (Criteria) this;
        }

        public Criteria andSeatNumberNotEqualTo(Integer value) {
            addCriterion("seat_number <>", value, "seatNumber");
            return (Criteria) this;
        }

        public Criteria andSeatNumberGreaterThan(Integer value) {
            addCriterion("seat_number >", value, "seatNumber");
            return (Criteria) this;
        }

        public Criteria andSeatNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("seat_number >=", value, "seatNumber");
            return (Criteria) this;
        }

        public Criteria andSeatNumberLessThan(Integer value) {
            addCriterion("seat_number <", value, "seatNumber");
            return (Criteria) this;
        }

        public Criteria andSeatNumberLessThanOrEqualTo(Integer value) {
            addCriterion("seat_number <=", value, "seatNumber");
            return (Criteria) this;
        }

        public Criteria andSeatNumberIn(List<Integer> values) {
            addCriterion("seat_number in", values, "seatNumber");
            return (Criteria) this;
        }

        public Criteria andSeatNumberNotIn(List<Integer> values) {
            addCriterion("seat_number not in", values, "seatNumber");
            return (Criteria) this;
        }

        public Criteria andSeatNumberBetween(Integer value1, Integer value2) {
            addCriterion("seat_number between", value1, value2, "seatNumber");
            return (Criteria) this;
        }

        public Criteria andSeatNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("seat_number not between", value1, value2, "seatNumber");
            return (Criteria) this;
        }

        public Criteria andFuelFormIsNull() {
            addCriterion("fuel_form is null");
            return (Criteria) this;
        }

        public Criteria andFuelFormIsNotNull() {
            addCriterion("fuel_form is not null");
            return (Criteria) this;
        }

        public Criteria andFuelFormEqualTo(Integer value) {
            addCriterion("fuel_form =", value, "fuelForm");
            return (Criteria) this;
        }

        public Criteria andFuelFormNotEqualTo(Integer value) {
            addCriterion("fuel_form <>", value, "fuelForm");
            return (Criteria) this;
        }

        public Criteria andFuelFormGreaterThan(Integer value) {
            addCriterion("fuel_form >", value, "fuelForm");
            return (Criteria) this;
        }

        public Criteria andFuelFormGreaterThanOrEqualTo(Integer value) {
            addCriterion("fuel_form >=", value, "fuelForm");
            return (Criteria) this;
        }

        public Criteria andFuelFormLessThan(Integer value) {
            addCriterion("fuel_form <", value, "fuelForm");
            return (Criteria) this;
        }

        public Criteria andFuelFormLessThanOrEqualTo(Integer value) {
            addCriterion("fuel_form <=", value, "fuelForm");
            return (Criteria) this;
        }

        public Criteria andFuelFormIn(List<Integer> values) {
            addCriterion("fuel_form in", values, "fuelForm");
            return (Criteria) this;
        }

        public Criteria andFuelFormNotIn(List<Integer> values) {
            addCriterion("fuel_form not in", values, "fuelForm");
            return (Criteria) this;
        }

        public Criteria andFuelFormBetween(Integer value1, Integer value2) {
            addCriterion("fuel_form between", value1, value2, "fuelForm");
            return (Criteria) this;
        }

        public Criteria andFuelFormNotBetween(Integer value1, Integer value2) {
            addCriterion("fuel_form not between", value1, value2, "fuelForm");
            return (Criteria) this;
        }

        public Criteria andFuelNumberIsNull() {
            addCriterion("fuel_number is null");
            return (Criteria) this;
        }

        public Criteria andFuelNumberIsNotNull() {
            addCriterion("fuel_number is not null");
            return (Criteria) this;
        }

        public Criteria andFuelNumberEqualTo(Integer value) {
            addCriterion("fuel_number =", value, "fuelNumber");
            return (Criteria) this;
        }

        public Criteria andFuelNumberNotEqualTo(Integer value) {
            addCriterion("fuel_number <>", value, "fuelNumber");
            return (Criteria) this;
        }

        public Criteria andFuelNumberGreaterThan(Integer value) {
            addCriterion("fuel_number >", value, "fuelNumber");
            return (Criteria) this;
        }

        public Criteria andFuelNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("fuel_number >=", value, "fuelNumber");
            return (Criteria) this;
        }

        public Criteria andFuelNumberLessThan(Integer value) {
            addCriterion("fuel_number <", value, "fuelNumber");
            return (Criteria) this;
        }

        public Criteria andFuelNumberLessThanOrEqualTo(Integer value) {
            addCriterion("fuel_number <=", value, "fuelNumber");
            return (Criteria) this;
        }

        public Criteria andFuelNumberIn(List<Integer> values) {
            addCriterion("fuel_number in", values, "fuelNumber");
            return (Criteria) this;
        }

        public Criteria andFuelNumberNotIn(List<Integer> values) {
            addCriterion("fuel_number not in", values, "fuelNumber");
            return (Criteria) this;
        }

        public Criteria andFuelNumberBetween(Integer value1, Integer value2) {
            addCriterion("fuel_number between", value1, value2, "fuelNumber");
            return (Criteria) this;
        }

        public Criteria andFuelNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("fuel_number not between", value1, value2, "fuelNumber");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("level like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("level not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdIsNull() {
            addCriterion("check_company_id is null");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdIsNotNull() {
            addCriterion("check_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdEqualTo(String value) {
            addCriterion("check_company_id =", value, "checkCompanyId");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdNotEqualTo(String value) {
            addCriterion("check_company_id <>", value, "checkCompanyId");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdGreaterThan(String value) {
            addCriterion("check_company_id >", value, "checkCompanyId");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("check_company_id >=", value, "checkCompanyId");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdLessThan(String value) {
            addCriterion("check_company_id <", value, "checkCompanyId");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("check_company_id <=", value, "checkCompanyId");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdLike(String value) {
            addCriterion("check_company_id like", value, "checkCompanyId");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdNotLike(String value) {
            addCriterion("check_company_id not like", value, "checkCompanyId");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdIn(List<String> values) {
            addCriterion("check_company_id in", values, "checkCompanyId");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdNotIn(List<String> values) {
            addCriterion("check_company_id not in", values, "checkCompanyId");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdBetween(String value1, String value2) {
            addCriterion("check_company_id between", value1, value2, "checkCompanyId");
            return (Criteria) this;
        }

        public Criteria andCheckCompanyIdNotBetween(String value1, String value2) {
            addCriterion("check_company_id not between", value1, value2, "checkCompanyId");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorIsNull() {
            addCriterion("inster_operator is null");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorIsNotNull() {
            addCriterion("inster_operator is not null");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorEqualTo(String value) {
            addCriterion("inster_operator =", value, "insterOperator");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorNotEqualTo(String value) {
            addCriterion("inster_operator <>", value, "insterOperator");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorGreaterThan(String value) {
            addCriterion("inster_operator >", value, "insterOperator");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("inster_operator >=", value, "insterOperator");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorLessThan(String value) {
            addCriterion("inster_operator <", value, "insterOperator");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorLessThanOrEqualTo(String value) {
            addCriterion("inster_operator <=", value, "insterOperator");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorLike(String value) {
            addCriterion("inster_operator like", value, "insterOperator");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorNotLike(String value) {
            addCriterion("inster_operator not like", value, "insterOperator");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorIn(List<String> values) {
            addCriterion("inster_operator in", values, "insterOperator");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorNotIn(List<String> values) {
            addCriterion("inster_operator not in", values, "insterOperator");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorBetween(String value1, String value2) {
            addCriterion("inster_operator between", value1, value2, "insterOperator");
            return (Criteria) this;
        }

        public Criteria andInsterOperatorNotBetween(String value1, String value2) {
            addCriterion("inster_operator not between", value1, value2, "insterOperator");
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

        public Criteria andAccidentTypeIsNull() {
            addCriterion("accident_type is null");
            return (Criteria) this;
        }

        public Criteria andAccidentTypeIsNotNull() {
            addCriterion("accident_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccidentTypeEqualTo(Integer value) {
            addCriterion("accident_type =", value, "accidentType");
            return (Criteria) this;
        }

        public Criteria andAccidentTypeNotEqualTo(Integer value) {
            addCriterion("accident_type <>", value, "accidentType");
            return (Criteria) this;
        }

        public Criteria andAccidentTypeGreaterThan(Integer value) {
            addCriterion("accident_type >", value, "accidentType");
            return (Criteria) this;
        }

        public Criteria andAccidentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("accident_type >=", value, "accidentType");
            return (Criteria) this;
        }

        public Criteria andAccidentTypeLessThan(Integer value) {
            addCriterion("accident_type <", value, "accidentType");
            return (Criteria) this;
        }

        public Criteria andAccidentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("accident_type <=", value, "accidentType");
            return (Criteria) this;
        }

        public Criteria andAccidentTypeIn(List<Integer> values) {
            addCriterion("accident_type in", values, "accidentType");
            return (Criteria) this;
        }

        public Criteria andAccidentTypeNotIn(List<Integer> values) {
            addCriterion("accident_type not in", values, "accidentType");
            return (Criteria) this;
        }

        public Criteria andAccidentTypeBetween(Integer value1, Integer value2) {
            addCriterion("accident_type between", value1, value2, "accidentType");
            return (Criteria) this;
        }

        public Criteria andAccidentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("accident_type not between", value1, value2, "accidentType");
            return (Criteria) this;
        }

        public Criteria andReservePriceIsNull() {
            addCriterion("reserve_price is null");
            return (Criteria) this;
        }

        public Criteria andReservePriceIsNotNull() {
            addCriterion("reserve_price is not null");
            return (Criteria) this;
        }

        public Criteria andReservePriceEqualTo(Double value) {
            addCriterion("reserve_price =", value, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceNotEqualTo(Double value) {
            addCriterion("reserve_price <>", value, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceGreaterThan(Double value) {
            addCriterion("reserve_price >", value, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceGreaterThanOrEqualTo(Double value) {
            addCriterion("reserve_price >=", value, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceLessThan(Double value) {
            addCriterion("reserve_price <", value, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceLessThanOrEqualTo(Double value) {
            addCriterion("reserve_price <=", value, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceIn(List<Double> values) {
            addCriterion("reserve_price in", values, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceNotIn(List<Double> values) {
            addCriterion("reserve_price not in", values, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceBetween(Double value1, Double value2) {
            addCriterion("reserve_price between", value1, value2, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andReservePriceNotBetween(Double value1, Double value2) {
            addCriterion("reserve_price not between", value1, value2, "reservePrice");
            return (Criteria) this;
        }

        public Criteria andInitialLicenceTimeIsNull() {
            addCriterion("initial_licence_time is null");
            return (Criteria) this;
        }

        public Criteria andInitialLicenceTimeIsNotNull() {
            addCriterion("initial_licence_time is not null");
            return (Criteria) this;
        }

        public Criteria andInitialLicenceTimeEqualTo(Date value) {
            addCriterion("initial_licence_time =", value, "initialLicenceTime");
            return (Criteria) this;
        }

        public Criteria andInitialLicenceTimeNotEqualTo(Date value) {
            addCriterion("initial_licence_time <>", value, "initialLicenceTime");
            return (Criteria) this;
        }

        public Criteria andInitialLicenceTimeGreaterThan(Date value) {
            addCriterion("initial_licence_time >", value, "initialLicenceTime");
            return (Criteria) this;
        }

        public Criteria andInitialLicenceTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("initial_licence_time >=", value, "initialLicenceTime");
            return (Criteria) this;
        }

        public Criteria andInitialLicenceTimeLessThan(Date value) {
            addCriterion("initial_licence_time <", value, "initialLicenceTime");
            return (Criteria) this;
        }

        public Criteria andInitialLicenceTimeLessThanOrEqualTo(Date value) {
            addCriterion("initial_licence_time <=", value, "initialLicenceTime");
            return (Criteria) this;
        }

        public Criteria andInitialLicenceTimeIn(List<Date> values) {
            addCriterion("initial_licence_time in", values, "initialLicenceTime");
            return (Criteria) this;
        }

        public Criteria andInitialLicenceTimeNotIn(List<Date> values) {
            addCriterion("initial_licence_time not in", values, "initialLicenceTime");
            return (Criteria) this;
        }

        public Criteria andInitialLicenceTimeBetween(Date value1, Date value2) {
            addCriterion("initial_licence_time between", value1, value2, "initialLicenceTime");
            return (Criteria) this;
        }

        public Criteria andInitialLicenceTimeNotBetween(Date value1, Date value2) {
            addCriterion("initial_licence_time not between", value1, value2, "initialLicenceTime");
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