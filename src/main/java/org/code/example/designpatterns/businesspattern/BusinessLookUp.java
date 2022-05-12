package org.code.example.designpatterns.businesspattern;

/**
 * @ClassName BusinessLookUp
 * @Description 业务查询服务
 * @Author Chaiphat
 * @Date 2020/1/9 16:37
 * @Version 1.0
 **/
public class BusinessLookUp {

    /**
     * 根据具体的业务类型，查询具体的业务类型办理人
     * @param serviceType
     * @return
     */
    public BusinessService getBusinessService(String serviceType){
        if("doCreateCount".equalsIgnoreCase(serviceType)){
            return new BusinessConsultantZh();
        }
        else{
            return new BusinessConsultantLi();
        }
    }

}
