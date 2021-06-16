package com.bjsxt.service.impl;

import com.bjsxt.api.item.ItemCatServiceAPI;
import com.bjsxt.commons.pojo.BaizhanResult;
import com.bjsxt.pojo.TbItemCat;
import com.bjsxt.service.ItemCatService;
import com.bjsxt.vo.ItemCatVo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台商品子系统，商品类型的服务实现
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired ItemCatServiceAPI itemCatServiceAPI;
    @Override
    public BaizhanResult getItemCategoryByParentId(Long parentId) {
        List<TbItemCat> itemCategoryByParentId =
                itemCatServiceAPI.getItemCategoryByParentId(parentId);
        List<ItemCatVo> itemCatVos = new ArrayList<>();
        for (TbItemCat tbItemCat : itemCategoryByParentId) {
            ItemCatVo itemCatVo = new ItemCatVo();
            //复制
            BeanUtils.copyProperties(tbItemCat,itemCatVo);
            itemCatVos.add(itemCatVo);
        }
        return BaizhanResult.ok(itemCatVos);
    }
}
