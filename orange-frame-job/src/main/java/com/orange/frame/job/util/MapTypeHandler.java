package com.orange.frame.job.util;

import com.alibaba.fastjson2.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author boranget
 * @date 2023/2/24
 * mybatis 查询与插入时需要对map转换
 */
//@MappedJdbcTypes(JdbcType.VARCHAR)  //数据库类型
//@MappedTypes({Map.class})
@Component
public class MapTypeHandler extends BaseTypeHandler<Map<String,String>> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Map map, JdbcType jdbcType) throws SQLException {
        String s = JSONObject.toJSONString(map);
        preparedStatement.setString(i,s);
    }

    @Override
    public Map getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Map map = JSONObject.parseObject(resultSet.getString(s), Map.class);
        return map;
    }

    @Override
    public Map getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Map map = JSONObject.parseObject(resultSet.getString(i), Map.class);
        return map;
    }

    @Override
    public Map getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Map map = JSONObject.parseObject(callableStatement.getString(i), Map.class);
        return map;
    }
}
