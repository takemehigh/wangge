
package Sql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class ChangeLock {
	public static  String selectLock(){
		Connection conn=ConnectionFactory.getConnection();
		Statement st=null;
		ResultSet rs=null;
		String isTrue=null;
		try {
			st=conn.createStatement();
			String sql="select KEY_VALUE from TB_GLOBAL_CONFIG where KEY_NAME='SDK_CONFIG_LOCK'";
			rs=st.executeQuery(sql);
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
	public static void Change(String operation){
		Connection conn=ConnectionFactory.getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			String sql=null;
			if(operation.equals("T")||operation.equals("t"))
			{
				sql="update TB_GLOBAL_CONFIG set KEY_VALUE='true' where KEY_NAME='SDK_CONFIG_LOCK'";
				st.execute(sql);
				System.out.println("已经修改成true");
			}
			else if(operation.equals("F")||operation.equals("f"))
			{
				sql="update TB_GLOBAL_CONFIG set KEY_VALUE='false' where KEY_NAME='SDK_CONFIG_LOCK'";
				st.execute(sql);
				System.out.println("已经修改成false");
			}
			else{
				System.out.println("取消修改");
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String isTrue=null;
	 	isTrue = selectLock();
		System.out.println("现在SDK_CONFIG_LOCK的值是"+isTrue);
		System.out.println("修改SDK_CONFIG_LOCK的值吗？(T/F/C)");
		Scanner s=new Scanner(System.in);
		String operation=s.next();
		Change(operation);
		s.close();
	}		
}
