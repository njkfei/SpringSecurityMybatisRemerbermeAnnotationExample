package xyz.hollysys.springsecurity.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import xyz.hollysys.springsecurity.model.User;

//@Repository("userDao")
public interface UserDao {

	@Select("select * from app_user where id=#{id}")
	@Results(value = {
			@Result(property = "ssoId", column = "sso_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "firstName", column = "first_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "lastName", column = "last_name", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	User findById(@Param("id") int id);

	@Select("select * from app_user where sso_id=#{sso_id}")
	@Results(value = {
			@Result(property = "ssoId", column = "sso_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "firstName", column = "first_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "lastName", column = "last_name", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	User findBySSO(@Param("sso_id") String sso_id);

	@Insert("insert into app_user(state) values('Deactive')")
	@Results(value = {
			@Result(property = "ssoId", column = "sso_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "firstName", column = "first_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "lastName", column = "last_name", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	int getUserId();

	@Insert("insert into app_user (sso_id,password,first_name,last_name,email,state) values(#{ssoId},#{password},#{firstName},#{lastName},#{email},#{state})")
	@Results(value = {
			@Result(property = "ssoId", column = "sso_id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "firstName", column = "first_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
			@Result(property = "lastName", column = "last_name", javaType = String.class, jdbcType = JdbcType.VARCHAR) })
	int save(User user);
}
