package com.bjsxt.service;

import com.bjsxt.commons.pojo.BaizhanResult;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;

public interface ItemService {
    BaizhanResult selectTbItemAllByPage(Integer page,Integer rows);
    BaizhanResult insertTbItem(TbItem tbItem, String paramData, String itemDesc);
    BaizhanResult deleteItemById(Long id);
    BaizhanResult onshelfItemById(Long id);
    BaizhanResult offshelfItemById(Long id);
    BaizhanResult preUpdateItemById(Long Id);
}
