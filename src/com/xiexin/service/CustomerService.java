package com.xiexin.service;

import com.xiexin.bean.Customer;
import com.xiexin.bean.CustomerData;
import com.xiexin.util.TextUitl;

import java.util.List;
import java.util.Scanner;

// 此类是  完成 客户的 所有业务 (增删改查)
public class CustomerService {
  private List<Customer> customerList;
  private Customer currentCustomer;
    // 1. 查, 登录 判断账号密码是否正确
        public void  checkPwd(String cardid, String  cardPwd){
             CustomerData customerData = CustomerData.getInstance();
            customerList  = customerData.getCustomerList();
                // 1. 拿到  carid中的账户名 和 cardPwd  对  list中的数据做对比
            System.out.println("cardid = " + cardid);
            System.out.println("cardPwd = " + cardPwd);

            for (Customer customer : customerList) {
                    if (customer.getAccount().equals(cardid) && customer.getPassword().equals(cardPwd)){
                        // 拿出来这个人
                        currentCustomer=  customer;
                        // 账户正确
                        System.out.println("欢迎"+customer.getCname()+"顾客登录!请注意您的安全"   );
                        TextUitl.oneLeveOption();  // 界面
                        Scanner scanner = new Scanner(System.in);
                        String option = scanner.nextLine();
                        oneOption(option);
                        // 如何 保证 按了 1 3 5... 让他再此回到 此界面呢 ?
                    }
            }
        }

    private void oneOption(String option) {
        switch (option){
            case "1":
                System.out.println("余额查询");
                   // 查询余额外
                doSelectMoney();
                    // 当按下1 之后,  回退到  1及选项
                goOneHome();
                break;
            case "2":
                goGetMoneyHome();
                goOneHome();
                break;
            case "3":
                System.out.println("转账");
                doTruanMoney();
                goOneHome();
                break;
            case "4":
                System.out.println("存款");
                doSaveMoney();
                goOneHome();
                break;
            case "5":
                System.out.println("退卡");
                doQuitCard();
                goOneHome();
                break;
        }
    }

    //转账
    private void doTruanMoney(){
        System.out.println("请输入对方的号码");
        Scanner scanner = new Scanner(System.in);
        String otherAccount = scanner.nextLine();
        System.out.println("请输入你要转账的金额：");
        String otherMoney = scanner.nextLine();
        Double otherMoneyInt = Double.parseDouble(otherMoney);
        double currentMoney = currentCustomer.getMoney()-otherMoneyInt;
        Customer other = null;
        for(Customer customer : customerList) {
            if (customer.getAccount().equals(otherAccount)) {
                other = customer;
            }
        }
        double otherOneMoney = other.getMoney() + otherMoneyInt;

        currentCustomer.setMoney(currentMoney);
        other.setMoney(otherOneMoney);

        for (Customer customer : customerList){
            System.out.println("customer = " + customer);
        }
    }

    // 查询余额
    private void doSelectMoney() {
        double money = currentCustomer.getMoney();
        System.out.println(" 余额是 " +money);
    }

    //返回1级目录
    private  void goOneHome( ){
        TextUitl.oneLeveOption();
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        System.out.println("option1 = " + option);
        oneOption(option);  // 递归算法
    }

    //取款
    private void goGetMoneyHome(){
        TextUitl.getMoneyUI();
        // 1. 让客户输入
        Scanner scanner = new Scanner(System.in);
        String  numIn = scanner.nextLine();
        if (numIn.equals("1")){
            // 那么 取款100 那么就应该 让 顾客的 钱 -100
            double money = currentCustomer.getMoney();
            money=money-100;
            System.out.println("您的余额是: " + money);
            // 取完款项之后,  更新 原有的 存款

        }
    }

    //存款
    private void doSaveMoney(){
        System.out.println("请输入你想存入的金额");
        Scanner scanner = new Scanner(System.in);
        String moneyIn = scanner.nextLine();
        Double moneyInInt = Double.valueOf(moneyIn);
        double newMoney = currentCustomer.getMoney() + moneyInInt;
        currentCustomer.setMoney(newMoney);
        System.out.println("您的余额是 " + newMoney);
    }

    //退卡
    private void doQuitCard(){
        System.out.println("您是否继续操作yes/no[Y/N]");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.equalsIgnoreCase("y")){
            goOneHome();
        }
        if (s.equalsIgnoreCase("n")){
            System.exit(0);
        }
    }
}
