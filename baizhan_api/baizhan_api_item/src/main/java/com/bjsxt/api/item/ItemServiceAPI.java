package com.bjsxt.api.item;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.pojo.TbItemParamItem;

import java.util.Date;
import java.util.List;

/**
 * 商品服务标准接口
 */
public interface ItemServiceAPI {
    /**
     * 根据商品id查询商品信息
     * @param id
     * @return
     */
    TbItem getItemById(Long id);

    /**
     * 根据商品id更改商品状态
     * @param id
     * @return
     */
    int changeItemStatus(Long id, Integer status, Date now);

    /**
     *
     * @param tbItem
     * @return
     */
    int insertTbItem(TbItem tbItem, TbItemParamItem tbItemParamItem, TbItemDesc tbItemDesc);
    /**
     * 分页查询商品
     * @param page 第几页
     * @param rows 多少条
     * @return
     */
    List<TbItem> selectTbItemAllByPage(Integer page,Integer rows);

    /**
     * 商品数量总条数
     * @return
     */
    long itemsCount();

}
