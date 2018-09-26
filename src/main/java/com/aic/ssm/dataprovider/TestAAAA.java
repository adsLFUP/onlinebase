package com.aic.ssm.dataprovider;

import com.aic.ssm.entity.IotMsg;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

public class TestAAAA {
	public static void main(String[] args) throws Base64DecodingException {
		DataProvider aaa = new DataProviderImpl();
		IotMsg iotMsgInfo = aaa.queryLatestData("4", "");
	}
}
