package com.alvism.util.common.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;

/**
 * @描述：jxls模版
 * @author lk
 */
public class AlvisMExcel {
    /** jxls模版文件目录 */
    private final static String TEMPLATE_PATH = "jxlsTemplate";
    /**
     * 导出excel
     * @param is - excel文件流
     * @param os - 生成模版输出流
     * @param beans - 模版中填充的数据
     * @throws IOException
     */
    public static void exportExcel(InputStream is, OutputStream os, Map<String, Object> beans) throws IOException {
        Context context = new Context();
        if (beans != null) {
            for (String key : beans.keySet()) {
                context.putVar(key, beans.get(key));
            }
        }
        JxlsHelper jxlsHelper = JxlsHelper.getInstance();
        Transformer transformer  = jxlsHelper.createTransformer(is, os);
        /*JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
        Map<String, Object> funcs = new HashMap<>();*/
        new CommentArea();
        /*evaluator.getJexlEngine().setFunctions(funcs);*/
        jxlsHelper.processTemplate(context, transformer);
    }

    /**
     * 导出excel
     * @param xlsPath excel文件
     * @param outPath 输出文件
     * @param beans 模版中填充的数据
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void exportExcel(String xlsPath, String outPath, Map<String, Object> beans) throws FileNotFoundException, IOException {
        exportExcel(new FileInputStream(xlsPath), new FileOutputStream(outPath), beans);
    }

    /**
     * 导出excel
     * @param xls excel文件
     * @param out 输出文件
     * @param beans 模版中填充的数据
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void exportExcel(File xls, File out, Map<String, Object> beans) throws IOException {
        exportExcel(new FileInputStream(xls), new FileOutputStream(out), beans);
    }

    /**
     * 获取jxls模版文件
     */
    public static File getTemplate(String name){
        String templatePath = AlvisMExcel.class.getClassLoader().getResource(TEMPLATE_PATH).getPath();
        File template = new File(templatePath, name);
        if(template.exists()){
            return template;
        }
        return null;
    }

}
