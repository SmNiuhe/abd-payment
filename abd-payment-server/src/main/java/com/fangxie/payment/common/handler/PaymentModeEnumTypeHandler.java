package com.fangxie.payment.common.handler;

import com.fangxie.payment.enums.PaymentModeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * 类型转换
 */
public class PaymentModeEnumTypeHandler extends BaseTypeHandler<PaymentModeEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, PaymentModeEnum paymentStatusEnum, JdbcType jdbcType) throws SQLException {

        preparedStatement.setInt(i, paymentStatusEnum.getCode());
    }


    private PaymentModeEnum convertToPaymentModeEnum(Integer paymenStatus) {

        if (Objects.isNull(paymenStatus)) {
            paymenStatus = 99;
        }
        return PaymentModeEnum.getByCode(paymenStatus);
    }


    @Override
    public PaymentModeEnum getNullableResult(ResultSet resultSet, String columnName) throws SQLException {

        return convertToPaymentModeEnum(resultSet.getInt(columnName));
    }

    @Override
    public PaymentModeEnum getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {

        return convertToPaymentModeEnum(resultSet.getInt(columnIndex));
    }

    @Override
    public PaymentModeEnum getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {

        return convertToPaymentModeEnum(callableStatement.getInt(columnIndex));
    }


}
