package com.bjsxt.controller;

import com.bjsxt.commons.pojo.BaizhanResult;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.service.ItemParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ItemParamController {
    /**
     * 规格参数查询
     *      访问的数据库表表格 tb_item_param
     *      查询逻辑 全表查询
     *
     * @return
     * 成功
     * {
     * 	"status": 200,
     * 	"msg": "OK",
     * 	"data": [{需要现实的数据集合}]
     * }
     * 	失败 status 400
     */

    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/backend/itemParam/selectItemParamByItemCatId/{itemCatId}")
    public BaizhanResult selectItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId){
        BaizhanResult baizhanResult = itemParamService.selectItemParamByItemCatId(itemCatId);
        return baizhanResult;
    }

    @RequestMapping("/backend/itemParam/deleteItemParamById")
    public BaizhanResult deleteItemParamById(Long id){
        BaizhanResult baizhanResult = itemParamService.deleteItemParamById(id);
        return baizhanResult;
    }

    @RequestMapping("/backend/itemParam/insertItemParam")
    public BaizhanResult insertItemParam(TbItemParam tbItemParam){
        BaizhanResult baizhanResult = itemParamService.insertItemParam(tbItemParam);
        return baizhanResult;
    }

    @RequestMapping("/backend/itemParam/selectHaveParam")
    public BaizhanResult selectHaveParam(Long itemCatId){
        BaizhanResult baizhanResult = itemParamService.selectHaveItemParamByItemCatId(itemCatId);
        return baizhanResult;
    }

    @RequestMapping("/backend/itemParam/selectItemParamAll")
    public BaizhanResult selectItemParamAll(){
        log.info("后台商品子系统，规格参数查询");
        return itemParamService.selectItemParamAll();
    }

}
