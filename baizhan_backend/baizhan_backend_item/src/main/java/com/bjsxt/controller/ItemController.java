package com.bjsxt.controller;

import com.bjsxt.commons.exception.DaoException;
import com.bjsxt.commons.pojo.BaizhanResult;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/backend/item/preUpdateItem")
    public BaizhanResult preUpdateItem(Long id){
        BaizhanResult baizhanResult = itemService.preUpdateItemById(id);
        return baizhanResult;
    }

    @RequestMapping("/backend/item/offshelfItemById")
    public BaizhanResult offshelfItemById(Long id){
        BaizhanResult baizhanResult = itemService.offshelfItemById(id);
        return baizhanResult;
    }
    /**
     * 商品上架
     * @param id
     * @return
     */
    @RequestMapping("/backend/item/onshelfItemById")
    public BaizhanResult onshelfItemById(Long id){
        BaizhanResult baizhanResult = itemService.onshelfItemById(id);
        return baizhanResult;
    }

    @RequestMapping("/backend/item/deleteItemById")
    public  BaizhanResult deleteItemById(Long id){
        BaizhanResult baizhanResult = itemService.deleteItemById(id);
        return baizhanResult;
    }

    @RequestMapping("/backend/item/insertTbItem")
    public BaizhanResult indsertTbItem(TbItem tbitem,String itemDesc,String paramData){
        BaizhanResult baizhanResult = itemService.insertTbItem(tbitem,itemDesc,paramData);
        return baizhanResult;
    }

    @RequestMapping("/backend/item/selectTbItemAllByPage")
    public BaizhanResult selectTbItemAllByPage(@RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "10") Integer rows){
        try {
            BaizhanResult baizhanResult = itemService.selectTbItemAllByPage(page, rows);
            return baizhanResult;
        }catch (DaoException e){
            return BaizhanResult.error(e.getMessage());
        }
    }
}
