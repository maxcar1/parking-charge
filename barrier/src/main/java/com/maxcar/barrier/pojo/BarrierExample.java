package com.maxcar.barrier.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BarrierExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BarrierExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBarrierIdIsNull() {
            addCriterion("barrier_id is null");
            return (Criteria) this;
        }

        public Criteria andBarrierIdIsNotNull() {
            addCriterion("barrier_id is not null");
            return (Criteria) this;
        }

        public Criteria andBarrierIdEqualTo(String value) {
            addCriterion("barrier_id =", value, "barrierId");
            return (Criteria) this;
        }

        public Criteria andBarrierIdNotEqualTo(String value) {
            addCriterion("barrier_id <>", value, "barrierId");
            return (Criteria) this;
        }

        public Criteria andBarrierIdGreaterThan(String value) {
            addCriterion("barrier_id >", value, "barrierId");
            return (Criteria) this;
        }

        public Criteria andBarrierIdGreaterThanOrEqualTo(String value) {
            addCriterion("barrier_id >=", value, "barrierId");
            return (Criteria) this;
        }

        public Criteria andBarrierIdLessThan(String value) {
            addCriterion("barrier_id <", value, "barrierId");
            return (Criteria) this;
        }

        public Criteria andBarrierIdLessThanOrEqualTo(String value) {
            addCriterion("barrier_id <=", value, "barrierId");
            return (Criteria) this;
        }

        public Criteria andBarrierIdLike(String value) {
            addCriterion("barrier_id like", value, "barrierId");
            return (Criteria) this;
        }

        public Criteria andBarrierIdNotLike(String value) {
            addCriterion("barrier_id not like", value, "barrierId");
            return (Criteria) this;
        }

        public Criteria andBarrierIdIn(List<String> values) {
            addCriterion("barrier_id in", values, "barrierId");
            return (Criteria) this;
        }

        public Criteria andBarrierIdNotIn(List<String> values) {
            addCriterion("barrier_id not in", values, "barrierId");
            return (Criteria) this;
        }

        public Criteria andBarrierIdBetween(String value1, String value2) {
            addCriterion("barrier_id between", value1, value2, "barrierId");
            return (Criteria) this;
        }

        public Criteria andBarrierIdNotBetween(String value1, String value2) {
            addCriterion("barrier_id not between", value1, value2, "barrierId");
            return (Criteria) this;
        }

        public Criteria andGatewayIpIsNull() {
            addCriterion("gateway_ip is null");
            return (Criteria) this;
        }

        public Criteria andGatewayIpIsNotNull() {
            addCriterion("gateway_ip is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayIpEqualTo(String value) {
            addCriterion("gateway_ip =", value, "gatewayIp");
            return (Criteria) this;
        }

        public Criteria andGatewayIpNotEqualTo(String value) {
            addCriterion("gateway_ip <>", value, "gatewayIp");
            return (Criteria) this;
        }

        public Criteria andGatewayIpGreaterThan(String value) {
            addCriterion("gateway_ip >", value, "gatewayIp");
            return (Criteria) this;
        }

        public Criteria andGatewayIpGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_ip >=", value, "gatewayIp");
            return (Criteria) this;
        }

        public Criteria andGatewayIpLessThan(String value) {
            addCriterion("gateway_ip <", value, "gatewayIp");
            return (Criteria) this;
        }

        public Criteria andGatewayIpLessThanOrEqualTo(String value) {
            addCriterion("gateway_ip <=", value, "gatewayIp");
            return (Criteria) this;
        }

        public Criteria andGatewayIpLike(String value) {
            addCriterion("gateway_ip like", value, "gatewayIp");
            return (Criteria) this;
        }

        public Criteria andGatewayIpNotLike(String value) {
            addCriterion("gateway_ip not like", value, "gatewayIp");
            return (Criteria) this;
        }

        public Criteria andGatewayIpIn(List<String> values) {
            addCriterion("gateway_ip in", values, "gatewayIp");
            return (Criteria) this;
        }

        public Criteria andGatewayIpNotIn(List<String> values) {
            addCriterion("gateway_ip not in", values, "gatewayIp");
            return (Criteria) this;
        }

        public Criteria andGatewayIpBetween(String value1, String value2) {
            addCriterion("gateway_ip between", value1, value2, "gatewayIp");
            return (Criteria) this;
        }

        public Criteria andGatewayIpNotBetween(String value1, String value2) {
            addCriterion("gateway_ip not between", value1, value2, "gatewayIp");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskIsNull() {
            addCriterion("subnet_mask is null");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskIsNotNull() {
            addCriterion("subnet_mask is not null");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskEqualTo(String value) {
            addCriterion("subnet_mask =", value, "subnetMask");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskNotEqualTo(String value) {
            addCriterion("subnet_mask <>", value, "subnetMask");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskGreaterThan(String value) {
            addCriterion("subnet_mask >", value, "subnetMask");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskGreaterThanOrEqualTo(String value) {
            addCriterion("subnet_mask >=", value, "subnetMask");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskLessThan(String value) {
            addCriterion("subnet_mask <", value, "subnetMask");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskLessThanOrEqualTo(String value) {
            addCriterion("subnet_mask <=", value, "subnetMask");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskLike(String value) {
            addCriterion("subnet_mask like", value, "subnetMask");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskNotLike(String value) {
            addCriterion("subnet_mask not like", value, "subnetMask");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskIn(List<String> values) {
            addCriterion("subnet_mask in", values, "subnetMask");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskNotIn(List<String> values) {
            addCriterion("subnet_mask not in", values, "subnetMask");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskBetween(String value1, String value2) {
            addCriterion("subnet_mask between", value1, value2, "subnetMask");
            return (Criteria) this;
        }

        public Criteria andSubnetMaskNotBetween(String value1, String value2) {
            addCriterion("subnet_mask not between", value1, value2, "subnetMask");
            return (Criteria) this;
        }

        public Criteria andMacAddressIsNull() {
            addCriterion("mac_address is null");
            return (Criteria) this;
        }

        public Criteria andMacAddressIsNotNull() {
            addCriterion("mac_address is not null");
            return (Criteria) this;
        }

        public Criteria andMacAddressEqualTo(String value) {
            addCriterion("mac_address =", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressNotEqualTo(String value) {
            addCriterion("mac_address <>", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressGreaterThan(String value) {
            addCriterion("mac_address >", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressGreaterThanOrEqualTo(String value) {
            addCriterion("mac_address >=", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressLessThan(String value) {
            addCriterion("mac_address <", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressLessThanOrEqualTo(String value) {
            addCriterion("mac_address <=", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressLike(String value) {
            addCriterion("mac_address like", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressNotLike(String value) {
            addCriterion("mac_address not like", value, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressIn(List<String> values) {
            addCriterion("mac_address in", values, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressNotIn(List<String> values) {
            addCriterion("mac_address not in", values, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressBetween(String value1, String value2) {
            addCriterion("mac_address between", value1, value2, "macAddress");
            return (Criteria) this;
        }

        public Criteria andMacAddressNotBetween(String value1, String value2) {
            addCriterion("mac_address not between", value1, value2, "macAddress");
            return (Criteria) this;
        }

        public Criteria andClientIpIsNull() {
            addCriterion("client_ip is null");
            return (Criteria) this;
        }

        public Criteria andClientIpIsNotNull() {
            addCriterion("client_ip is not null");
            return (Criteria) this;
        }

        public Criteria andClientIpEqualTo(String value) {
            addCriterion("client_ip =", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotEqualTo(String value) {
            addCriterion("client_ip <>", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpGreaterThan(String value) {
            addCriterion("client_ip >", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpGreaterThanOrEqualTo(String value) {
            addCriterion("client_ip >=", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpLessThan(String value) {
            addCriterion("client_ip <", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpLessThanOrEqualTo(String value) {
            addCriterion("client_ip <=", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpLike(String value) {
            addCriterion("client_ip like", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotLike(String value) {
            addCriterion("client_ip not like", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpIn(List<String> values) {
            addCriterion("client_ip in", values, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotIn(List<String> values) {
            addCriterion("client_ip not in", values, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpBetween(String value1, String value2) {
            addCriterion("client_ip between", value1, value2, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotBetween(String value1, String value2) {
            addCriterion("client_ip not between", value1, value2, "clientIp");
            return (Criteria) this;
        }

        public Criteria andServerIpIsNull() {
            addCriterion("server_ip is null");
            return (Criteria) this;
        }

        public Criteria andServerIpIsNotNull() {
            addCriterion("server_ip is not null");
            return (Criteria) this;
        }

        public Criteria andServerIpEqualTo(String value) {
            addCriterion("server_ip =", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpNotEqualTo(String value) {
            addCriterion("server_ip <>", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpGreaterThan(String value) {
            addCriterion("server_ip >", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpGreaterThanOrEqualTo(String value) {
            addCriterion("server_ip >=", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpLessThan(String value) {
            addCriterion("server_ip <", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpLessThanOrEqualTo(String value) {
            addCriterion("server_ip <=", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpLike(String value) {
            addCriterion("server_ip like", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpNotLike(String value) {
            addCriterion("server_ip not like", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpIn(List<String> values) {
            addCriterion("server_ip in", values, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpNotIn(List<String> values) {
            addCriterion("server_ip not in", values, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpBetween(String value1, String value2) {
            addCriterion("server_ip between", value1, value2, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpNotBetween(String value1, String value2) {
            addCriterion("server_ip not between", value1, value2, "serverIp");
            return (Criteria) this;
        }

        public Criteria andClientPortIsNull() {
            addCriterion("client_port is null");
            return (Criteria) this;
        }

        public Criteria andClientPortIsNotNull() {
            addCriterion("client_port is not null");
            return (Criteria) this;
        }

        public Criteria andClientPortEqualTo(String value) {
            addCriterion("client_port =", value, "clientPort");
            return (Criteria) this;
        }

        public Criteria andClientPortNotEqualTo(String value) {
            addCriterion("client_port <>", value, "clientPort");
            return (Criteria) this;
        }

        public Criteria andClientPortGreaterThan(String value) {
            addCriterion("client_port >", value, "clientPort");
            return (Criteria) this;
        }

        public Criteria andClientPortGreaterThanOrEqualTo(String value) {
            addCriterion("client_port >=", value, "clientPort");
            return (Criteria) this;
        }

        public Criteria andClientPortLessThan(String value) {
            addCriterion("client_port <", value, "clientPort");
            return (Criteria) this;
        }

        public Criteria andClientPortLessThanOrEqualTo(String value) {
            addCriterion("client_port <=", value, "clientPort");
            return (Criteria) this;
        }

        public Criteria andClientPortLike(String value) {
            addCriterion("client_port like", value, "clientPort");
            return (Criteria) this;
        }

        public Criteria andClientPortNotLike(String value) {
            addCriterion("client_port not like", value, "clientPort");
            return (Criteria) this;
        }

        public Criteria andClientPortIn(List<String> values) {
            addCriterion("client_port in", values, "clientPort");
            return (Criteria) this;
        }

        public Criteria andClientPortNotIn(List<String> values) {
            addCriterion("client_port not in", values, "clientPort");
            return (Criteria) this;
        }

        public Criteria andClientPortBetween(String value1, String value2) {
            addCriterion("client_port between", value1, value2, "clientPort");
            return (Criteria) this;
        }

        public Criteria andClientPortNotBetween(String value1, String value2) {
            addCriterion("client_port not between", value1, value2, "clientPort");
            return (Criteria) this;
        }

        public Criteria andServerPortIsNull() {
            addCriterion("server_port is null");
            return (Criteria) this;
        }

        public Criteria andServerPortIsNotNull() {
            addCriterion("server_port is not null");
            return (Criteria) this;
        }

        public Criteria andServerPortEqualTo(String value) {
            addCriterion("server_port =", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortNotEqualTo(String value) {
            addCriterion("server_port <>", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortGreaterThan(String value) {
            addCriterion("server_port >", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortGreaterThanOrEqualTo(String value) {
            addCriterion("server_port >=", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortLessThan(String value) {
            addCriterion("server_port <", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortLessThanOrEqualTo(String value) {
            addCriterion("server_port <=", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortLike(String value) {
            addCriterion("server_port like", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortNotLike(String value) {
            addCriterion("server_port not like", value, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortIn(List<String> values) {
            addCriterion("server_port in", values, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortNotIn(List<String> values) {
            addCriterion("server_port not in", values, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortBetween(String value1, String value2) {
            addCriterion("server_port between", value1, value2, "serverPort");
            return (Criteria) this;
        }

        public Criteria andServerPortNotBetween(String value1, String value2) {
            addCriterion("server_port not between", value1, value2, "serverPort");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeIsNull() {
            addCriterion("barrier_type is null");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeIsNotNull() {
            addCriterion("barrier_type is not null");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeEqualTo(String value) {
            addCriterion("barrier_type =", value, "barrierType");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeNotEqualTo(String value) {
            addCriterion("barrier_type <>", value, "barrierType");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeGreaterThan(String value) {
            addCriterion("barrier_type >", value, "barrierType");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeGreaterThanOrEqualTo(String value) {
            addCriterion("barrier_type >=", value, "barrierType");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeLessThan(String value) {
            addCriterion("barrier_type <", value, "barrierType");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeLessThanOrEqualTo(String value) {
            addCriterion("barrier_type <=", value, "barrierType");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeLike(String value) {
            addCriterion("barrier_type like", value, "barrierType");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeNotLike(String value) {
            addCriterion("barrier_type not like", value, "barrierType");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeIn(List<String> values) {
            addCriterion("barrier_type in", values, "barrierType");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeNotIn(List<String> values) {
            addCriterion("barrier_type not in", values, "barrierType");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeBetween(String value1, String value2) {
            addCriterion("barrier_type between", value1, value2, "barrierType");
            return (Criteria) this;
        }

        public Criteria andBarrierTypeNotBetween(String value1, String value2) {
            addCriterion("barrier_type not between", value1, value2, "barrierType");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionIsNull() {
            addCriterion("barrier_position is null");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionIsNotNull() {
            addCriterion("barrier_position is not null");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionEqualTo(String value) {
            addCriterion("barrier_position =", value, "barrierPosition");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionNotEqualTo(String value) {
            addCriterion("barrier_position <>", value, "barrierPosition");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionGreaterThan(String value) {
            addCriterion("barrier_position >", value, "barrierPosition");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionGreaterThanOrEqualTo(String value) {
            addCriterion("barrier_position >=", value, "barrierPosition");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionLessThan(String value) {
            addCriterion("barrier_position <", value, "barrierPosition");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionLessThanOrEqualTo(String value) {
            addCriterion("barrier_position <=", value, "barrierPosition");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionLike(String value) {
            addCriterion("barrier_position like", value, "barrierPosition");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionNotLike(String value) {
            addCriterion("barrier_position not like", value, "barrierPosition");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionIn(List<String> values) {
            addCriterion("barrier_position in", values, "barrierPosition");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionNotIn(List<String> values) {
            addCriterion("barrier_position not in", values, "barrierPosition");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionBetween(String value1, String value2) {
            addCriterion("barrier_position between", value1, value2, "barrierPosition");
            return (Criteria) this;
        }

        public Criteria andBarrierPositionNotBetween(String value1, String value2) {
            addCriterion("barrier_position not between", value1, value2, "barrierPosition");
            return (Criteria) this;
        }

        public Criteria andInOutCarIsNull() {
            addCriterion("in_out_car is null");
            return (Criteria) this;
        }

        public Criteria andInOutCarIsNotNull() {
            addCriterion("in_out_car is not null");
            return (Criteria) this;
        }

        public Criteria andInOutCarEqualTo(String value) {
            addCriterion("in_out_car =", value, "inOutCar");
            return (Criteria) this;
        }

        public Criteria andInOutCarNotEqualTo(String value) {
            addCriterion("in_out_car <>", value, "inOutCar");
            return (Criteria) this;
        }

        public Criteria andInOutCarGreaterThan(String value) {
            addCriterion("in_out_car >", value, "inOutCar");
            return (Criteria) this;
        }

        public Criteria andInOutCarGreaterThanOrEqualTo(String value) {
            addCriterion("in_out_car >=", value, "inOutCar");
            return (Criteria) this;
        }

        public Criteria andInOutCarLessThan(String value) {
            addCriterion("in_out_car <", value, "inOutCar");
            return (Criteria) this;
        }

        public Criteria andInOutCarLessThanOrEqualTo(String value) {
            addCriterion("in_out_car <=", value, "inOutCar");
            return (Criteria) this;
        }

        public Criteria andInOutCarLike(String value) {
            addCriterion("in_out_car like", value, "inOutCar");
            return (Criteria) this;
        }

        public Criteria andInOutCarNotLike(String value) {
            addCriterion("in_out_car not like", value, "inOutCar");
            return (Criteria) this;
        }

        public Criteria andInOutCarIn(List<String> values) {
            addCriterion("in_out_car in", values, "inOutCar");
            return (Criteria) this;
        }

        public Criteria andInOutCarNotIn(List<String> values) {
            addCriterion("in_out_car not in", values, "inOutCar");
            return (Criteria) this;
        }

        public Criteria andInOutCarBetween(String value1, String value2) {
            addCriterion("in_out_car between", value1, value2, "inOutCar");
            return (Criteria) this;
        }

        public Criteria andInOutCarNotBetween(String value1, String value2) {
            addCriterion("in_out_car not between", value1, value2, "inOutCar");
            return (Criteria) this;
        }

        public Criteria andInOutTypeIsNull() {
            addCriterion("in_out_type is null");
            return (Criteria) this;
        }

        public Criteria andInOutTypeIsNotNull() {
            addCriterion("in_out_type is not null");
            return (Criteria) this;
        }

        public Criteria andInOutTypeEqualTo(Integer value) {
            addCriterion("in_out_type =", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeNotEqualTo(Integer value) {
            addCriterion("in_out_type <>", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeGreaterThan(Integer value) {
            addCriterion("in_out_type >", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("in_out_type >=", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeLessThan(Integer value) {
            addCriterion("in_out_type <", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeLessThanOrEqualTo(Integer value) {
            addCriterion("in_out_type <=", value, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeIn(List<Integer> values) {
            addCriterion("in_out_type in", values, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeNotIn(List<Integer> values) {
            addCriterion("in_out_type not in", values, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeBetween(Integer value1, Integer value2) {
            addCriterion("in_out_type between", value1, value2, "inOutType");
            return (Criteria) this;
        }

        public Criteria andInOutTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("in_out_type not between", value1, value2, "inOutType");
            return (Criteria) this;
        }

        public Criteria andParkLimitIsNull() {
            addCriterion("park_limit is null");
            return (Criteria) this;
        }

        public Criteria andParkLimitIsNotNull() {
            addCriterion("park_limit is not null");
            return (Criteria) this;
        }

        public Criteria andParkLimitEqualTo(String value) {
            addCriterion("park_limit =", value, "parkLimit");
            return (Criteria) this;
        }

        public Criteria andParkLimitNotEqualTo(String value) {
            addCriterion("park_limit <>", value, "parkLimit");
            return (Criteria) this;
        }

        public Criteria andParkLimitGreaterThan(String value) {
            addCriterion("park_limit >", value, "parkLimit");
            return (Criteria) this;
        }

        public Criteria andParkLimitGreaterThanOrEqualTo(String value) {
            addCriterion("park_limit >=", value, "parkLimit");
            return (Criteria) this;
        }

        public Criteria andParkLimitLessThan(String value) {
            addCriterion("park_limit <", value, "parkLimit");
            return (Criteria) this;
        }

        public Criteria andParkLimitLessThanOrEqualTo(String value) {
            addCriterion("park_limit <=", value, "parkLimit");
            return (Criteria) this;
        }

        public Criteria andParkLimitLike(String value) {
            addCriterion("park_limit like", value, "parkLimit");
            return (Criteria) this;
        }

        public Criteria andParkLimitNotLike(String value) {
            addCriterion("park_limit not like", value, "parkLimit");
            return (Criteria) this;
        }

        public Criteria andParkLimitIn(List<String> values) {
            addCriterion("park_limit in", values, "parkLimit");
            return (Criteria) this;
        }

        public Criteria andParkLimitNotIn(List<String> values) {
            addCriterion("park_limit not in", values, "parkLimit");
            return (Criteria) this;
        }

        public Criteria andParkLimitBetween(String value1, String value2) {
            addCriterion("park_limit between", value1, value2, "parkLimit");
            return (Criteria) this;
        }

        public Criteria andParkLimitNotBetween(String value1, String value2) {
            addCriterion("park_limit not between", value1, value2, "parkLimit");
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

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechIsNull() {
            addCriterion("static_speech is null");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechIsNotNull() {
            addCriterion("static_speech is not null");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechEqualTo(String value) {
            addCriterion("static_speech =", value, "staticSpeech");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechNotEqualTo(String value) {
            addCriterion("static_speech <>", value, "staticSpeech");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechGreaterThan(String value) {
            addCriterion("static_speech >", value, "staticSpeech");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechGreaterThanOrEqualTo(String value) {
            addCriterion("static_speech >=", value, "staticSpeech");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechLessThan(String value) {
            addCriterion("static_speech <", value, "staticSpeech");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechLessThanOrEqualTo(String value) {
            addCriterion("static_speech <=", value, "staticSpeech");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechLike(String value) {
            addCriterion("static_speech like", value, "staticSpeech");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechNotLike(String value) {
            addCriterion("static_speech not like", value, "staticSpeech");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechIn(List<String> values) {
            addCriterion("static_speech in", values, "staticSpeech");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechNotIn(List<String> values) {
            addCriterion("static_speech not in", values, "staticSpeech");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechBetween(String value1, String value2) {
            addCriterion("static_speech between", value1, value2, "staticSpeech");
            return (Criteria) this;
        }

        public Criteria andStaticSpeechNotBetween(String value1, String value2) {
            addCriterion("static_speech not between", value1, value2, "staticSpeech");
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

        public Criteria andMqttTopicIsNull() {
            addCriterion("mqtt_topic is null");
            return (Criteria) this;
        }

        public Criteria andMqttTopicIsNotNull() {
            addCriterion("mqtt_topic is not null");
            return (Criteria) this;
        }

        public Criteria andMqttTopicEqualTo(String value) {
            addCriterion("mqtt_topic =", value, "mqttTopic");
            return (Criteria) this;
        }

        public Criteria andMqttTopicNotEqualTo(String value) {
            addCriterion("mqtt_topic <>", value, "mqttTopic");
            return (Criteria) this;
        }

        public Criteria andMqttTopicGreaterThan(String value) {
            addCriterion("mqtt_topic >", value, "mqttTopic");
            return (Criteria) this;
        }

        public Criteria andMqttTopicGreaterThanOrEqualTo(String value) {
            addCriterion("mqtt_topic >=", value, "mqttTopic");
            return (Criteria) this;
        }

        public Criteria andMqttTopicLessThan(String value) {
            addCriterion("mqtt_topic <", value, "mqttTopic");
            return (Criteria) this;
        }

        public Criteria andMqttTopicLessThanOrEqualTo(String value) {
            addCriterion("mqtt_topic <=", value, "mqttTopic");
            return (Criteria) this;
        }

        public Criteria andMqttTopicLike(String value) {
            addCriterion("mqtt_topic like", value, "mqttTopic");
            return (Criteria) this;
        }

        public Criteria andMqttTopicNotLike(String value) {
            addCriterion("mqtt_topic not like", value, "mqttTopic");
            return (Criteria) this;
        }

        public Criteria andMqttTopicIn(List<String> values) {
            addCriterion("mqtt_topic in", values, "mqttTopic");
            return (Criteria) this;
        }

        public Criteria andMqttTopicNotIn(List<String> values) {
            addCriterion("mqtt_topic not in", values, "mqttTopic");
            return (Criteria) this;
        }

        public Criteria andMqttTopicBetween(String value1, String value2) {
            addCriterion("mqtt_topic between", value1, value2, "mqttTopic");
            return (Criteria) this;
        }

        public Criteria andMqttTopicNotBetween(String value1, String value2) {
            addCriterion("mqtt_topic not between", value1, value2, "mqttTopic");
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