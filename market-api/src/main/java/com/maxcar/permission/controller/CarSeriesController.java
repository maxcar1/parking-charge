package com.maxcar.permission.controller;

import com.maxcar.base.model.ChooseInfoRequest;
import com.maxcar.base.pojo.CarBrand;
import com.maxcar.base.pojo.CarSeries;
import com.maxcar.base.pojo.InterfaceResult;
import com.maxcar.base.service.DaSouCheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地系统供页面搜索使用
 * yangsj
 */
@RestController
@RequestMapping("/api/brand")
public class CarSeriesController {
    @Autowired
    DaSouCheService daSouCheService;
    /**
     * maxcar系统需求，自适应接口
     */
    @GetMapping("/choose")
    public Object hierarchyInfo(){
        InterfaceResult result = new InterfaceResult();
        List<ChooseInfoRequest> request = new ArrayList<>();
        List<CarBrand> carBrandList = daSouCheService.getAllBrand();
        for(CarBrand carBrand:carBrandList){
            ChooseInfoRequest chooseInfoRequest = new ChooseInfoRequest();
            chooseInfoRequest.setLabel(carBrand.getBrandName());
            chooseInfoRequest.setValue(carBrand.getBrandCode());
            List<CarSeries> carSeriesList = daSouCheService.getAllSeries(carBrand.getId());
            List<ChooseInfoRequest> chooseList = new ArrayList<>();
            for(CarSeries carSeries:carSeriesList){
                ChooseInfoRequest choose = new ChooseInfoRequest();
                choose.setLabel(carSeries.getSeriesName());
                choose.setValue(carSeries.getSeriesCode());
                chooseList.add(choose);
                //三层遍历所有车型，本地系统如果需要，放行下方注释即可
               /* List<CarModel> carModelList = daSouCheService.getAllModel(carSeries.getId());
                List<ChooseInfoRequest> chooseResultList = new ArrayList<>();
                for(CarModel carModel:carModelList){
                    ChooseInfoRequest chooseResult = new ChooseInfoRequest();
                    chooseResult.setValue(carModel.getModelCode());
                    chooseResult.setLabel(carModel.getModelName());
                    chooseResultList.add(chooseResult);
                };
                choose.setChildren(chooseResultList);*/
            };
            chooseInfoRequest.setChildren(chooseList);
            request.add(chooseInfoRequest);
        };
        result.InterfaceResult200(request);
        return result;
    }
}
