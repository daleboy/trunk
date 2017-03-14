package com.eshore.nrms.sysmgr.vo;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class FileValidateCode {

	private static final long ACTIVE_TIME = 10000;	//有效时间  10秒
	private static final long CLEAR_TIME = 3600000;	//清理时间	1小时
	private static final List<RandomCode> codes = new Vector<RandomCode>();
	
	private static final Thread t = new Thread(new Runnable() {
			public void run() {
				while(true){
					try {
						Thread.sleep(CLEAR_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("开始清理："+codes.size() + " pice of code left");
					codes.clear();
				}
			}
	});
	
	static{
		if(!t.isAlive()){
			t.start();
		}
	}
	
	
	
	public static boolean isActiveCode(String code){
		int index = codes.indexOf(new RandomCode(code));
		if(index >= 0){
			RandomCode rc = codes.get(index);
			codes.remove(index);
			long now = System.currentTimeMillis();
			return now - rc.birthDate < ACTIVE_TIME ;
		}
		return false;
	}
	
	public static RandomCode genRandomCode(){
		RandomCode code = new RandomCode();
		codes.add(code);
		return code;
	}
	public boolean isInvalid(RandomCode rc){
		long now = System.currentTimeMillis();
		return now - rc.birthDate > ACTIVE_TIME ;	
	}
	
	public static class RandomCode{
		private long birthDate;
		private String code;
		public RandomCode() {
			this.birthDate = System.currentTimeMillis();
			code = UUID.randomUUID().toString();
		}
		public RandomCode(String code){
			this.code = code;
		}
		public String getCode() {
			return code;
		}
		public boolean isInvalid(){
			long now = System.currentTimeMillis();
			return now - birthDate > ACTIVE_TIME ;	
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((code == null) ? 0 : code.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RandomCode other = (RandomCode) obj;
			if (code == null) {
				if (other.code != null)
					return false;
			} else if (!code.equals(other.code))
				return false;
			return true;
		}
	
		
		
	}
}
