// Generated from lab6.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link lab6Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface lab6Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link lab6Parser#compUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompUnit(lab6Parser.CompUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(lab6Parser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#funcType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncType(lab6Parser.FuncTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(lab6Parser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#blockItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItem(lab6Parser.BlockItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(lab6Parser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#lVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLVal(lab6Parser.LValContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(lab6Parser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#constDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDecl(lab6Parser.ConstDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#bType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBType(lab6Parser.BTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#constDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstDef(lab6Parser.ConstDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#constInitVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstInitVal(lab6Parser.ConstInitValContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#constExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstExp(lab6Parser.ConstExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(lab6Parser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(lab6Parser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#initVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitVal(lab6Parser.InitValContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(lab6Parser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(lab6Parser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#lOrExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLOrExp(lab6Parser.LOrExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#lAndExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLAndExp(lab6Parser.LAndExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#eqExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExp(lab6Parser.EqExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#eqNeq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqNeq(lab6Parser.EqNeqContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#relExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExp(lab6Parser.RelExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare(lab6Parser.CompareContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#addExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExp(lab6Parser.AddExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#addSub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(lab6Parser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#mulExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExp(lab6Parser.MulExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#mulDiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(lab6Parser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#unaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExp(lab6Parser.UnaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#funcRParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncRParams(lab6Parser.FuncRParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExp(lab6Parser.PrimaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#unaryOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(lab6Parser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(lab6Parser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(lab6Parser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#if_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_(lab6Parser.If_Context ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#while_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_(lab6Parser.While_Context ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#break_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak_(lab6Parser.Break_Context ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#continue_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinue_(lab6Parser.Continue_Context ctx);
	/**
	 * Visit a parse tree produced by {@link lab6Parser#return_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_(lab6Parser.Return_Context ctx);
}