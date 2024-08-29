package com.lab4dx.s03_jdbc_di.controller;

import com.lab4dx.s03_jdbc_di.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
// 스프링 컨테이너에는 컴포넌트 스캐너 존재
// 컴포넌트(@Component)
    // 컨테이너에서 생성하고 주입할 수 있는 객체 타입
    // 스프링 컨테이너의 컴포넌트 스캐너가 컴포넌트(@Component)를 자동으로 찾아서 객체로 만든 후
    // 필요한 곳에 사용하고 주입
    // 컨트롤러(@Controller)는 컴포넌트(@Component)의 자식임

@Controller
// 폴더 형식으로 정리하기 편하다는 장점이 있음
@RequestMapping("/customer") // 주소가 ~~/customer/~~ ex. customer/list, customer/detail
public class CustomerController {
    @Autowired // 컨테이너에 있는 객체 주입
    DataSource dataSource; // DB에 접속한 Connection 반환

    @GetMapping("/list.do")
    public void list(Model model) throws Exception { // error 발생시 500 화면 띄우는 방식으로 error 처리
        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from customer");
        List<Customer> customers = new ArrayList<>();
        while (rs.next()){ // 한 줄이 있다 == customer 객체가 하나 있다
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setCustomerName(rs.getString("customer_name"));
            customer.setCustomerEmail(rs.getString("customer_email"));
            customer.setCustomerPhone(rs.getString("customer_phone"));
            customers.add(customer);
        }
        model.addAttribute("customers", customers);
    }
}
