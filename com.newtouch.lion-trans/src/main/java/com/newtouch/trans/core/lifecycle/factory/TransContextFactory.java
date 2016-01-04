/*
 * Copyright 2012, NEWTOUCH Co., Ltd. All right reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF NEWTOUCH CO., LTD.
 * THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF NEWTOUCH CO., LTD.
 */
package com.newtouch.trans.core.lifecycle.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.newtouch.trans.core.exception.ApplicationContextException;
import com.newtouch.trans.core.lifecycle.processunit.ProcessUnit;
import com.newtouch.trans.core.model.ApplicationErrorCode;
import com.newtouch.trans.core.model.Request;
import com.newtouch.trans.core.model.TransContext;

/**
 * 交易上下文工厂类，用于创建交易上下文
 * 
 * @author dongfeng.zhang
 * @author 阳葵 修改获取PU的方式，改为启动的时候一次性初始化所有PU与transCode的关系
 * @version 1.0
 */
@Component
public class TransContextFactory implements ApplicationContextAware,InitializingBean {
	private ApplicationContext springContext = null;
	private Map<String,List<ProcessUnit>> puCache = new HashMap<String,List<ProcessUnit>>();
	
	/**
	 * 创建交易上下文
	 * 
	 * @param request
	 *            请求包
	 * 
	 * @return 交易上下文
	 */
	public TransContext createTransContext(Request request) {
		String transCode = request.getTransCode();
		List<ProcessUnit> pus = this.puCache.get(transCode);
		if(pus==null||pus.isEmpty()){
			throw new ApplicationContextException(ApplicationErrorCode.APP_CONTEXT_EXCEPTION,"transCode:"+transCode+",未定义");
		}
		
		TransContext transContext = new TransContext(request, pus.toArray(new ProcessUnit[0]));
		return transContext;
	}


	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.springContext = context;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, ProcessUnit> allProcessUnits = springContext.getBeansOfType(ProcessUnit.class);
		for (ProcessUnit pu : allProcessUnits.values()) {
			String transCode = pu.getTransCode();
			List<ProcessUnit> pus = (List<ProcessUnit>) this.puCache.get(transCode);
			if(pus==null){
				pus =  new ArrayList<ProcessUnit>();
			}
			pus.add(pu);
			this.puCache.put(transCode, pus);
		}
		
		for (List<ProcessUnit> list : puCache.values()) {
			Collections.sort(list, new Comparator<ProcessUnit>() {
				public int compare(ProcessUnit processUnit1, ProcessUnit processUnit2) {
					int thisVal = processUnit1.getOrder();
					int anotherVal = processUnit2.getOrder();
					return (thisVal < anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1));
				}
			});
		}
		
	}

}
