instance LexFoodFre of LexFood =
   open SyntaxFre, ParadigmsFre in {

    oper

        wine_N = mkN "vin" ;
        pizza_N = mkN "pizza" feminine;
        cheese_N  = mkN "fromage" ;
        fish_N  = mkN "poisson" ;

        fresh_A  = mkA "frais" "fraîche" ;
        warm_A  = mkA "tiède" ;
        italian_A  = mkA "italien" ;
        expensive_A  = mkA "dispendieux" ;
        delicious_A = mkA "délicieux" ;
        boring_A  = mkA "ennuyant" ;

   }
