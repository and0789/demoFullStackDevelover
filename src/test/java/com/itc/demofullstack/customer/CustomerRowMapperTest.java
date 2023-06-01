package com.itc.demofullstack.customer;


import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerRowMapperTest {


    @Test
    void mapRow() throws SQLException {
        // Given
        CustomerRowMapper customerRowMapper = new CustomerRowMapper();

        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("Andre");
        when(resultSet.getString("email")).thenReturn("andre@gmail.com");
        when(resultSet.getString("password")).thenReturn("123456");
        when(resultSet.getInt("age")).thenReturn(23);
        when(resultSet.getString("gender")).thenReturn("MALE");


        // When
        Customer actual = customerRowMapper.mapRow(resultSet, 1);

        // Then
        Customer expected = new Customer(
                1, "Andre", "andre@gmail.com", "123456", 23, Gender.MALE
        );
        assertThat(actual).isEqualTo(expected);

    }
}