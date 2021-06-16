package com.bjsxt.api.item;

import com.bjsxt.pojo.TbItemParam;

import java.util.List;

public interface ItemParamServiceAPI {
    /**
     * 所有的标准服务，尽量不和任何的业务耦合，从而提高服务的通用性
     * 比如：返回结果是返回结果是list<TbItemPara>，所有需要查询规格参数集合的都可被调用
     * 比如：返回结果是Result，只有需要返回对应对象的才可以调用
     */
    int deleteItemParamById(Long id);

    int insertItemParam(TbItemParam tbItemParam);

    TbItemParam selectParamItemByItemCatId(Long itemCatId);

    List<TbItemParam> selectItemParamAll();
}
