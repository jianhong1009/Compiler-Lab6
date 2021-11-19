import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Stack;

public class Visitor extends lab6BaseVisitor<Void> {
    public PrintStream ps = new PrintStream(new FileOutputStream(Test.outputPath));
    public static String exp = "";
    public static ArrayList<Variable> variableList = new ArrayList<>();
    public static int num = 0;
    public static int ifNum = 0;
    public static int whileNum = 0;
    public static boolean endFlag = false;
    public static boolean funcFlag = false;
    public static boolean globalVarFlag = false;
    public static Stack<Integer> blockNumStack = new Stack<>();
    public static Stack<Integer> whileNumStack = new Stack<>();
    public static int blockNum = -1;

    public Visitor() throws FileNotFoundException {
        System.setOut(ps);
    }

    @Override
    public Void visitCompUnit(lab6Parser.CompUnitContext ctx) {
        blockNumStack.push(++blockNum);
        for (lab6Parser.DeclContext e : ctx.decl()) {
            globalVarFlag = true;
            visit(e);
            globalVarFlag = false;
        }
        visit(ctx.funcDef(0));
        return null;
    }

    @Override
    public Void visitFuncDef(lab6Parser.FuncDefContext ctx) {
        System.out.println("declare i32 @getint()");
        System.out.println("declare void @putint(i32)");
        System.out.println("declare i32 @getch()");
        System.out.println("declare void @putch(i32)");
        System.out.println("define dso_local i32 @main() {");
        visit(ctx.block());
        System.out.println("}");
        blockNumStack.pop();
        return null;
    }

    @Override
    public Void visitFuncType(lab6Parser.FuncTypeContext ctx) {
        return super.visitFuncType(ctx);
    }

    @Override
    public Void visitBlock(lab6Parser.BlockContext ctx) {
        blockNumStack.push(++blockNum);
        for (lab6Parser.BlockItemContext e : ctx.blockItem()) {
            visit(e);
        }
        blockNumStack.pop();
        return null;
    }

    @Override
    public Void visitBlockItem(lab6Parser.BlockItemContext ctx) {
        return super.visitBlockItem(ctx);
    }

    @Override
    public Void visitStmt(lab6Parser.StmtContext ctx) {
        ifNum++;
        if (ctx.lVal() != null) {
            if (Variable.isConst(ctx.lVal().getText())) {
                System.exit(1);
            }
            String var = ctx.lVal().getText();
            exp = "";
            visit(ctx.exp());
            String s = "";
            if (!funcFlag) {
                s = new PostfixExpression().func(exp);
            } else {
                s = "%" + num;
                funcFlag = false;
            }
            System.out.println("    store i32 " + s + ", i32* " + Variable.getStore(var));
        } else if (ctx.return_() != null) {
            exp = "";
            visit(ctx.exp());
            String s = "";
            if (!funcFlag) {
                s = new PostfixExpression().func(exp);
            } else {
                s = "%" + num;
                funcFlag = false;
            }
            System.out.println("    ret i32 " + s);
            endFlag = true;
        } else if (ctx.block() != null) {
            visit(ctx.block());
        } else if (ctx.if_() != null) {

            exp = "";
            visit(ctx.cond());
            String s = new PostfixExpression2().func(exp);

            System.out.println("    br i1 " + s + ",label %true" + ifNum + ", label %false" + ifNum);

            System.out.println("true" + ifNum + ":");
            int tempIfNum = ifNum;
            visit(ctx.stmt(0));
            ifNum = tempIfNum;
            if (endFlag) {
                endFlag = false;
            } else {
                System.out.println("    br label %end" + ifNum);
            }

            System.out.println("false" + ifNum + ":");
            tempIfNum = ifNum;
            if (ctx.stmt(1) != null) {
                visit(ctx.stmt(1));
            }
            ifNum = tempIfNum;
            if (endFlag) {
                endFlag = false;
            } else {
                System.out.println("    br label %end" + ifNum);
            }

            System.out.println("end" + ifNum + ":");
        } else if (ctx.while_() != null) {
            whileNum++;
            whileNumStack.push(whileNum);
            System.out.println("    br label %start_" + whileNum);

            exp = "";
            visit(ctx.cond());
            System.out.println("start_" + whileNum + ":");
            String s = new PostfixExpression2().func(exp);

            System.out.println("    br i1 " + s + ",label %true_" + whileNum + ", label %false_" + whileNum);

            System.out.println("true_" + whileNum + ":");
            int tempWhileNum = whileNum;
            visit(ctx.stmt(0));
            whileNum = tempWhileNum;
            if (endFlag) {
                endFlag = false;
            } else {
                System.out.println("    br label %start_" + whileNum);
            }

            System.out.println("false_" + whileNum + ":");
            whileNumStack.pop();
        } else if (ctx.break_() != null) {
            if (!endFlag) {
                System.out.println("    br label %false_" + whileNumStack.peek());
                endFlag = true;
            }
        } else if (ctx.continue_() != null) {
            if (!endFlag) {
                System.out.println("    br label %start_" + whileNumStack.peek());
                endFlag = true;
            }
        } else {
            exp = "";
            if (ctx.exp() != null) {
                visit(ctx.exp());
                String s = "";
                if (!funcFlag) {
                    s = new PostfixExpression().func(exp);
                } else {
                    s = "%" + num;
                    funcFlag = false;
                }
            }
        }
        return null;
    }

