package com.bjsxt.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bjsxt.api.item.ItemCatServiceAPI;
import com.bjsxt.commons.exception.DaoException;
import com.bjsxt.mapper.TbItemCatMapper;
import com.bjsxt.pojo.TbItemCat;
import com.mysql.cj.QueryResult;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class ItemCatServiceAPIImpl implements ItemCatServiceAPI {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * 根据类目id查询商品种类
     * @param Cid
     * @return
     */
    @Override
    public TbItemCat getItemCategoryByCid(Long Cid) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("id",Cid);
            TbItemCat tbItemCat = itemCatMapper.selectOne(queryWrapper);
            if(!tbItemCat.equals("")&&tbItemCat != null){
                return tbItemCat;
            }
            throw new DaoException("获取商品种类失败");
        }catch (DaoException e){
            throw new DaoException("获取商品种类失败");
        }
    }

    /**
     * 根据父节点主键，查询子节点集合
     * @param parentId 父节点主键
     * @return
     */
    @Override
    public List<TbItemCat> getItemCategoryByParentId(Long parentId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        //增加查询条件
        queryWrapper.eq("parent_id",parentId);
        List<TbItemCat> resultList = itemCatMapper.selectList(queryWrapper);
        return resultList;
    }
}
