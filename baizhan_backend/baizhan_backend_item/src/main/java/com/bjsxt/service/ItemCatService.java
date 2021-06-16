package com.bjsxt.service;

import com.bjsxt.commons.pojo.BaizhanResult;

public interface ItemCatService {
    BaizhanResult getItemCategoryByParentId(Long parentId);
}
