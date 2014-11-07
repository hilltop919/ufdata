package com.hilltop.function;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wabacus.config.component.IComponentConfigBean;
import com.wabacus.system.ReportRequest;
import com.wabacus.system.serveraction.IServerAction;


/**
 * 切换帐套
 * @author yushan
 *
 */
public class SwitchDatabase implements IServerAction
{

    /**
     * 针对组件通过ajax调用服务器端方法
     */
    public String executeSeverAction(ReportRequest rrequest,IComponentConfigBean ccbean,List<Map<String,String>> lstData,Map<String,String> mCustomizedData)
    {
        
        HttpSession session=rrequest.getRequest().getSession();
		session.setAttribute("ufdata_database", getUfdataDatabase(lstData));
		System.out.println(getUfdataDatabase(lstData));
        rrequest.getWResponse().getMessageCollector().success("设置成功，当前帐套为："+getUfdataDatabase(lstData));//向前台提示一条信息，这里还可以终止后续处理
        return "设置成功!";
    }

    private String getUfdataDatabase(List<Map<String,String>> lstData)
    {
        if(lstData==null||lstData.size()==0)
        {
        }else
        {
            for(Map<String,String> mParams:lstData)
            {
                for(Entry<String,String> entryTmp:mParams.entrySet())
                {
                	if("param_id".equals(entryTmp.getKey())){
                		return entryTmp.getValue();
                	}
                }
            }
        }
        return "";
    }

	@Override
	public String executeServerAction(HttpServletRequest arg0,
			HttpServletResponse arg1, List<Map<String, String>> arg2) {
		return null;
	}
}

