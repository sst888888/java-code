package com.example.controller;

import com.example.entity.ExcelUtil;
import com.example.entity.Member4PhoneDTO;
import com.example.entity.MemberStaticExcelVO;
import com.example.entity.ThreadPoolUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/excel")
public class ExcelController {

    private ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtils.genetePool(30, 30);

    @PostMapping("/test")
    public void test(MultipartFile file, HttpServletResponse response) throws Exception {
        if (file.isEmpty()) {
            return;
        }

        ExcelUtil<Member4PhoneDTO> util = new ExcelUtil<>(Member4PhoneDTO.class);
        List<Member4PhoneDTO> p1List = util.importExcel(file.getInputStream());
//        List<Member4PhoneDTO> p2List = util.importExcel("Sheet3", file.getInputStream(),0);
        if (CollectionUtils.isEmpty(p1List)) {
//            throw new ServiceException("请填写查询会员手机号");
            return;
        }

        List<String> phone1 = p1List.stream().map(Member4PhoneDTO::getPhone1).collect(Collectors.toList());
        List<String> phone2 = p1List.stream().map(Member4PhoneDTO::getPhone2).collect(Collectors.toList());

        phone1.removeIf(StringUtils::isBlank);
        phone2.removeIf(StringUtils::isBlank);

        List<String> collect = phone1.stream().distinct().collect(Collectors.toList());
        System.out.println("phone1 size " + collect.size());
        System.out.println("phone2 size " + phone2.size());

        List<String> intersection = new ArrayList<>();
        List<List<String>> partition = Lists.partition(phone2, 50000);
        CountDownLatch countDownLatch = new CountDownLatch(partition.size());
        partition.forEach((tmpList) -> {
            threadPoolExecutor.execute(() -> {
                try {
                    List<String> tt = tmpList.stream().filter(collect::contains).collect(Collectors.toList());
                    if (!CollectionUtils.isEmpty(tt)) {
                        intersection.addAll(tt);
                    }
                } catch (Exception e) {
                    log.error("下级会员充值和提现统计执行异常，会员ID", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        });

        countDownLatch.await();


//        List<String> intersection = phone2.stream()
//                .filter(collect::contains)
//                .collect(Collectors.toList());
//        log.error("相同号码个数为{}, {}" ,intersection.size(), intersection);

//        for (int i = 0; i < collect.size(); i++) {
//            String s = collect.get(i);
//            if (!intersection.contains(s)) {
//                log.error("不同号码{}", s);
//            }
//        }

//        System.out.println("phone1 size " + phone1.size());
//
//        Set<String> seen = new HashSet<>();
//        Set<String> duplicates = phone1.stream()
//                .filter(e -> !seen.add(e))
//                .collect(Collectors.toSet());
//
//        System.out.println("重复数据： " + duplicates); // 输出: [a, b]



        List<MemberStaticExcelVO> result = new ArrayList<>();

        for (int i = 0; i < phone2.size(); i++) {
            String s = phone2.get(i);
            if (!intersection.contains(s)) {
                MemberStaticExcelVO vo = new MemberStaticExcelVO();
                vo.setPhone(s);
                result.add(vo);
            }
        }

        System.out.println("phone1 size " + phone1.size());
        System.out.println("phone2 size " + phone2.size());
        System.out.println("result size " + result.size());
        log.error("相同号码个数为{}, {}" ,intersection.size(), intersection);
        doWriteExcel(response, result);

//        return intersection.toString();
    }


    private void doWriteExcel(HttpServletResponse response, List<MemberStaticExcelVO> resultList) {
        ExcelUtil<MemberStaticExcelVO> util2 = new ExcelUtil<>(MemberStaticExcelVO.class);
        util2.exportExcel(response, resultList, "统计结果");
    }

}
