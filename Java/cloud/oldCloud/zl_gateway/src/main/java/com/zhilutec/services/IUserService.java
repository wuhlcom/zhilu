/**
* @author :wuhongliang wuhongliang@zhilutec.com
* @version :2017年10月24日 下午6:48:29 *
*/
package com.zhilutec.services;

import com.alibaba.fastjson.JSONObject;
import com.zhilutec.common.result.Result;
import com.zhilutec.dbs.entities.User;

public interface IUserService {

	String add(JSONObject jsonObj);

	User getUser(JSONObject jsonObject);

	User query(String username);

	String update(JSONObject jsonObj);

	String delete(JSONObject jsonObj);

}
