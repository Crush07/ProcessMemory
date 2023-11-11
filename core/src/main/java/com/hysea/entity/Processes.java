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
@XStreamConverter(value = ProcessesNodeConverter.class)
public class Processes {

    private List<Process> processes;

}
