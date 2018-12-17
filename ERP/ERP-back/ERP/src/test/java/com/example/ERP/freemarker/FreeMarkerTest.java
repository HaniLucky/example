package com.example.ERP.freemarker;

import freemarker.core.ParseException;
import freemarker.template.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FreeMarkerTest {

    // 模板+数据=静态页面
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要生成的模块名：");
        String tempNm = sc.nextLine();

        // 模块名
        // String tempNm = "nation";
        // 模块名全部小写
        String tempNmLower = tempNm.toLowerCase();
        // 模块名首字母大写
        String tempNmUpper = tempNmLower.substring(0, 1).toUpperCase() + tempNmLower.substring(1);

        // 项目所在位置
        String projectPath = System.getProperty("user.dir");

        // 各层级包所在位置（目前还做不到自动创建package 需要手动创建好）
        String contPath = projectPath + "src\\main\\java\\com\\example\\ERP\\controller";

        // 文件名前缀
        String contPrefix = "";

        // 文件名后缀
        String contSuffix = "Controller.java";

        // 模板路径
        String ftlPath = "D:\\github\\example\\ERP\\ERP-back\\ERP\\src\\test\\java\\com\\example\\ERP\\freemarker\\ftl";
        String contFtlFtlPath = "controller.ftl";

        // 模板数据
        Map<String, String> valMap = new HashMap<String, String>();
        valMap.put("tempNm", tempNmLower);

        // 创建模板
        Configuration cfg = createFkCfg(projectPath, ftlPath);
        createControllerTemp(tempNmUpper, contPath, contPrefix, contSuffix, valMap, cfg, contFtlFtlPath);

    }

    private static Configuration createFkCfg(String projectPath, String ftlPath) throws IOException {
        // 1. 创建FreeMarker的核心对象
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        // 2. 给核心对象设置模板所在的位置（目录）
        cfg.setDirectoryForTemplateLoading(new File(projectPath + ftlPath));
        return cfg;
    }

    private static void createControllerTemp(String tempNmUpper, String contPath, String contPrefix, String contSuffix,
                                             Map<String, String> valMap, Configuration cfg, String contFtlFtlPath) throws TemplateNotFoundException,
            MalformedTemplateNameException, ParseException, IOException, TemplateException {
        Template template = cfg.getTemplate(contFtlFtlPath);
        Writer out = new FileWriter(new File(contPath + contPrefix + tempNmUpper + contSuffix));
        template.process(valMap, out);
    }


}
