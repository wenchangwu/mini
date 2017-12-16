package com.lakala.mini.server.core.manager;

import com.lakala.mini.server.core.domain.PsamInfoHis;

/**
 * PSAM历史信息模块服务接口
 * @author QW
 * 2010-12-3 下午12:45:02
 */
public interface IPsamInfoHisManager {
	PsamInfoHis getPsamInfoHis(Long id);
	
}
