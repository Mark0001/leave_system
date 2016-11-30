package com.tku.leave.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


import com.tku.leave.domain.LeaveDetail;

	public class Csv0
	{
	   public static void Excel(List<LeaveDetail> leavedetaillist)
	   {
		   long mainid=leavedetaillist.get(0).getMainId();
            
		    //System.out.println("leavedetaillist="+leavedetaillist);
		   /*
		   for(int i=0;i<leavedetaillist.size();i++){
            long[] mainid;
            mainid=new long[leavedetaillist.size()];
            
            String[] className;
            className=new  String[leavedetaillist.size()];
            className[i]=leavedetaillist.get(i).getClassName();
            
            String[] className;
            className=new  String[leavedetaillist.size()];
            className[i]=leavedetaillist.get(i).getClassName();
            
            String[] className;
            className=new  String[leavedetaillist.size()];
            className[i]=leavedetaillist.get(i).getClassName();
            
           */
            
            
            
			
            generateCsvFile("C:\\LeaveMain"+mainid+".csv",leavedetaillist); 
		   }
		   
		   //generateCsvFile("E:\\LeaveMainId"+UUID.randomUUID()+".csv",mainid,name,className,beginTime,endTime,reason,leaveTime); 
	   
	   
	   private static void generateCsvFile(String sFileName,List<LeaveDetail> leavedetaillist)
	   {
		   
		try
		{
		    FileWriter writer = new FileWriter(sFileName);
		    /*
		    String BeginTime=String.valueOf(beginTime);
		    String EndTime=String.valueOf(endTime);
		    String Mainid=String.valueOf(mainid);
			*/
		    
		    writer.append("假單編號");
		    writer.append(',');
		    writer.append("姓名");
		    writer.append(',');
		    writer.append("假別");
		    writer.append(',');
		    writer.append("開始時間");
		    writer.append(',');
		    writer.append("結束時間");
		    writer.append(',');
		    writer.append("時數");
		    writer.append(',');
		    writer.append("假由");
		    writer.append('\n');
		    for(int i=0;i<leavedetaillist.size();i++){
		    writer.append(String.valueOf(leavedetaillist.get(i).getMainId()));
		    writer.append(',');
		    writer.append(leavedetaillist.get(i).getName());
		    writer.append(',');
		    writer.append(leavedetaillist.get(i).getClassName());
		    writer.append(',');
		    writer.append(String.valueOf(leavedetaillist.get(i).getBeginTime()));
		    writer.append(',');
		    writer.append(String.valueOf(leavedetaillist.get(i).getEndTime()));
		    writer.append(',');
		    writer.append(leavedetaillist.get(i).getLeaveTime());
		    writer.append(',');
		    writer.append(leavedetaillist.get(i).getReason());
	         writer.append('\n');
				
		    }
				
		    //generate whatever data you want
				
		    writer.flush();
		    writer.close();
		   // System.out.println("OKKK!");
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
	    }
	}