    @Override
    public Void visitIf_(lab6Parser.If_Context ctx) {
        return super.visitIf_(ctx);
    }

    @Override
    public Void visitWhile_(lab6Parser.While_Context ctx) {
        return super.visitWhile_(ctx);
    }

    @Override
    public Void visitBreak_(lab6Parser.Break_Context ctx) {
        return super.visitBreak_(ctx);
    }

    @Override
    public Void visitContinue_(lab6Parser.Continue_Context ctx) {
        return super.visitContinue_(ctx);
    }

    @Override
    public Void visitReturn_(lab6Parser.Return_Context ctx) {
        return super.visitReturn_(ctx);
    }

    @Override
    public Void visitLVal(lab6Parser.LValContext ctx) {
        return super.visitLVal(ctx);
    }

    @Override
    public Void visitDecl(lab6Parser.DeclContext ctx) {
        return super.visitDecl(ctx);
    }

    @Override
    public Void visitConstDecl(lab6Parser.ConstDeclContext ctx) {
        return super.visitConstDecl(ctx);
    }

    @Override
    public Void visitBType(lab6Parser.BTypeContext ctx) {
        return super.visitBType(ctx);
    }

    @Override
    public Void visitConstDef(lab6Parser.ConstDefContext ctx) {
        Variable.checkRepeat(ctx.ident().getText());
        String var = ctx.ident().getText();
        int temp = 0;
        if (!globalVarFlag) {
            System.out.println("    %" + (num + 1) + " = alloca i32");
            temp = ++num;
        }
        exp = "";
        visit(ctx.constInitVal());
        String s = "";
        if (!funcFlag) {
            char[] str1 = exp.toCharArray();
            for (int i = 0; i < str1.length; i++) {
                String string = "";
                boolean flag = false;
                if (Character.isLetter(str1[i]) || str1[i] == '_') {
                    string += str1[i];
                    i++;
                    flag = true;
                    for (; i < str1.length && (Character.isDigit(str1[i]) || Character.isLetter(str1[i]) || str1[i] == '_'); i++) {
                        string += str1[i];
                    }
                }
                if (flag) {
                    Variable.checkExist(string);
                    if (!Variable.isConst(string)) {
                        System.exit(1);
                    }
                    i--;
                }
            }
            s = new PostfixExpression().func(exp);
        } else {
            s = "%" + num;
            funcFlag = false;
        }
        if (!globalVarFlag) {
            System.out.println("    store i32 " + s + ", i32* %" + temp);
            variableList.add(new Variable(var, "%" + temp, "null", 0, blockNumStack.peek(), 2, true));
        } else {
            System.out.println("@" + var + " = dso_local global i32 " + s);
            variableList.add(new Variable(var, "@" + var, "null", Integer.parseInt(s), blockNumStack.peek(), 2, true));
        }
        return null;
    }

