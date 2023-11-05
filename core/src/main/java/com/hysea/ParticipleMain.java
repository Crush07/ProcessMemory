package com.hysea;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.jcp.xml.dsig.internal.dom.Utils;
import org.nlpcn.commons.lang.tire.domain.Forest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.nlpcn.commons.lang.tire.library.Library;



public class ParticipleMain {

    public static void main(String[] args) {

        //<[^>]*> > 空格
        //"[ ]*[+]\n[^"]*" > 空格
        File file = new File("G:\\project\\processMemory\\core\\src\\main\\resources\\process\\DriversLicenseProcess.xml");
        String str = txt2String(file);

        //不一定非中文就不是步骤
//        str = str.replaceAll("[^\\u4e00-\\u9fa5]+"," ");
        str = str.replaceAll("\n"," ");
        str = str.replaceAll("\r"," ");
        str = str.replaceAll("，"," ");
        str = str.replaceAll("<[^>]*>"," ");
        str = str.replaceAll(" +"," ");
        System.out.println(str);

        Result result = ToAnalysis.parse(str); //分词结果的一个封装，主要是一个List<Term>的terms
//        System.out.println(result.getTerms());

        List<Term> terms = result.getTerms(); //拿到terms
//        System.out.println(terms.size());

        Set<String> termSet = terms.stream().map(Term::getName).collect(Collectors.toSet());
//        for(int i=0; i<terms.size(); i++) {
//            String word = terms.get(i).getName(); //拿到词
//            String natureStr = terms.get(i).getNatureStr(); //拿到词性
//            System.out.println(word + ":" + natureStr);
//        }
        System.out.println(termSet);

    }

    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            // 构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            // 使用readLine方法，一次读一行
            while((s = br.readLine())!=null){
                result.append(System.lineSeparator()).append(s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
