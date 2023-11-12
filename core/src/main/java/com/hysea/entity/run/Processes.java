package com.hysea.entity.run;

import com.hysea.converter.ProcessesConverter;
import com.hysea.entity.run.tree.MainBodyTreeNode;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@XStreamAlias("processes")
@XStreamConverter(value = ProcessesConverter.class)
public class Processes extends MainBodyTreeNode{

    private List<Process> processes;

}
