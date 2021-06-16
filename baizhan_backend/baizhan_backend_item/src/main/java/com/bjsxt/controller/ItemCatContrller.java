package com.bjsxt.controller;

import com.bjsxt.commons.pojo.BaizhanResult;
import com.bjsxt.service.ItemCatService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemCatContrller {
    @Autowired
    ItemCatService itemCatService;
    /**
     * 根据父类目查询所有子类目
     * @param id 父商品类型主键
     * @return
     */
    @RequestMapping("/backend/itemCategory/selectItemCategoryByParentId")
    public BaizhanResult selectItemCategoryByParentId(@RequestParam(name = "id",defaultValue = "0") Long id){
        BaizhanResult itemCategoryByParentId = itemCatService.getItemCategoryByParentId(id);
        return itemCategoryByParentId;
    }
}
