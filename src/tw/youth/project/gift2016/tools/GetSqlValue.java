package tw.youth.project.gift2016.tools;

import tw.youth.project.gift2016.sql.adep.ADEP;
import tw.youth.project.gift2016.sql.afab.AFAB;
import tw.youth.project.gift2016.sql.ainventory.AINVENTORY;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.apresent.APRESENT;
import tw.youth.project.gift2016.sql.aqty.AQTY;
import tw.youth.project.gift2016.sql.avdr.AVDR;
import tw.youth.project.gift2016.sql.user.AEMP;
import tw.youth.project.gift2016.sql.user.AUSER;

public class GetSqlValue {
	
	private Object[] objs;
	public GetSqlValue(String key,Object obj) {
		// TODO Auto-generated constructor stub
		switch(key){
		case "auser" :
			objs = ((AUSER)obj).getValues();
			break;
		case "aemp" :
			objs = ((AEMP)obj).getValues();
			break;
		case "avdr" :
			objs = ((AVDR)obj).getValues();
			break;
		case "aqty" :
			objs = ((AQTY)obj).getValues();
			break;
		case "apresent" :
			objs = ((APRESENT)obj).getValues();
			break;
		case "aodr" :
			objs = ((AODR)obj).getValues();
			break;
		case "aio" :
			objs = ((AIO)obj).getValues();
			break;
		case "ainventory" :
			objs = ((AINVENTORY)obj).getValues();
			break;
		case "afab" :
			objs = ((AFAB)obj).getValues();
			break;
		case "adep" :
			objs = ((ADEP)obj).getValues();
			break;
		}
	}
	
	public Object[] getValues(){
		return objs;
	}
}
