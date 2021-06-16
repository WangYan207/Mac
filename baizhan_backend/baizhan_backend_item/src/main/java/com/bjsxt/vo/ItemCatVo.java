package com.bjsxt.vo;

import com.bjsxt.pojo.TbItemCat;

public class ItemCatVo extends TbItemCat {
    //推倒属性，根据其他属性，推导得出计算结果
    public boolean getLeaf(){
        return !getIsParent();
    }
    public void setLeaf(Boolean leaf){}
}
