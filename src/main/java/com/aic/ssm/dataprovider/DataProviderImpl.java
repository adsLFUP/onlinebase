package com.aic.ssm.dataprovider;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.aic.ssm.entity.IotMsg;
import com.aic.ssm.util.MyBatisUtil;
import com.aic.ssm.util.Util;
import com.aic.ssm.util.waveformDispose;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;


/**
 * @author          liufei
 *
 * @Date            
 *
 * @Descirption     在线系统sdk
 * 
 */
public class DataProviderImpl implements DataProvider{
	SqlSession openSession = MyBatisUtil.getSession();
	
	/**查询在线系统实时数据
	 * @param guid 
	 * @return
	 * @throws Base64DecodingException 
	 */
	public IotMsg queryLatestData(String channelglobalindex,String guid) throws Base64DecodingException {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		if(Util.isNullOrEmpty(channelglobalindex))
			channelglobalindex = "1";
		Calendar currentCalendar = Calendar.getInstance();
		int currentYear = currentCalendar.get(Calendar.YEAR);
		int currentMonth = currentCalendar.get(Calendar.MONTH)+1;
		
		Map dataMap = new HashMap();
		if(currentMonth<10){
			dataMap.put("table", "VInfoTable_E"+currentYear+"0"+currentMonth);
		}else{
			dataMap.put("table", "VInfoTable_E"+currentYear+currentMonth);
		}
		dataMap.put("channelglobalindex", channelglobalindex);
		
		Map latestData = openSession.selectOne("Online.queryLatestData", dataMap);
		int samplepoint = (int) latestData.get("samplepoint");
		
		byte[] floatdecode = Base64.decode(latestData.get("vdatatext")+"");
		double[] InputSignalArray=new double[floatdecode.length/4];
		for (int i = 0,j = 0; i < floatdecode.length; i+=4,j++) {
			 byte[] b = new byte[4];
			 b[0]= floatdecode[i];
			 b[1]= floatdecode[i+1];
			 b[2]= floatdecode[i+2];
			 b[3]= floatdecode[i+3];
			 float fvalue=byte2float(b, 0);
			 InputSignalArray[j] = fvalue;
		}
		double RMS = waveformDispose.GetRMS( InputSignalArray, samplepoint);//有效值
		double Min = waveformDispose.GetMin( InputSignalArray, samplepoint);//最小值
		double Max = waveformDispose.GetMax( InputSignalArray, samplepoint);//最大值
		double Slope = waveformDispose.GetSlope( InputSignalArray, samplepoint);//斜度
		double Kurtosis = waveformDispose.GetKurtosis( InputSignalArray, samplepoint);//峭度
		double WaveFactor = waveformDispose.GetWaveFactor( InputSignalArray, samplepoint); //波形指标
		double PeakFactor = waveformDispose.GetPeakFactor( InputSignalArray, samplepoint);//峰值指标
		double PulseFactor = waveformDispose.GetPulseFactor( InputSignalArray, samplepoint);//脉冲指标
		double ClearanceFactor = waveformDispose.GetClearanceFactor( InputSignalArray, samplepoint);//裕度指标
		double KurtosisFactor = waveformDispose.GetKurtosisFactor( InputSignalArray, samplepoint);//峭度指标
		
		IotMsg IotMsg = new IotMsg();
		IotMsg.setRMSV(RMS);
		IotMsg.setClearnanceFactorV(ClearanceFactor);
		IotMsg.setKurtosisFactorV(KurtosisFactor);
		IotMsg.setKurtosisV(Kurtosis);
		IotMsg.setPeakFactorV(PeakFactor);
		IotMsg.setPeakV(Max);//此处峰值为最大值
		IotMsg.setPPV(Max-Min);
		IotMsg.setPulseFactorV(PulseFactor);
		IotMsg.setSlopeV(Slope);
		IotMsg.setWaveFactorV(WaveFactor);
		
		return IotMsg;
	}
	
	//这个函数将byte转换成float
		public static float byte2float(byte[] b, int index) {  
		    int l;                                           
		    l = b[index + 0];                                
		    l &= 0xff;                                       
		    l |= ((long) b[index + 1] << 8);                 
		    l &= 0xffff;                                     
		    l |= ((long) b[index + 2] << 16);                
		    l &= 0xffffff;                                   
		    l |= ((long) b[index + 3] << 24);                
		    return Float.intBitsToFloat(l);                  
		}
}
