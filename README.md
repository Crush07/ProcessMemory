# ProcessMemory
背流程，记科目三路线，做菜工序，主持活动的流程

## 存在的主体
### Processes 
流程集，可包含多个流程

### Process
流程，每个流程都有唯一的流程名

### ProcessNode 
流程结点，流程中的结点

### ProcessStep 
流程映射步骤，通过process（映射名）来映射对应的流程

### DisOrder
无顺序步骤集，步骤没有特定顺序，DisOrder不可以直接嵌套Conditions，Conditions必须嵌套在有顺序的集合下

### Steps
顺序步骤集，步骤有特定顺序，必须按照顺序执行

### Step
步骤，流程中的最小单位，步骤名可以自定义

### Conditions
情况集，包含不同的情况

### Condition
情况，每个情况对应的一些步骤

## 属于集合的主体
Processes，Process，DisOrder，Steps，Conditions

## 属于非集合的主体
ProcessNode，ProcessStep，Step，Condition

## 无序的主体
DisOrder

## 有序的主体
Processes，Process，Steps，Conditions，ProcessNode，ProcessStep，Step，Condition

## 流程文件存放位置
resources/process

## 功能
- [X] 将流程xml文件通过xStream，重写转换器转换成java对象，这个java对象称为流程对象
- [X] 遍历流程对象中的步骤，每个步骤被遍历到之后让用户回答选择题，并设置10秒超时时间，如果超时，则显示不合格，如果选择错误直接抛异常
- [ ] 新建并保存流程到xml文件目录中