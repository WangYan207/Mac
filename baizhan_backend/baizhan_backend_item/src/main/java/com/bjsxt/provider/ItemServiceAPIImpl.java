package com.bjsxt.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bjsxt.api.item.ItemServiceAPI;
import com.bjsxt.commons.exception.DaoException;
import com.bjsxt.commons.pojo.BaizhanResult;
import com.bjsxt.mapper.TbItemDescMapper;
import com.bjsxt.mapper.TbItemMapper;
import com.bjsxt.mapper.TbItemParamItemMapper;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.IDUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@DubboService
public class ItemServiceAPIImpl implements ItemServiceAPI {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    TbItemParamItemMapper tbItemParamItemMapper;
    @Autowired
    TbItemDescMapper tbItemDescMapper;

    @Override
    public TbItem getItemById(Long id) {
        try{
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("id",id);
            TbItem tbItem = tbItemMapper.selectOne(queryWrapper);
            if(tbItem != null && !tbItem.equals("")){
                return tbItem;
            }
            throw new DaoException("查询商品失败");
        }catch (DaoException e){
            throw new DaoException("查询商品失败");
        }
    }

    @Override
    @Transactional(rollbackFor = {DaoException.class})
    public int changeItemStatus(Long id,Integer status,Date now) {
        try {
//            QueryWrapper queryWrapper = new QueryWrapper();
//            queryWrapper.eq("id", id);
            TbItem tbItem = new TbItem();
            tbItem.setUpdated(now);
            tbItem.setStatus(status);
            tbItem.setId(id);
            int deleteResult = tbItemMapper.updateById(tbItem);
            if(deleteResult !=1){
                throw new DaoException("删除失败，数据异常");
            }
            return deleteResult;
        }catch (DaoException e){
            throw new DaoException("删除失败");
        }
    }

    @Override
    public int insertTbItem(TbItem tbItem, TbItemParamItem tbItemParamItem, TbItemDesc tbItemDesc) {

        try {
            System.out.println("不能太离谱吧:tbItem.getImage() = " + tbItem.getImage());
            int result = tbItemMapper.insert(tbItem);
            if(result != 1){
                throw new DaoException("新增Item时异常");
            }
            result = tbItemParamItemMapper.insert(tbItemParamItem);
            if(result != 1){
                throw new DaoException("新增ItemParamItem时异常");
            }

            result = tbItemDescMapper.insert(tbItemDesc);
            if(result != 1){
                throw new DaoException("新增ItemDesc时发生异常");
            }
            return 1;
        }catch(DaoException e){
            throw new DaoException("插入失败");
        }
    }

    @Override
    public List<TbItem> selectTbItemAllByPage(Integer page, Integer rows) {
        IPage<TbItem> ipage = new Page<>(page,rows);
        IPage<TbItem> tbItemIPage = tbItemMapper.selectPage(ipage, new QueryWrapper<TbItem>());
        //分页逻辑获取结果集
        List<TbItem> result = tbItemIPage.getRecords();
        return result;
    }

    @Override
    public long itemsCount() {
        int counts = tbItemMapper.selectCount(new QueryWrapper<TbItem>());
        return counts;
    }
}
