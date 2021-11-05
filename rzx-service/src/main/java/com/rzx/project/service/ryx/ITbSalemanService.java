package com.rzx.project.service.ryx;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rzx.project.model.domain.TbSaleman;

/**
 * 任意行用户Service接口
 *
 * @author zy
 * @date 2021-11-02
 */
public interface ITbSalemanService extends IService<TbSaleman> {

    /**
     * 查询任意行用户
     *
     * @param salemanId 任意行用户ID
     * @return 任意行用户
     */
    TbSaleman selectTbSalemanById(String salemanId);

    /**
     * 查询任意行用户列表
     *
     * @param tbSaleman 任意行用户
     * @return 任意行用户集合
     */
    List<TbSaleman> selectTbSalemanList(TbSaleman tbSaleman);

    /**
     * 新增任意行用户
     *
     * @param tbSaleman 任意行用户
     * @return 结果
     */
    int insertTbSaleman(TbSaleman tbSaleman);

    /**
     * 修改任意行用户
     *
     * @param tbSaleman 任意行用户
     * @return 结果
     */
    int updateTbSaleman(TbSaleman tbSaleman);

    /**
     * 批量删除任意行用户
     *
     * @param salemanIds 需要删除的任意行用户ID
     * @return 结果
     */
    int deleteTbSalemanByIds(String[] salemanIds);

    /**
     * 删除任意行用户信息
     *
     * @param salemanId 任意行用户ID
     * @return 结果
     */
    int deleteTbSalemanById(String salemanId);
}
