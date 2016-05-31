package org.ibase4j.provider.sys;

import java.util.Map;

import org.ibase4j.core.support.dubbo.BaseProviderImpl;
import org.ibase4j.core.support.dubbo.spring.annotation.DubboService;
import org.ibase4j.mybatis.generator.dao.SysPermissionMapper;
import org.ibase4j.mybatis.generator.model.SysPermission;
import org.ibase4j.mybatis.sys.dao.SysPermissionExpandMapper;
import org.ibase4j.provider.sys.SysPermissionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.github.pagehelper.PageInfo;

/**
 * URL权限管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:49
 */
@DubboService(interfaceClass = SysPermissionProvider.class)
@CacheConfig(cacheNames = "sysPermission")
public class SysPermissionProviderImpl extends BaseProviderImpl<SysPermission> implements SysPermissionProvider {
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Autowired
	private SysPermissionExpandMapper sysPermissionExpandMapper;

	@Cacheable
	public boolean doCheckPermissionByUserId(Integer userId, String url) {
		return sysPermissionExpandMapper.getPermissionByUserId(userId, url) > 0;
	}

	@Cacheable
	public SysPermission queryById(Integer id) {
		return sysPermissionMapper.selectByPrimaryKey(id);
	}

	public SysPermission update(SysPermission record) {
		if (record.getId() == null) {
			sysPermissionMapper.insert(record);
		} else {
			sysPermissionMapper.updateByPrimaryKey(record);
		}
		return record;
	}

	public void delete(Integer id) {
		sysPermissionMapper.deleteByPrimaryKey(id);
	}

	public PageInfo<SysPermission> query(Map<String, Object> params) {
		return null;
	}
}