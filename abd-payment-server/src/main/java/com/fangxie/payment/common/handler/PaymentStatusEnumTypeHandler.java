package com.fangxie.payment.common.handler;

import com.fangxie.payment.enums.PaymentStatusEnum;
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
public class PaymentStatusEnumTypeHandler extends BaseTypeHandler<PaymentStatusEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, PaymentStatusEnum paymentStatusEnum, JdbcType jdbcType) throws SQLException {

        preparedStatement.setInt(i, paymentStatusEnum.getCode());
    }


    private PaymentStatusEnum convertToPaymentStatusEnum(Integer paymenStatus) {

        if (Objects.isNull(paymenStatus)) {
            paymenStatus = 99;
        }

        return PaymentStatusEnum.getByCode(paymenStatus);
    }


    @Override
    public PaymentStatusEnum getNullableResult(ResultSet resultSet, String columnName) throws SQLException {

        return convertToPaymentStatusEnum(resultSet.getInt(columnName));
    }

    @Override
    public PaymentStatusEnum getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {

        return convertToPaymentStatusEnum(resultSet.getInt(columnIndex));
    }

    @Override
    public PaymentStatusEnum getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {

        return convertToPaymentStatusEnum(callableStatement.getInt(columnIndex));
    }


}
