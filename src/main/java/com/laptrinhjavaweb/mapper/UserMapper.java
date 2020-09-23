package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet rs) {
		try {
			UserModel user = new UserModel();
			user.setId(rs.getLong("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setFullName(rs.getString("fullname"));
			user.setStatus(rs.getInt("status"));
			//user.setRoleId(rs.getLong("roleid"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(rs.getString("code"));
				role.setName(rs.getString("name"));
				user.setRole(role);
			}catch (Exception e){
				System.err.println(e.getMessage());
			}
			
			
			user.setCreatedDate(rs.getTimestamp("createdate"));
			user.setCreatedBy(rs.getString("createby"));
			if(rs.getTimestamp("modifieddate")!=null) {
				user.setModifiedDate(rs.getTimestamp("modifieddate"));
			}
			if(rs.getString("modifiedby")!=null) {
				user.setModifiedBy(rs.getString("modifiedby"));
			}
			return user;
		} catch (Exception e) {
			return null;
		}
	
	}

}
