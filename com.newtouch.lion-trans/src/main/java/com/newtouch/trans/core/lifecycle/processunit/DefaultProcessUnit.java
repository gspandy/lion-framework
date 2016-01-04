package com.newtouch.trans.core.lifecycle.processunit;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.newtouch.trans.core.exception.TransException;
import com.newtouch.trans.core.model.ApplicationErrorCode;
import com.newtouch.trans.core.model.TransContext;

/**
 * 默认参与流程，子类可以继承该类实现具体的业务功能
 * 
 * @author 阳葵 2014年9月4日 上午11:44:25
 *
 */
public abstract class DefaultProcessUnit<T> implements ProcessUnit {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private int order;

	@Override
	public boolean isResponsibilityOwner(TransContext ctx) {
		return true;
	}

	@Override
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public abstract void doExecute(T reqBody, TransContext ctx);

	public abstract String getClassName();

	@SuppressWarnings("unchecked")
	@Override
	public void execute(TransContext ctx) {

		T reqBody = null;
		boolean hasError = false;
		try {
			reqBody = (T) JSON.parseObject(ctx.getRequest().getRequestJson(),Class.forName(this.getClassName()));
		} catch (ClassNotFoundException e) {
			logger.error("json解析异常", e);
			hasError = true;
		}

		if (hasError) {
			throw new TransException(ApplicationErrorCode.REQUEST_ERROR,"JSON格式转换异常");
		}

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> validRetval = validator.validate(reqBody);
		StringBuffer sb = new StringBuffer();
		if (!validRetval.isEmpty()) {// 校验失败
			for (ConstraintViolation<T> t : validRetval) {
				sb.append(t.getMessage());
			}
		}
		String checkError = sb.toString();
		if (StringUtils.isNotEmpty(checkError)) {
			checkError = "请求参数格式校验不通过：" + checkError;
			logger.error("交易号：{},{}", this.getTransCode(), checkError);
			throw new TransException(ApplicationErrorCode.REQUEST_ERROR,
					checkError);
		}

		this.doExecute(reqBody, ctx);
	}

}
