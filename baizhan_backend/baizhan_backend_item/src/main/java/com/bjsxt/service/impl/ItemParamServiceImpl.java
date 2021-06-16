package com.bjsxt.service.impl;

import com.bjsxt.api.item.ItemParamServiceAPI;
import com.bjsxt.commons.exception.DaoException;
import com.bjsxt.commons.pojo.BaizhanResult;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.service.ItemParamService;
import com.bjsxt.utils.IDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private ItemParamServiceAPI itemParamServiceAPI;

    @Override
    public BaizhanResult selectItemParamByItemCatId(Long itemCatId) {
        try {
            TbItemParam tbItemParam = itemParamServiceAPI.selectParamItemByItemCatId(itemCatId);
            return BaizhanResult.ok(tbItemParam);

        }catch (DaoException e){
            return BaizhanResult.error("oooooooo,完犊子");
        }
    }

    @Override
    public BaizhanResult deleteItemParamById(Long id) {
        try{
          itemParamServiceAPI.deleteItemParamById(id);
                return BaizhanResult.ok();
        }catch(DaoException e){
            return BaizhanResult.error("服务器忙，请稍后再试");
        }
    }

    @Override
    public BaizhanResult insertItemParam(TbItemParam tbItemParam) {
        try {
            Date now = new Date();
            tbItemParam.setId(IDUtils.genItemId());
            tbItemParam.setCreated(now);
            tbItemParam.setUpdated(now);
            itemParamServiceAPI.insertItemParam(tbItemParam);
        }catch (DaoException e ){
            return BaizhanResult.error("服务器忙,请稍后再试");
        }
        return BaizhanResult.ok();
    }

    @Override
    public BaizhanResult selectHaveItemParamByItemCatId(Long itemCatId) {
        TbItemParam tbItemParam = itemParamServiceAPI.selectParamItemByItemCatId(itemCatId);
        if(tbItemParam == null){
            return BaizhanResult.ok();
        }
        return BaizhanResult.error("该品类已添加过规格参数");
    }

    @Override
    public BaizhanResult selectItemParamAll() {
        try {
            List<TbItemParam> tbItemParams = itemParamServiceAPI.selectItemParamAll();
            return BaizhanResult.ok(tbItemParams);
        }catch (DaoException e) {
        log.error("调用com.bjsxt.api.item.ItemParamServiceAPI#selectItemParamAll数据库访问异常，异常内容是"+e.getMessage());
        return BaizhanResult.error("服务器忙，请稍后重试");
        }
    }
}
