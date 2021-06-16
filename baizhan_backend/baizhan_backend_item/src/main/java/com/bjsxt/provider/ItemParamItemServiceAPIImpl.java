package com.bjsxt.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bjsxt.api.item.ItemParamItemServiceAPI;
import com.bjsxt.commons.exception.DaoException;
import com.bjsxt.mapper.TbItemParamItemMapper;
import com.bjsxt.pojo.TbItemParamItem;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class ItemParamItemServiceAPIImpl implements ItemParamItemServiceAPI {

    @Autowired
    TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public TbItemParamItem getItemParamItemById(Long id) {
        try{
            QueryWrapper qw = new QueryWrapper();
            qw.eq("item_id",id);//商品ID
            TbItemParamItem tbItemParamItem = tbItemParamItemMapper.selectOne(qw);
            if(tbItemParamItem != null && !tbItemParamItem.equals("")){
                return tbItemParamItem;
            }
            throw new DaoException("查询商品参数详细信息失败");

        }catch (DaoException e){
            throw new DaoException("查询商品参数详细信息失败");
        }
    }
}
