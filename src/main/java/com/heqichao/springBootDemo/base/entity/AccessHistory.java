package com.heqichao.springBootDemo.base.entity;

import com.heqichao.springBootDemo.base.entity.BaseEntity;
import org.springframework.stereotype.Component;

@Component("access_history")
public class AccessHistory extends BaseEntity {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 2958815618699561322L;
	
	private String accessIp;
    private String  cmdKey;
    private String  cmdValue;
    
    
	public String getAccessIp() {
		return accessIp;
	}
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}
	public String getCmdKey() {
		return cmdKey;
	}
	public void setCmdKey(String cmdKey) {
		this.cmdKey = cmdKey;
	}
	public String getCmdValue() {
		return cmdValue;
	}
	public void setCmdValue(String cmdValue) {
		this.cmdValue = cmdValue;
	}
    
    


}
