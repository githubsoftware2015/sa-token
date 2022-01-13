package com.dongao.system.service.impl;

import com.common.core.text.Convert;
import com.dongao.system.domain.SysUser;
import com.dongao.system.mapper.SysUserMapper;
import com.dongao.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 服务层实现
 * 
 * @author dongao
 * @date 2022-01-12
 */
@Service
public class SysUserServiceImpl implements ISysUserService
{
	@Autowired
	private SysUserMapper sysUserMapper;

	/**
     * 查询用户信息
     * 
     * @param userId 用户ID
     * @return 用户信息
     */
    @Override
	public SysUser selectSysUserById(Long userId)
	{
	    return sysUserMapper.selectSysUserById(userId);
	}
	
	/**
     * 查询用户列表
     * 
     * @param sysUser 用户信息
     * @return 用户集合
     */
	@Override
	public List<SysUser> selectSysUserList(SysUser sysUser)
	{
	    return sysUserMapper.selectSysUserList(sysUser);
	}
	
    /**
     * 新增用户
     * 
     * @param sysUser 用户信息
     * @return 结果
     */
	@Override
	public int insertSysUser(SysUser sysUser)
	{
	    return sysUserMapper.insertSysUser(sysUser);
	}
	
	/**
     * 修改用户
     * 
     * @param sysUser 用户信息
     * @return 结果
     */
	@Override
	public int updateSysUser(SysUser sysUser)
	{
	    return sysUserMapper.updateSysUser(sysUser);
	}

	/**
     * 删除用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSysUserByIds(String ids)
	{
		return sysUserMapper.deleteSysUserByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据用户名获取用户信息
	 * @param userName
	 * @return
	 */
	@Override
	public SysUser selectUserByLoginName(String userName) {
		return sysUserMapper.selectUserByLoginName(userName);
	}

}
