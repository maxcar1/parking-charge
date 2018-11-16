package com.maxcar.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.pojo.Magic;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.StringUtils;
import com.maxcar.base.util.UuidUtils;
import com.maxcar.market.dao.PropertyContractMapper;
import com.maxcar.market.model.request.*;
import com.maxcar.market.model.response.GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse;
import com.maxcar.market.model.response.GetPropertyContractAllPack;
import com.maxcar.market.model.response.GetPropertyContractAllResponse;
import com.maxcar.market.model.response.GetPropertyContractResponse;
import com.maxcar.market.pojo.AreaShop;
import com.maxcar.market.pojo.PropertyContract;
import com.maxcar.market.pojo.PropertyContractDetail;
import com.maxcar.market.service.AreaShopService;
import com.maxcar.market.service.PropertyContractService;
import com.maxcar.market.service.mapperService.PropertyContractDetailMapperService;
import com.maxcar.market.utils.ToolUtils;
import com.maxcar.tenant.pojo.UserTenant;
import com.maxcar.tenant.service.UserTenantService;
import com.maxcar.user.entity.Configuration;
import com.maxcar.user.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("propertyContractService")
public class PropertyContractServiceImpl extends BaseServiceImpl<PropertyContract, String> implements PropertyContractService {


    @Autowired
    private PropertyContractMapper propertyContractMapper;

    @Autowired
    private PropertyContractDetailMapperService propertyContractDetailMapperService;

    @Autowired
    private UserTenantService userTenantService;

    @Autowired
    private AreaShopService areaShopService;

    @Autowired
    private ConfigurationService configurationService;

    @Override
    public BaseDao<PropertyContract, String> getBaseMapper() {
        return propertyContractMapper;
    }


    /**
     * param:
     * describe: 根据 市场ID 或者 商户ID  或者 区域ID 查询 车位信息 (三选其一)
     * create_date:  lxy   2018/9/26  11:15
     **/
    @Override
    public GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse getCarTotalByMarketIdOrTenantIdOrAreaId(GetCarSpaceAndOfficeByMarketIdOrAreaIdRequest request) throws Exception {

        if (StringUtils.isBlank(request.getMarketId())) {
            return null;
        }

        Configuration configuration = new Configuration();
        configuration.setMarketId(request.getMarketId());
        configuration.setConfigurationKey(Magic.EXHIBITION_AREA);

        List<Configuration> propertyContractStatus = configurationService.searchConfiguration(configuration);

        if (null == propertyContractStatus || propertyContractStatus.isEmpty()) {
            return null;
        }

        Double carArea = Double.valueOf(propertyContractStatus.get(0).getConfigurationValue().trim());

        GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse response = new GetCarTotalByMarketIdOrTenantIdOrAreaIdResponse();

        Integer carTotal = 0;
        Integer carNumber = 0;
        Integer exhibitionNumber = 0;
        Integer office = 0;

        if (!StringUtils.isEmpty(request.getTenantId())) {
            // 根据 商户ID 查询车位数  展厅转换为车位
            GetPropertyContractDetailListRequest getPropertyContractDetailListRequest = new GetPropertyContractDetailListRequest();
            getPropertyContractDetailListRequest.setPropertyContractDetailStatus(Magic.CONTRACT_STATUS_NORMAL);
            getPropertyContractDetailListRequest.setTenantId(request.getTenantId());

            List<PropertyContractDetail> propertyContractDetailList = propertyContractDetailMapperService.getPropertyContractDetailList(getPropertyContractDetailListRequest);

            if (null != propertyContractDetailList && !propertyContractDetailList.isEmpty()) {
                for (PropertyContractDetail propertyContractDetail : propertyContractDetailList) {
                    int number = Integer.parseInt(new java.text.DecimalFormat("0").format(propertyContractDetail.getAreaTotal()));

                    if (Magic.CONTRACT_CATEGORY_CAR_SPACE.equals(propertyContractDetail.getContractCategory())) {
                        // 租赁物业是车位
                        carTotal += number;

                        carNumber += number;
                    } else if (Magic.CONTRACT_CATEGORY_EXHIBITION.equals(propertyContractDetail.getContractCategory())) {
                        // 租赁物业是展厅
                        BigDecimal b = new BigDecimal(propertyContractDetail.getAreaTotal() / carArea);
                        carTotal += b.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();

                        exhibitionNumber += number;
                    } else if (Magic.CONTRACT_CATEGORY_OFFICE.equals(propertyContractDetail.getContractCategory())) {
                        // 租赁物业是办公室
                        office += number;
                    }
                }

            }

            response.setCarNumber(carNumber);
            response.setExhibitionNumber(exhibitionNumber);
            response.setCarTotal(carTotal);
            response.setOffice(office);

            return response;
        }

        if (!StringUtils.isEmpty(request.getAreaId()) || !StringUtils.isEmpty(request.getMarketId())) {
            // 查询该市场 或者 区域 下 车位数量
            List<AreaShop> carSpaceAndOfficeByMarketId = areaShopService.getCarSpaceAndOfficeByMarketId(request);

            if (null != carSpaceAndOfficeByMarketId && !carSpaceAndOfficeByMarketId.isEmpty()) {

                for (AreaShop areaShop : carSpaceAndOfficeByMarketId) {
                    int number = Integer.parseInt(new java.text.DecimalFormat("0").format(areaShop.getParkSpace()));

                    if (Magic.CONTRACT_CATEGORY_CAR_SPACE.equals(areaShop.getType())) {
                        // 区域类型为车位
                        carNumber += number;
                        carTotal += number;
                    } else if (Magic.CONTRACT_CATEGORY_EXHIBITION.equals(areaShop.getType())) {
                        // 区域车位为展厅
                        BigDecimal b = new BigDecimal(areaShop.getParkSpace() / carArea);
                        carTotal += b.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();

                        exhibitionNumber += number;
                    }
                }
            }

            response.setCarNumber(carNumber);
            response.setExhibitionNumber(exhibitionNumber);
            response.setCarTotal(carTotal);
            response.setOffice(office);

            return response;
        }

        return null;
    }

