package cn.hsf.hsf.config;

import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TableNamePrefix extends PhysicalNamingStrategyStandardImpl {

    @Value("${database.prefix}")
    private String prefix;  //读取application.properties配置的前缀

    protected String addUnderscores(String name) {
        if (name == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(name.replace('.', '_'));
        for (int i = 1; i < stringBuffer.length() - 1; i++) {
            if (Character.isLowerCase(stringBuffer.charAt(i-1)) && Character.isUpperCase(stringBuffer.charAt(i)) && Character.isLowerCase(stringBuffer.charAt(i+1))) {
                stringBuffer.insert(i++, '_');
            }
        }
        return stringBuffer.toString().toLowerCase();
    }
}


