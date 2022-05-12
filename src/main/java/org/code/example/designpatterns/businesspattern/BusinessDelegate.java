package org.code.example.designpatterns.businesspattern;

/**
 * @ClassName BusinessDelegate
 * @Description 业务顾问或者大堂业务专员
 * @Author Chaiphat
 * @Date 2020/1/9 16:40
 * @Version 1.0
 **/
public class BusinessDelegate {

    /**
     *   业务查询
     */

    private BusinessLookUp lookUpService = new BusinessLookUp();

    /**
     * 业务服务
     */
    private BusinessService businessService;

    /**
     * 服务类型
     */
    private String serviceType;


    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }


    public void doTask(){
        // 查询具体的服务窗口
        businessService = lookUpService.getBusinessService(serviceType);

        // 委托窗口进行业务处理
        if(serviceType.equalsIgnoreCase("doCreateCount")){
            businessService.doCreateCount();
        }
        else{
            businessService.doSaveMoney();
        }
    }
}
