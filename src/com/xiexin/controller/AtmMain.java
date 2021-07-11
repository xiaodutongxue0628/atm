package com.xiexin.controller;
// 自动导包

import com.xiexin.bean.Customer;
import com.xiexin.bean.CustomerData;
import com.xiexin.util.TextUitl;

import java.util.List;
import java.util.Scanner;

/**
 *  atm 取款机入口
 */
public class AtmMain {

    private static String cardid;
    private static String cardPwd;

    public static void main(String[] args) {
        // 测试 客户类的 数据   // 作业1: 对 manager管理类 做出  单例模式. 并测试 是否数据初始了.
        CustomerData customerData = CustomerData.getInstance();
        List<Customer> customerList = customerData.getCustomerList();
        // 是一个阶段
        TextUitl.welcome();
         int i = 0;
        while (i<6){
            // 输入账户密码 一个阶段  练习:  如果 超过5次 就 终结程序(跳出循环)
            doWritePassword();
            // 其他操作-- 1)校验角色, 2)判断账户密码的正确性
            doCheckPassword(cardid,cardPwd);
            i++;
        }
    }
    // 判断角色 和 判断密码
    private static void doCheckPassword(String cardid, String  cardPwd) {
        // 1. 先校验角色,  判断 cardid 的长度.
        if (cardid.length()==8) { // 客户
            // 校验密码
        }
    }
    //  输入张密
    private static void doWritePassword() {
        System.out.println("请输入卡号");
        Scanner scanner = new Scanner(System.in);
        cardid = scanner.nextLine();
        System.out.println("cardid = " + cardid);
        System.out.println("请输入密码");
        cardPwd = scanner.nextLine();
        System.out.println("cardPwd = " + cardPwd);
    }


}
