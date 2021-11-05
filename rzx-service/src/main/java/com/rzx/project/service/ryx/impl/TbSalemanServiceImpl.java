package com.rzx.project.service.ryx.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rzx.project.model.domain.TbSaleman;
import com.rzx.project.mapper.ryx.TbSalemanMapper;
import com.rzx.project.service.ryx.ITbSalemanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任意行用户Service业务层处理
 *
 * @author zy
 * @date 2021-11-02
 */
@DS("slave_1")
@Service
public class TbSalemanServiceImpl extends ServiceImpl<TbSalemanMapper,TbSaleman> implements ITbSalemanService {
    @Autowired
    private TbSalemanMapper tbSalemanMapper;

    /**
     * 查询任意行用户
     *
     * @param salemanId 任意行用户ID
     * @return 任意行用户
     */
    @Override
    public TbSaleman selectTbSalemanById(String salemanId) {
        return tbSalemanMapper.selectTbSalemanById(salemanId);
    }

    /**
     * 查询任意行用户列表
     *
     * @param tbSaleman 任意行用户
     * @return 任意行用户
     */
    @Override
    public List<TbSaleman> selectTbSalemanList(TbSaleman tbSaleman) {
        return tbSalemanMapper.selectTbSalemanList(tbSaleman);
    }

    /**
     * 新增任意行用户
     *
     * @param tbSaleman 任意行用户
     * @return 结果
     */
    @Override
    public int insertTbSaleman(TbSaleman tbSaleman) {
        return tbSalemanMapper.insert(tbSaleman);
    }

    /**
     * 修改任意行用户
     *
     * @param tbSaleman 任意行用户
     * @return 结果
     */
    @Override
    public int updateTbSaleman(TbSaleman tbSaleman) {
        return tbSalemanMapper.updateTbSaleman(tbSaleman);
    }

    /**
     * 批量删除任意行用户
     *
     * @param salemanIds 需要删除的任意行用户ID
     * @return 结果
     */
    @Override
    public int deleteTbSalemanByIds(String[] salemanIds) {
        return tbSalemanMapper.deleteTbSalemanByIds(salemanIds);
    }

    /**
     * 删除任意行用户信息
     *
     * @param salemanId 任意行用户ID
     * @return 结果
     */
    @Override
    public int deleteTbSalemanById(String salemanId) {
        return tbSalemanMapper.deleteTbSalemanById(salemanId);
    }
}
