package com.bjsxt.service;

import com.bjsxt.commons.pojo.BaizhanResult;
import com.bjsxt.pojo.TbItemParam;

/**
 * 后台系统中item子系统的商品规格参数服务接口
 * 不是服务标准接口，不是api接口
 * 当前接口只给当前系统使用，不给外部提供服务
 */

public interface ItemParamService {
    /**
     * 查询商品规格参数
     * @param itemCatId
     * @return
     */
    BaizhanResult selectItemParamByItemCatId(Long itemCatId);

    /**
     * 根据id删除规格参数
     * @param id
     * @return
     */
    BaizhanResult deleteItemParamById(Long id);
    /**
     * 新增产品参数按钮
     * @param tbItemParam
     * @return
     */
    BaizhanResult insertItemParam(TbItemParam tbItemParam);
    /**
     * 根据产品种类查规格参数
     * @param itemCatId
     * @return
     */
    BaizhanResult selectHaveItemParamByItemCatId(Long itemCatId);
    /**
     * 查询所有规格参数
     * @return
     */
   BaizhanResult selectItemParamAll();

}
