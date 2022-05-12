package common.viewer;

import common.bo.RequestStat;
import common.util.EmailSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName EmailViewer
 * @Description
 * @Author Chaiphat
 * @Date 2020/6/27 20:27
 * @Version 1.0
 **/
public class EmailViewer implements StatViewer {

    private EmailSender emailSender;
    private List<String> toAddresses = new ArrayList<>();

    public EmailViewer() {
        this.emailSender = new EmailSender(/*省略参数*/);
    }

    public EmailViewer(EmailSender emailSender) { this.emailSender = emailSender; }

    public EmailViewer(List<String> emailToAddresses) {
        this.toAddresses = emailToAddresses;
    }

    public void addToAddress(String address) { toAddresses.add(address); }

    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        // format the requestStats to HTML style.
        // send it to email toAddresses.
    }
}
