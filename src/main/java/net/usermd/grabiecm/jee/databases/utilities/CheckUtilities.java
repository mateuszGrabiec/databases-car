package net.usermd.grabiecm.jee.databases.utilities;

import org.springframework.stereotype.Service;

@Service
public class CheckUtilities {
    public boolean checkData(String s1) {
        if (!s1.isEmpty()) return true;
        else return false;
    }

    public boolean checkData(String s1, String s2) {
        return checkData(s1) && checkData(s2);
    }

    public boolean checkData(String s1, String s2, String s3) {
        return checkData(s1, s2) && checkData(s3);
    }
}
