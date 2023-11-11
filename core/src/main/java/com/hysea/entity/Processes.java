package com.hysea.entity;

import com.hysea.converter.ProcessesConverter;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@XStreamConverter(value = ProcessesConverter.class)
public class Processes {

    private List<Process> processes;

}
