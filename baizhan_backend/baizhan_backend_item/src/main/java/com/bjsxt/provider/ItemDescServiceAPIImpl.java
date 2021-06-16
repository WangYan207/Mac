package com.bjsxt.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bjsxt.api.item.ItemDescServiceAPI;
import com.bjsxt.commons.exception.DaoException;
import com.bjsxt.mapper.TbItemDescMapper;
import com.bjsxt.pojo.TbItemDesc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.QueryEval;

@DubboService
public class ItemDescServiceAPIImpl implements ItemDescServiceAPI {
    @Autowired
    TbItemDescMapper tbItemDescMapper;

    @Override
    public TbItemDesc getItemDescById(Long id) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("item_id",id);
            TbItemDesc tbItemDesc = tbItemDescMapper.selectOne(queryWrapper);
            if(tbItemDesc != null && !tbItemDesc.equals("")){
                return tbItemDesc;
            }
            throw new DaoException("商品图文详细信息查询失败");
        }catch (DaoException e){
            throw new DaoException("商品图文详细信息查询失败");
        }
    }
}
