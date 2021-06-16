package com.bjsxt.api.item;

import com.bjsxt.pojo.TbItemCat;

import java.util.List;

public interface ItemCatServiceAPI {
    /**
     * 根据父节点主键，查询子节点集合
     * @param parentId 父节点主键
     * @return
     */
    List<TbItemCat> getItemCategoryByParentId(Long parentId);

    /**
     * 根据商品id查询商品所属种类
     * @param Cid
     * @return
     */
    TbItemCat getItemCategoryByCid(Long Cid);

}