    @Override
    public Void visitConstInitVal(lab6Parser.ConstInitValContext ctx) {
        return super.visitConstInitVal(ctx);
    }

    @Override
    public Void visitConstExp(lab6Parser.ConstExpContext ctx) {
        return super.visitConstExp(ctx);
    }

    @Override
    public Void visitVarDecl(lab6Parser.VarDeclContext ctx) {
        return super.visitVarDecl(ctx);
    }

    @Override
    public Void visitVarDef(lab6Parser.VarDefContext ctx) {
        Variable.checkRepeat(ctx.ident().getText());
        if (ctx.initVal() == null) {
            String var = ctx.ident().getText();
            int temp = 0;
            if (!globalVarFlag) {
                System.out.println("    %" + (num + 1) + " = alloca i32");
                temp = ++num;
                variableList.add(new Variable(var, "%" + temp, "null", 0, blockNumStack.peek(), 1, false));
            } else {
                System.out.println("@" + var + " = dso_local global i32 0");
                variableList.add(new Variable(var, "@" + var, "null", 0, blockNumStack.peek(), 1, false));
            }
        } else {
            String var = ctx.ident().getText();
            int temp = 0;
            if (!globalVarFlag) {
                System.out.println("    %" + (num + 1) + " = alloca i32");
                temp = ++num;
            }
            exp = "";
            visit(ctx.initVal());
            String s = "";
            if (!funcFlag) {
                s = new PostfixExpression().func(exp);
            } else {
                s = "%" + num;
                funcFlag = false;
            }
            if (!globalVarFlag) {
                System.out.println("    store i32 " + s + ", i32* %" + temp);
                variableList.add(new Variable(var, "%" + temp, "null", 0, blockNumStack.peek(), 2, false));
            } else {
                System.out.println("@" + var + " = dso_local global i32 " + s);
                variableList.add(new Variable(var, "@" + var, "null", 0, blockNumStack.peek(), 2, false));
            }
        }
        return null;
    }

    @Override
    public Void visitInitVal(lab6Parser.InitValContext ctx) {
        return super.visitInitVal(ctx);
    }

    @Override
    public Void visitCond(lab6Parser.CondContext ctx) {
        return super.visitCond(ctx);
    }

    @Override
    public Void visitExp(lab6Parser.ExpContext ctx) {
        return super.visitExp(ctx);
    }

    @Override
    public Void visitLOrExp(lab6Parser.LOrExpContext ctx) {
        if (ctx.lOrExp() != null) {
            visit(ctx.lOrExp());
            exp += "|";
            visit(ctx.lAndExp());
        } else {
            visit(ctx.lAndExp());
        }
        return null;
    }

    @Override
    public Void visitLAndExp(lab6Parser.LAndExpContext ctx) {
        if (ctx.lAndExp() != null) {
            visit(ctx.lAndExp());
            exp += "&";
            visit(ctx.eqExp());
        } else {
            visit(ctx.eqExp());
        }
        return null;
    }

    @Override
    public Void visitEqExp(lab6Parser.EqExpContext ctx) {
        return super.visitEqExp(ctx);
    }

    @Override
    public Void visitEqNeq(lab6Parser.EqNeqContext ctx) {
        if (ctx.getText().equals("==")) {
            exp += "="; // ==
        } else {
            exp += "~"; // !=
        }
        return null;
    }

    @Override
    public Void visitRelExp(lab6Parser.RelExpContext ctx) {
        return super.visitRelExp(ctx);
    }

