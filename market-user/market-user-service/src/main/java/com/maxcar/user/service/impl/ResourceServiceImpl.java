package com.maxcar.user.service.impl;

import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.user.dao.ResourceMapper;
import com.maxcar.user.entity.Resource;
import com.maxcar.user.entity.ResourceExample;
import com.maxcar.user.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.List;

@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource,String> implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public BaseDao<Resource, String> getBaseMapper() {
        return resourceMapper;
    }

    @Override
    public String getResourceIdByUrl(String url) {
        ResourceExample example = new ResourceExample();
//        example.createCriteria().andResourceUrlLike("%" + url + "");
//        example.createCriteria().andResourceUrlEqualTo(url);
        List<Resource> resources = resourceMapper.selectByExample(example);
        AntPathMatcher matcher = new AntPathMatcher();
        for(Resource resource:resources) {
            if(matcher.match(resource.getResourceUrl(), url))
                return resource.getResourceId();
        }
        return null;
//        return (resources!=null&&resources.size()>0)?resources.get(0).getResourceId():null;
    }


    public static void main(String[] args){
        AntPathMatcher matcher = new AntPathMatcher();
        String pattern = "/user/menus/**/*";
        System.out.println("pattern:"+pattern);
        System.out.println("/user/menus/false："+matcher.match(pattern,"/user/menus/false/1/1"));
    }
}