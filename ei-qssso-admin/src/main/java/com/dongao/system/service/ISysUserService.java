package com.dongao.system.service;


import com.dongao.system.domain.SysUser;

import java.util.List;

/**
 * 用户 服务层
 * 
 * @author dongao
 * @date 2022-01-12
 */
public interface ISysUserService 
{
	/**
     * 查询用户信息
     * 
     * @param userId 用户ID
     * @return 用户信息
     */
	public SysUser selectSysUserById(Long userId);
	
	/**
     * 查询用户列表
     * 
     * @param sysUser 用户信息
     * @return 用户集合
     */
	public List<SysUser> selectSysUserList(SysUser sysUser);
	
	/**
     * 新增用户
     * 
     * @param sysUser 用户信息
     * @return 结果
     */
	public int insertSysUser(SysUser sysUser);
	
	/**
     * 修改用户
     * 
     * @param sysUser 用户信息
     * @return 结果
     */
	public int updateSysUser(SysUser sysUser);
		
	/**
     * 删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteSysUserByIds(String ids);

	/**
	 * 根据用户名获取用户信息
	 * @param userName
	 * @return
	 */
    SysUser selectUserByLoginName(String userName);
}
