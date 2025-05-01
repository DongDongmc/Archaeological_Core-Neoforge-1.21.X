package com.ddmc.archaeological_core.test;

import com.ddmc.archaeological_core.api.BaseBrushItem;

public class TestBrushItem extends BaseBrushItem {

    public TestBrushItem(Properties properties, int brushLevel) {
        super(properties, brushLevel);
    }
    @Override
    public int additionalLevel(){
        return 2;
    }
}
