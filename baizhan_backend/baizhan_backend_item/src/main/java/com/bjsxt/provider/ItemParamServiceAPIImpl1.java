package com.bjsxt.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bjsxt.api.item.ItemParamServiceAPI;
import com.bjsxt.commons.exception.DaoException;
import com.bjsxt.mapper.TbItemParamMapper;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.IDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 统一的服务实现，可以对外提供服务。
 * 当前系统使用的时候，本地注入用autowired，其他系统使用的时候用DubboReference
 */
@DubboService
@Slf4j
public class ItemParamServiceAPIImpl1 implements ItemParamServiceAPI {


    /**
     * 注入规格参数数据访问接口的代理实现
     */
    @Autowired
    private TbItemParamMapper itemParamMapper;

    /**
     * 根据ID删除规格参数
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = {DaoException.class})
    public int deleteItemParamById(Long id) {
        try{
            QueryWrapper qw = new QueryWrapper();
            qw.eq("id",id);
            int deleteResult = itemParamMapper.delete(qw);
            if(deleteResult != 1){
                throw new DaoException("删除失败");
            }
            return 1;
        }catch (DaoException e){
                throw new DaoException("后台管理系统-删除规格参数失败");
        }
    }

    /**
     * 新增规格参数按钮
     * @param tbItemParam
     * @return
     */
    @Override
    @Transactional(rollbackFor = {DaoException.class})
    public int insertItemParam(TbItemParam tbItemParam) {
        try {
            int insertResult = itemParamMapper.insert(tbItemParam);
            if(insertResult == 1){
                return insertResult;
            }else {
                throw new DaoException("添加失败");
            }

        }catch(DaoException e){
            throw new DaoException("后台管理系统-插入规格参数失败");
        }

    }

    /**
     * 根据产品种类id查看规格参数
     * @param itemCatId
     * @return
     */
    @Override
    public TbItemParam selectParamItemByItemCatId(Long itemCatId) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("item_cat_id",itemCatId);
        TbItemParam tbItemParam = itemParamMapper.selectOne(qw);
        return tbItemParam;
    }

    /**
     * 查询所有的规格参数
     * 商业项目中，要有严格的异常处理逻辑，不能有任何不受控制的异常，传递到顶层调用者
     * 在WEB项目中，任何传递到Controller中的异常，都受控制
     * @return
     */
    @Override
    public List<TbItemParam> selectItemParamAll() {
        try {


            // 根据参数条件，查询多数据
            // 如果无条件查询全部数据，直接提供一个参数类型的对象
            QueryWrapper<TbItemParam> queryWrapper = new QueryWrapper<>();
            List<TbItemParam> resultList =
                    itemParamMapper.selectList(queryWrapper);
            return resultList;
        }catch (Exception e){
            //异常处理
            log.error("查询所有规格参数-数据库访问异常");
            throw new DaoException("查询所有规格参数-数据库访问异常");

        }
    }
}
