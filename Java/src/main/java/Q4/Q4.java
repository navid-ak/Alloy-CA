package Q4;

import static edu.mit.csail.sdg.alloy4.A4Reporter.NOP;
import static edu.mit.csail.sdg.alloy4compiler.ast.Sig.UNIV;
import java.util.Arrays;
import edu.mit.csail.sdg.alloy4.Err;
import edu.mit.csail.sdg.alloy4.Util;
import edu.mit.csail.sdg.alloy4compiler.ast.Attr;
import edu.mit.csail.sdg.alloy4compiler.ast.Command;
import edu.mit.csail.sdg.alloy4compiler.ast.Decl;
import edu.mit.csail.sdg.alloy4compiler.ast.Expr;
import edu.mit.csail.sdg.alloy4compiler.ast.ExprConstant;
import edu.mit.csail.sdg.alloy4compiler.ast.Func;
import edu.mit.csail.sdg.alloy4compiler.ast.Sig;
import edu.mit.csail.sdg.alloy4compiler.ast.Sig.PrimSig;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Options;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Solution;
import edu.mit.csail.sdg.alloy4compiler.translator.TranslateAlloyToKodkod;

public class Q4 {

    public static  void main(String[] args) throws Err {

        PrimSig Cell = new PrimSig("Cell");

        PrimSig Inmate = new PrimSig("Inmate");
        Expr room = Inmate.addField("room", Cell);

        PrimSig Gang = new PrimSig("Gang");
        Expr members = Gang.addField("members", Inmate);

        Decl gone = Gang.oneOf("gone");
        Decl gtwo = Gang.oneOf("gtwo");
        Decl x = Inmate.oneOf("x");
        Decl y = Inmate.oneOf("y");

        System.out.println(gone.get().equal(gtwo.get()).not().and(x.get().in(gone.get().join(members)).and(y.get().in(gtwo.get().join(members))).and(x.get().join(room).equal(y.get().join(room)))).forSome(gone,gtwo).forNo(x,y));

        Func safe = new Func(null, "safe", null, null,
                gone.get().equal(gtwo.get()).not().and(x.get().in(gone.get().join(members)).and(y.get().in(gtwo.get().join(members))).and(x.get().join(room).equal(y.get().join(room)))).forSome(gone,gtwo).forNo(x,y)
        );

        Command run = new Command(false, 5, 3, 3,
                safe.call()
        );

        A4Options opt = new A4Options();
        opt.solver = A4Options.SatSolver.SAT4J;

        A4Solution sol = TranslateAlloyToKodkod.execute_command(NOP, Arrays.asList(new Sig[]{Gang,Inmate,Cell}), run, opt);
        System.out.println("[Solution]:");
        System.out.println(sol.toString());

    }
}
