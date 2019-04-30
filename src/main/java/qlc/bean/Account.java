/** 
 * @Package qlc.bean 
 * @Description 
 * @author yfhuang521@gmail.com
 * @date 2019年4月30日 下午2:09:17 
 * @version V1.0 
 */ 
package qlc.bean;

import com.alibaba.fastjson.JSONObject;

/** 
 * @Description Account info
 * @author yfhuang521@gmail.com
 * @date 2019年4月30日 下午2:09:17 
 */
public class Account {
	
	public String address;	
	
	public String publicKey;	
	
	public String privateKey;	
	
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("address", address);
		json.put("publicKey", publicKey);
		json.put("privateKey", privateKey);
		return json;
	}

}
