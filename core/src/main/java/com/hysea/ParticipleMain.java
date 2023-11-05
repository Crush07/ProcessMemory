package com.hysea;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.jcp.xml.dsig.internal.dom.Utils;
import org.nlpcn.commons.lang.tire.domain.Forest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.nlpcn.commons.lang.tire.library.Library;



public class ParticipleMain {

    public static void main(String[] args) {

        //<[^>]*> > 空格
        //"[ ]*[+]\n[^"]*" > 空格
        String str = "绿灯闪时已经在斑马线上，点刹过，红灯，停车，挂一档，绿灯，起步，升二挡，绿灯，点刹过，挂一档，绿灯，起步，升二挡，调整座位，调整档位，调整灯光，准备好了，下车，按按钮，上车，关门，系安全带，打火，做灯光考试，听语音报考试路线，左转向灯，离合带刹车，挂一档，松离合，等3秒，关左灯，听语音打右灯，打右灯，到起始线前停，起步往最右侧路线行驶，升二挡，在快到三角形的地方停，停车挂一档，打右灯，起步，在三角形的地方停，调整方向盘过，过了弯道再停一次，打左灯，起步变道最左侧道路，挂二挡，公交车站点刹，播报保持直线行驶，播报直线行驶完，踩油门，速度提到25左右，踩离合，升三挡，松离合，踩油门，速度提到35左右，踩离合，升四挡，松离合，稳速度，数8秒，刹车，速度降到25左右，踩离合，挂二挡，慢慢松离合，红绿灯，人行横道，在没有过斑马线的时候减速，播报会车，点刹，播报前方掉头，打左转向灯，下水道与肩部平行，方向盘打死，学校区域，点刹，红绿灯，人行横道，在没有过斑马线的时候减速，播报超车，打左转向灯，数三秒，变道，方向摆正，打右转向灯，数三秒，变道，播报变道，打左转向灯，数三秒，变道，播报左转，打左转向灯，红绿灯，人行横道，在没有过斑马线的时候减速，看不到斑马线时方向盘打60度，走最右侧车道，在第一条虚线前停车，打右转向灯，靠边停车，挂空挡，拉手刹，熄火，开门数3秒再关上，解安全带";

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

//    private static class Inner {
//        static Forest forest;
//        static {
//            try {
//                forest = Library.makeForest(Utils.class.getResourceAsStream("/library/userLibrary.dic"));
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.exit(1);
//            }
//        }
//    }
}
