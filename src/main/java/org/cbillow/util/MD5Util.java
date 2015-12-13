package org.cbillow.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
 
	public static byte[] getMD5Mac(byte[] bySourceByte){
        byte[] byDisByte;
        MessageDigest md;
        try{
        md = MessageDigest.getInstance("MD5"); 
        md.reset();
        md.update(bySourceByte);
        byDisByte = md.digest(); 
        }catch (NoSuchAlgorithmException n){
        	
        return(null);
        }
        return(byDisByte);
        }

	public static String bintoascii(byte []bySourceByte)

        {
               int len,i;
               byte tb;
               char high,tmp,low;
               StringBuilder result=new StringBuilder();
               len=bySourceByte.length;
               for(i=0;i<len;i++)
               {
                      tb=bySourceByte[i];
                      tmp=(char)((tb>>>4)&0x000f);
                      if(tmp>=10)
                             high=(char)('a'+tmp-10);
                      else
                             high=(char)('0'+tmp);
                      result.append(high);
                      tmp=(char)(tb&0x000f);
                      if(tmp>=10)
                             low=(char)('a'+tmp-10);
                      else
                             low=(char)('0'+tmp);
                      result.append(low);
               }
               return result.toString();
        }
	 public static String getMD5ofStr(String inbuf) throws UnsupportedEncodingException
	 {
		 if(inbuf==null||"".equals(inbuf)) return "";
		 return bintoascii(getMD5Mac(inbuf.getBytes("GBK")));
	 }
	 public static String createSignUsingMD5(String inbuf) throws UnsupportedEncodingException{
		 return getMD5ofStr(inbuf).toLowerCase();
	 }
}
