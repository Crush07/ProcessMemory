package com.hysea.entity;

import com.hysea.Main;
import com.hysea.converter.ProcessesNodeConverter;
import com.hysea.util.RandomUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
@Data
@XStreamAlias("processes")
@XStreamConverter(value = ProcessesNodeConverter.class)
public class Processes {

    @XStreamImplicit(itemFieldName = "process")
    private List<Process> processes;

    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @Data
    @XStreamAlias("steps")
    public static class Steps extends ProcessNode {

        @XStreamImplicit(itemFieldName = "step")
        private List<ProcessNode> steps;

        @EqualsAndHashCode(callSuper = true)
        @NoArgsConstructor
        @Data
        @XStreamAlias("step")
        @XStreamConverter(value = ToAttributedValueConverter.class,strings = {"step"})
        public static class Step extends ProcessNode {
            private String step;

            @Override
            public void next() throws Exception {
                List<String> awaitSelectList = Main.getAwaitSelectList();

                int stepIndex = 0;
                for (int i = 0; i < awaitSelectList.size(); i++) {
                    if(awaitSelectList.get(i).equals(step)){
                        stepIndex = i;
                    }
                }

                String[] tempSteps = {"","","","","","","","","",""};
                int realStepIndex = (int) (Math.random() * 10);

                List<Integer> excludeIndex = new ArrayList<>();
                excludeIndex.add(stepIndex);
                List<Integer> randomIndexAndExclude = RandomUtils.randomIndexAndExclude(awaitSelectList.size(), excludeIndex, 9);
                randomIndexAndExclude.add(realStepIndex,stepIndex);
                for (int i = 0; i < randomIndexAndExclude.size(); i++) {
                    tempSteps[i] = awaitSelectList.get(randomIndexAndExclude.get(i));
                }

                for (int i = 0; i < tempSteps.length; i++) {
                    System.out.println(i + ":" + tempSteps[i]);
                }

                int i = new Scanner(System.in).nextInt();
                if(realStepIndex != i){
                    Main.setIsExit(true);
                    throw new Exception();
                }

                afterNext();
            }
        }

        @Override
        public void next() throws Exception {
            for (ProcessNode step : getSteps()) {
                step.next();
            }
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @Data
    @XStreamAlias("process-step")
    public static class ProcessStep extends Process {
        /**
         * -process : crossTheRoad
         */

        @XStreamAlias("process")
        @XStreamAsAttribute
        private String mappingProcessId;
    }

    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @Data
    @XStreamAlias("conditions")
    public static class Conditions extends ProcessNode {

        @XStreamImplicit(itemFieldName = "condition")
        private List<Condition> conditions;

        @EqualsAndHashCode(callSuper = true)
        @NoArgsConstructor
        @Data
        @XStreamAlias("condition")
        public static class Condition extends Process {
            /**
             * condition-name : 绿灯闪时已经在斑马线上
             * steps : {"step":"点刹"}
             */

            @XStreamAlias("name")
            @XStreamAsAttribute
            private String conditionName;

            @Override
            public void next() throws Exception {
                for (ProcessNode processNode : getProcessNodeList()) {
                    processNode.next();
                }
            }
        }

        @Override
        public void next() throws Exception {
            int i = (int) (Math.random() * getConditions().size());
            getConditions().get(i).next();
        }

        
    }

    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @Data
    @XStreamAlias("disorder")
    public static class Disorder extends Steps {

        @Override
        public void next() throws Exception {
            for (ProcessNode step : getSteps()) {

            }
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @XStreamAlias("process")
    @Data
    public static class Process extends Steps {

        /**
         * -id : crossTheRoad
         * conditions : {"condition":[{"condition-name":"绿灯闪时已经在斑马线上","steps":{"step":"点刹"}},{"condition-name":"红灯","steps":{"step":["停车","挂一档","绿灯","起步","挂二挡"]}},{"condition-name":"绿灯","steps":{"step":["点刹过","挂一档","绿灯","起步","挂二挡"]}}]}
         * steps : {"disorder":{"step":["调整座位","检查档位","检查灯光"]},"step":["说准备好了","下车","按按钮","上车","关门","系安全带","打火","做灯光考试","听语音报考试路线","左转向灯","离合带刹车","挂一档","松离合","数3秒","关左转向灯","听语音打右转向灯","打右转向灯","到起始线前停","起步往最右侧路线行驶","挂二挡","在快到三角形的地方停","停车挂一档，打右转向灯","起步","在三角形的地方停","调整方向盘过","过了弯道再停一次，打左转向灯","起步变道最左侧道路","挂二挡","公交车站点刹","播报保持直线行驶","播报保持直线行驶完成","踩油门，速度提到25左右，踩离合，挂三档，松离合","踩油门，速度提到35左右，踩离合，挂四档，松离合","稳速度，数8秒","刹车，速度降到25左右，踩离合，挂二挡，慢慢松离合","红绿灯，人行横道，在没有过斑马线的时候减速","播报会车，点刹","播报前方掉头，打左转向灯","下水道与肩部平行，方向盘打死","学校区域，点刹","红绿灯，人行横道，在没有过斑马线的时候减速","播报超车，打左转向灯，数3秒，变道，方向摆正，打右转向灯，数3秒，变道","播报变道，打左转向灯，数3秒，变道","播报左转，打左转向灯","红绿灯，人行横道，在没有过斑马线的时候减速","看不到斑马线时方向盘打60度，走最右侧车道","在第一条虚线前停车，打右转向灯","靠边停车","回空挡，拉手刹，熄火","开门数3秒再关上，解安全带"],"process-step":[{"-process":"crossTheRoad"},{"-process":"crossTheRoad"},{"-process":"crossTheRoad"}]}
         */
        @XStreamAlias("id")
        @XStreamAsAttribute
        private String processId;

        @XStreamImplicit
        private List<ProcessNode> processNodeList;

        @Override
        public void next() throws Exception {
            for (ProcessNode processNode : getProcessNodeList()) {
                processNode.next();
            }
        }

    }
}