    @Override
    public Void visitCompare(lab6Parser.CompareContext ctx) {
        if (ctx.getText().equals("<=")) {
            exp += "《"; // <=
        } else if (ctx.getText().equals(">=")) {
            exp += "》"; // >=
        } else {
            exp += ctx.getText(); // < >
        }
        return null;
    }

    @Override
    public Void visitAddExp(lab6Parser.AddExpContext ctx) {
        return super.visitAddExp(ctx);
    }

    @Override
    public Void visitAddSub(lab6Parser.AddSubContext ctx) {
        exp += ctx.getText();
        return null;
    }

    @Override
    public Void visitMulExp(lab6Parser.MulExpContext ctx) {
        return super.visitMulExp(ctx);
    }

    @Override
    public Void visitMulDiv(lab6Parser.MulDivContext ctx) {
        exp += ctx.getText();
        return null;
    }

    @Override
    public Void visitUnaryExp(lab6Parser.UnaryExpContext ctx) {
        if (ctx.primaryExp() != null) {
            visit(ctx.primaryExp());
        } else if (ctx.ident() != null) {
            String func = ctx.ident().getText();
            if (func.equals("getint") || func.equals("getch") || func.equals("putint") || func.equals("putch")) {
                if (func.equals("getint") && ctx.funcRParams() == null) {
//                    System.out.println("    %" + (num + 1) + " = call i32 @getint()");
//                    num++;
//                    funcFlag = true;
                    exp += "@getint";
                } else if (func.equals("putint") && ctx.funcRParams() != null) {
                    exp = "";
                    visit(ctx.funcRParams());
                    String s = new PostfixExpression().func(exp);
                    System.out.println("    call void @putint(i32 " + s + ")");
                    funcFlag = true;
                } else if (func.equals("getch") && ctx.funcRParams() == null) {
//                    System.out.println("    %" + (num + 1) + " = call i32 @getch()");
//                    num++;
//                    funcFlag = true;
                    exp += "@getch";
                } else if (func.equals("putch") && ctx.funcRParams() != null) {
                    exp = "";
                    visit(ctx.funcRParams());
                    String s = new PostfixExpression().func(exp);
                    System.out.println("    call void @putch(i32 " + s + ")");
                    funcFlag = true;
                } else {
                    System.exit(1);
                }
            } else {
                System.exit(1);
            }
        } else {
            visit(ctx.unaryOp());
            visit(ctx.unaryExp());
        }
        return null;
    }

    @Override
    public Void visitFuncRParams(lab6Parser.FuncRParamsContext ctx) {
        return super.visitFuncRParams(ctx);
    }

    @Override
    public Void visitPrimaryExp(lab6Parser.PrimaryExpContext ctx) {
        if (ctx.exp() != null) {
            exp += "(";
            visit(ctx.exp());
            exp += ")";
        } else if (ctx.lVal() != null) {
            exp += ctx.lVal().getText();
        } else {
            visit(ctx.number());
        }
        return null;
    }

    @Override
    public Void visitUnaryOp(lab6Parser.UnaryOpContext ctx) {
        exp += ctx.getText();
        return null;
    }

    @Override
    public Void visitIdent(lab6Parser.IdentContext ctx) {
        return super.visitIdent(ctx);
    }

    @Override
    public Void visitNumber(lab6Parser.NumberContext ctx) {
        if (ctx.DecimalConst() != null) {
            exp += ctx.DecimalConst().getText();
        } else if (ctx.OctalConst() != null) {
            if (ctx.OctalConst().getText().equals("0")) {
                exp += "0";
            } else {
                String s = ctx.OctalConst().getText().substring(1);
                exp += String.valueOf(Integer.parseInt(s, 8));
            }
        } else {
            String s = ctx.HexadecimalConst().getText().substring(2);
            exp += String.valueOf(Integer.parseInt(s, 16));
        }
        return null;
    }

    public int testing() {

        return 1;
    }
}