    /**
     * param:  propertyContract  合同主表实体
     * describe: 新增合同
     * create_date:  lxy   2018/8/15  17:48
     **/
    @Override
    public boolean addPropertyContract(AddContractRequest request) {
        UserTenant addUserTenant = new UserTenant();

        addUserTenant.setTenantName(request.getUserTenantPack().getTenantName());

        UserTenant userTenant = userTenantService.selectUserTenant(addUserTenant);

        addUserTenant.setMarketId(request.getPropertyContract().getMarketId());
        addUserTenant.setTenantName(request.getUserTenantPack().getTenantName());
        addUserTenant.setTenantType(request.getUserTenantPack().getTenantType());
        addUserTenant.setTenantShopName(request.getUserTenantPack().getShopName());
        addUserTenant.setTenantArea(request.getUserTenantPack().getShopArea());
        addUserTenant.setLicenceNo(request.getUserTenantPack().getLicenceNo());
        addUserTenant.setContactName(request.getUserTenantPack().getContactName());
        addUserTenant.setContactMobile(request.getUserTenantPack().getContactMobile());
        addUserTenant.setTenantPhone(request.getUserTenantPack().getContactMobile());
        addUserTenant.setContactIdCard(request.getUserTenantPack().getContactIdCard());
        addUserTenant.setCorporateName(request.getUserTenantPack().getCorporateName());
        addUserTenant.setCorporatePhone(request.getUserTenantPack().getCorporatePhone());
        addUserTenant.setCorporateIdCard(request.getUserTenantPack().getCorporateIdCard());
        addUserTenant.setRegisterTime(new Date());
        addUserTenant.setStatus("1");
        InterfaceResult interfaceResult;

        if (null == userTenant) {
            // 新增商户
            addUserTenant.setId(UuidUtils.generateIdentifier());
            addUserTenant.setCheckInTime(new Date());

            interfaceResult = userTenantService.addUserTenant(addUserTenant);
        } else {
            //修改商户信息
            // 合同状态和商户状态相对应
            addUserTenant.setId(userTenant.getId());
            addUserTenant.setStatus(Magic.CONTRACT_STATUS_NORMAL.toString());

            interfaceResult = userTenantService.updateUserTenantById(addUserTenant);
        }

        if (!"200".equals(interfaceResult.getCode())) {
            return false;
        }

        String propertyContractId = UuidUtils.generateIdentifier();
        String maxContractNo = propertyContractMapper.MaxContractNoByMarketId(request.getPropertyContract().getMarketId());
        String contractNo = (null == maxContractNo || maxContractNo.isEmpty()) ?
                ToolUtils.gettimeFormart(null) : ToolUtils.gettimeFormart(maxContractNo);

        PropertyContract propertyContract = request.getPropertyContract();

        propertyContract.setId(propertyContractId);
        propertyContract.setContractNo(contractNo);
        propertyContract.setTenantId(addUserTenant.getId());
        propertyContract.setStatus(null);
        propertyContract.setContractImage(null);
        propertyContract.setGuaranteeImage(null);
        propertyContract.setCommitmentImage(null);
        propertyContract.setIsvalid(null);
        propertyContract.setRemark(null);
        propertyContract.setUpdateTime(null);
        propertyContract.setUpdateOperator(null);
        propertyContract.setInsertTime(null);

        if (ToolUtils.isOperationFail(propertyContractMapper.insertSelective(propertyContract))) {
            return false;
        }

        if (null == request.getPropertyContractDetailList() || request.getPropertyContractDetailList().isEmpty()) {
            return true;
        }

        request.getPropertyContractDetailList().forEach(x -> {
            x.setId(UuidUtils.generateIdentifier());
            x.setPropertyContractId(propertyContractId);
            x.setMarketId(propertyContract.getMarketId());
            x.setTenantId(propertyContract.getTenantId());
            x.setInsertOperator(propertyContract.getInsertOperator());
            x.setUpdateOperator(propertyContract.getUpdateOperator());
            x.setUpdateTime(propertyContract.getUpdateTime());
            x.setPropertyContractDetailStatus(null);
            x.setTerminationRemark(null);
            x.setTerminationMessage(null);
            x.setTerminationTime(null);
            x.setIsvalid(null);
            x.setInsertTime(null);
            x.setUpdateTime(null);
            x.setUpdateOperator(null);

            if (propertyContractDetailMapperService.insertSelective(x)) {

              /*  String[] areaIds = x.getAreaId().split(",");

                for (int i = 0; i < areaIds.length; i++) {
                    // 修改物业状态
                    areaShopService.updateRentById(areaIds[i], Magic.AREA_SHOP_RENT_STATUS_RENT);
                }*/

                areaShopService.updateRentById(x.getAreaId(), Magic.AREA_SHOP_RENT_STATUS_RENT);
            }

        });
        return true;
    }


