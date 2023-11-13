package com.hysea.entity.run;

import com.hysea.Main;
import com.hysea.util.RandomUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@XStreamAlias("step")
public class Step extends ProcessNode {
    private String step;

    @Override
    public void next() throws Exception {
        List<String> awaitSelectList = Main.getAwaitSelectList();

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
        if (realStepIndex != i) {
            Main.setIsExit(true);
            throw new Exception();
        }

        afterNext();
    }
}