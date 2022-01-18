package org.sec.payload;

public class Evil {
    public Evil(){
        try {
            Runtime.getRuntime().exec("");
        }catch (Exception ignored){}
    }
}