    /**
     * param:
     * describe: 导出合同列表
     * create_date:  lxy   2018/9/3  15:46
     **/
    @Override
    public List<GetPropertyContractAllPack> pageInfoGetPropertyContractAllResponse(GetPropertyContractAllRequest request) {

        List<GetPropertyContractAllResponse> allContract = propertyContractMapper.getAll(request);

        if (null == allContract || allContract.isEmpty()) {
            return null;
        }

        List<GetPropertyContractAllPack> list = new ArrayList<>(allContract.size());

        allContract.forEach(x -> {
            GetPropertyContractAllPack pack = new GetPropertyContractAllPack();
            pack.setContractNo(x.getContractNo());
            pack.setTenantName(x.getTenantName());

            pack.setTenantContactName(x.getTenantContactName());
            pack.setTenantContactMobile(x.getTenantContactMobile());

            // 合同
            try {

                Configuration configurationContractStatus = new Configuration();
                configurationContractStatus.setMarketId(request.getMarketId());
                configurationContractStatus.setConfigurationKey(Magic.PROPERTY_CONTRACT_STATUS);

                List<Configuration> propertyContractStatus = configurationService.searchConfiguration(configurationContractStatus);

                if (null != propertyContractStatus && !propertyContractStatus.isEmpty()) {
                    propertyContractStatus.forEach(configuration -> {
                        if ((x.getPropertyContractStatus().toString()).equals(configuration.getConfigurationValue())) {
                            pack.setPropertyContractStatusName(configuration.getConfigurationName());
                        }
                    });
                }
                Configuration configurationTenantType = new Configuration();
                configurationTenantType.setMarketId(request.getMarketId());
                configurationTenantType.setConfigurationKey(Magic.TENANT_TYPE);

                List<Configuration> tenantType = configurationService.searchConfiguration(configurationTenantType);

                if (null != tenantType && !tenantType.isEmpty()) {
                    tenantType.forEach(configuration -> {
                        if ((x.getTenantType().toString()).equals(configuration.getConfigurationValue())) {
                            pack.setTenantType(configuration.getConfigurationName());
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            GetPropertyContractDetailListRequest getPropertyContractDetailListRequest = new GetPropertyContractDetailListRequest();

            getPropertyContractDetailListRequest.setPropertyContractId(x.getPropertyContractId());

            List<PropertyContractDetail> propertyContractDetailList = propertyContractDetailMapperService.getPropertyContractDetailList(getPropertyContractDetailListRequest);

            if (null != propertyContractDetailList) {
                propertyContractDetailList.forEach(contractDetail -> {
                    // 根据区域id 查询区域信息 暂时展示ID 待联调是 展示区域名字
                    if (Magic.CONTRACT_CATEGORY_CAR_SPACE.equals(contractDetail.getContractCategory())) {
                        pack.setCarSpaceNos(contractDetail.getAreaName());
                    } else if (Magic.CONTRACT_CATEGORY_EXHIBITION.equals(contractDetail.getContractCategory())) {
                        pack.setExhibitionNos(contractDetail.getAreaName());
                    } else if (Magic.CONTRACT_CATEGORY_OFFICE.equals(contractDetail.getContractCategory())) {
                        pack.setOfficeNos(contractDetail.getAreaName());
                    }

                });
            }

            list.add(pack);

        });

        return list;
    }

    /**
     * param:
     * describe: 查看合同列表
     * create_date:  lxy   2018/8/16  19:18
     **/
    @Override
    public PageInfo<GetPropertyContractAllResponse> getPropertyContractAll(GetPropertyContractAllRequest request) {

        PageHelper.startPage(request.getCurPage(), request.getPageSize());

        List<GetPropertyContractAllResponse> allContract = propertyContractMapper.getAll(request);

        if (allContract == null || allContract.isEmpty()) {

            PageInfo<GetPropertyContractAllResponse> pageInfo = new PageInfo<>(allContract);

            return pageInfo;
        }

        GetPropertyContractDetailListRequest getPropertyContractDetailListRequest = new GetPropertyContractDetailListRequest();
        allContract.forEach(x -> {
            getPropertyContractDetailListRequest.setPropertyContractId(x.getPropertyContractId());

            String carSpaceNos = "";
            Double carSpaceTotal = 0.00;
            String exhibitionNos = "";
            Double exhibitionTotal = 0.0;
            String officeNos = "";
            Double officeTotal = 0.0;
            String warehouseNos = "";
            Double warehouseTotal = 0.0;
            String temporaryCarSpaceNos = "";
            Double temporaryCarSpaceTotal = 0.0;
            String otherNos = "";
            Double otherTotal = 0.0;

            List<PropertyContractDetail> list = propertyContractDetailMapperService.getPropertyContractDetailList(getPropertyContractDetailListRequest);
            if (null != list && !list.isEmpty()) {
                for (PropertyContractDetail contractDetail : list) {
                    if (Magic.CONTRACT_CATEGORY_CAR_SPACE.equals(contractDetail.getContractCategory())) {
                        carSpaceNos += carSpaceNos.isEmpty() ? contractDetail.getAreaName() : "," + contractDetail.getAreaName();
                        carSpaceTotal += contractDetail.getAreaTotal();
                    } else if (Magic.CONTRACT_CATEGORY_EXHIBITION.equals(contractDetail.getContractCategory())) {
                        exhibitionNos += exhibitionNos.isEmpty() ? contractDetail.getAreaName() : "," + contractDetail.getAreaName();
                        exhibitionTotal += contractDetail.getAreaTotal();
                    } else if (Magic.CONTRACT_CATEGORY_OFFICE.equals(contractDetail.getContractCategory())) {
                        officeNos += officeNos.isEmpty() ? contractDetail.getAreaName() : "," + contractDetail.getAreaName();
                        officeTotal += contractDetail.getAreaTotal();
                    } else if (Magic.CONTRACT_CATEGORY_WAREHOUSE.equals(contractDetail.getContractCategory())) {
                        warehouseNos += officeNos.isEmpty() ? contractDetail.getAreaName() : "," + contractDetail.getAreaName();
                        warehouseTotal += contractDetail.getAreaTotal();
                    } else if (Magic.CONTRACT_CATEGORY_TEMPORARY_CAR_SPACE.equals(contractDetail.getContractCategory())) {
                        temporaryCarSpaceNos += officeNos.isEmpty() ? contractDetail.getAreaName() : "," + contractDetail.getAreaName();
                        temporaryCarSpaceTotal += contractDetail.getAreaTotal();
                    } else if (Magic.CONTRACT_CATEGORY_OTHER.equals(contractDetail.getContractCategory())) {
                        otherNos += officeNos.isEmpty() ? contractDetail.getAreaName() : "," + contractDetail.getAreaName();
                        otherTotal += contractDetail.getAreaTotal();
                    }

                    x.setTerminationRemark(contractDetail.getTerminationRemark());
                    x.setTerminationMessage(contractDetail.getTerminationMessage());
                    x.setTerminationTime(contractDetail.getTerminationTime());
                }

                x.setIsCategory(true);
            } else {
                x.setIsCategory(false);
            }

            x.setCarSpaceNos(carSpaceNos);
            x.setCarSpaceTotal(carSpaceTotal);
            x.setExhibitionNos(exhibitionNos);
            x.setExhibitionTotal(exhibitionTotal);
            x.setOfficeNos(officeNos);
            x.setOfficeTotal(officeTotal);
            x.setWarehouseNos(warehouseNos);
            x.setWarehouseTotal(warehouseTotal);
            x.setTemporaryCarSpaceNos(temporaryCarSpaceNos);
            x.setTemporaryCarSpaceTotal(temporaryCarSpaceTotal);
            x.setOtherNos(otherNos);
            x.setOtherTotal(otherTotal);

        });
        PageInfo<GetPropertyContractAllResponse> pageInfo = new PageInfo<>(allContract);

        return pageInfo;
    }


    /**
     * param:
     * describe: 修改合同信息
     * create_date:  lxy   2018/8/16  19:19
     **/
    @Override
    public boolean updatePropertyContract(UpdatePropertyContractRequest request) {

        PropertyContract recod = propertyContractMapper.selectByPrimaryKey(request.getPropertyContractId());

        // 合同正常的情况下才允许修改
        if (recod == null || !Magic.CONTRACT_STATUS_NORMAL.equals(recod.getStatus())) {
            return false;
        }

        if (Magic.CONTRACT_STATUS_END.equals(request.getPropertyContractStatus())) {
            // 如过是终止合同 判断是否租了物业 没有租物业直接终止
            GetPropertyContractDetailListRequest request1 = new GetPropertyContractDetailListRequest();
            request1.setPropertyContractId(request.getPropertyContractId());
            // 查看是否租了物业
            List<PropertyContractDetail> list = propertyContractDetailMapperService.getPropertyContractDetailList(request1);
            if (null == list || list.isEmpty()) {
                // 没有租赁物业信息 终止合同 --> 调用终止合同接口
                propertyContractDetailMapperService.updatePropertyContractDetail(request.getPropertyContractId(), Magic.CONTRACT_STATUS_END);

                return true;
            }

            if (null == request.getTerminationMessage() || request.getTerminationMessage().isEmpty() || null == request.getTerminationTime()) {
                return false;
            }

        }


        PropertyContract propertyContract = new PropertyContract();

        propertyContract.setId(recod.getId());
        //合同不立即终结 按时间终结
        //propertyContract.setStatus(request.getPropertyContractStatus());

        propertyContract.setContractImage(request.getContractImage());
        propertyContract.setGuaranteeImage(request.getGuaranteeImage());
        propertyContract.setCommitmentImage(request.getCommitmentImage());

        propertyContract.setUpdateOperator(request.getUpdateOperator());
        propertyContract.setUpdateTime(new Date());

        // 修改合同信息
        if (ToolUtils.isOperationFail(propertyContractMapper.updateByPrimaryKeySelective(propertyContract))) {
            return false;
        }

        // 终止合同 关联详情表 写入合同原因
        if (Magic.CONTRACT_STATUS_END.equals(request.getPropertyContractStatus())) {

            PropertyContractDetail propertyContractDetail = new PropertyContractDetail();
            propertyContractDetail.setPropertyContractId(propertyContract.getId());
            propertyContractDetail.setTerminationRemark(request.getTerminationRemark());
            propertyContractDetail.setTerminationMessage(request.getTerminationMessage());
            propertyContractDetail.setTerminationTime(request.getTerminationTime());
            //合同不立即终结 按时间终结
            // propertyContractDetail.setPropertyContractDetailStatus(propertyContract.getStatus());

            propertyContractDetail.setUpdateOperator(propertyContract.getUpdateOperator());
            propertyContractDetail.setUpdateTime(propertyContract.getUpdateTime());
            // 修改合同状态正常的子合同
            propertyContractDetailMapperService.updateDetailByPropertyContractId(propertyContractDetail);
        }

        // 更新商户信息
        UserTenant updateUserTenant = new UserTenant();
        updateUserTenant.setId(recod.getTenantId());
        updateUserTenant.setCopyBusinessLicense(request.getCopyBusinessLicense());
        updateUserTenant.setCorporatePhotoFace(request.getCorporatePhotoFace());
        updateUserTenant.setCorporatePhotoBack(request.getCorporatePhotoBack());
        updateUserTenant.setContactPhotoFace(request.getContactPhotoFace());
        updateUserTenant.setContactPhotoBack(request.getContactPhotoBack());
        updateUserTenant.setUpdateOperator(request.getUpdateOperator());
        updateUserTenant.setUpdateTime(new Date());
        updateUserTenant.setCateringImage(request.getCateringImage());
        updateUserTenant.setHealthImage(request.getHealthImage());

        userTenantService.updateUserTenantById(updateUserTenant);

        return true;
    }

    /**
     * param:
     * describe: 查看合同详情
     * create_date:  lxy   2018/8/17  12:02
     **/
    @Override
    public GetPropertyContractResponse getPropertyContract(GetPropertyContractRequest request) {
        PropertyContract propertyContract = propertyContractMapper.selectByPrimaryKey(request.getPropertyContractId());

        if (null == propertyContract) {
            return null;
        }

        GetPropertyContractDetailListRequest getPropertyContractDetailListRequest = new GetPropertyContractDetailListRequest();
        getPropertyContractDetailListRequest.setPropertyContractId(request.getPropertyContractId());
        // 查看合同的物业列表
        List<PropertyContractDetail> list = propertyContractDetailMapperService.getPropertyContractDetailList(getPropertyContractDetailListRequest);

        // 查看合同的商户详情
        GetPropertyContractResponse response = new GetPropertyContractResponse();

        response.setPropertyContract(propertyContract);
        response.setPropertyContractDetailList(list);

        if (null != list && !list.isEmpty()) {
            response.setTerminationRemark(list.get(0).getTerminationRemark());
            response.setTerminationMessage(list.get(0).getTerminationMessage());
            response.setTerminationTime(list.get(0).getTerminationTime());
        }

        UserTenantPack userTenantPack = new UserTenantPack();

        UserTenant userTenantById = new UserTenant();
        userTenantById.setId(propertyContract.getTenantId());

        UserTenant userTenant = userTenantService.selectUserTenant(userTenantById);
        if (null != userTenant) {

            userTenantPack.setContactPhotoBack(userTenant.getContactPhotoBack());
            userTenantPack.setContactPhotoFace(userTenant.getContactPhotoFace());
            userTenantPack.setCorporatePhotoBack(userTenant.getCorporatePhotoBack());
            userTenantPack.setCorporatePhotoFace(userTenant.getCorporatePhotoFace());
            userTenantPack.setTenantName(userTenant.getTenantName());
            userTenantPack.setTenantType(userTenant.getTenantType());
            userTenantPack.setContactName(userTenant.getContactName());
            userTenantPack.setContactMobile(userTenant.getContactMobile());
            userTenantPack.setContactIdCard(userTenant.getContactIdCard());
            userTenantPack.setLicenceNo(userTenant.getLicenceNo());
            userTenantPack.setCorporateName(userTenant.getCorporateName());
            userTenantPack.setCorporateIdCard(userTenant.getCorporateIdCard());
            userTenantPack.setCorporatePhone(userTenant.getCorporatePhone());
            userTenantPack.setShopArea(userTenant.getTenantArea());
            userTenantPack.setShopName(userTenant.getTenantShopName());
            userTenantPack.setCopyBusinessLicense(userTenant.getCopyBusinessLicense());
            userTenantPack.setCateringImage(userTenant.getCateringImage());
            userTenantPack.setHealthImage(userTenant.getHealthImage());

        }

        response.setUserTenantPack(userTenantPack);

        return response;
    }


    /**
     * param:
     * describe: 根据商户ID 查询正常订购物业信息
     * create_date:  lxy   2018/8/22  15:44
     **/
    @Override
    public PageInfo<PropertyContractDetail> getPropertyContractDetailList(GetPropertyContractDetailListRequest request) {
        PageHelper.startPage(request.getCurPage(), request.getPageSize());

        request.setPropertyContractDetailStatus(Magic.CONTRACT_STATUS_NORMAL);

        List<PropertyContractDetail> propertyContractDetailList = propertyContractDetailMapperService.getPropertyContractDetailList(request);

        PageInfo<PropertyContractDetail> pageInfo = new PageInfo<>(propertyContractDetailList);

        return pageInfo;
    }

    /**
     * param:
     * describe: 合同到期或终止定时扫描
     * create_date:  lxy   2018/8/23  15:40
     **/
    @Override
    public void endPropertyContract() throws Exception {
        propertyContractDetailMapperService.endPropertyContract();
    }


}
