--# -path=.:/home/mbergeron/.local/gf-rgl/present

concrete FoodFre of Food =
    open SyntaxFre,ParadigmsFre in {

    lincat

        Comment = Utt ;
        Item = NP ;
        Kind = CN ;
        Quality = AP ;

    lin

        Pred item quality = mkUtt (mkCl item quality) ;

        This kind = mkNP this_Det kind ;
        That kind = mkNP that_Det kind ;
        These kind = mkNP these_Det kind ;
        Those kind = mkNP those_Det kind ;

        Mod quality kind = mkCN quality kind ;

        Wine = mkCN (mkN "vin") ;
        Pizza = mkCN (mkN "pizza" "pizzas" feminine) ;
        Cheese = mkCN (mkN "fromage") ;
        Fish = mkCN (mkN "poisson") ;

        Very quality = mkAP very_AdA quality ;

        Fresh = mkAP (mkA "frais") ;
        Warm = mkAP (mkA "tiède") ;
        Italian = mkAP (mkA "italien") ;
        Expensive = mkAP (mkA "dispendieux") ;
        Delicious = mkAP (mkA "délicieux") ;
        Boring = mkAP (mkA "ennuyant") ;

}
