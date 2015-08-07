package Sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeTime {
	public static String IsTrue(){
			Connection conn=ConnectionFactory.getConnection();
			String isTrue=null;
			Statement st=null;
			ResultSet rs=null;
			String sql2="select KEY_VALUE from TB_GLOBAL_CONFIG where KEY_NAME='SDK_CONFIG_LOCK'";
			try {
				st=conn.createStatement();
				rs=st.executeQuery(sql2);
				while(rs.next()){
					isTrue=rs.getString(1);
				}
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			return isTrue;
	}
	public static void Change(String time){
				Connection conn=ConnectionFactory.getConnection();
				Statement st=null;
			try {
				st=conn.createStatement();
				String sql="update TB_GLOBAL_CONFIG set KEY_VALUE='"+time+"' where KEY_NAME='SDK_CONFIG_TIME'";
				st.execute(sql);
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				try {
					st.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		
		String isTrue=IsTrue();
		String time=null;
		Boolean isNumber=false;
		for(String a:args){
			if(args[0].matches("^[-]{1}\\d*$")){System.out.println("输入值不能为负数");return;}
			if(args[0].matches("^[1-9]{1}\\d*$")||args[0].matches("\\b0{1}\\b")){
				if(Integer.parseInt(args[0])>10000)
				{
					System.out.println(a);
					System.out.println("输入时间太大");
					return;
				}
				time=a; 
				isNumber=true;
				}	
				System.out.println(a);
		}
		
		if(isTrue.equals("true")){
			if(isNumber==true){
			Change(time);
			System.out.println("修改时间成功");
			}
			else
			{
				System.out.println("输入的不是合法时间");
			}
		}
		else{System.out.println("不允许修改时间");
		}	
	}
}





