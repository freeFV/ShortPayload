package org.sec.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ShortMethodAdapter extends MethodVisitor implements Opcodes {

    private final String methodName;

    public ShortMethodAdapter(int api, MethodVisitor mv, String methodName) {
        super(api,mv);
        this.methodName = methodName;
    }

    @Override
    public void visitCode() {
//        if(!methodName.equals("<init>")){
//            super.visitCode();
//        }
        super.visitCode();
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        // delete line number
    }
}
