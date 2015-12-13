package org.cbillow.util;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 中文转拼音，
 * 支持中文中夹杂英文字符,多音字失败
 */
public class ConvertPinyinUtil {
    public static final String HETERONYM = "heteronym";//名字为多音字
    protected static final Logger LOGGER = LoggerFactory.getLogger(ConvertPinyinUtil.class);

    public static String chinese2Pinyin(String chinese) {
        try {
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            StringBuilder sb = new StringBuilder();
            String[] onePinyin;
            for (int i = 0; i < chinese.length(); i++) {
                onePinyin = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i), format);
                if (onePinyin == null) {
                    //是英文直接输出
                    sb.append(chinese.charAt(i));
                } else {
                    //是中文默认取第一个
                    sb.append(onePinyin[0].toUpperCase());
                    if (onePinyin.length > 1) {
                        //多音字取第一个拼音
                        return onePinyin[0].toUpperCase();
                    }
                }
            }
            return sb.toString();
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            LOGGER.error("拼音转换出现异常:", badHanyuPinyinOutputFormatCombination);
            return "";
        }
    }

}