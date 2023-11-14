package com.hysea.entity.run;

import com.hysea.Main;
import com.hysea.entity.run.list.MainBodyList;
import com.hysea.entity.run.tree.MainBodyTreeNode;
import com.hysea.util.RandomUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@XStreamAlias("step")
public class Step extends ProcessNode {
    private String step;

    @Override
    public void next() throws Exception {
        Class<? extends MainBodyTreeNode> clazz = getParent().getClass();
        if(clazz.isAssignableFrom(Steps.class)){

            List<String> awaitSelectList = getAllStep().stream().map(Step::getStep).collect(Collectors.toList());

            int stepIndex = 0;
            for (int i = 0; i < awaitSelectList.size(); i++) {
                if (awaitSelectList.get(i).equals(step)) {
                    stepIndex = i;
                    break;
                }
            }

            String[] tempSteps = {"", "", "", "", "", "", "", "", "", ""};
            int realStepIndex = (int) (Math.random() * 10);

            List<Integer> excludeIndex = new ArrayList<>();
            excludeIndex.add(stepIndex);
            List<Integer> randomIndexAndExclude = RandomUtils.randomIndexAndExclude(awaitSelectList.size(), excludeIndex, 9);
            randomIndexAndExclude.add(realStepIndex, stepIndex);
            for (int i = 0; i < randomIndexAndExclude.size(); i++) {
                tempSteps[i] = awaitSelectList.get(randomIndexAndExclude.get(i));
            }

            for (int i = 0; i < tempSteps.length; i++) {
                System.out.println(i + ":" + tempSteps[i]);
            }

            int i = new Scanner(System.in).nextInt();
            if (!tempSteps[realStepIndex].equals(tempSteps[i])) {
                System.out.println("应该是："+tempSteps[realStepIndex]);
                Main.setIsExit(true);
                throw new Exception();
            }

            afterNext();
        }else if(clazz.isAssignableFrom(Disorder.class)){

            List<String> awaitSelectList = getAllStep().stream().map(Step::getStep).collect(Collectors.toList());


            List<String> excludeStepList = new ArrayList<>();
            List<Integer> excludeIndex = new ArrayList<>();

            MainBodyList childList = getParent().getChildList();
            for (MainBodyTreeNode mainBodyTreeNode : childList) {
                if(mainBodyTreeNode.getClass().isAssignableFrom(Step.class)){
                    Step temp = (Step) mainBodyTreeNode;
                    excludeStepList.add(temp.getStep());
                }
            }
            int stepIndex = 0;
            for (String excludeStep : excludeStepList) {
                for (int i = 0; i < awaitSelectList.size(); i++) {
                    if (awaitSelectList.get(i).equals(excludeStep)) {
                        excludeIndex.add(i);
                        break;
                    }
                }
            }

            String[] tempSteps = {"", "", "", "", "", "", "", "", "", ""};
            List<Integer> realStepIndexList = new ArrayList<>();
            for(int i = 0;i < excludeIndex.size();i++){
                int realStepIndex = (int) (Math.random() * 10);
                if(realStepIndexList.contains(realStepIndex)){
                    i--;
                }else{
                    realStepIndexList.add(realStepIndex);
                }
            }
            realStepIndexList = realStepIndexList.stream().sorted().collect(Collectors.toList());

            List<Integer> randomIndexAndExclude = RandomUtils.randomIndexAndExclude(awaitSelectList.size(), excludeIndex, 10 - realStepIndexList.size());
            for (int i = 0; i < realStepIndexList.size(); i++) {
                randomIndexAndExclude.add(realStepIndexList.get(i), excludeIndex.get(i));
            }
            for (int i = 0; i < randomIndexAndExclude.size(); i++) {
                tempSteps[i] = awaitSelectList.get(randomIndexAndExclude.get(i));
            }

            for (int i = 0; i < tempSteps.length; i++) {
                System.out.println(i + ":" + tempSteps[i]);
            }

            int c = new Scanner(System.in).nextInt();
            int i;
            for (i = 0; i < realStepIndexList.size(); i++) {
                if (tempSteps[realStepIndexList.get(i)].equals(tempSteps[c])) {
                    break;
                }
            }

            if(i == realStepIndexList.size()){
                Main.setIsExit(true);
                throw new Exception();
            }

            afterNext();
        }
    }
}