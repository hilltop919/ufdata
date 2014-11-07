package com.hilltop.function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wabacus.system.ReportRequest;
import com.wabacus.system.dataset.select.common.AbsCommonDataSetValueProvider;

public class CustomerPromptDataSource extends AbsCommonDataSetValueProvider
{
    public List<Map<String,String>> getLstTypePromptOptions(ReportRequest rrequest,String txtValue)
    {
        List<Map<String,String>> lstResults=new ArrayList<Map<String,String>>();
        
        
        Connection conn=rrequest.getConnection(); 

        //所选帐套
        String ufdata_database=(String) rrequest.getRequest().getSession().getAttribute("ufdata_database");
        
        String sql = " SELECT ccuscode,ccusname FROM "+ufdata_database+".dbo.customer order by ccuscode ";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
		        Map<String,String> mOption=new HashMap<String,String>();
		        mOption.put("ccuscode",rs.getString("ccuscode"));
		        mOption.put("ccusname",rs.getString("ccusname"));
		        lstResults.add(mOption);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
        
        
        
        return lstResults;
    }

    public Map<String,String> getAutoCompleteColumnsData(ReportRequest arg0,Map<String,String> arg1)
    {
        return null;
    }

    public List<Map<String,String>> getDynamicColGroupDataSet(ReportRequest arg0)
    {
        return null;
    }

    public List<Map<String,String>> getLstSelectBoxOptions(ReportRequest arg0,Map<String,String> arg1)
    {
        return null;
    }
}
