package com.bjsxt.service.impl;

import com.bjsxt.api.item.ItemCatServiceAPI;
import com.bjsxt.api.item.ItemDescServiceAPI;
import com.bjsxt.api.item.ItemParamItemServiceAPI;
import com.bjsxt.api.item.ItemServiceAPI;
import com.bjsxt.commons.exception.DaoException;
import com.bjsxt.commons.pojo.BaizhanResult;
import com.bjsxt.pojo.*;
import com.bjsxt.service.ItemService;
import com.bjsxt.utils.IDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemServiceAPI itemServiceAPI;
    @Autowired
    private ItemParamItemServiceAPI itemParamItemServiceAPI;
    @Autowired
    private ItemCatServiceAPI itemCatServiceAPI;
    @Autowired
    private ItemDescServiceAPI itemDescServiceAPI;

    /**
     * 商品预更新
     * @param id
     * @return
     */
    @Override
    public BaizhanResult preUpdateItemById(Long id) {
        //获取商品 信息
        TbItem tbItem = itemServiceAPI.getItemById(id);
        System.out.println("tbItem.toString() = " + tbItem.toString());
        //获取商品种类 信息 根据种类id
        TbItemCat tbItemCat = itemCatServiceAPI.getItemCategoryByCid(tbItem.getCid());
        //获取商品图文详细 信息
        TbItemDesc itemDesc = itemDescServiceAPI.getItemDescById(id);
        //获取商品详细参数 信息
        TbItemParamItem tbItemParamItem = itemParamItemServiceAPI.getItemParamItemById(id);
        Map date = new HashMap();
        date.put("itemCat",tbItemCat);
        date.put("item",tbItem);
        date.put("itemDesc",itemDesc);
        date.put("itemParamItem",tbItemParamItem);
        return BaizhanResult.ok(date);
    }

    /**
     * 下架操作实现类
     * @param id
     * @return
     */
    @Override
    public BaizhanResult offshelfItemById(Long id) {
        try{
            int result = itemServiceAPI.changeItemStatus(id, 2, new Date());
            if(result == 1){
                return BaizhanResult.ok();
            }
            throw new DaoException("服务器忙，请稍后再试");
        }catch (DaoException e){
            throw new DaoException("服务器忙，请稍后再试");
        }
    }

    /**
     * 上架操作实现类
     * @param id
     * @return
     */
    @Override
    public BaizhanResult onshelfItemById(Long id) {
        try {
            int i = itemServiceAPI.changeItemStatus(id,1,new Date());
            if(i == 1){
                return BaizhanResult.ok();
            }
            return BaizhanResult.error("修改状态失败");
        }catch (DaoException e){
            throw new DaoException("修改状态失败");
        }
    }

    @Override
    public BaizhanResult deleteItemById(Long id) {
        try{
            int i = itemServiceAPI.changeItemStatus(id,3,new Date());
            if(i==1){
                return BaizhanResult.ok();
            }
            return BaizhanResult.error("插入失败");
        }catch (DaoException e){
            throw new DaoException("插入失败");
        }
    }

    @Override
    public BaizhanResult insertTbItem(TbItem tbItem, String itemDesc, String paramData) {
        try {
            //生成id
            Long id = IDUtils.genItemId();
            Date now = new Date();
            tbItem.setId(id);
            tbItem.setStatus(2);
            tbItem.setUpdated(now);
            tbItem.setCreated(now);

            TbItemParamItem tbItemParamItem = new TbItemParamItem();
            tbItemParamItem.setId(IDUtils.genItemId());
            tbItemParamItem.setItemId(id);
            tbItemParamItem.setCreated(now);
            tbItemParamItem.setUpdated(now);
            tbItemParamItem.setParamData(paramData);

            TbItemDesc tbItemDesc = new TbItemDesc();
            tbItemDesc.setItemId(id);
            tbItemDesc.setCreated(now);
            tbItemDesc.setUpdated(now);
            tbItemDesc.setItemDesc(itemDesc);

            int result = itemServiceAPI.insertTbItem(tbItem, tbItemParamItem, tbItemDesc);
            if(result !=1 ){
                return BaizhanResult.error("服务器正忙，请稍后再试");
            }
            return BaizhanResult.ok();
        }catch (DaoException e){
            throw new DaoException("插入失败");
        }

    }

    @Override
    public BaizhanResult selectTbItemAllByPage(Integer page, Integer rows) {
        List<TbItem> result = itemServiceAPI.selectTbItemAllByPage(page, rows);
        Long total = itemServiceAPI.itemsCount();
        Map<String,Object> data = new HashMap<>();
        data.put("result",result);
        data.put("total",total);
        return BaizhanResult.ok(data);
    }
}
