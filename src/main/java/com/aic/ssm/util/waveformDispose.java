package com.aic.ssm.util;


public class waveformDispose {

	//有效值
	public static double GetRMS(double[] InputSignalArray,int SamplePoint)
	{
		double RMS=0;

		if(SamplePoint<=0||Util.isNullOrEmpty(InputSignalArray))
			return 0;
		;
		for(int ii=0;ii<SamplePoint;ii++)
		  RMS+=InputSignalArray[ii] * InputSignalArray[ii];
		;
		 RMS=Math.sqrt(RMS/(double)SamplePoint);
		return RMS;
	}


	//最小值
	public static double GetMin(double[] InputSignalArray,int SamplePoint)
	{
		double MinV=0;
		if(SamplePoint<=0||Util.isNullOrEmpty(InputSignalArray))
			return 0;
		   ;
	    for(int ii=0;ii<SamplePoint;ii++)
		{
	      if(InputSignalArray[ii]<MinV)
		    MinV=InputSignalArray[ii];
		}
		return MinV;
	}

	//最大值
	public static double GetMax(double[] InputSignalArray,int SamplePoint)
	{
			double MaxV=0;

		if(SamplePoint<=0||Util.isNullOrEmpty(InputSignalArray))
			return 0;
		;
	    for(int ii=0;ii<SamplePoint;ii++)
		{
	      if(InputSignalArray[ii]>MaxV)
		    MaxV=InputSignalArray[ii];
		}
		return MaxV;
	}

	//斜度
	public static double GetSlope(double[] InputSignalArray,int SamplePoint)
	{
		double Slope=0;

		if(SamplePoint<=0||Util.isNullOrEmpty(InputSignalArray))
			return 0;
		;
		for(int ii=0;ii<SamplePoint;ii++)
		{
			Slope+=Math.pow(InputSignalArray[ii],3);
		}
		;
		Slope/=SamplePoint;

		return Slope;
	}

	//峭度
	public static double GetKurtosis(double[] InputSignalArray,int SamplePoint)
	{
		double Kurtosis=0;
		if(SamplePoint<=0||Util.isNullOrEmpty(InputSignalArray))
			return 0;
	      ;
		for(int ii=0;ii<SamplePoint;ii++)
		{
			Kurtosis+=Math.pow(InputSignalArray[ii],4);
		};
		Kurtosis/=SamplePoint;

		return Kurtosis;
	}

	  //波形指标
	public static double GetWaveFactor(double[] InputSignalArray,int SamplePoint)
	{
		double WaveFactor=0;
		double RMS=0;
		double FabsMean=0; 

		if(SamplePoint<=0||Util.isNullOrEmpty(InputSignalArray))
		  return 0;
			;

		for(int ii=0;ii<SamplePoint;ii++)
		{
			FabsMean+=Math.abs(InputSignalArray[ii]);
			RMS+=InputSignalArray[ii] * InputSignalArray[ii];
		};
		FabsMean/=SamplePoint;
		RMS=Math.sqrt(RMS/(double)SamplePoint);
		;
		if(FabsMean==0)
		  return 0;
		;
		WaveFactor=RMS/FabsMean;
		return WaveFactor;
	}

	//峰值指标
	public static double GetPeakFactor(double[] InputSignalArray,int SamplePoint)
	{
		double PeakFactor=0;
		double RMS=0;
		double MaxV=0;
		double fabsV;
		if(SamplePoint<=0||Util.isNullOrEmpty(InputSignalArray))
			return 0;
	      ;
		for(int ii=0;ii<SamplePoint;ii++)
		{
		  RMS+=InputSignalArray[ii] * InputSignalArray[ii];
		  fabsV=Math.abs(InputSignalArray[ii]);
	      if(fabsV>MaxV)
		    MaxV=fabsV;
		  ;
		};
		if(RMS==0)
			return 0;
		;
		RMS=Math.sqrt(RMS/(double)SamplePoint);
		PeakFactor=MaxV/RMS;
		return PeakFactor;
	}

	//脉冲指标
	public static  double GetPulseFactor(double[] InputSignalArray,int SamplePoint)
	{
		double PulseFactor=0;
			double MaxV=0;
			double FabsMean=0;
				double fabsV;
		if(SamplePoint<=0||Util.isNullOrEmpty(InputSignalArray))
			return 0;
		;
		for(int ii=0;ii<SamplePoint;ii++)
		{
			FabsMean+=Math.abs(InputSignalArray[ii]);
			fabsV=Math.abs(InputSignalArray[ii]);
	       if(fabsV>MaxV)
		     MaxV=fabsV;
		};

		if(FabsMean==0)
			return 0;

		FabsMean/=SamplePoint;
		PulseFactor=MaxV/FabsMean;

		return PulseFactor;
	}

	//裕度指标
	public  static double GetClearanceFactor(double[] InputSignalArray,int SamplePoint)
	{
		double ClearanceFactor=0;
			double MaxV=0;
		double SqrtV=0;
			double fabsV;
		if(SamplePoint<=0||Util.isNullOrEmpty(InputSignalArray))
			return 0;
		;
	  
		for(int ii=0;ii<SamplePoint;ii++)
		{
			fabsV=Math.abs(InputSignalArray[ii]);
	       if(fabsV>MaxV)
		     MaxV=fabsV;
		   ;
		   SqrtV+=Math.sqrt(fabsV);
		};

		SqrtV/=SamplePoint;
		SqrtV=Math.pow(SqrtV,2);
		if(SqrtV==0)
			return 0;
		;
		ClearanceFactor=MaxV/SqrtV;

		return ClearanceFactor;
	}

	//峭度指标
	public  static double GetKurtosisFactor(double[] InputSignalArray,int SamplePoint)
	{
	   double KurtosisFactor=0;
	   double Kurtosis=0;
	   	double RMS=0;
		if(SamplePoint<=0||Util.isNullOrEmpty(InputSignalArray))
			return 0;
		;
		for(int ii=0;ii<SamplePoint;ii++)
		{
		    Kurtosis+=Math.pow(InputSignalArray[ii],4)/SamplePoint;
			 RMS+=InputSignalArray[ii] * InputSignalArray[ii];
		};
		;
//		Kurtosis/=SamplePoint;
		 RMS=Math.sqrt(RMS/(double)SamplePoint);
		 if(RMS==0)
			 return 0;
		 ;
		 KurtosisFactor=Kurtosis/Math.pow(RMS,4);

	   return KurtosisFactor;
	}
}