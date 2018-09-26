package com.aic.ssm.dataprovider;

import java.util.Map;

import com.aic.ssm.entity.IotMsg;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;


/**
 * @author          liufei
 *
 * @Date            2018年7月11日-下午4:13:40
 *
 * @Descirption     AIC在线系统-数据查询接口
 * 
 *
 */
public interface DataProvider {
	
	/**
	 * @Description 查询最新数据
	 * @param guid
	 * @return
	 * @throws Base64DecodingException 
	 * @Date 2018年8月28日
	 */
	public IotMsg queryLatestData(String channelglobalindex,String guid) throws Base64DecodingException;

}
