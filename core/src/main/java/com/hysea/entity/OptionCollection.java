package com.hysea.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OptionCollection {

    private List<String> awaitSelectList;

    private List<Integer> realIndexList;

    public OptionCollection() {

        awaitSelectList = new ArrayList<>();

        realIndexList = new ArrayList<>();

    }
}
